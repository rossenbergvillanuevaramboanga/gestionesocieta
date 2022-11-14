package it.prova.gestionesocieta.service;

import java.util.List;

import it.prova.gestionesocieta.model.Societa;

public interface SocietaService {
	
	//CRUD Operations
	//Read All
	public List<Societa> listAllSocieta();
	//Read
	public Societa caricaSingolaSocieta(Long id);
	//Update
	public void aggiorna(Societa societaInstance);
	//Create
	public void inserisciNuovo(Societa societaInstance);
	//Remove
	public void rimuovi(Societa societaInstance);
	
	//Other operations
	public List<Societa> findByExample(Societa example);
	
	//La lista distinta delle società in cui lavora almeno un dipendente con una RAL a partire da 30000 euro

	public List<Societa> listaSocietaConAlmenoUnDipentenConRALMaggioreA30000();
	


}
