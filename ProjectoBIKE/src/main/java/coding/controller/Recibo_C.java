package coding.controller;

import java.io.IOException;
import java.util.Vector;

import coding.model.Ciclista;
import coding.model.Parque;
import coding.model.Recibo;
import coding.model.Reserva;

public class Recibo_C {
	public Vector<Recibo> recibos = new Vector<Recibo>();
	public Recibo ccl; 
	
	Parque parque = new Parque();
	Reserva_C res = new Reserva_C();
	
	public Recibo_C() {}
	
	//COMPORTAMENTOS
	//VERIFICACAO DA EXISTENCIA DO RECIBOS
	public boolean existe(int id) {
		res.recuperarReservas();
		Reserva r;
		for(int i = 0; i < res.reservs.size(); i++) {
			r = (Reserva)res.reservs.get(i);
			if(r.getId_reserva() == id) {
				return true;
			}
		}
		
		return false;
	}
	
	//PARA CANCELAMENTO
	public boolean existeCancel(int id) {
		recuperarRecibos();
		for(int i = 0; i < recibos.size(); i++) {
			ccl = (Recibo)recibos.get(i);
			if(ccl.getId_recibo() == id) {
				return true;
			}
		}
		
		return false;
	}
	
	//PEGAR BIKE
	public int idBikes(int id) {
		for(int i = 0; i < recibos.size(); i++) {
			ccl = (Recibo)recibos.get(i);
			if(ccl.getId_recibo() == id) {
				return ccl.getId_bike();
			}
		}
		
		return 0;
	}
	
	//PEGAR CICLISTA
	public String ciclista(int id) {
		for(int i = 0; i < recibos.size(); i++) {
			ccl = (Recibo)recibos.get(i);
			if(ccl.getId_recibo() == id) {
				return ccl.getNome_ciclista();
			}
		}
		
		return null;
	}
	
	//PEGAR DATA
	public String data(int id) {
		for(int i = 0; i < recibos.size(); i++) {
			ccl = (Recibo)recibos.get(i);
			if(ccl.getId_recibo() == id) {
				return ccl.getHora_saida();
			}
		}
		
		return null;
	}
	
	//PEGAR NOME DE CICLISTA
	public String nomeCiclista(int id) {
		Ciclista_C ciclis = new Ciclista_C();
		Ciclista c;
		ciclis.recuperarCiclistas();
		res.recuperarReservas();
		Reserva r;
		int id_ciclista = 0;
		
		for(int i = 0; i < res.reservs.size(); i++) {
			r = (Reserva)res.reservs.get(i);
			if(r.getId_reserva() == id) {
				id_ciclista = r.getId_ciclista();
				break;
			}
		}
		
		for(int i = 0; i < ciclis.ciclis.size(); i++) {
			c = (Ciclista)ciclis.ciclis.get(i);
			if(c.getId_ciclista() == id_ciclista) {
				return c.getNome();
			}
		}
		return null;
	}
	
	//PEGAR ID DO CICLISTA
	public int idCiclista(String nome) {
		Ciclista_C ciclis = new Ciclista_C();
		Ciclista c;
		ciclis.recuperarCiclistas();
		
		for(int i = 0; i < ciclis.ciclis.size(); i++) {
			c = (Ciclista)ciclis.ciclis.get(i);
			if(c.getNome().equals(nome)) {
				return c.getId_ciclista();
			}
		}
		return 0;
	}
	
	//PEGAR HORA DE SAIDA
	public String horaSaida(int reserv) {
		res.recuperarReservas();
		Reserva r;
		for(int i = 0; i < res.reservs.size(); i++) {
			r = (Reserva)res.reservs.get(i);
			if(r.getId_reserva() == reserv) {
				return r.getHora_saida();
			}
		}
		
		return null;
	}
	
	//PEGAR ID DA BIKE
	public int idBike(int reserv) {
		res.recuperarReservas();
		Reserva r;
		for(int i = 0; i < res.reservs.size(); i++) {
			r = (Reserva)res.reservs.get(i);
			if(r.getId_reserva() == reserv) {
				return r.getId_bike();
			}
		}
		
		return 0;
	}
	
	//REGISTAR RECIBO int id_recibo, int id_bike, String nome_ciclista, String hora_saida, String hora_entrada, int tempo_permanencia, double custo
	public void registar(int id_reserva, String hora_entrada, int tempo, double custo){		
		
		try {
			Recibo ccl = new Recibo(id_reserva,idBike(id_reserva),nomeCiclista(id_reserva),horaSaida(id_reserva),hora_entrada,tempo,custo);
			recibos.add(ccl);
			parque.escreverFicheiro(recibos, "C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//recibos.arq");
				
		}catch(Exception e) {
			
		}
	}
	
	//RECUPERAR RECIBOS
	@SuppressWarnings("unchecked")
	public void recuperarRecibos() {
		try {
			recibos.addAll(parque.lerFicheiro("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//recibos.arq"));
		}catch(Exception e) {
//			System.out.println("Nenhuma reserva no sistema!");
		}
	}
	
	//REMOVER RECIBO
	public void remover(int id) throws IOException {
		
		for(int i = 0; i < recibos.size(); i++) {
			Recibo u = (Recibo)recibos.get(i);
			if(u.getId_recibo() == id) {
				recibos.removeElementAt(i);
			}
		}
		parque.escreverFicheiro(recibos, "C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//recibos.arq");
		
	}
}
