package coding.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import coding.model.Ciclista;
import coding.model.Utilizador;

public class Login {
	
	Utilizador_C u = new Utilizador_C();
	Ciclista_C c = new Ciclista_C();
	Vector<Utilizador> usuarios = u.users;
	Vector<Ciclista> ciclistas = c.ciclis;
	
	//VALIDACAO DE USUARIOS
	public boolean validacao(String campo_user, String campo_pswd) throws IOException {
		u.recuperarUsers();
		
		for(int i = 0; i < usuarios.size(); i++) {
			Utilizador usr = (Utilizador)usuarios.get(i);
			if(usr.getUname().equals(campo_user) && usr.getPsw().equals(campo_pswd)) {
				escr("userLogado.txt",usr.getId_user()+"");
				isAdmin(campo_user,campo_pswd);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean validacaoC(String campo_user, String campo_pswd) {
		c.recuperarCiclistas();
		Ciclista cic;
		for(int i = 0; i < ciclistas.size(); i++) {
			cic = (Ciclista)ciclistas.get(i);
			if(cic.getUname().equals(campo_user) && cic.getPsw().equals(campo_pswd)) {
				escr("userLogado.txt",cic.getId_ciclista()+"");
				writeAdmin("ciclista");
				return true;
			}
		}
		return false;
	} 
//	
	public void isAdmin(String username, String psw) {
		Utilizador usr;
		for(int i = 0; i < usuarios.size(); i++) {
			usr = (Utilizador)usuarios.get(i);
			if(usr.getUname().equals(username) && usr.getType_user().equals("Administrador") && usr.getPsw().equalsIgnoreCase(psw)) {
				writeAdmin("true");
				break;
			}
			else if(usr.getUname().equals(username) && usr.getType_user().equals("Geral") && usr.getPsw().equalsIgnoreCase(psw)) { 
				writeAdmin("false"); 
				break;
			}
		}
	}
//	
	public void writeAdmin(String adm) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//admin.txt"));
			bw.append(adm);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	
	public String readAdmin() {
		String adm = "false";
		try {
			BufferedReader br = new BufferedReader(new FileReader("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//admin.txt"));
			try {
				String line = br.readLine();
				if(line.equals("true")) {
					adm = "true";
				}else if(line.equals("false")) {
					adm = "false";
				}else {
					adm = "empty";
				}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return adm;
	}
//	
	public void escr(String file,String cod) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//"+file));
			bw.append(cod+"");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	
	public String ler(String file) {
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//"+file));
			try {
				line = br.readLine();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;
	}
	
	public static void main(String args[]) {
		Utilizador_C u = new Utilizador_C();
		u.recuperarUsers();
		u.registar(u.pegarUltimo()+1, "Administrador", "administrador@infobase.co.mz", "Administrador", "administrador", "admin");
		for(Utilizador usr: u.users){
    		System.out.println(usr.getName_user());
		}
	}
}
