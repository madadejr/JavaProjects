package coding.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Reserva implements Serializable{
	int id_reserva;
	int id_bike;
	int id_ciclista;
	String hora_saida;
	
	public Reserva() {}
	
	public Reserva(int id_reserva, int id_bike, int id_ciclista, String hora_saida) {
		this.id_reserva = id_reserva;
		this.id_bike = id_bike;
		this.id_ciclista = id_ciclista;
		this.hora_saida = hora_saida;
	}

	public int getId_reserva() {
		return id_reserva;
	}

	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}

	public int getId_bike() {
		return id_bike;
	}

	public void setId_bike(int id_bike) {
		this.id_bike = id_bike;
	}

	public int getId_ciclista() {
		return id_ciclista;
	}

	public void setId_ciclista(int id_ciclista) {
		this.id_ciclista = id_ciclista;
	}

	public String getHora_saida() {
		return hora_saida;
	}

	public void setHora_saida(String hora_saida) {
		this.hora_saida = hora_saida;
	}
}
