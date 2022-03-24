package coding.controller;

import java.io.IOException;
import java.util.Vector;

import coding.model.Reserva;
import coding.model.Parque;

public class Reserva_C {

	public Vector<Reserva> reservs = new Vector<Reserva>();
	public Reserva ccl; 
	
	Parque parque = new Parque();
	
	public Reserva_C() {}
	
	//COMPORTAMENTOS
	//VERIFICACAO DA EXISTENCIA DO RESERVAS
	public boolean existe(int id, String data) {
		recuperarReservas();
		Reserva ccl;
		for(int i = 0; i < reservs.size(); i++) {
			ccl = (Reserva)reservs.get(i);
			if(ccl.getId_bike() == id && ccl.getHora_saida().equals(data)) {
				return true;
			}
		}
		
		return false;
	}
	
	//CANCELAMENTO
	public boolean existe(int id) {
		recuperarReservas();
		Reserva ccl;
		for(int i = 0; i < reservs.size(); i++) {
			ccl = (Reserva)reservs.get(i);
			if(ccl.getId_reserva() == id) {
				return true;
			}
		}
		
		return false;
	}
	
	//REGISTAR RESERVA int id_reserva, int id_bike, String nome_bike, String id_ciclista, String hora_saida
	public void registar(int id_reserva, int id_bike, int id_ciclista, String hora_saida){		
		
		try {
			Reserva ccl = new Reserva(id_reserva,id_bike,id_ciclista,hora_saida);
			reservs.add(ccl);
			parque.escreverFicheiro(reservs, "C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//reservas.arq");
				
		}catch(Exception e) {
			
		}
	}
	
	//RECUPERAR RESERVAS
	@SuppressWarnings("unchecked")
	public void recuperarReservas() {
		try {
			reservs.addAll(parque.lerFicheiro("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//reservas.arq"));
		}catch(Exception e) {
//			System.out.println("Nenhuma reserva no sistema!");
		}
	}
	
	//REMOVER RESERVA
	public void remover(int id) throws IOException {
		
		for(int i = 0; i < reservs.size(); i++) {
			Reserva u = (Reserva)reservs.get(i);
			if(u.getId_reserva() == id) {
				reservs.removeElementAt(i);
			}
		}
		parque.escreverFicheiro(reservs, "C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//reservas.arq");
		
	}
	
	//ACTUALIZAR RESERVA
	public void actualizar(int id_reserva, int id_bike, int id_ciclista, String hora_saida) {
		
		try {
			for(int i = 0; i < reservs.size(); i++) {
				Reserva ccl = (Reserva)reservs.get(i);
				if(ccl.getId_reserva() == id_reserva) {
					if(id_bike != 0) {
						ccl.setId_bike(id_bike);
					}
					if(id_ciclista != 0) {
						ccl.setId_ciclista(id_ciclista);
					}
					if(hora_saida != null) {
						ccl.setHora_saida(hora_saida);
					}
					reservs.setElementAt(ccl, i);
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			parque.escreverFicheiro(reservs, "C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//reservas.arq");
		} catch (IOException e) {
		}
	}
		
	//PEGAR ULTIMO ID
	public int pegarUltimo() {
		
		if(reservs.size() > 0) {
			Reserva arm = (Reserva)reservs.get(reservs.size()-1);
			return arm.getId_reserva();
		}
		return 1000;
	}
}
