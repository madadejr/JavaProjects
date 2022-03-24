package coding.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Bike implements Serializable{

	int id_bike;
	String marca;
	String modelo;
	String imagem;
	String estado;
	String hora_entrada;
	
	public Bike() {}
	
	public Bike(int id, String marca, String modelo, String imagem, String estado, String hora_entrada) {
		this.id_bike = id;
		this.marca = marca;
		this.modelo = modelo;
		this.imagem = imagem;
		this.estado = estado;
		this.hora_entrada = hora_entrada;
	}

	public int getId_bike() {
		return id_bike;
	}

	public void setId_bike(int id_bike) {
		this.id_bike = id_bike;
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

	public String getHora_entrada() {
		return hora_entrada;
	}

	public void setHora_entrada(String hora_entrada) {
		this.hora_entrada = hora_entrada;
	}
}
