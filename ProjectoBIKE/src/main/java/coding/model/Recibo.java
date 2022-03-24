package coding.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Recibo implements Serializable{
	int id_recibo;
	int id_bike;
	String nome_ciclista;
	String hora_saida;
	String hora_entrada;
	int tempo_permanencia;
	double custo;
	
	public Recibo() {}
	
	public Recibo(int id_recibo, int id_bike, String nome_ciclista, String hora_saida, String hora_entrada, int tempo_permanencia, double custo) {
		this.id_recibo = id_recibo;
		this.id_bike = id_bike;
		this.nome_ciclista = nome_ciclista;
		this.hora_saida = hora_saida;
		this.hora_entrada = hora_entrada;
		this.tempo_permanencia = tempo_permanencia;
		this.custo = custo;
	}

	public int getId_recibo() {
		return id_recibo;
	}

	public void setId_recibo(int id_recibo) {
		this.id_recibo = id_recibo;
	}

	public int getId_bike() {
		return id_bike;
	}

	public void setId_bike(int id_bike) {
		this.id_bike = id_bike;
	}
	public String getNome_ciclista() {
		return nome_ciclista;
	}

	public void setNome_ciclista(String nome_ciclista) {
		this.nome_ciclista = nome_ciclista;
	}

	public String getHora_saida() {
		return hora_saida;
	}

	public void setHora_saida(String hora_saida) {
		this.hora_saida = hora_saida;
	}

	public String getHora_entrada() {
		return hora_entrada;
	}

	public void setHora_entrada(String hora_entrada) {
		this.hora_entrada = hora_entrada;
	}

	public int getTempo_permanencia() {
		return tempo_permanencia;
	}

	public void setTempo_permanencia(int tempo_permanencia) {
		this.tempo_permanencia = tempo_permanencia;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}
}
