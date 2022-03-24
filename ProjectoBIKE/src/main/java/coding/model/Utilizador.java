package coding.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Utilizador implements Serializable{

	int id_user;
	String name_user;
	String email;
	String type_user;
	String uname;
	String psw;
	
	public Utilizador() {}
	
	public Utilizador(int i, String n, String email, String t, String us, String sh) {
		id_user = i;
		name_user = n;
		this.email = email;
		type_user = t;
		setUname(us);
		psw = sh;
	}
	
	//GETTERS AND SETTERS
	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
}
