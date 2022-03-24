package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Produto implements Serializable{

	private int id;
	private int quantidade;
	private String nome;
	private String marca;
	private String modelo;
	private String imagem;
	private String estado;
	private String hora_saida;
	private double custo;
	
	//tempo_permanecnia_armazem
	
	public Produto(int id, String nome, String marca, String modelo, String imagem, String estado, String hora_saida, double custo, int quantidade) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.modelo = modelo;
		this.estado = estado;
		this.imagem = imagem;
		this.hora_saida = hora_saida;
		this.custo = custo;
		this.quantidade = quantidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getHora_saida() {
		return hora_saida;
	}

	public void setHora_saida(String hora_saida) {
		this.hora_saida = hora_saida;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}
