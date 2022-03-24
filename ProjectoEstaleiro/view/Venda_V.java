package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.Armazem_C;
import controller.Produto_C;
import controller.Recibo_C;
import controller.Utilizador_C;
import model.Produto;
import model.Utilizador;

public class Venda_V implements ActionListener{
	private JFrame tela;
	private JTextField liquido,campo_produto;
	private JPanel condicao_registo,processamento_venda,total_painel, painelProd;
	//private JComboBox comboClientes;
	public static JComboBox<String> lista_clientes;
	private JTable tabela;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JButton calcular, concluir, limpar;
	private JLabel total_liquido,total,imagemTotal;
	public static JLabel valorPagar;
	private FundoBg fundo;
	private ImageIcon icone,imtotal;
	private JScrollPane barra_rolagem;
	private JTextField campo_cliente;
	private int ident; 
	
	public Venda_V() throws IOException{
		tela=new JFrame();
		fundo=new FundoBg("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//menu_icone.jpg");
		icone=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//icone_tela_login.png");
		tela.setTitle("VENDA");
		tela.setSize(750,750);
		tela.setIconImage(icone.getImage());
		tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tela.setLocationRelativeTo(null);
		tela.setResizable(false);
		tela.add(fundo);
		
		//INSTANCIAS
		//::>JPanel
		condicao_registo=new JPanel();
		processamento_venda=new JPanel();
		total_painel=new JPanel();
		painelProd = new JPanel();
		
		//::>ImageIcon
	    imtotal=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//total.png");	
		
		//::>JTextField
		liquido=new JTextField(10);
		liquido.setEditable(false);
		liquido.setText("0.0");
		campo_produto = new JTextField(20);
		campo_cliente = new JTextField(20);
	
		
		//::>JTable
		criaJTable();
		
		//::>JButton
		concluir=new JButton("Concluir",new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//comprar.png"));
		calcular = new JButton("Calcular", new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//calc.png"));
		limpar = new JButton("Limpar", new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//novo2.png"));
		
		//::>JLabel
		total_liquido=new JLabel("Total sem IVA:      ");
		valorPagar=new JLabel("0.0 MZN ");
		valorPagar.setFont(new Font("Arial",Font.BOLD,30));
		imagemTotal=new JLabel(imtotal);
		total = new JLabel("Registado!");
		total.setForeground(Color.red);
		total.setVisible(false);
		
		//::>JSCROLLPANE
	    barra_rolagem=new JScrollPane(tabela);
	    
	   
		//Painel clientes
		condicao_registo.setLayout(new FlowLayout(FlowLayout.LEFT));
		condicao_registo.add(campo_cliente);
		condicao_registo.setBorder(BorderFactory.createTitledBorder("Cliente"));
		
		//Painel Produtos
		processamento_venda.setLayout(new BorderLayout(10,10));
		painelProd.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		painelProd.add(new JLabel("Pesquisar Produto"));
		painelProd.add(campo_produto);
		processamento_venda.add("North",painelProd);
		processamento_venda.add("Center",barra_rolagem);
		
		//Painel Total
		total_painel.setLayout(new FlowLayout(FlowLayout.LEFT));
		total_painel.add(total_liquido);
		condicao_registo.add(new JLabel("         "));
		total_painel.add(liquido);
		total_painel.add(new JLabel("                            "));
		total_painel.add(limpar);
		total_painel.add(calcular);
		total_painel.add(concluir);
		
		
		//processamento_venda.add(total_liquido);
		processamento_venda.setBorder(BorderFactory.createTitledBorder("Produtos"));
		
		//Tela
		fundo.setLayout(new FlowLayout(FlowLayout.LEFT));
		fundo.add(condicao_registo);
		fundo.add(processamento_venda);
		fundo.add(total_painel);
		fundo.add(imagemTotal);
		fundo.add(new JLabel("         "));
		fundo.add(valorPagar);
		fundo.add(new JLabel("                                                                                                          "));
		fundo.add(total);
		
		
		//ACCOES
		concluir.addActionListener(this);
		calcular.addActionListener(this);
		limpar.addActionListener(this);
		
		campo_produto.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				Produto_C.procurarProd(campo_produto, modelo);
				
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
		
		new Venda_V();
	}




	//CRIAR TABELA
	private void criaJTable() {
	    tabela = new JTable(modelo);
	    tabela.setPreferredScrollableViewportSize(new Dimension(700,350));
	    modelo.addColumn("ID");
	    modelo.addColumn("Nome");
	    modelo.addColumn("Quantidade Disponível");
	    modelo.addColumn("Preco");
	    modelo.addColumn("Quantidade da Venda");
	    modelo.addColumn("SubTotal");
	    tabela.getColumnModel().getColumn(0).setPreferredWidth(20);
	    tabela.getColumnModel().getColumn(1).setPreferredWidth(90);
	    tabela.getColumnModel().getColumn(2).setPreferredWidth(20);
	    tabela.getColumnModel().getColumn(3).setPreferredWidth(50);
	    tabela.getColumnModel().getColumn(4).setPreferredWidth(20);
	    tabela.getColumnModel().getColumn(5).setPreferredWidth(10);
	    tabela.setRowSelectionAllowed(false);
	    
	    Produto_C.pesquisarProd(modelo);
	}
	
	//CALCULAR
	public void calcular() {
		double subTotal = 0, soma = 0;
		String qty;
		int qtyP;
		double pr;
		for(int i = 0; i < tabela.getModel().getRowCount(); i++) {
			qty = (String)tabela.getModel().getValueAt(i, 4);
			pr = (Double)tabela.getModel().getValueAt(i, 3);
			if(!qty.isEmpty()) {
				qtyP = (Integer)tabela.getModel().getValueAt(i, 2);
				if(Integer.parseInt(qty) > qtyP) {
					JOptionPane.showMessageDialog(null, "A quantidade selecionada para o produto "+(String)tabela.getModel().getValueAt(i, 1)+" é elevada!");
					break;
				}else if(Integer.parseInt(qty) < 0){
					JOptionPane.showMessageDialog(null, "A quantidade selecionada para o produto "+(String)tabela.getModel().getValueAt(i, 1)+" é Negativa!");
					break;
				}else{
					subTotal = pr*(Integer.parseInt(qty));
					tabela.getModel().setValueAt(subTotal, i, 5);
					soma += subTotal;
				}
			}
		}
		
		liquido.setText(soma+"");
		valorPagar.setText(soma+(soma*0.17)+" MZN");
	}
	
	//PEGAR DATA E HORA EM TEMPO REAL
	public static String pegarData() {
		
        Date data = Calendar.getInstance().getTime();
        DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        return d.format(data);   
	               
	}
	
	//GRAVAR RECIBO
	public void gravarRecibo() {
		ident = Recibo_C.pegarUltimo()+1;
		int ident_prod=0, quanty, dias = 0;
		double subTotal=0.0;
		String nom="", qty="", us="", data=pegarData(), dias_horas="";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date d1 = null, d2 = null;
		String [] d = null;
		
		for(int k = 0; k < Utilizador_C.users.size(); k++) {
			Utilizador u = (Utilizador)Utilizador_C.users.get(k);
			if(u.getId_user() == Integer.parseInt(Login.ler())) {
				us = u.getName_user();
			}
		}
		
		for(int i = 0; i < tabela.getModel().getRowCount(); i++) {
			qty = (String)tabela.getModel().getValueAt(i, 4);
			if(!qty.isEmpty()) {
				ident_prod = (Integer)tabela.getModel().getValueAt(i, 0);
				nom = (String)tabela.getModel().getValueAt(i, 1);
				subTotal = (Double)tabela.getModel().getValueAt(i, 5);
				quanty = Integer.parseInt(qty);
				
				try {
					d1 = df.parse(data); 
					Produto p;
					Vector<Produto> prods = Produto_C.prods;
					for(int l = 0; l<prods.size(); l++) {
						p = (Produto)prods.get(l);
						
						if(p.getId() == ident_prod) {
							d = p.getHora_saida().split(" - ");
							d2 = df.parse(d[1]);
							break;
						}
					}
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
//				LocalDate begin = LocalDate.of(1990, Month.OCTOBER, 20);
//				LocalDate end = LocalDate.of(2005, Month.MAY, 23);
//				long days = ChronoUnit.DAYS.between(begin, end);
				/**
				 * divide por 1000 para converter em segundos
				 * divide por 60 para converter em minutos 
				 * divide por 60 novamente para converter em horas
				 * divide por 24 para converter em dias
				 */
				
				long dt = (d1.getTime() - d2.getTime()) + 3600000;
				dias = (int) (dt / 86400000L);
				dias_horas = dias + " dia(s)";
				if(dias < 1) {
					dias = (int) (dt / 3600000L);
					dias_horas = dias + " hora(s)";
				}
				
				
				try {
					Recibo_C.registar(ident,ident_prod,nom,subTotal,data,us,campo_cliente.getText(),quanty,modelo);
					Armazem_C.registar(ident_prod, dias_horas, ident, modelo);
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Falha!"+e.getMessage());
				}
			}
		}
		
		Recibo_C.decrementarQty(tabela);
	}
	
	//ACTUALIZAR TELA
	public void limpar() {
		campo_cliente.setText("");
		liquido.setText("0.0");
		valorPagar.setText("0.0 MZN ");
		campo_produto.setText("");
		Produto_C.pesquisarProd(modelo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == calcular) {
			try {
				calcular();
			}catch(Exception m) {
				JOptionPane.showMessageDialog(null, "Não foi possível calcular! Tente novamente.");
			}
		}
		if(e.getSource() == limpar) {
			limpar();
		}
		if(e.getSource() == concluir) {
			if(!liquido.getText().equals("0.0")) {

				if(campo_cliente.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Por favor, insira o nome do cliente!");
				}
				else{
					if(JOptionPane.showConfirmDialog(null,"Tem certeza?", "Confirmacão", JOptionPane.YES_NO_OPTION)==0) {
						try {
							gravarRecibo();
							new Recibo_V(ident);
							limpar();
						}catch(Exception k) {
							JOptionPane.showMessageDialog(null, "Não foi possível concluir! Tente novamente.");
						}
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Não é possível concluir com valor a pagar 0.0!");
			}
		}
		
	}
}

