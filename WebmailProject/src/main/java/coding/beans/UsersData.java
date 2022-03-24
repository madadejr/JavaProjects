package coding.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

@SuppressWarnings("serial")
public class UsersData implements Serializable
{
	@SuppressWarnings("rawtypes")
	Hashtable tabela;
	int Id=1;  // still not used
//=========================================
	@SuppressWarnings("rawtypes")
	public UsersData(){
		tabela = new Hashtable();
    }
//=========================================
	@SuppressWarnings("unchecked")
	public void storeNewUser(String login, String nome, String pass, String email){  
		
		lerArquivo("C:\\Users\\CPC - MOISES MADADE\\eclipse-workspace\\WebmailProject\\src\\main\\java\\coding\\beans\\cliente.dat");
		
		ClientInfo c = new ClientInfo(login, pass, nome, email);
		
		tabela.put(login,c);
		Id++;
		
		gravarArquivo(tabela, "C:\\Users\\CPC - MOISES MADADE\\eclipse-workspace\\WebmailProject\\src\\main\\java\\coding\\beans\\cliente.dat");
		log(login);
	}
//=====================================
   public boolean checkUser(String Login, String Pass){     
	 lerArquivo("C:\\Users\\CPC - MOISES MADADE\\eclipse-workspace\\WebmailProject\\src\\main\\java\\coding\\beans\\cliente.dat");
	   
	 ClientInfo c=(ClientInfo)tabela.get(Login);
     if((c == null)||(Pass == null)) {
    	 System.out.println("DEBUG: checkUser: c="+Login);
    	 return false;
     }
     String password=c.getPass();
     System.out.println("DEBUG: checkUser: login="+Login+ "Password= "+password+
                         "Password_Log="+Pass);
     if(password.compareTo(Pass)==0) {
    	 log(Login);
    	 return true;}
     else
    	 return false;
   }   
//=========================================
   public boolean userExist(String Login){ 
	   lerArquivo("C:\\Users\\CPC - MOISES MADADE\\eclipse-workspace\\WebmailProject\\src\\main\\java\\coding\\beans\\cliente.dat");
	   
	   ClientInfo c=(ClientInfo)tabela.get(Login);
	   if(c==null)
		   return false;
	   else
		   return true;  
   }   
//============================================
  @SuppressWarnings("rawtypes")
  public String[] listUsers(){  
	  lerArquivo("C:\\Users\\CPC - MOISES MADADE\\eclipse-workspace\\WebmailProject\\src\\main\\java\\coding\\beans\\cliente.dat");
	  Enumeration e = tabela.elements();
      ClientInfo aux;
      String lista[];
      int i=0;
      lista=new String[tabela.size()];

      if(e != null){
    	  System.out.println("DEBUG: LIST OF USERS ");
    	  while(e.hasMoreElements()){
    		  aux=(ClientInfo)e.nextElement();
    		  System.out.println("USER: "+aux.getLogin()+"   "+aux.getEmail());
    		  lista[i++] = aux.getLogin()+"-"+aux.getNome()+"-"+aux.getEmail()+"-"+aux.getPass(); 
          } //while
    	  return lista;
      }//e
      else
         return null;
   }
  
  public boolean valida(String ema) {
	  listUsers();
	  String email = "";
	  try {
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\CPC - MOISES MADADE\\eclipse-workspace\\WebmailProject\\src\\main\\java\\coding\\beans\\logged.txt"));
		email = br.readLine();
		br.close();
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  
	  
	  for(int i = 0; i < listUsers().length; i++) {
		  String[] em = listUsers()[i].split("-");
		  if(email.equalsIgnoreCase(em[0]) && ema.equalsIgnoreCase(em[2])) {
			  return true;
		  }
	  }
	  
	  return false;
  }
  
  public boolean verifica(String ema) {
	  listUsers();
	  String email = "";
	  try {
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\CPC - MOISES MADADE\\eclipse-workspace\\WebmailProject\\src\\main\\java\\coding\\beans\\logged.txt"));
		email = br.readLine();
		br.close();
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  
	  
	  for(int i = 0; i < listUsers().length; i++) {
		  if(email.equalsIgnoreCase(ema)) {
			  return true;
		  }
	  }
	  
	  return false;
  }
  
  public void log(String email) {
	  FileWriter myWriter;
	try {
		myWriter = new FileWriter(new File("C:\\Users\\CPC - MOISES MADADE\\eclipse-workspace\\WebmailProject\\src\\main\\java\\coding\\beans\\logged.txt"));
		myWriter.write(email);
		myWriter.close(); 
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
//============================================
  //FICHEIRO DE OBJECTO
  public void gravarArquivo(Hashtable lista, String nomeArq) {
      File arq = new File(nomeArq);
      try {
        arq.delete();
        arq.createNewFile();

        ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
        objOutput.writeObject(lista);
        objOutput.close();

      } catch(IOException erro) {
          System.out.printf("Erro: %s", erro.getMessage());
      }
    }

   public void lerArquivo(String nomeArq) {
      try {
        File arq = new File(nomeArq);
        if (arq.exists()) {
           ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
           tabela = (Hashtable)objInput.readObject();
           objInput.close();
        }
      } catch(IOException erro1) {
          System.out.printf("Erro: %s", erro1.getMessage());
      } catch(ClassNotFoundException erro2) {
          System.out.printf("Erro: %s", erro2.getMessage());
      }
    }
}