package coding.controller;

import java.io.IOException;
import java.util.Vector;

import coding.model.Parque;
import coding.model.Ciclista;

public class Ciclista_C {

	public Vector<Ciclista> ciclis = new Vector<Ciclista>();
	public Ciclista ccl; 
	
	Parque parque = new Parque();
	
	public Ciclista_C() {}
	
	//COMPORTAMENTOS
	//VERIFICACAO DA EXISTENCIA DO CICLISTAS
	public boolean existe(String ciclistaname) {
		recuperarCiclistas();
		Ciclista ccl;
		for(int i = 0; i < ciclis.size(); i++) {
			ccl = (Ciclista)ciclis.get(i);
			if(ccl.getUname().equals(ciclistaname)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean existeID(int id) {
		recuperarCiclistas();
		Ciclista ccl;
		for(int i = 0; i < ciclis.size(); i++) {
			ccl = (Ciclista)ciclis.get(i);
			if(ccl.getId_ciclista() == id) {
				return true;
			}
		}
		
		return false;
	}
	
	//REGISTAR CICLISTA //int id, String nome, String contacto, String morada, String uname, String psw
	public void registar(int id_ciclista, String name_ciclista, String contacto, String morada, String ciclistaname, String psw){		
		
		try {
			Ciclista ccl = new Ciclista(id_ciclista,name_ciclista,contacto,morada,ciclistaname,psw);
			ciclis.add(ccl);
			parque.escreverFicheiro(ciclis, "C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//ciclistas.arq");
			
			System.out.println("USER: "+ciclistaname+"\nPASSWORD: "+psw);
	
		}catch(Exception e) {
			
		}
	}
	
	//RECUPERAR CICLISTAS
	@SuppressWarnings("unchecked")
	public void recuperarCiclistas() {
		try {
			ciclis.addAll(parque.lerFicheiro("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//ciclistas.arq"));
		}catch(Exception e) {
			System.out.println("Nenhum ciclista no sistema!");
		}
	}
	
	//REMOVER CICLISTA
	public void remover(int id) throws IOException {
		
		for(int i = 0; i < ciclis.size(); i++) {
			Ciclista u = (Ciclista)ciclis.get(i);
			if(u.getId_ciclista() == id) {
				ciclis.removeElementAt(i);
			}
		}
		parque.escreverFicheiro(ciclis, "C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//ciclistas.arq");
		
	}
	
	//ACTUALIZAR CICLISTA
	public void actualizar(int id_ciclista, String name_ciclista, String contacto, String morada, String ciclistaname, String psw) {
		
		try {
			for(int i = 0; i < ciclis.size(); i++) {
				Ciclista ccl = (Ciclista)ciclis.get(i);
				if(ccl.getId_ciclista() == id_ciclista) {
					if(name_ciclista != null) {
						ccl.setNome(name_ciclista);
					}
					if(contacto != null) {
						ccl.setContacto(contacto);
					}
					if(morada != null) {
						ccl.setMorada(morada);
					}
					if(ciclistaname != null) {
						ccl.setUname(ciclistaname);
					}
					if(psw != null) {
						ccl.setPsw(psw);
					}
					ciclis.setElementAt(ccl, i);
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			parque.escreverFicheiro(ciclis, "C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//ciclistas.arq");
		} catch (IOException e) {
		}
	}
		
	//PEGAR ULTIMO ID
	public int pegarUltimo() {
		
		if(ciclis.size() > 0) {
			Ciclista arm = (Ciclista)ciclis.get(ciclis.size()-1);
			return arm.getId_ciclista();
		}
		return 1000;
	}
}
