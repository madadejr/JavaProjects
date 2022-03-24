package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Armazem implements Serializable{

	private String tempo_permanencia;
	private int id_produto; 
	private int id_recibo;
	
	public Armazem(int id_produto, String tempo_permanencia, int id_recibo) {
		// TODO Auto-generated constructor stub
		this.tempo_permanencia = tempo_permanencia;
		this.id_produto = id_produto;
		this.setId_recibo(id_recibo);
	}

	public String getTempo_permanencia() {
		return tempo_permanencia;
	}

	public void setTempo_permanencia(String tempo_permanencia) {
		this.tempo_permanencia = tempo_permanencia;
	}

	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}

	public int getId_recibo() {
		return id_recibo;
	}

	public void setId_recibo(int id_recibo) {
		this.id_recibo = id_recibo;
	}
	
}
