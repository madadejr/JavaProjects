package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Recibo implements Serializable{

	private int id_recibo;
	private int id_produto;
	private String nome_produto;
	private double valor_pago;
	private String data_pagamento;
	private String nome_user;
	private String nome_cliente;
	private int quantidade;
	
	public Recibo(int id_recibo, int id_produto, String nome_produto, double valor_pago, String data_pagamento, String nome_user, String nome_cliente, int quantidade) {
		// TODO Auto-generated constructor stub
		this.id_produto = id_produto;
		this.id_recibo = id_recibo;
		this.nome_produto = nome_produto;
		this.nome_cliente = nome_cliente;
		this.nome_user = nome_user;
		this.data_pagamento = data_pagamento;
		this.valor_pago = valor_pago;
		this.setQuantidade(quantidade);
	}

	public int getId_recibo() {
		return id_recibo;
	}

	public void setId_recibo(int id_recibo) {
		this.id_recibo = id_recibo;
	}

	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}

	public String getNome_produto() {
		return nome_produto;
	}

	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}

	public double getValor_pago() {
		return valor_pago;
	}

	public void setValor_pago(double valor_pago) {
		this.valor_pago = valor_pago;
	}

	public String getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(String data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

	public String getNome_user() {
		return nome_user;
	}

	public void setNome_user(String nome_user) {
		this.nome_user = nome_user;
	}

	public String getNome_cliente() {
		return nome_cliente;
	}

	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}
