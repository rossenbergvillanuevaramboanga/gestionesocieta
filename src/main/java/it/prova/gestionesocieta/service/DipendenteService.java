package it.prova.gestionesocieta.service;

import java.util.List;

import it.prova.gestionesocieta.model.Dipendente;

public interface DipendenteService {
	
	//CRUD Operations
	//Read All
	public List<Dipendente> listAllDipendenti();
	//Read 
	public Dipendente caricaSingoloDipendente(Long id);
	//Update
	public void aggiorna(Dipendente dipendenteInstance);
	//Create
	public void inserisciNuovo(Dipendente dipendenteInstance);
	//Remove
	public void rimuovi(Dipendente dipendenteInstance);
	
	//Other methods

	//dipendente più anziano  - lavorativamente parlando – delle società fondate prima del 1990
	public Dipendente caricaDipendentePiuAnzianoSocietaFondatePrimaDel1990();

	
	

}
