import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

public class Verificar {

	public static Vector<String> contasPorVerificar = new Vector<>();
	public static Vector<String> contasExistentes = new Vector<>();
	public static Vector<String> contasSemIM = new Vector<>();
	public static String[] pathnames;
	
	public Verificar() {
		// TODO Auto-generated constructor stub
	}

	public static String lerFicheiro(String file, Vector<String> contas) {
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("D:\\"+file));
			try {
				line = br.readLine();
				while(line != null) {
					contas.addElement(line);
					line = br.readLine();;
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
		return line;
	}
	
	public static void imprimirSemIM() {
		
		for(String contas: contasPorVerificar) {
			if(!existe(contas)) {
				contasSemIM.addElement(contas);
			}
		}
		
	}
	
	public static boolean existe(String conta) {
		for(String contas2: contasExistentes) {
			if(conta.equals(contas2)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean contem(String conta) {
		for(String contas2: pathnames) {
			if((conta.substring(3, 9)).equals(contas2.substring(0, 6))) {
				return true;
			}
		}
		return false;
	}
		
	public static void main(String[] args) {
//		lerFicheiro("contasPorVerificar.txt", contasPorVerificar);
//		lerFicheiro("contasExistentes.txt", contasExistentes);
//		
//		imprimirSemIM();
//		System.out.println("=============Contas Sem IM==========");
//		int i = 0;
//		for(String c: contasSemIM){
//			System.out.println(c);
//			i++;
//		}
//		System.out.println(i);
//		
//		System.out.println("=============Contas Sem Assinaturas==========");
//
//        File f = new File("D:\\sig1");
//        pathnames = f.list();
//       
//        i = 0;
//        for(String k: contasSemIM) {
//        	if(!contem(k)) {
//        		System.out.println(k);
//        		i++;
//        	}
//        }
//        System.out.println(i);
        
        
//        File f2 = new File("D:\\New folder");
//        pathnames = f2.list();
//        for(String pathname: pathnames) {
//        	System.out.println(pathname);
//        }
        
//        lerFicheiro("CONTINHAS.txt", contasPorVerificar);
//        for(String contas: contasPorVerificar) {
//        	System.out.println(contas.substring(0, 6));
//		}
		
	}

}
