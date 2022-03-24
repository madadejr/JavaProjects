package controller;

import java.io.IOException;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Estaleiro;
import model.Produto;

public class Produto_C {

	public static Vector<Produto> prods = new Vector<Produto>();
	private static Estaleiro estaleiro = new Estaleiro();
	
	//COMPORTAMENTOS
	//VERIFICACAO DA EXISTENCIA DO PRODUTOS
	public static boolean existe(JTextField txt) {
		Produto prod;
		
		for(int i = 0; i < prods.size(); i++) {
			prod = (Produto)prods.get(i);
			if(prod.getId() == Integer.parseInt(txt.getText())) {
				return true;
			}
		}
		
		return false;
	}
	
	//REGISTAR PRODUTO
	public static void registar(JTextField id, JTextField nome, JTextField marca, JComboBox<String> modela, JTextField imagem, JComboBox<String> estado, String data, JTextField custo, JTextField qty, DefaultTableModel modelo) throws IOException, ClassNotFoundException {
		int code = Integer.parseInt(id.getText());
		String name = nome.getText();
		String marc = marca.getText();
		String model = (String) modela.getSelectedItem();
		String image = imagem.getText();
		String estad = (String) estado.getSelectedItem();
		double cust = Double.parseDouble(custo.getText());
		int q = Integer.parseInt(qty.getText());
		
		
		if(!existe(id)) {
			try {
				Produto prod = new Produto(code, name, marc, model, image, estad, data, cust,q);
				prods.add(prod);
				estaleiro.escreverFicheiro(prods, "produtos.arq");
				pesquisar(modelo);
		
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Falha!"+e.getMessage());
			}
		}else JOptionPane.showMessageDialog(null, "Não podem existir prods com mesma informação!");
	}
	
	//RECUPERAR PRODUTOS
	@SuppressWarnings("unchecked")
	public static void recuperarProds() {
		try {
			prods.addAll(estaleiro.lerFicheiro("produtos.arq"));
		}catch(Exception e) {
//			JOptionPane.showMessageDialog(null, "Nenhum produto no sistema, por favor, inicie com uma conta Admin!");
		}
	}
	
	//REMOVER PRODUTO
	public static void remover(JTextField id) throws IOException {
		
		if(existe(id)) {
			Estaleiro estaleiro = new Estaleiro();
			for(int i = 0; i < prods.size(); i++) {
				Produto u = (Produto)prods.get(i);
				if(u.getId() == Integer.parseInt(id.getText())) {
					prods.removeElementAt(i);
				}
			}
			estaleiro.escreverFicheiro(prods, "produtos.arq");
			
		}else JOptionPane.showMessageDialog(null, "Produto não existe!");
	}
	
	//ACTUALIZAR PRODUTO
	public static void actualizar(JTextField id, JTextField nome, JTextField marca, JComboBox<String> modela, JTextField imagem, JComboBox<String> estado, String data, JTextField custo, JTextField qty, DefaultTableModel modelo) {
		int code = Integer.parseInt(id.getText());
		String name = nome.getText();
		String marc = marca.getText();
		String model = (String) modela.getSelectedItem();
		String image = imagem.getText();
		String estad = (String) estado.getSelectedItem();
		double cust = Double.parseDouble(custo.getText());
		int q = Integer.parseInt(qty.getText());
		
		if(existe(id)) {
		
			for(int i = 0; i < prods.size(); i++) {
				Produto prod = (Produto)prods.get(i);
				if(prod.getId() == code) {
					prod.setNome(name);
					prod.setCusto(cust);
					prod.setEstado(estad);
					prod.setHora_saida(data);
					prod.setImagem(image);
					prod.setMarca(marc);
					prod.setModelo(model);
					prod.setQuantidade(q);
					prods.setElementAt(prod, i);
				}
			}
			try {
				estaleiro.escreverFicheiro(prods, "produtos.arq");
				pesquisar(modelo);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Falha na actualização!");
			}
			
		}else JOptionPane.showMessageDialog(null, "Produto não existe!");
	}
	
