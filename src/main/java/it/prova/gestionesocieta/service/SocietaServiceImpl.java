package it.prova.gestionesocieta.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesocieta.exception.DeleteSocietaWithDipendentiException;
import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.repository.DipendenteRepository;
import it.prova.gestionesocieta.repository.SocietaRepository;

@Service
public class SocietaServiceImpl implements SocietaService {
	
	@Autowired
	private SocietaRepository societaRepository;
	
	@Autowired
	private DipendenteRepository dipendenteRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<Societa> listAllSocieta() {
		// TODO Auto-generated method stub
		return (List<Societa>) societaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Societa caricaSingolaSocieta(Long id) {
		// TODO Auto-generated method stub
		return societaRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Societa societaInstance) {
		// TODO Auto-generated method stub
		societaRepository.save(societaInstance);
		
	}

	@Transactional
	public void inserisciNuovo(Societa societaInstance) {
		// TODO Auto-generated method stub
		societaRepository.save(societaInstance);
	}

	@Transactional
	public void rimuovi(Societa societaInstance) {
		// TODO Auto-generated method stub
		if(!societaInstance.getDipendenti().isEmpty()) throw new DeleteSocietaWithDipendentiException("Societa with Dipendenti");
		societaRepository.delete(societaInstance);
	}

	@Override
	public List<Societa> findByExample(Societa example) {
		// TODO Auto-generated method stub
		String query = "select s from Societa s where s.id = s.id ";
		
		//ragioneSociale
		if (StringUtils.isNotEmpty(example.getRagioneSociale()))
			query += " and s.ragioneSociale like '%" + example.getRagioneSociale() + "%' ";
		//indirizzo
		if (StringUtils.isNotEmpty(example.getIndirizzo()))
			query += " and s.indirizzo like '%" + example.getIndirizzo() + "%' ";
		//dataFondazione
		if (example.getDataFondazione() != null)
			query += " and s.dataFondazione = '" + example.getDataFondazione().toInstant() +"' ";

		return entityManager.createQuery(query, Societa.class).getResultList();
	}

	@Override
	public List<Societa> listaSocietaConAlmenoUnDipentenConRALMaggioreA30000() {
		// TODO Auto-generated method stub
		int ral = 30000;
		return (List<Societa>) societaRepository.findAllDistinctByDipendenti_RedditoAnnuoLordoGreaterThan(ral);
	}
	
	

}
