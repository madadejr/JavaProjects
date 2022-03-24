package controller;

import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Estaleiro;
import model.Armazem;

public class Armazem_C {

	public static Vector<Armazem> arms = new Vector<Armazem>();
	private static Estaleiro estaleiro = new Estaleiro();
	
	//COMPORTAMENTOS		
	//REGISTAR ARMAZEM
	public static void registar(int id, String data, int rec, DefaultTableModel modelo) throws IOException, ClassNotFoundException {
		
		try {
			Armazem arm = new Armazem(id,data,rec);
			arms.add(arm);
			estaleiro.escreverFicheiro(arms, "armazens.arq");
	
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Falha!"+e.getMessage());
		}
	}
	//REMOVER ARMAZEM
		public static void remover(int id) throws IOException {
			
				Estaleiro estaleiro = new Estaleiro();
				for(int i = 0; i < arms.size(); i++) {
					Armazem u = (Armazem)arms.get(i);
					if(u.getId_recibo() == id) {
						arms.removeElementAt(i);
					}
				}
				estaleiro.escreverFicheiro(arms, "armazens.arq");
		}
	//RECUPERAR ARMAZEMS
	@SuppressWarnings("unchecked")
	public static void recuperarArms() {
		try {
			arms.addAll(estaleiro.lerFicheiro("armazens.arq"));
		}catch(Exception e) {
//			JOptionPane.showMessageDialog(null, "Nenhum utilizador no sistema, por favor, inicie com uma conta Admin!");
		}
	}
	
//	public static void main(String args []) {
//		recuperarArms();
//		for(int i = 0; i < arms.size(); i++) {
//			Armazem u = (Armazem)arms.get(i);
//			System.out.println(u.getId_produto()+" "+u.getId_recibo()+" "+u.getTempo_permanencia());
//		}
//	}
}