	//PREENCHER A TABELA DE DADOS
	public static void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        Produto prod;
        for (int i = 0; i < prods.size(); i++) {
            prod = (Produto)prods.get(i);
        	modelo.addRow(new Object[]{prod.getId(), prod.getNome(), prod.getMarca(), prod.getModelo(), prod.getEstado(), prod.getCusto(), prod.getImagem(), prod.getHora_saida(), prod.getQuantidade()});
        }
    }
	
	//PROCURAR PRODUTO
	public static void procurar(JTextField id, JTextField nome, JTextField marca, JComboBox<String> model, JComboBox<String> estado, JTextField data, DefaultTableModel modelo) {
		
		modelo.setNumRows(0);
		int ident = 0;
		if(!id.getText().isEmpty()) ident = Integer.parseInt(id.getText());
		
		for(int i = 0; i < prods.size(); i++) {
			Produto prod = (Produto)prods.get(i);
			if(!id.getText().isEmpty() && nome.getText().isEmpty() && marca.getText().isEmpty() && data.getText().isEmpty() && model.getSelectedIndex()==-1 && estado.getSelectedIndex()==-1) {
				if(prod.getId() == ident) {
					
					modelo.addRow(new Object[]{prod.getId(), prod.getNome(), prod.getMarca(), prod.getModelo(), prod.getEstado(), prod.getCusto(), prod.getImagem(), prod.getHora_saida(), prod.getQuantidade()});
				}
			}
			else if(id.getText().isEmpty() && !nome.getText().isEmpty() && marca.getText().isEmpty() && data.getText().isEmpty() && model.getSelectedIndex()==-1 && estado.getSelectedIndex()==-1) {
				if(prod.getNome().contains(nome.getText())) {
					
					modelo.addRow(new Object[]{prod.getId(), prod.getNome(), prod.getMarca(), prod.getModelo(), prod.getEstado(), prod.getCusto(), prod.getImagem(), prod.getHora_saida(), prod.getQuantidade()});
				}
			}
			else if(id.getText().isEmpty() && nome.getText().isEmpty() && !marca.getText().isEmpty() && data.getText().isEmpty() && model.getSelectedIndex()==-1 && estado.getSelectedIndex()==-1) {
				if(prod.getMarca().contains(marca.getText())) {
					
					modelo.addRow(new Object[]{prod.getId(), prod.getNome(), prod.getMarca(), prod.getModelo(), prod.getEstado(), prod.getCusto(), prod.getImagem(), prod.getHora_saida(), prod.getQuantidade()});
				}
			}
			else if(id.getText().isEmpty() && nome.getText().isEmpty() && marca.getText().isEmpty() && !data.getText().isEmpty() && model.getSelectedIndex()==-1 && estado.getSelectedIndex()==-1) {
				if(prod.getHora_saida().contains(data.getText())) {
					
					modelo.addRow(new Object[]{prod.getId(), prod.getNome(), prod.getMarca(), prod.getModelo(), prod.getEstado(), prod.getCusto(), prod.getImagem(), prod.getHora_saida(), prod.getQuantidade()});
				}
			}
			else if(id.getText().isEmpty() && nome.getText().isEmpty() && marca.getText().isEmpty() && data.getText().isEmpty() && model.getSelectedIndex()!=-1 && estado.getSelectedIndex()==-1) {
				if(prod.getModelo().contains((String)model.getSelectedItem())) {
					
					modelo.addRow(new Object[]{prod.getId(), prod.getNome(), prod.getMarca(), prod.getModelo(), prod.getEstado(), prod.getCusto(), prod.getImagem(), prod.getHora_saida(), prod.getQuantidade()});
				}
			}
			else if(id.getText().isEmpty() && nome.getText().isEmpty() && marca.getText().isEmpty() && data.getText().isEmpty() && model.getSelectedIndex()==-1 && estado.getSelectedIndex()!=-1) {
				if(prod.getEstado().contains((String)estado.getSelectedItem())) {
					
					modelo.addRow(new Object[]{prod.getId(), prod.getNome(), prod.getMarca(), prod.getModelo(), prod.getEstado(), prod.getCusto(), prod.getImagem(), prod.getHora_saida(), prod.getQuantidade()});
				}
			}
			else if(id.getText().isEmpty() && !nome.getText().isEmpty() && !marca.getText().isEmpty() && data.getText().isEmpty() && model.getSelectedIndex()==-1 && estado.getSelectedIndex()==-1) {
				if(prod.getNome().contains(nome.getText()) && prod.getMarca().contains(marca.getText())) {
					
					modelo.addRow(new Object[]{prod.getId(), prod.getNome(), prod.getMarca(), prod.getModelo(), prod.getEstado(), prod.getCusto(), prod.getImagem(), prod.getHora_saida(), prod.getQuantidade()});
				}
			}
			else if(id.getText().isEmpty() && !nome.getText().isEmpty() && marca.getText().isEmpty() && data.getText().isEmpty() && model.getSelectedIndex()!=-1 && estado.getSelectedIndex()==-1) {
				if(prod.getNome().contains(nome.getText()) && prod.getModelo().contains((String)model.getSelectedItem())) {
					
					modelo.addRow(new Object[]{prod.getId(), prod.getNome(), prod.getMarca(), prod.getModelo(), prod.getEstado(), prod.getCusto(), prod.getImagem(), prod.getHora_saida(), prod.getQuantidade()});
				}
			}
			else if(id.getText().isEmpty() && !nome.getText().isEmpty() && marca.getText().isEmpty() && !data.getText().isEmpty() && model.getSelectedIndex()==-1 && estado.getSelectedIndex()==-1) {
				if(prod.getNome().contains(nome.getText()) && prod.getHora_saida().contains(data.getText())) {
					
					modelo.addRow(new Object[]{prod.getId(), prod.getNome(), prod.getMarca(), prod.getModelo(), prod.getEstado(), prod.getCusto(), prod.getImagem(), prod.getHora_saida(), prod.getQuantidade()});
				}
			}
			else if(id.getText().isEmpty() && nome.getText().isEmpty() && !marca.getText().isEmpty() && !data.getText().isEmpty() && model.getSelectedIndex()==-1 && estado.getSelectedIndex()==-1) {
				if(prod.getMarca().contains(marca.getText()) && prod.getHora_saida().contains(data.getText())) {
					
					modelo.addRow(new Object[]{prod.getId(), prod.getNome(), prod.getMarca(), prod.getModelo(), prod.getEstado(), prod.getCusto(), prod.getImagem(), prod.getHora_saida(), prod.getQuantidade()});
				}
			}
			else if(id.getText().isEmpty() && nome.getText().isEmpty() && marca.getText().isEmpty() && !data.getText().isEmpty() && model.getSelectedIndex()!=-1 && estado.getSelectedIndex()==-1) {
				if(prod.getModelo().contains((String)model.getSelectedItem()) && prod.getHora_saida().contains(data.getText())) {
					
					modelo.addRow(new Object[]{prod.getId(), prod.getNome(), prod.getMarca(), prod.getModelo(), prod.getEstado(), prod.getCusto(), prod.getImagem(), prod.getHora_saida(), prod.getQuantidade()});
				}
			}
			else if(id.getText().isEmpty() && nome.getText().isEmpty() && !marca.getText().isEmpty() && data.getText().isEmpty() && model.getSelectedIndex()!=-1 && estado.getSelectedIndex()==-1) {
				if(prod.getModelo().contains((String)model.getSelectedItem()) && prod.getMarca().contains(marca.getText())) {
					
					modelo.addRow(new Object[]{prod.getId(), prod.getNome(), prod.getMarca(), prod.getModelo(), prod.getEstado(), prod.getCusto(), prod.getImagem(), prod.getHora_saida(), prod.getQuantidade()});
				}
			}
		}
	}
	
	//PEGAR ULTIMO ID
	public static int pegarUltimo() {
		if(prods.size() > 0) {
			Produto arm = (Produto)prods.get(prods.size()-1);
			return arm.getId();
		}
		return 0;
	}
	
	//PREENCHER A TABELA DE DADOS
	public static void pesquisarProd(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        Produto prod;
        for (int i = 0; i < prods.size(); i++) {
            prod = (Produto)prods.get(i);
            if(prod.getQuantidade() != 0) {
            	modelo.addRow(new Object[]{prod.getId(), prod.getNome(), prod.getQuantidade(), prod.getCusto(), "", "0.0"});
            }
        }
    }
	
	//PRODUTO NA VENDA
	public static void procurarProd(JTextField txt, DefaultTableModel modelo) {
		modelo.setNumRows(0);
				
		for(int i = 0; i < prods.size(); i++) {
			Produto prod = (Produto)prods.get(i);
			if(prod.getNome().contains(txt.getText()) && prod.getQuantidade() != 0) {
				
				modelo.addRow(new Object[]{prod.getId(), prod.getNome(), prod.getQuantidade(), prod.getCusto(), "", "0.0"});
			}
			
		}
	}

}
