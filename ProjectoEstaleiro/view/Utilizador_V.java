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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.Utilizador_C;

public class Utilizador_V implements ActionListener, MouseListener{
	//
	private JFrame tela;
	private JTextField campoId,campoUser,campoUsername;
	private JPasswordField campoPass;
	private JLabel iduser,nomeUser,password,tipouser,username,gerar;
	private ImageIcon imapagar,imeditar,icone,imgravar,imnovo,improcurar;
	private JButton cancelar, lregistar,lapagar,leditar, procurar, novo;
	private JComboBox<String> comboTipo;
	private FundoBg fundo;
	private JCheckBox pesq;
	
	private JTable tabela;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JScrollPane barra_rolagem;
	
	//PAINES
	private JPanel p2, painel_tipo, painel_botoes, painel_cancel, p;
	
	//
	public Utilizador_V()throws IOException{
		icone=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//icone_tela_login.png");
		fundo=new FundoBg("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//login_fundo.jpg");
		tela=new JFrame();
		tela.setTitle("Utilizadores");
		tela.setSize(900,500);
		tela.setResizable(false);
		tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tela.setLocationRelativeTo(null);
		tela.setIconImage(icone.getImage());
		tela.add(fundo);
		
		//INSTANCIAS
		//::> JLabel
		tipouser=new JLabel("Tipo de utilizador: ");
		iduser = new JLabel("Codigo: ");
		nomeUser = new JLabel("Nome: ");
		password = new JLabel("Senha: ");
		username = new JLabel("Utilizador: ");
		gerar = new JLabel("gerar");
		gerar.setFont(new Font("Agency FB", Font.BOLD, 18));
		gerar.setForeground(Color.red);
		gerar.setToolTipText("Gerar senha!");
		
		//
		campoId = new JTextField(5);
		campoId.setEditable(false);
		campoUser = new JTextField(15);
		campoUsername = new JTextField(15);
		campoPass = new JPasswordField(10);
		campoPass.setEditable(false);
		
		int c = Utilizador_C.pegarUltimo()+1;
		campoId.setText(c+"");
		
		//::>JTable
		criaJTable();
		
		//::>JSCROLLPANE
	    barra_rolagem=new JScrollPane(tabela);
		
		//::>ImageIcon
	    imapagar=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//deletar.png");
		imeditar=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//update.png");
		imgravar=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//gravar.png");
		improcurar=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//pesquisa.png");
		imnovo=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//novo.png");
		//
		lregistar=new JButton(imgravar);
		lregistar.setToolTipText("Adicinar Utilizador");
		lapagar=new JButton(imapagar);
		lapagar.setToolTipText("Remover Utilizador");
		leditar=new JButton(imeditar);
		leditar.setToolTipText("Actualizar dados do Utilizador");
		procurar=new JButton(improcurar);
		procurar.setToolTipText("Procurar Utilizador");
		novo=new JButton(imnovo);
		novo.setToolTipText("Limpar");
		
	
		
		//::>JPanel
		painel_tipo=new JPanel();
		painel_botoes = new JPanel();
		painel_cancel = new JPanel();
		p = new JPanel();
		p2 = new JPanel();
		
		//::>JCheckBox
  		pesq = new JCheckBox("Pesquisa");
		
		
		//::>JButton
		cancelar=new JButton("Fechar");
		
		//::>JComboBox
		comboTipo=new JComboBox<>();
		comboTipo.addItem("Administrador");
		comboTipo.addItem("Genérico");
		comboTipo.setSelectedIndex(-1);
		
		//Painel Tipo
		p.setLayout(new BorderLayout());
		painel_tipo.setLayout(new FlowLayout(FlowLayout.LEFT));
		painel_tipo.add(iduser);
		painel_tipo.add(campoId);
		painel_tipo.add(new JLabel("   "));
		painel_tipo.add(nomeUser);
		painel_tipo.add(campoUser);
		painel_tipo.add(new JLabel("   "));
		painel_tipo.add(username);
		painel_tipo.add(campoUsername);
		painel_tipo.add(new JLabel("     "));
		painel_tipo.add(password);
		painel_tipo.add(campoPass);
		painel_tipo.add(gerar);
		painel_tipo.add(new JLabel("    "));
		painel_tipo.add(tipouser);
		painel_tipo.add(comboTipo);
		
		p.add("North", new JLabel(" "));
		p.add("West",new JLabel("         "));
		p.add("Center",painel_tipo);
		p.add("East", new JLabel("           "));
		p.add("South", painel_botoes);
		
		//P2
		p2.setLayout(new BorderLayout());
		p2.add("Center", p);
		p2.add("South", barra_rolagem);
		
		//Painel Botoes
		painel_botoes.setLayout(new FlowLayout(FlowLayout.RIGHT,5,5));

		painel_botoes.add(pesq);
		painel_botoes.add(novo);
		painel_botoes.add(lregistar);
		painel_botoes.add(procurar);
		painel_botoes.add(leditar);
		painel_botoes.add(lapagar);
		
		//Painel Cancelar
		painel_cancel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		painel_cancel.add(cancelar);
		
		//TELA
		fundo.setLayout(new BorderLayout());
		fundo.add("Center", p2);
		fundo.add("South", painel_cancel);
		
		//::>ACCOES
	    cancelar.addActionListener(this);
	    novo.addActionListener(this);
	    lregistar.addActionListener(this);
	    leditar.addActionListener(this);
	    lapagar.addActionListener(this);
	    procurar.addActionListener(this);
	    gerar.addMouseListener(this);
	    pesq.addActionListener(this);
		
	    
		//VISIBILIDADE DA TELA
		tela.setVisible(true);
	}
	
	
	private void criaJTable() {
        tabela = new JTable(modelo);
        tabela.setPreferredScrollableViewportSize(new Dimension(50,250));
        modelo.addColumn("Codigo");
        modelo.addColumn("Nome");
        modelo.addColumn("Tipo");
        modelo.addColumn("Username");
        tabela.getColumnModel().getColumn(0).setPreferredWidth(8);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(20);
        tabela.setRowSelectionAllowed(true);
        
        tabela.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		int codig = (Integer)tabela.getModel().getValueAt(tabela.getSelectedRow(), 0);
        		String nome = (String)tabela.getModel().getValueAt(tabela.getSelectedRow(), 1);
        		String tipo = (String)tabela.getModel().getValueAt(tabela.getSelectedRow(), 2);
        		String username = (String)tabela.getModel().getValueAt(tabela.getSelectedRow(), 3);
        		
        		campoId.setText(codig+"");
        		campoUser.setText(nome);
        		campoUsername.setText(username);
        		comboTipo.setSelectedItem(tipo);
        		campoPass.setText(Utilizador_C.pegarPasse(codig));
        	}
        });
        
        Utilizador_C.pesquisar(modelo);
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
		Utilizador_C.recuperarUsers();
		new Utilizador_V();
	}


	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent f) {
		if(f.getSource()==cancelar){
			try {
				if(Login.readAdmin() == false) {
					
					new Login();
				}
				tela.dispose();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(f.getSource() == novo) {
			limpar();
		}
		else if(f.getSource()==lregistar) {
			
			if(campoId.getText().equals("") || campoUser.getText().equals("") || campoPass.getText().equals("") || comboTipo.getSelectedIndex()==(-1)) {
				JOptionPane.showMessageDialog(null, "Por favor, Preencha os campos devidamente!");
				
			}else {
				try {
					if(Utilizador_C.users.size() == 0 && comboTipo.getSelectedIndex()==1) {
						JOptionPane.showMessageDialog(null, "Por favor, crie uma conta Admin!");
					}
					else {
						Utilizador_C.registar(campoId, campoUser, comboTipo, campoUsername, campoPass, modelo);
						limpar();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		else if(f.getSource()==procurar) {
			if(campoId.getText().equals("") && campoUser.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Por favor, indique o utilizador que deseja procurar!");
			}else Utilizador_C.procurar(campoId,campoUser,modelo);
		}
		else if(f.getSource()==leditar) {
			if(campoId.getText().equals("") || campoUser.getText().equals("") || campoPass.getText().equals("") || comboTipo.getSelectedIndex()==(-1)) 
				JOptionPane.showMessageDialog(null, "Por favor, indique o utilizador que deseja actualizar seus dados!");
			else Utilizador_C.actualizar(campoId, campoUser, comboTipo, campoUsername, campoPass, modelo);
		}
		else if(f.getSource()==lapagar) {
			
			if(campoId.getText().equals("") || campoUser.getText().equals("") || campoPass.getText().equals("") || comboTipo.getSelectedIndex()==(-1)) {
				JOptionPane.showMessageDialog(null, "Por favor, indique o utilizador que deseja excluir!");
			}else {
				if(JOptionPane.showConfirmDialog(null,"Deseja mesmo excluir?", "Confirmação", JOptionPane.YES_NO_OPTION)==0) {
					try {
						Utilizador_C.remover(campoId, campoUsername);
						limpar(); 
					} catch (IOException e) {
						
					}
				}
			}
		}
		else if(f.getSource() == pesq) {
			if(pesq.isSelected()) {
				campoId.setEditable(true);
				campoId.setText("");
			}else {
				campoId.setEditable(false);
				limpar();
			}
		}
		
	}
	
	//ACTUALIZAR TELA
	public void limpar() {
		campoId.setText("");
		campoUser.setText("");
		campoPass.setText("");
		campoUsername.setText("");
		comboTipo.setSelectedIndex(-1);
		int c = Utilizador_C.pegarUltimo()+1;
		Utilizador_C.pesquisar(modelo);
		campoId.setText(c+"");
		campoId.setEditable(false);
		pesq.setSelected(false);
	}


	@Override
	public void mouseClicked(MouseEvent a) {
		if(a.getSource() == gerar) {
			Utilizador_C.gerarCodigo(campoPass);
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

}

