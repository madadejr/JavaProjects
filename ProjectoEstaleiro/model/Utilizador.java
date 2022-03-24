package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Utilizador implements Serializable{

	private int id_user;
	private String name_user;
	private String type_user;
	private String username;
	private String senha;
	
	
	public Utilizador(int i, String n, String t, String us, String sh) {
		id_user = i;
		name_user = n;
		type_user = t;
		setUsername(us);
		senha = sh;
	}
	
	//GETTERS AND SETTERS
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getName_user() {
		return name_user;
	}

	public void setName_user(String name_user) {
		this.name_user = name_user;
	}

	public String getType_user() {
		return type_user;
	}

	public void setType_user(String type_user) {
		this.type_user = type_user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
