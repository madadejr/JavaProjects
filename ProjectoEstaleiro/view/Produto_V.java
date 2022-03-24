package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//import controller.Armazem_C;
import controller.Produto_C;
//import model.Armazem;
import model.Estaleiro;

public class Produto_V implements ActionListener{
	private JFrame tela;
	private JLabel codigo,marca,modela,nome,estado,preco, imagem,hora_saida,quantidade;
	private JTextField campo_imagem,campo_codigo,campo_marca,campo_nome,campo_preco, campo_hora,campo_quantidade;
	private JButton tempo,carregar_data,carregar,ver,novo,gravar,excluir,actualizar, procurar,fechar;
	public static JComboBox<String> lista_estado, lista_modelo;
	private JTable tabela;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JScrollPane barra_rolagem;
	private ImageIcon icone,imnovo,imgravar,imexcluir,imlistar,imfechar,improcurar;
	private FundoBg fundo;
	//Paineis
	private JPanel painel_codigo,painel_nome,painel_botoes,painel_foto;
	private JCheckBox pesq;
	private JFileChooser fc;
	
	//CONSTRUTOR
	public Produto_V()throws IOException{
		tela=new JFrame();
		fundo=new FundoBg("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//menu_icone.jpg");
		icone=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//icone_tela_login.png");
		tela.setTitle("Produto");
		tela.setSize(1080,600);
		tela.setIconImage(icone.getImage());
		tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tela.setLocationRelativeTo(null);
		tela.setResizable(false);
		tela.add(fundo);
		
		//INSTANCIAR VARIAVEIS 
		//::>ImageIcon
		imnovo=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//novo.png");
		imgravar=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//gravar.png");
		imexcluir=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//deletar.png");
		imlistar=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//update.png");
		imfechar=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//fechar.png");
		improcurar=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//pesquisa.png");
		//::>JPanel
		painel_codigo=new JPanel();
		painel_nome=new JPanel();
		painel_botoes=new JPanel();
		painel_foto=new JPanel();
		
		//::>JLabel
		codigo=new JLabel("Codigo:");
		nome=new JLabel("Nome:");
		marca=new JLabel("Marca:");
		modela=new JLabel("Modelo:");
		estado=new JLabel("Estado:");
		preco=new JLabel("Custo:");
		hora_saida=new JLabel("Data e Hora de Saida:");
		imagem=new JLabel("Foto Carregada");
		imagem.setVisible(false);
		quantidade=new JLabel("Quantidade");
		
		//::>JTextField
		campo_codigo=new JTextField(5);
		campo_codigo.setEditable(false);
		campo_nome=new JTextField(20);
		campo_marca=new JTextField(20);
		campo_preco=new JTextField(8);
		campo_hora=new JTextField(15);
		campo_hora.setEditable(false);
		campo_imagem=new JTextField();
		campo_imagem.setVisible(false);
		campo_quantidade=new JTextField(8);
		
		//::>JComboBox
		lista_estado = new JComboBox<>();
		lista_estado.addItem("Inspecionado");
		lista_estado.addItem("Nao Inspecionado");
		lista_estado.setSelectedIndex(-1);
		
		lista_modelo = new JComboBox<>();
		lista_modelo.addItem("Bloco");
		lista_modelo.addItem("Tijolo");
		lista_modelo.addItem("Lancis");
		lista_modelo.addItem("Grelha");
		lista_modelo.addItem("Paves");
		lista_modelo.setSelectedIndex(-1);
		
		int c = Produto_C.pegarUltimo()+1;
		campo_codigo.setText(c+"");
		
		//::>JCheckBox
  		pesq = new JCheckBox("Pesquisa");
		
		//::>JButton
		novo=new JButton("Novo",imnovo);
		gravar=new JButton("Gravar",imgravar);
		excluir=new JButton("Excluir",imexcluir);
		actualizar=new JButton("Actualizar",imlistar);
		fechar=new JButton("Fechar",imfechar);
		procurar=new JButton("Procurar",improcurar);
		carregar=new JButton("Carregar imagem");
		ver=new JButton("Ver");
		ver.setVisible(false);
		carregar_data=new JButton("Hora");
		tempo=new JButton("Armazem");
		tempo.setVisible(false);
		//::>JTable
		criaJTable();
		
		//::>JSCROLLPANE
	    barra_rolagem=new JScrollPane(tabela);
	 
	    //::>ACCOES
	    fechar.addActionListener(this);
	    novo.addActionListener(this);
	    gravar.addActionListener(this);
	    actualizar.addActionListener(this);
	    excluir.addActionListener(this);
	    procurar.addActionListener(this);
	    pesq.addActionListener(this);
	    carregar.addActionListener(this);
	    ver.addActionListener(this);
	    carregar_data.addActionListener(this);
	    tempo.addActionListener(this);
	    
		//PAINEL CODIGO
		painel_codigo.setLayout(new FlowLayout(FlowLayout.LEFT));
		painel_codigo.add(codigo); painel_codigo.add(campo_codigo); painel_codigo.add(new JLabel());
		painel_codigo.add(nome); painel_codigo.add(campo_nome); 
		painel_codigo.add(marca); painel_codigo.add(campo_marca);
		painel_codigo.add(modela); painel_codigo.add(lista_modelo); painel_codigo.add(new JLabel());
		painel_codigo.add(estado); painel_codigo.add(lista_estado); painel_codigo.add(pesq); painel_codigo.add(new JLabel());
		
		//PAINEL IMAGEM
		painel_foto.setLayout(new FlowLayout(FlowLayout.LEFT));
		painel_foto.add(campo_imagem);
		painel_foto.add(imagem);
		painel_foto.add(ver);
		
		//PAINEL NOME
		painel_nome.setLayout(new FlowLayout(FlowLayout.LEFT));
		painel_nome.add(quantidade); painel_nome.add(campo_quantidade);
		painel_nome.add(preco); painel_nome.add(campo_preco);
		painel_nome.add(hora_saida); painel_nome.add(campo_hora);painel_nome.add(carregar_data);
		painel_nome.add(new JLabel("                        "));  
		painel_nome.add(carregar); painel_nome.add(painel_foto);  

		//::>PAINEL BOTOES
	    painel_botoes.setLayout(new FlowLayout(FlowLayout.LEFT));
		painel_botoes.add(painel_codigo); painel_botoes.add(painel_nome);
		painel_botoes.add(pesq);
		painel_botoes.add(novo);
		painel_botoes.add(gravar);
		painel_botoes.add(procurar);
		painel_botoes.add(excluir);
		painel_botoes.add(actualizar);
		painel_botoes.add(fechar);
		painel_botoes.add(new JLabel("                                                         "));
		painel_botoes.add(tempo);
		
		//TELA
		fundo.setLayout(new FlowLayout(FlowLayout.LEFT));
		fundo.add(painel_codigo);
		fundo.add(painel_nome);
		fundo.add(painel_botoes);
		fundo.add(barra_rolagem);
		
		//DEFINIR VISIBILIDADE
		tela.setVisible(true);
		
	}
	
