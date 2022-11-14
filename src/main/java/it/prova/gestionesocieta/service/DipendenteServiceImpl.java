package it.prova.gestionesocieta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.repository.DipendenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService {

	@Autowired
	private DipendenteRepository dipendenteRepository;
	
	@Transactional(readOnly = true)
	public List<Dipendente> listAllDipendenti() {
		// TODO Auto-generated method stub
		return (List<Dipendente>) dipendenteRepository.findAll() ;
	}

	@Transactional(readOnly = true)
	public Dipendente caricaSingoloDipendente(Long id) {
		// TODO Auto-generated method stub
		return dipendenteRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Dipendente dipendenteInstance) {
		// TODO Auto-generated method stub
		dipendenteRepository.save(dipendenteInstance);
		
	}

	@Transactional
	public void inserisciNuovo(Dipendente dipendenteInstance) {
		// TODO Auto-generated method stub
		dipendenteRepository.save(dipendenteInstance);
		
	}

	@Transactional
	public void rimuovi(Dipendente dipendenteInstance) {
		// TODO Auto-generated method stub
		dipendenteRepository.delete(dipendenteInstance);
		
	}

	@Override
	public Dipendente caricaDipendentePiuAnzianoSocietaFondatePrimaDel1990() {
		// TODO Auto-generated method stub
		return null;
	}

}
