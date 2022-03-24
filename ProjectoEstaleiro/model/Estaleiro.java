package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Estaleiro implements Serializable{
	
	public Estaleiro() {}
	
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
	public static void pegarTime(JTextField campo) {
		
        Date data = Calendar.getInstance().getTime();
        DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat h = DateFormat.getTimeInstance();
        campo.setText(h.format(data)+" - "+d.format(data));   
	               
	}
}
