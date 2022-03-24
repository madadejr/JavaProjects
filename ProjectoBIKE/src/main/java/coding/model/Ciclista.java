package coding.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Ciclista implements Serializable{

	int id_ciclista;
	String nome;
	String contacto;
	String morada;
	String uname;
	String psw;
	
	public Ciclista() {}
	
	public Ciclista(int id, String nome, String contacto, String morada, String uname, String psw) {
		this.id_ciclista = id;
		this.nome = nome;
		this.contacto = contacto;
		this.morada = morada;
		this.uname = uname;
		this.psw = psw;
	}

	public int getId_ciclista() {
		return id_ciclista;
	}

	public void setId_ciclista(int id_ciclista) {
		this.id_ciclista = id_ciclista;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}
	
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}
}
