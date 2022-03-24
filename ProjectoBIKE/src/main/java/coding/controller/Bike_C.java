package coding.controller;

import java.io.IOException;
import java.util.Vector;

import coding.model.Parque;
import coding.model.Bike;

public class Bike_C {
	public Vector<Bike> bikes = new Vector<Bike>();
	public Bike bk; 
	
	Parque parque = new Parque();
	
	//COMPORTAMENTOS
	//VERIFICACAO DA EXISTENCIA DO BIKES
	
	public boolean existe(int id) {
		recuperarBikes();
		for(int i = 0; i < bikes.size(); i++) {
			bk = (Bike)bikes.get(i);
			if(bk.getId_bike() == id) {
				return true;
			}
		}
		
		return false;
	}
	
	//REGISTAR BIKE int id, String marca, String modelo, String imagem, String estado, String hora_entrada
	public void registar(int id_bike, String marca, String modelo, String imagem, String estado, String hora_entrada){		
		
		try {
			Bike bk = new Bike(id_bike,marca,modelo,imagem,estado,hora_entrada);
			bikes.add(bk);
			parque.escreverFicheiro(bikes, "C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//bikes.arq");
				
		}catch(Exception e) {
			
		}
	}
	
	//RECUPERAR BIKES
	@SuppressWarnings("unchecked")
	public void recuperarBikes() {
		try {
			bikes.addAll(parque.lerFicheiro("C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//bikes.arq"));
		}catch(Exception e) {
			System.out.println("Nenhuma bike no sistema!");
		}
	}
	
	//REMOVER BIKE
	public void remover(int id) throws IOException {
		
		for(int i = 0; i < bikes.size(); i++) {
			Bike u = (Bike)bikes.get(i);
			if(u.getId_bike() == id) {
				bikes.removeElementAt(i);
			}
		}
		parque.escreverFicheiro(bikes, "C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//bikes.arq");
		
	}
	
	//ACTUALIZAR BIKE
	public void actualizar(int id_bike, String marca, String modelo, String imagem, String estado, String hora_entrada) {
	
		try {
			for(int i = 0; i < bikes.size(); i++) {
				Bike bk = (Bike)bikes.get(i);
				if(bk.getId_bike() == id_bike) {
					if(marca != null) {
						bk.setMarca(marca);
					}
					if(modelo != null) {
						bk.setModelo(modelo);
					}
					if(imagem != null) {
						bk.setImagem(imagem);
					}
					if(estado != null) {
						bk.setEstado(estado);
					}
					if(hora_entrada != null) {
						bk.setHora_entrada(hora_entrada);
					}
					bikes.setElementAt(bk, i);
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			parque.escreverFicheiro(bikes, "C://Users//CPC - MOISES MADADE//eclipse-workspace//ProjectoBIKE//src//main//java//coding//bikes.arq");
		} catch (IOException e) {
		}
	}
		
	//PEGAR ULTIMO ID
	public int pegarUltimo() {
		if(bikes.size() > 0) {
			Bike arm = (Bike)bikes.get(bikes.size()-1);
			return arm.getId_bike();
		}
		return 1000;
	}
	
	public String pegarDateTime() {
		return parque.pegarTime();
	}
}
