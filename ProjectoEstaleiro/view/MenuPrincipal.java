package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Armazem_C;
import controller.Produto_C;
import controller.Recibo_C;
import controller.Utilizador_C;

public class MenuPrincipal implements MouseListener, ActionListener{
	private JMenu menu,janela;
	private JMenuBar barra;
	
	private JFrame tela;
	private JLabel produto,utilizador,recibo,venda,sair,datetime;
	private ImageIcon imusuarios,improduto,imutilizador,imrecibo,icone,imvendas,imsair;
	private FundoBg fundo;
	private JMenuItem menu_produto,menu_utilizador,menu_recibo,janela_mudaruser,janela_fechar;
	private JPanel painel_centro, painel_cima, painel_baixo, painel_mbaixo;

	//CONSTRUTOR
	public MenuPrincipal()throws IOException{
		
		fundo=new FundoBg("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//login_fundo.jpg");
		icone=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//icone_tela_login.png");
		tela=new JFrame();
		tela.setExtendedState(JFrame.MAXIMIZED_BOTH);
		tela.setUndecorated(false);
		tela.setTitle("MENU PRINCIPAL");
		tela.setSize(1080,600);
		tela.setLocationRelativeTo(null);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setIconImage(icone.getImage());
		tela.setResizable(true);
		tela.add(fundo);
		
		//::>ImageIcon
		imsair=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//logout_icone.png");
		improduto=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//produto.jpg");
		imrecibo=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//factura.jpg");
		imvendas=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//venda.png");
		imusuarios=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//users_icone.png");
		imutilizador=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//fornecedor.jpg");
		
		//::>JLabel
		produto=new JLabel(improduto);
		produto.setToolTipText("Gerir Produtos");
		utilizador=new JLabel(imutilizador);
		utilizador.setToolTipText("Gerir Utilizadores");
		recibo=new JLabel(imrecibo);
		recibo.setToolTipText("Visualizar Reciboss");
		venda=new JLabel(imvendas);
		venda.setToolTipText("Efectuar Vendas");
		sair=new JLabel(imsair);
		
		//::>JMenuBar
		barra=new JMenuBar();
		
		//::>JMenu
		menu=new JMenu("Ficheiro");
		janela=new JMenu("Janela");
		
		//::>JMenuItem
		menu_produto=new JMenuItem("Produto", new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//produto_m.jpg"));
		menu_utilizador=new JMenuItem("Definições de Utilizadores", imusuarios);
		menu_recibo=new JMenuItem("Recibos", new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//factura_m.jpg"));
		janela_mudaruser=new JMenuItem("Mudar Utilizador",imusuarios);
		janela_fechar=new JMenuItem("Fechar",imsair);
		
		//::>ACCOES MOUSELISTENER
		produto.addMouseListener(this);
		utilizador.addMouseListener(this);
		venda.addMouseListener(this);
		recibo.addMouseListener(this);
		sair.addMouseListener(this);
		janela_mudaruser.addActionListener(this);
		janela_fechar.addActionListener(this);
		menu_produto.addActionListener(this);
		menu_utilizador.addActionListener(this);
		menu_recibo.addActionListener(this);
		
		painel_centro = new JPanel();
		painel_cima = new JPanel();
		painel_baixo = new JPanel();
		painel_mbaixo = new JPanel();
		painel_centro.setLayout(new BorderLayout());
		painel_cima.setLayout(new FlowLayout(FlowLayout.CENTER));
		painel_baixo.setLayout(new FlowLayout(FlowLayout.CENTER,70,70));
		painel_mbaixo.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//MENU
		menu.add(menu_recibo);
		
		if(Login.readAdmin() == true) {
			menu.addSeparator();
			menu.add(menu_produto);
			menu.addSeparator();
			menu.add(menu_utilizador);
		}
		
		janela.add(janela_mudaruser);
		janela.addSeparator();
		janela.add(janela_fechar);
		
		//BARRA
		barra.add(menu);
		barra.add(janela);
		
		datetime = new JLabel();
		datetime.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		
		//TELA
		painel_cima.add(utilizador);
		painel_cima.add(new JLabel("                      "));
		painel_cima.add(produto);
		painel_cima.add(new JLabel("                      "));
		
		painel_baixo.add(venda);
		painel_cima.add(new JLabel("                      "));
		painel_baixo.add(recibo);
		
		painel_centro.add("North", new JLabel(new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//login_fundo_2.jpg")));
		painel_centro.add("Center", painel_cima);
		painel_centro.add("South", painel_baixo);
		
		painel_mbaixo.add(datetime);
		fundo.setLayout(new BorderLayout(0,50));
		fundo.add("Center", painel_centro);
		fundo.add("South", painel_mbaixo);
				
		tela.setJMenuBar(barra);
		
		//PEGAR DATA E HORA EM TEMPO REAL
		Thread th = new Thread(new Runnable() {
	        public void run() {
	            while(true) { 
	                Date data = Calendar.getInstance().getTime();
	                DateFormat d = DateFormat.getDateInstance();
	                DateFormat h = DateFormat.getTimeInstance();
	                datetime.setText("DATA: "+d.format(data)+"       HORA: "+h.format(data));   
	                try {
	                    Thread.sleep(1000); 
	                } catch(InterruptedException ex){
	                }
	            }
	        }
	    }); th.start();
	
		
		//DEFINIR VISIBILIDADE
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
	

	@Override
	public void mouseClicked(MouseEvent c) {
		if(c.getSource()==produto){
			
			try {
				new Produto_V();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(c.getSource()==utilizador){
			try {
				if(Login.readAdmin() == true) {
					new Utilizador_V();
				}else JOptionPane.showMessageDialog(null, "É necessária uma conta de Aministrador!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(c.getSource()==venda){
			try {
				new Venda_V();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(c.getSource()==recibo){
			try {
				new Recibos_V();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actionPerformed(ActionEvent c) {
		//Gestao das Accoes do menu
		if(c.getSource()==janela_mudaruser){
			try {
				if(JOptionPane.showConfirmDialog(null,"Tem certeza?", "Confirmacão", JOptionPane.YES_NO_OPTION)==0) {
					tela.dispose();
					new Login();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(c.getSource()==janela_fechar){
			try {
				if(JOptionPane.showConfirmDialog(null,"Tem certeza?", "Confirmacão", JOptionPane.YES_NO_OPTION)==0) {
					System.exit(0);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(c.getSource()==menu_utilizador){
			try {
				new Utilizador_V();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(c.getSource()==menu_produto){
			try {
				new Produto_V();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(c.getSource()==menu_recibo){
			try {
				new Recibos_V();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
		
	//Classe teste para a janela
	public static void main(String args[]) throws IOException{
		Utilizador_C.recuperarUsers();
		Produto_C.recuperarProds();
		Armazem_C.recuperarArms();
		Recibo_C.recuperarRecs();
		new MenuPrincipal();
	}		

}

