package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import controller.Armazem_C;
import controller.Produto_C;
import controller.Recibo_C;
import controller.Utilizador_C;

public class Loading extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JProgressBar barra;
	private JPanel p,fundo;
	private ImageIcon icone;
	private JLabel lbl, lbl2;

	public Loading() throws IOException {
		this.setTitle("Carregamento do Sistema");
		this.setUndecorated(true);
		this.setSize(400, 250);
		this.setLocationRelativeTo(null);
		icone=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//icone_tela_login.png");
		fundo=new FundoBg("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//login_fundo.jpg");
		fundo.setLayout(new BorderLayout(0,10));
		this.setIconImage(icone.getImage());
		this.add(fundo);
	
		p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		barra = new JProgressBar();
		barra.setForeground(new Color(99,184,237));
		barra.setBorder(BorderFactory.createLineBorder(Color.GRAY.brighter()));
		barra.setStringPainted(true);
		
		lbl = new JLabel("M Investimentos, Lda.");
		lbl.setFont(new Font("Agency FB",Font.BOLD,32));
		lbl.setForeground(new Color(0,0,0));
		lbl2 = new JLabel("Carregando o sistema.");
		lbl2.setFont(new Font("Times New Roman",Font.BOLD,12));
		
		p.add(barra);
		p.add(lbl2);
		
		fundo.add("South",p);
		fundo.add("East", lbl);
		
		this.setVisible(true);
		
		new Thread(new Correr()).start();
	}
		
	public class FundoBg extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
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
	
	public class Correr implements Runnable{
		public void run() {
			for(int i = 0; i <= 100; i++) {
				barra.setValue(i);
				barra.repaint();
				
				if(i%2 !=0) {
					lbl2.setText("Carregando o sistema...");
				}else lbl2.setText("Carregando o sistema..");
					
				try {
					Thread.sleep(50);
				}catch(Exception e) {
					
				}
				
				if(i == 100) {
					dispose();
					try {
						new Login();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
	public static void main(String args[]) throws IOException {
		
		//CARREGAR VECTORES
		Utilizador_C.recuperarUsers();
		Produto_C.recuperarProds();
		Armazem_C.recuperarArms();
		Recibo_C.recuperarRecs();
		
		new Loading();
	}
}

