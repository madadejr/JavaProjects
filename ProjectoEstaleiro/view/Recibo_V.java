package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Recibo_C;
import model.Recibo;

public class Recibo_V implements ActionListener{
	private JFrame tela;
	private JLabel nomeCliente,pago,recibo;
	private ImageIcon icone;
	private FundoBg fundo;
	private JPanel painelTopo,painelSul,painelNome,painelBaixo,painelCentral, painelPagar, painelEndereco, painelCont, painelDat, painelEnd, painelClt, painelVd;
	private JButton botao;

	private JScrollPane barra_rolagem;
	private JTable tabela;
	private DefaultTableModel modelo = new DefaultTableModel();
	private static Vector<Recibo> recs = Recibo_C.recs;
	
	public Recibo_V(int id_recibo) throws IOException{
		tela=new JFrame();
		fundo=new FundoBg("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//menu_icone.jpg");
		icone=new ImageIcon("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoEstaleiro//view//imagens//icone_tela_login.png");
		tela.setTitle("Recibo");
		tela.setSize(450,600);
		tela.setUndecorated(false);
		tela.setIconImage(icone.getImage());
		tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tela.setLocationRelativeTo(null);
		tela.setResizable(false);
		tela.add(fundo);
		
		Recibo rec=null;
		for(int i =0; i<recs.size(); i++) {
			rec = (Recibo)recs.get(i);
			if(rec.getId_recibo() == id_recibo) {
				break;
			}
		}
		
		//INSTANCIAS
		//::>JLabel
		recibo=new JLabel("M Investimentos, Lda.");
		recibo.setFont(new Font("Times New Roman",Font.BOLD,30));
		recibo.setForeground(new Color(0,0,0));
	
		nomeCliente=new JLabel(rec.getNome_cliente());
		pago=new JLabel(Venda_V.valorPagar.getText()+" MZN");
		pago.setFont(new Font("Times New Roman",Font.BOLD,20));
		
		//TABELA
		criaJTable(id_recibo);
		
		//SCROLLPANE
		barra_rolagem = new JScrollPane(tabela);
		
		//Botao
		botao = new JButton("OK");
		
		//::>PAINEL TOPO
		painelTopo=new JPanel();
		painelTopo.setLayout(new FlowLayout(FlowLayout.CENTER));
		painelTopo.add(recibo);
		
		//::>PAINEL PAGAR
		painelPagar = new JPanel();
		painelPagar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		painelPagar.add(pago);
		
		//::>PAINEL NOME
		painelNome=new JPanel();
		painelClt = new JPanel();
		painelVd = new JPanel();
		painelClt.setLayout(new FlowLayout(FlowLayout.RIGHT));
		painelVd.setLayout(new FlowLayout(FlowLayout.LEFT));
		painelNome.setLayout(new GridLayout(1,2));
		painelClt.add(new JLabel("Cliente: "));
		painelClt.add(nomeCliente);
		painelVd.add(new JLabel("Vendedor: "));
		painelVd.add(new JLabel(rec.getNome_user()));
		painelNome.add(painelVd);
		painelNome.add(painelClt);
		
		//::>PAINEL ENDERECO
		painelEndereco = new JPanel();
		painelCont = new JPanel();
		painelDat = new JPanel();
		painelEnd = new JPanel();
		painelEndereco.setLayout(new GridLayout(3,1));
		painelCont.setLayout(new FlowLayout(FlowLayout.CENTER));
		painelDat.setLayout(new FlowLayout(FlowLayout.CENTER));
		painelEnd.setLayout(new FlowLayout(FlowLayout.CENTER));
		painelCont.add(new JLabel("Contacto: minvestimentos@info.co.mz"));
		painelEnd.add(new JLabel("Rua da Clareza, nr 1150, Maputo"));
		painelDat.add(new JLabel(Venda_V.pegarData()));
		painelEndereco.add(painelCont);
		painelEndereco.add(painelEnd);
		painelEndereco.add(painelDat);
		
		//::>PainelSul
		painelSul=new JPanel();
		painelSul.setLayout(new BorderLayout(5,5));
		painelSul.add("North",painelPagar);
		painelSul.add("Center",painelNome);
		painelSul.add("South", painelEndereco);
		
		//::>PainelCentral
		painelCentral=new JPanel();
		painelCentral.setLayout(new BorderLayout(5,5));
		painelCentral.add("Center", barra_rolagem);
		painelCentral.add("South", painelSul);
		
		//::>PainelBaixo
		painelBaixo=new JPanel();
		painelBaixo.setLayout(new FlowLayout(FlowLayout.CENTER));
		painelBaixo.add(botao);
		
		//ACCOES
		botao.addActionListener(this);
		
		//TELA
		fundo.setLayout(new BorderLayout());
		fundo.add("Center",painelCentral);
		fundo.add("North", painelTopo);
		fundo.add("South", painelBaixo);
		
		
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
	
	//CRIACAO DA TABELA COM MODELO DEFAULT
	private void criaJTable(int id_recibo) {
        tabela = new JTable(modelo);
        tabela.setPreferredScrollableViewportSize(new Dimension(420,200));
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Quantidade");
        modelo.addColumn("Custo (MZN)");
        tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(10);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(20);
        tabela.setEnabled(false);
        
        Recibo_C.pegarRecibo(id_recibo, modelo);
    }
	
	
	//
	public static void main(String args[]) throws IOException{
		new Recibo_V(1);
	}


	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == botao) {
			tela.dispose();
		}
	}

}
