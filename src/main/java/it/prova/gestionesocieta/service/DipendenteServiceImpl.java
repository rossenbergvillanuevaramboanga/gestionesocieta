package it.prova.gestionesocieta.service;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.repository.DipendenteRepository;


@Service
public class DipendenteServiceImpl implements DipendenteService {

	@Autowired
	private DipendenteRepository dipendenteRepository;
	
	@Autowired
	private SocietaService societaRepository;
	
	@Autowired
	private EntityManager entityManager;
	
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

	@Transactional(readOnly = true)
	public Dipendente caricaDipendentePiuAnzianoSocietaFondatePrimaDel1990() throws ParseException{
		// TODO Auto-generated method stub
		Date data = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1990");
//		return entityManager.createQuery("from Dipendente d join fetch d.societa s where s.dataFondazione <= ?1 order by d.dataAssunzione asc ",
//		          Dipendente.class).setParameter(1, data).getSingleResult();
		return dipendenteRepository.findFirst1BySocieta_DataFondazioneBeforeOrderByDataAssunzioneAsc(data);
	}

}
