package coding.beans;

import java.io.*;

@SuppressWarnings("serial")
public class ClientInfo implements Serializable
{
	String login;
	String pass;
	String nome;
	String email;
    	
	public ClientInfo(){
     }
	
	public ClientInfo(String login, String pass, String nome, 
                        String email)
	{
		this.login = login;
		this.pass = pass;
		this.nome = nome;
		this.email = email;
	}
    public void setLogin( String value )
    {
        login = value;
    }

    public void setPass( String value )
    {
        pass = value;
    }

	public void setNome( String value )
    {
        nome = value;
    }
	
	public void setEmail( String value)
	{
		email = value;
	}
		
    public String getLogin() { return login; }
    public String getPass() { return pass; }
    public String getNome() { return nome; }
	public String getEmail() { return email; }

}