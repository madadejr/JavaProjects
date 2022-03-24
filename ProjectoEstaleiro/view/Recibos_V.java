package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.Armazem_C;
import controller.Recibo_C;

public class Recibos_V implements ActionListener{
	private JFrame tela;
	private JPanel condicao_registo,processamento_venda;
	//private JComboBox comboClientes;
	private JTable tabela;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JButton limpar;
	private FundoBg fundo;
	private ImageIcon icone;
	private JScrollPane barra_rolagem;
	private JTextField campo_recibo, campo_data_nome;
	
	public Recibos_V() throws IOException{
		tela=new JFrame();
		fundo=new FundoBg("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//menu_icone.jpg");
		icone=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//icone_tela_login.png");
		tela.setTitle("VENDA");
		tela.setSize(950,600);
		tela.setIconImage(icone.getImage());
		tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tela.setLocationRelativeTo(null);
		tela.setResizable(false);
		tela.add(fundo);
		
		//INSTANCIAS
		//::>JPanel
		condicao_registo=new JPanel();
		processamento_venda=new JPanel();
		
		//::>JTextField
		campo_recibo = new JTextField(5);
		campo_recibo.setToolTipText("Insira o ID do recibo");
		campo_data_nome = new JTextField(10);
		campo_data_nome.setToolTipText("Insira o nome do produto ou a data do recibo (dd/MM/yyyy)");
	
		
		//::>JTable
		criaJTable();
		
		//::>JButton
		limpar = new JButton(new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//deletar.png"));
		limpar.setToolTipText("Remover");
		
		//::>JSCROLLPANE
	    barra_rolagem=new JScrollPane(tabela);
	    
	   
		//Painel Recibos
		condicao_registo.setLayout(new FlowLayout(FlowLayout.LEFT));
		condicao_registo.add(campo_recibo); condicao_registo.add(campo_data_nome); condicao_registo.add(limpar);
		condicao_registo.setBorder(BorderFactory.createTitledBorder("Recibo"));
		
		//Painel Registos
		processamento_venda.setLayout(new BorderLayout(10,10));
		processamento_venda.add("Center",barra_rolagem);
						
		//Tela
		fundo.setLayout(new FlowLayout(FlowLayout.LEFT));
		fundo.add(condicao_registo);
		fundo.add(processamento_venda);
		
		
		//ACCOES
		limpar.addActionListener(this);
		
		campo_recibo.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				Recibo_C.procurar(campo_recibo, modelo);
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
		campo_data_nome.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				Recibo_C.procurarDataNome(campo_data_nome, modelo);
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		//VISIBILIDADE
		tela.setVisible(true);
		
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
	
	
	//TESTE DA TELA
	public static void main (String args[]) throws IOException{
		Recibo_C.recuperarRecs();
		Armazem_C.recuperarArms();
		new Recibos_V();
	}


//rec.getId_recibo(), rec.getId_produto(), rec.getNome_produto(), rec.getValor_pago(), rec.getData_pagamento(), rec.getNome_user(), rec.getNome_cliente(), rec.getQuantidade(), tempo

	//CRIAR TABELA
	private void criaJTable() {
	    tabela = new JTable(modelo);
	    tabela.setPreferredScrollableViewportSize(new Dimension(920,450));
	    modelo.addColumn("ID-Recibo");
	    modelo.addColumn("ID-Produto");
	    modelo.addColumn("Nome-Produto");
	    modelo.addColumn("Valor Pago");
	    modelo.addColumn("Data de Pagamento");
	    modelo.addColumn("Nome de Utilizador");
	    modelo.addColumn("Nome de Cliente");
	    modelo.addColumn("Qty. do Produto");
	    modelo.addColumn("Permanencia");
	    tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
	    tabela.getColumnModel().getColumn(1).setPreferredWidth(5);
	    tabela.getColumnModel().getColumn(2).setPreferredWidth(10);
	    tabela.getColumnModel().getColumn(3).setPreferredWidth(10);
	    tabela.getColumnModel().getColumn(4).setPreferredWidth(20);
	    tabela.getColumnModel().getColumn(5).setPreferredWidth(50);
	    tabela.getColumnModel().getColumn(6).setPreferredWidth(50);
	    tabela.getColumnModel().getColumn(7).setPreferredWidth(5);
	    tabela.getColumnModel().getColumn(8).setPreferredWidth(5);
	    tabela.setRowSelectionAllowed(true);
	    
	    tabela.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {

        	    int codig = (Integer)tabela.getModel().getValueAt(tabela.getSelectedRow(), 0);
        	    campo_recibo.setText(codig+"");
        	}
        });
	    
	    Recibo_C.pesquisar(modelo);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == limpar) {
			if(Login.readAdmin() == true) {
				if(campo_recibo.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Por favor, indique o recibo que deseja remover!");
				else {
					if(JOptionPane.showConfirmDialog(null,"Tem certeza?", "Confirmacão", JOptionPane.YES_NO_OPTION)==0) {
					
						try {
							Recibo_C.remover(campo_recibo);
							Recibo_C.pesquisar(modelo);
							campo_recibo.setText("");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}else JOptionPane.showMessageDialog(null, "É necessária uma conta de Aministrador!");
		}
		
	}
}