	private void criaJTable() {
        tabela = new JTable(modelo);
        tabela.setPreferredScrollableViewportSize(new Dimension(1040,400));
        modelo.addColumn("Codigo");
        modelo.addColumn("Nome");
        modelo.addColumn("Marca");
        modelo.addColumn("Modelo");
        modelo.addColumn("Estado");
        modelo.addColumn("Custo");
        modelo.addColumn("Foto");
        modelo.addColumn("Data e Hora de saida");
        modelo.addColumn("Quantidade");
        tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(30);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(30);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(6).setPreferredWidth(30);
        tabela.getColumnModel().getColumn(7).setPreferredWidth(30);
        tabela.getColumnModel().getColumn(8).setPreferredWidth(10);
        tabela.setRowSelectionAllowed(true);
        
        tabela.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		int codig = (Integer)tabela.getModel().getValueAt(tabela.getSelectedRow(), 0);
        		String nome = (String)tabela.getModel().getValueAt(tabela.getSelectedRow(), 1);
        		String marca = (String)tabela.getModel().getValueAt(tabela.getSelectedRow(), 2);
        		String modela = (String)tabela.getModel().getValueAt(tabela.getSelectedRow(), 3);
        		String estado = (String)tabela.getModel().getValueAt(tabela.getSelectedRow(), 4);
        		double custo = (double)tabela.getModel().getValueAt(tabela.getSelectedRow(), 5);
        		String foto = (String)tabela.getModel().getValueAt(tabela.getSelectedRow(), 6);
        		String data = (String)tabela.getModel().getValueAt(tabela.getSelectedRow(), 7);
        		int quant = (Integer)tabela.getModel().getValueAt(tabela.getSelectedRow(), 8);
        		
        		campo_codigo.setText(codig+"");
        		campo_nome.setText(nome);
        		campo_marca.setText(marca);
        		lista_modelo.setSelectedItem(modela);
        		lista_estado.setSelectedItem(estado);
        		campo_preco.setText(custo+"");
        		campo_imagem.setText(foto);
        		campo_hora.setText(data);
        		campo_quantidade.setText(quant+"");
        		ver.setVisible(true);
        		imagem.setVisible(true);
        		//tempo.setVisible(true);
        	}
        });
        
        Produto_C.pesquisar(modelo);
    }
	
	@SuppressWarnings("serial")
	public class FundoBg extends JPanel{
		private BufferedImage img=null;
		private int x=0;
		private int y=0;
		
		public FundoBg(String urlImg) throws IOException{
			this.img=ImageIO.read(new File(urlImg));
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics gr=(Graphics2D)g.create();
			gr.drawImage(img, x, y, this.getWidth(), this.getHeight(), this);
			gr.dispose();
		}
	}
	
	
	//Classe teste para a janela
	public static void main(String args[]) throws IOException{
		new Produto_V();
	}

	@Override
	public void actionPerformed(ActionEvent f) {
		if(f.getSource()==fechar){
			try {
				tela.dispose();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(f.getSource() == novo) {
			limpar();
		}
		else if(f.getSource()==gravar) {
			
			if(campo_codigo.getText().equals("") || campo_nome.getText().equals("") || campo_marca.getText().equals("") || campo_preco.getText().equals("") || lista_modelo.getSelectedIndex()==(-1) || lista_estado.getSelectedIndex()==(-1) || campo_hora.getText().equals("") || campo_imagem.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Por favor, Preencha os campos devidamente!");
			}else {
				try {
					Produto_C.registar(campo_codigo, campo_nome, campo_marca, lista_modelo, campo_imagem, lista_estado,campo_hora.getText(), campo_preco, campo_quantidade, modelo);
					limpar();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		else if(f.getSource()==actualizar) {
			if(Login.readAdmin() == true) {
				if(campo_codigo.getText().equals("") || campo_nome.getText().equals("") || campo_marca.getText().equals("") || campo_preco.getText().equals("") || lista_modelo.getSelectedIndex()==(-1) || lista_estado.getSelectedIndex()==(-1) || campo_hora.getText().equals("") || campo_imagem.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor, indique o produto que deseja actualizar seus dados!");
				}else Produto_C.actualizar(campo_codigo, campo_nome, campo_marca, lista_modelo, campo_imagem, lista_estado,campo_hora.getText(), campo_preco, campo_quantidade, modelo);
			}else JOptionPane.showMessageDialog(null, "É necessária uma conta de Aministrador!");
		}
		else if(f.getSource()==excluir) {
			if(Login.readAdmin() == true) {
				if(campo_codigo.getText().equals("") || campo_nome.getText().equals("") || campo_marca.getText().equals("") || campo_preco.getText().equals("") || lista_modelo.getSelectedIndex()==(-1) || lista_estado.getSelectedIndex()==(-1) || campo_hora.getText().equals("") || campo_imagem.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor, indique o produto que deseja excluir!");
				}else {
					if(JOptionPane.showConfirmDialog(null,"Deseja mesmo excluir?", "Confirmacao", JOptionPane.YES_NO_OPTION)==0) {
						try {
							Produto_C.remover(campo_codigo);
							limpar();
						} catch (IOException e) {
							
						}
					}
				}
			}else JOptionPane.showMessageDialog(null, "É necessária uma conta de Aministrador!");
		}
		else if(f.getSource()==procurar) {
			if(campo_codigo.getText().equals("") && campo_nome.getText().equals("") && campo_marca.getText().equals("") && campo_preco.getText().equals("") && lista_modelo.getSelectedIndex()==(-1) && lista_estado.getSelectedIndex()==(-1) && campo_hora.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Por favor, indique o produto que deseja procurar!");
			}else Produto_C.procurar(campo_codigo,campo_nome,campo_marca,lista_modelo,lista_estado,campo_hora,modelo);
		}
		else if(f.getSource() == pesq) {
			if(pesq.isSelected()) {
				campo_codigo.setEditable(true);
				campo_codigo.setText("");
				campo_hora.setEditable(true);
			}else {
				campo_codigo.setEditable(false);
				int c = Produto_C.pegarUltimo()+1;
				campo_codigo.setText(c+"");
				campo_hora.setEditable(false);
			}
		}
		else if(f.getSource() == carregar) {
			
			fc = new JFileChooser();
			int x = fc.showOpenDialog(null);
			
			if(x == JFileChooser.APPROVE_OPTION) {
				File f1 = fc.getSelectedFile();
				campo_imagem.setText(corrigido(f1.getAbsolutePath()));
				
				ImageIcon icon = new ImageIcon(campo_imagem.getText());
	            JOptionPane.showMessageDialog(
	                    null,
	                    "",
	                    "FOTO", JOptionPane.INFORMATION_MESSAGE,
	                    icon);
	            
	            imagem.setVisible(true);
	            ver.setVisible(true);
			}
		}
		else if(f.getSource() == ver) {
			ImageIcon icon = new ImageIcon(campo_imagem.getText());
            JOptionPane.showMessageDialog(
                    null,
                    "",
                    "FOTO", JOptionPane.INFORMATION_MESSAGE,
                    icon);
		}
		else if(f.getSource() == carregar_data) {
			Estaleiro.pegarTime(campo_hora);
		}
//		else if(f.getSource() == tempo) {
//			Armazem arm;
//			for(int i =0; i<Armazem_C.arms.size(); i++) {
//				arm = (Armazem)Armazem_C.arms.get(i);
//				if(arm.getId_produto() == Integer.parseInt(campo_codigo.getText())) {
//					JOptionPane.showMessageDialog(null, "Tempo de Permanencia do produto: "+arm.getTempo_permanencia());
//					break;
//				}
//			}
//		}
		
	}
	
	//CORRIGIR PATH
	public String corrigido(String path) {
		String correctedPath = "";
		char character = ' ', character2 = path.charAt(2);
		for(int i=0; i<path.length(); i++) {
			character = path.charAt(i);
			if(character == character2) {
				correctedPath += "//";
			}else {
				correctedPath += character;
			}
		}
		
		return correctedPath;
	}
	
	//ACTUALIZAR TELA
	public void limpar() {
		campo_nome.setText("");
		campo_imagem.setText("");
		campo_preco.setText("");
		campo_hora.setText("");
		campo_hora.setEditable(false);
		campo_quantidade.setText("");
		ver.setVisible(false);
		imagem.setVisible(false);
		lista_modelo.setSelectedIndex(-1);
		lista_estado.setSelectedIndex(-1);
		int c = Produto_C.pegarUltimo()+1;
		campo_codigo.setText(c+"");
		campo_codigo.setEditable(false);
		campo_marca.setText("");
		pesq.setSelected(false);
		tempo.setVisible(false);
		Produto_C.pesquisar(modelo);
	}

}
