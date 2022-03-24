package controller;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Estaleiro;
import model.Utilizador;

public class Utilizador_C {

	private static final char[] car = new char[62];
	private static final Random rd = new Random();
	public static Vector<Utilizador> users = new Vector<Utilizador>();
	private static Estaleiro estaleiro = new Estaleiro();
	
	//COMPORTAMENTOS
	//VERIFICACAO DA EXISTENCIA DO USUARIOS
	public static boolean existe(JTextField txt, JTextField txt2) {
		Utilizador usr;
		
		for(int i = 0; i < users.size(); i++) {
			usr = (Utilizador)users.get(i);
			if(usr.getId_user() == Integer.parseInt(txt.getText()) || usr.getUsername().equalsIgnoreCase(txt2.getText())) {
				return true;
			}
		}
		
		return false;
	}
	//VERIFICACAO DA EXISTENCIA DO USUARIOS SEARCH
	public static boolean existeP(JTextField txt, JTextField txt2) {
		Utilizador usr;
		
		for(int i = 0; i < users.size(); i++) {
			usr = (Utilizador)users.get(i);
			if(usr.getId_user() == Integer.parseInt(txt.getText()) || usr.getName_user().equalsIgnoreCase(txt2.getText())) {
				return true;
			}
		}
		
		return false;
	}
	
	//REGISTAR USUARIO
	public static void registar(JTextField txt, JTextField txt2, JComboBox<String> cbo, JTextField txt3, JTextField txt4, DefaultTableModel modelo) throws IOException, ClassNotFoundException {
		int c = Integer.parseInt(txt.getText());
		String n = txt2.getText();
		String t = (String) cbo.getSelectedItem();
		String us = txt3.getText();
		String s = txt4.getText();
		
		if(!existe(txt, txt3)) {
			try {
				Utilizador usr = new Utilizador(c,n,t,us,s);
				users.add(usr);
				estaleiro.escreverFicheiro(users, "usuarios.arq");
				pesquisar(modelo);
				
				System.out.println("USER: "+us+"\nPASSWORD: "+s);
		
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Falha!"+e.getMessage());
			}
		}else JOptionPane.showMessageDialog(null, "Não podem existir utilizadores com mesma informação!");
	}
	
	//RECUPERAR USUARIOS
	@SuppressWarnings("unchecked")
	public static void recuperarUsers() {
		try {
			users.addAll(estaleiro.lerFicheiro("usuarios.arq"));
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Nenhum utilizador no sistema, por favor, inicie com uma conta Admin!");
		}
	}
	
	//REMOVER USUARIO
	public static void remover(JTextField txt, JTextField txt3) throws IOException {
		
		if(existe(txt, txt3)) {
			Estaleiro estaleiro = new Estaleiro();
			for(int i = 0; i < users.size(); i++) {
				Utilizador u = (Utilizador)users.get(i);
				if(u.getId_user() == Integer.parseInt(txt.getText())) {
					users.removeElementAt(i);
				}
			}
			estaleiro.escreverFicheiro(users, "usuarios.arq");
			
		}else JOptionPane.showMessageDialog(null, "Utilizador não existe!");
	}
	
	//ACTUALIZAR USUARIO
	public static void actualizar(JTextField txt, JTextField txt2, JComboBox<String> cbo, JTextField txt3, JTextField txt4, DefaultTableModel modelo) {
		
		if(existe(txt, txt3)) {
		
			for(int i = 0; i < users.size(); i++) {
				Utilizador usr = (Utilizador)users.get(i);
				if(usr.getId_user() == (Integer.parseInt(txt.getText()))) {
					usr.setName_user(txt2.getText());
					usr.setType_user((String)cbo.getSelectedItem());
					usr.setSenha(txt4.getText());
					users.setElementAt(usr, i);
				}
			}
			try {
				estaleiro.escreverFicheiro(users, "usuarios.arq");
				pesquisar(modelo);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Falha na actualização!");
			}
			
		}else JOptionPane.showMessageDialog(null, "Utilizador não existe!");
	}
	
	//PREENCHER A TABELA DE DADOS
	public static void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        Utilizador usr;
        for (int i = 0; i < users.size(); i++) {
            usr = (Utilizador)users.get(i);
        	modelo.addRow(new Object[]{usr.getId_user(), usr.getName_user(), usr.getType_user(), usr.getUsername(), usr.getSenha()});
        }
    }
	
	//PROCURAR USUARIO
	public static void procurar(JTextField txt, JTextField txt2, DefaultTableModel modelo) {
		
		modelo.setNumRows(0);
		
		for(int i = 0; i < users.size(); i++) {
			Utilizador usr = (Utilizador)users.get(i);
			if(!txt2.getText().isEmpty() && txt.getText().isEmpty()) {
				if(usr.getName_user().contains(txt2.getText())) {
					
		        	modelo.addRow(new Object[]{usr.getId_user(), usr.getName_user(), usr.getType_user(), usr.getUsername(), usr.getSenha()});
				}
			}
			else if(!txt.getText().isEmpty() && txt2.getText().isEmpty()) {
				if(usr.getId_user() == Integer.parseInt(txt.getText())) {
					
		        	modelo.addRow(new Object[]{usr.getId_user(), usr.getName_user(), usr.getType_user(), usr.getUsername(), usr.getSenha()});
				}
			}
			else if(!txt2.getText().isEmpty() && !txt.getText().isEmpty()) {
				if(usr.getName_user().contains(txt2.getText()) && usr.getId_user() == Integer.parseInt(txt.getText())) {
					
		        	modelo.addRow(new Object[]{usr.getId_user(), usr.getName_user(), usr.getType_user(), usr.getUsername(), usr.getSenha()});
				}
			}
		}
	}
	
	//PEGAR ULTIMO ID
	public static int pegarUltimo() {
		if(users.size() > 0) {
			Utilizador arm = (Utilizador)users.get(users.size()-1);
			return arm.getId_user();
		}
		return 1000;
	}
	
	//PEGAR PASSWORD
	public static String pegarPasse(int id) {
		Utilizador usr;
		
		for(int i = 0; i < users.size(); i++) {
			usr = (Utilizador)users.get(i);
			if(usr.getId_user() == id) {
				return usr.getSenha();
			}
		}
		return null;
	}
	
	//GERAR CODIGO
	static{
		for(int i = 48, j = 0; i < 123; i++) {
			if(Character.isLetterOrDigit(i)) {
				car[j] = (char)i;
				j++;
			}
		}
	}
	
	public static void gerarCodigo(JTextField txt) {
		final char[] result = new char[8];
		for(int k = 0; k < 8; k++) {
			result[k] = car[rd.nextInt(car.length)]; 
		}
		
		txt.setText(new String(result));
	}

}
