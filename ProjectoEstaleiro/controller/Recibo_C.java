package controller;

import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Armazem;
import model.Estaleiro;
import model.Produto;
import model.Recibo;

public class Recibo_C {

	public static Vector<Recibo> recs = new Vector<Recibo>();
	public static Vector<Produto> prods = Produto_C.prods;
	private static Estaleiro estaleiro = new Estaleiro();
	
	//COMPORTAMENTOS
	//VERIFICACAO DA EXISTENCIA DO RECIBOS
	public static boolean existe(JTextField txt) {
		Recibo prod;
		
		for(int i = 0; i < recs.size(); i++) {
			prod = (Recibo)recs.get(i);
			if(prod.getId_recibo() == Integer.parseInt(txt.getText())) {
				return true;
			}
		}
		
		return false;
	}
		
	//REGISTAR RECIBO 
	public static void registar(int id, int id_prod, String nome, double custo, String data, String utilizador, String cliente, int quantidade, DefaultTableModel modelo) throws IOException, ClassNotFoundException {
		
		try {
			Recibo prod = new Recibo(id, id_prod, nome, custo, data, utilizador, cliente, quantidade);
			recs.add(prod);
			estaleiro.escreverFicheiro(recs, "recibos.arq");
	
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Falha!"+e.getMessage());
		}
	}
	
	//RECUPERAR RECIBOS
	@SuppressWarnings("unchecked")
	public static void recuperarRecs() {
		try {
			recs.addAll(estaleiro.lerFicheiro("recibos.arq"));
		}catch(Exception e) {
//			JOptionPane.showMessageDialog(null, "Nenhum produto no sistema, por favor, inicie com uma conta Admin!");
		}
	}
	
	//REMOVER RECIBO
	public static void remover(JTextField id) throws IOException {
		
		if(existe(id)) {
			Estaleiro estaleiro = new Estaleiro();
			while(existe(id)) {
				for(int i = 0; i < recs.size(); i++) {
					Recibo u = (Recibo)recs.get(i);
					if(u.getId_recibo() == Integer.parseInt(id.getText())) {
						recs.removeElementAt(i);
					}
				}
			}
			estaleiro.escreverFicheiro(recs, "recibos.arq");
			Armazem_C.remover(Integer.parseInt(id.getText()));
			
		}else JOptionPane.showMessageDialog(null, "Recibo não existe!");
	}
	
	//PREENCHER A TABELA DE DADOS
	public static void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        Armazem arm;
        String tempo="";
        Recibo rec;
        for (int i = 0; i < recs.size(); i++) {
            rec = (Recibo)recs.get(i);
            for(int k =0; k<Armazem_C.arms.size(); k++) {
				arm = (Armazem)Armazem_C.arms.get(k);
				if(arm.getId_produto() == rec.getId_produto() && arm.getId_recibo() == rec.getId_recibo()) {
					tempo = arm.getTempo_permanencia();
					break;
				}
			}
        	modelo.addRow(new Object[]{rec.getId_recibo(), rec.getId_produto(), rec.getNome_produto(), rec.getValor_pago(), rec.getData_pagamento(), rec.getNome_user(), rec.getNome_cliente(), rec.getQuantidade(), tempo});
	   }
    }
	
	public static void pegarRecibo(int id_recibo,DefaultTableModel modelo) {
		modelo.setNumRows(0);
        Recibo rec;
        for (int i = 0; i < recs.size(); i++) {
            rec = (Recibo)recs.get(i);
        	if(rec.getId_recibo() == id_recibo) {
        		modelo.addRow(new Object[]{rec.getId_produto(), rec.getNome_produto(), rec.getQuantidade(), rec.getValor_pago()});
        	}
        }
	}
	
	//PROCURAR RECIBO
	public static void procurar(JTextField id, DefaultTableModel modelo) {
		
		String id1 = id.getText();
		
		Armazem arm;
		String tempo="";
		
		modelo.setNumRows(0);
		if(!id1.isEmpty()) {
			for(int i = 0; i < recs.size(); i++) {
				Recibo rec = (Recibo)recs.get(i);
				if(id1.equals(rec.getId_recibo()+"")) {
					
					for(int k =0; k<Armazem_C.arms.size(); k++) {
						arm = (Armazem)Armazem_C.arms.get(k);
						if(arm.getId_produto() == rec.getId_produto() && arm.getId_recibo() == rec.getId_recibo()) {
							tempo = arm.getTempo_permanencia();
							break;
						}
					}
		        	modelo.addRow(new Object[]{rec.getId_recibo(), rec.getId_produto(), rec.getNome_produto(), rec.getValor_pago(), rec.getData_pagamento(), rec.getNome_user(), rec.getNome_cliente(), rec.getQuantidade(), tempo});
				}
			}
		}
		else pesquisar(modelo);
	}
	
	public static void procurarDataNome(JTextField campo, DefaultTableModel modelo) {		
		Armazem arm;
		String tempo="";
		
		modelo.setNumRows(0);
		if(!campo.getText().isEmpty()) {
			for(int i = 0; i < recs.size(); i++) {
				Recibo rec = (Recibo)recs.get(i);
				if(rec.getNome_produto().contains(campo.getText()) || rec.getData_pagamento().contains(campo.getText())) {
					for(int k =0; k<Armazem_C.arms.size(); k++) {
						arm = (Armazem)Armazem_C.arms.get(k);
						if(arm.getId_produto() == rec.getId_produto() && arm.getId_recibo() == rec.getId_recibo()) {
							tempo = arm.getTempo_permanencia();
							break;
						}
					}
		        	modelo.addRow(new Object[]{rec.getId_recibo(), rec.getId_produto(), rec.getNome_produto(), rec.getValor_pago(), rec.getData_pagamento(), rec.getNome_user(), rec.getNome_cliente(), rec.getQuantidade(), tempo});
				}
			}
		}
		else pesquisar(modelo);
	}
	
	//PEGAR ULTIMO ID
	public static int pegarUltimo() {
		if(recs.size() > 0) {
			Recibo rec = (Recibo)recs.get(recs.size()-1);
			return rec.getId_recibo();
		}
		return 0;
	}
	
	//COMPLEMENTO DA VENDA
	public static void decrementarQty(JTable tabela) {
		Produto prod, prod2;
		String qty;
		int qtyP = 0;
		for(int i = 0; i < tabela.getModel().getRowCount(); i++) {
			prod = (Produto) prods.get(i);
			
			qty = (String)tabela.getModel().getValueAt(i, 4);
			if(!qty.isEmpty()) {
				qtyP = (Integer)tabela.getModel().getValueAt(i, 2);						
				qtyP = qtyP - Integer.parseInt(qty);
				
				prod2 = new Produto(prod.getId(),prod.getNome(),prod.getMarca(),prod.getModelo(),prod.getImagem(),prod.getEstado(),prod.getHora_saida(),prod.getCusto(), qtyP);
				
				prods.setElementAt(prod2, i);
			}
		}
		
		try {
			estaleiro.escreverFicheiro(prods, "produtos.arq");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Falha!");
		}
	}
}
