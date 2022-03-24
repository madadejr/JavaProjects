package coding.controller;

import java.io.IOException;
import java.util.Vector;
import coding.model.Parque;
import coding.model.Utilizador;

public class Utilizador_C {

	public Vector<Utilizador> users = new Vector<Utilizador>();
	public Utilizador usr; 
	
	Parque parque = new Parque();
	
	public Utilizador_C() {}
	
	//COMPORTAMENTOS
	//VERIFICACAO DA EXISTENCIA DO USUARIOS
	public boolean existe(String username) {
		recuperarUsers();
		Utilizador usr;
		for(int i = 0; i < users.size(); i++) {
			usr = (Utilizador)users.get(i);
			if(usr.getUname().equals(username)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean existeID(int id) {
		recuperarUsers();
		Utilizador usr;
		for(int i = 0; i < users.size(); i++) {
			usr = (Utilizador)users.get(i);
			if(usr.getId_user() == id) {
				return true;
			}
		}
		
		return false;
	}
	
	//REGISTAR USUARIO
	public void registar(int id_user, String name_user, String email, String tipo_user, String username, String psw){		
		
		try {
			Utilizador usr = new Utilizador(id_user,name_user,email,tipo_user,username,psw);
			users.add(usr);
			parque.escreverFicheiro(users, "C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//usuarios.arq");
			
			System.out.println("USER: "+username+"\nPASSWORD: "+psw);
	
		}catch(Exception e) {
			
		}
	}
	
	//RECUPERAR USUARIOS
	@SuppressWarnings("unchecked")
	public void recuperarUsers() {
		try {
			users.addAll(parque.lerFicheiro("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//usuarios.arq"));
		}catch(Exception e) {
			System.out.println("Nenhum utilizador no sistema!");
		}
	}
	
	//REMOVER USUARIO
	public void remover(int id) throws IOException {
		
		for(int i = 0; i < users.size(); i++) {
			Utilizador u = (Utilizador)users.get(i);
			if(u.getId_user() == id) {
				users.removeElementAt(i);
			}
		}
		parque.escreverFicheiro(users, "C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//usuarios.arq");
		
	}
	
	//ACTUALIZAR USUARIO
	public void actualizar(int id_user, String name_user, String email, String tipo_user, String username, String psw) {
		
		try {
			for(int i = 0; i < users.size(); i++) {
				Utilizador usr = (Utilizador)users.get(i);
				if(usr.getId_user() == id_user) {
					if(name_user != null) {
						usr.setName_user(name_user);
					}
					if(email != null) {
						usr.setEmail(email);
					}
					if(tipo_user != null) {
						usr.setType_user(tipo_user);
					}
					if(username != null) {
						usr.setUname(username);
					}
					if(psw != null) {
						usr.setPsw(psw);
					}
					users.setElementAt(usr, i);
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			parque.escreverFicheiro(users, "C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//usuarios.arq");
		} catch (IOException e) {
		}
	}
		
	//PEGAR ULTIMO ID
	public int pegarUltimo() {
		
		if(users.size() > 0) {
			Utilizador arm = (Utilizador)users.get(users.size()-1);
			return arm.getId_user();
		}
		return 1000;
	}
}
