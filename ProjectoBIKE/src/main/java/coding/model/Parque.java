package coding.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

@SuppressWarnings("serial")
public class Parque implements Serializable{
	
	public Parque() {}
	
	@SuppressWarnings({ "resource", "rawtypes" })
	public void escreverFicheiro(Vector v, String path) throws IOException {
		FileOutputStream arq = new FileOutputStream(path);
		ObjectOutputStream obj = new ObjectOutputStream(arq);
		obj.writeObject(v);
		obj.flush();
	}

	@SuppressWarnings({ "resource", "rawtypes" })
	public Vector lerFicheiro(String path) {
		Vector v = null;
		try {
			FileInputStream arq = new FileInputStream(path);
			ObjectInputStream obj = new ObjectInputStream(arq);
			v = (Vector)obj.readObject();
		
		}catch(Exception e) {
			
		}
		
		return v;
	}
	
	//PEGAR DATA E HORA EM TEMPO REAL
	public String pegarTime() {
		
        Date data = Calendar.getInstance().getTime();
        return data+"";   
	               
	}
	
//	public static void main(String args[]) {
//		Parque p = new Parque();
//		System.out.println(p.pegarTime());
//	}
}
