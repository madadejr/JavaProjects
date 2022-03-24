package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Utilizador_C;
import model.Utilizador;

public class Login implements ActionListener,MouseListener,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Atributos da tela de login
	private JFrame tela;
	private JLabel user,password,gerir_users,labelgerir;
	private JTextField campo_user;
	private JPasswordField campo_pswd;
	private ImageIcon imbotao_entrar,imbotao_cancelar,icone,imusuarios;
	private FundoBg fundo;
	private JButton botao_entrar,botao_cancelar;
	private static Vector<Utilizador> usuarios = Utilizador_C.users;
	
	
	//Construtor
	public Login() throws IOException{
		tela=new JFrame();
		icone=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//icone_tela_login.png");
		fundo=new FundoBg("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//login_fundo.jpg");
		tela.setTitle("Login");
		tela.setIconImage(icone.getImage());
		tela.setSize(500,350);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setLocationRelativeTo(null);
		tela.setResizable(false);
		//ADICIONANDO IMAGEM DE FUNDO
		tela.add(fundo);
			
		//INSTANCIAR VARIAVEIS 
		//::>ImageIcon
		imbotao_entrar=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//login_icone.png");
		imbotao_cancelar=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//logout_icone.png");
		imusuarios=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//users_icone.png");
		
		//::>JLabel
		user=new JLabel("UTILIZADOR ");
		user.setFont(new Font("Agency FB",Font.BOLD,20));
		password=new JLabel("SENHA ");
		password.setFont(new Font("Agency FB",Font.BOLD,20));
		gerir_users=new JLabel("Criar Conta");
		gerir_users.setForeground(Color.blue);
		labelgerir=new JLabel(imusuarios);
		//::>JTextField
		campo_user=new JTextField(20);
		//::>JPasswordField
		campo_pswd=new JPasswordField(20);
		//::>JButton
		botao_entrar=new JButton("Entrar",imbotao_entrar);
		botao_entrar.setForeground(Color.darkGray);
		botao_cancelar=new JButton("Cancelar",imbotao_cancelar);
		botao_cancelar.setForeground(Color.darkGray);
		
		//::>ACCOES ACTIONLISTENERS
		botao_entrar.addActionListener(this);
		botao_cancelar.addActionListener(this);
		//::>ACCOES MOUSELISTENER
		gerir_users.addMouseListener(this);
		labelgerir.addMouseListener(this);
		
		
	    //TELA
		fundo.setLayout(new FlowLayout(FlowLayout.RIGHT));
		fundo.add(new JLabel("                                                                                                                                                                             "));
		fundo.add(new JLabel("                                                                                                                                                                             "));
		fundo.add(new JLabel("                                                                                                                                                                             "));
		fundo.add(new JLabel("                                                                                                                                                                             "));
		fundo.add(user);  fundo.add(campo_user);
		fundo.add(new JLabel("                                                                                                                                                                             "));
		fundo.add(password); fundo.add(campo_pswd);
		fundo.add(new JLabel("                                                                                                                                                                             "));

		fundo.add(botao_entrar);
		fundo.add(botao_cancelar);
		fundo.add(new JLabel("                                                                                                                                                                             "));
		fundo.add(labelgerir);
		fundo.add(gerir_users);
		
		if(usuarios.size() == 0) {
			botao_entrar.setEnabled(false);
		}else {
			labelgerir.setVisible(false);
			gerir_users.setVisible(false);
		}
		
		//Definir Visibilidade da tela
		tela.setVisible(true);
	}
	//CLASSE RESPONSAVEL POR TRATAR A IMAGEM DE FUNDO
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
	
	@Override
	public void actionPerformed(ActionEvent b){
		if(b.getSource()==botao_entrar){
			try {
				if(validacao()) {
					new MenuPrincipal();
					tela.setVisible(false);
				}
				else JOptionPane.showMessageDialog(null, "Credenciais não reconhecidas!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(b.getSource()==botao_cancelar){
			System.exit(0);
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent g) {
		if(g.getSource()==gerir_users||g.getSource()==labelgerir){
			if(g.getSource()==gerir_users||g.getSource()==labelgerir){
				writeAdmino(1);
				try {
					new Utilizador_V();
				} catch (IOException e) {
					e.printStackTrace();
				}
				tela.dispose();
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
	
	
	//Classe teste para a janela
	public static void main(String args[]) throws IOException, ClassNotFoundException{
		new Login();
	}
	
	//VALIDACAO DE USUARIOS
	@SuppressWarnings("deprecation")
	public boolean validacao() throws IOException {
		String nu = campo_user.getText();
		String cs = campo_pswd.getText();
		
		for(int i = 0; i < usuarios.size(); i++) {
			Utilizador usr = (Utilizador)usuarios.get(i);
			if(usr.getUsername().equalsIgnoreCase(nu) && usr.getSenha().equals(cs)) {
				escr(usr.getId_user());
				isAdmin();
				return true;
			}
		}
		
		return false;
	}
//	
	@SuppressWarnings("deprecation")
	public void isAdmin() {
		Utilizador usr;
		for(int i = 0; i < usuarios.size(); i++) {
			usr = (Utilizador)usuarios.get(i);
			if(usr.getSenha().equals(campo_pswd.getText()) && usr.getType_user().equals("Administrador") && usr.getUsername().equalsIgnoreCase(campo_user.getText())) {
				writeAdmin(true);
				break;
			}
			else if(usr.getSenha().equals(campo_pswd.getText()) && usr.getType_user().equals("Genérico") && usr.getUsername().equalsIgnoreCase(campo_user.getText())) { 
				writeAdmin(false); 
				break;
			}
		}
	}
//	
	public static void writeAdmin(boolean adm) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("admin.txt"));
			bw.append(adm+"");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeAdmino(int adm) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("admin.txt"));
			bw.append(adm+"");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	
	public static boolean readAdmin() {
		boolean adm = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader("admin.txt"));
			try {
				String line = br.readLine();
				if(line.equalsIgnoreCase("true")) {
					adm = true;
				}else if(line.equals("1")) {
					adm = false;
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
		
		return adm;
	}
//	
	public static void escr(int cod) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("userLogado.txt"));
			bw.append(cod+"");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	
	public static String ler() {
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("userLogado.txt"));
			try {
				line = br.readLine();
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
}

