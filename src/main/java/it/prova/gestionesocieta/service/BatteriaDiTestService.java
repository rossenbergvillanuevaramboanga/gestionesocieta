package it.prova.gestionesocieta.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.exception.DeleteSocietaWithDipendentiException;
import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Societa;

@Service
public class BatteriaDiTestService {
	
	@Autowired
	private SocietaService societaService;
	
	@Autowired
	private DipendenteService dipendenteService;

	public void testInserisciNuovaSocieta() throws ParseException {
		// TODO Auto-generated method stub
		Long nowInMillisecondi = new Date().getTime();
		
		Societa nuovaSocieta = new Societa("Societa "+ nowInMillisecondi, "Via dei ", new SimpleDateFormat("DD-MM-YYYY").parse("14-11-2022"));
		if (nuovaSocieta.getId() != null)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: transient object con id valorizzato");
		// salvo
		societaService.inserisciNuovo(nuovaSocieta);
		if (nuovaSocieta.getId() == null || nuovaSocieta.getId() < 1)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");

		System.out.println("testInserisciNuovaSocieta........OK \n");
	}

	public void testFindByExampleSocieta() throws ParseException {
		// TODO Auto-generated method stub
		
		Societa exampleSocieta = new Societa();
		exampleSocieta.setDataFondazione(new SimpleDateFormat("DD-MM-YYYY").parse("14-11-2022"));
		List<Societa> societaExample = societaService.findByExample(exampleSocieta);
		

		if (societaExample.size() != 1)
			throw new RuntimeException("testFindByExampleSocieta...failed: inserimento fallito");

		System.out.println("testFindByExampleSocieta........OK \n");
	}

	public void testRemoveSocieta() throws ParseException {
		// TODO Auto-generated method stub
		//Inserisco Societa
		Long nowInMillisecondi = new Date().getTime();	
		Societa nuovaSocieta = new Societa("Societa "+ nowInMillisecondi, "Via dei ", new SimpleDateFormat("DD-MM-YYYY").parse("14-11-2022"));
		societaService.inserisciNuovo(nuovaSocieta);
		
		//Inserisco Dipendente
		Dipendente nuovoDipendente = new Dipendente("Pippo", "Baudo", new SimpleDateFormat("DD-MM-YYYY").parse("14-11-2022"), 50000);
		nuovoDipendente.setSocieta(nuovaSocieta);
		dipendenteService.inserisciNuovo(nuovoDipendente);
		
		nuovaSocieta.getDipendenti().add(nuovoDipendente);
		societaService.aggiorna(nuovaSocieta);
			
		try {
			societaService.rimuovi(nuovaSocieta);
		} catch (DeleteSocietaWithDipendentiException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		dipendenteService.rimuovi(nuovoDipendente);
		societaService.aggiorna(nuovaSocieta);
		societaService.rimuovi(nuovaSocieta);
		
		System.out.println("testRemoveSocieta........OK \n");
		
	}

	public void testInserisciDipendente() throws ParseException{
		// TODO Auto-generated method stub
		Long nowInMillisecondi = new Date().getTime();	
		Societa nuovaSocieta = new Societa("Societa "+ nowInMillisecondi, "Via dei ", new SimpleDateFormat("DD-MM-YYYY").parse("14-11-2022"));
		societaService.inserisciNuovo(nuovaSocieta);
		
		//Inserisco Dipendente
		Dipendente nuovoDipendente = new Dipendente("Pippo", "Baudo", new SimpleDateFormat("DD-MM-YYYY").parse("14-11-2022"), 50000);
		nuovoDipendente.setSocieta(nuovaSocieta);
		dipendenteService.inserisciNuovo(nuovoDipendente);
		
		nuovaSocieta.getDipendenti().add(nuovoDipendente);
		societaService.aggiorna(nuovaSocieta);
		
		if(nuovaSocieta.getId() == null) throw new RuntimeException("testInserisciDipendente...failed: inserimento fallito");
		if(nuovoDipendente.getId() == null) throw new RuntimeException("testInserisciDipendente...failed: inserimento fallito");
		
		System.out.println("testInserisciDipendente........OK \n");
		
	}

	public void testModificaDipendente() throws Exception{
		// TODO Auto-generated method stub
		Long nowInMillisecondi = new Date().getTime();	
		Societa nuovaSocieta = new Societa("Societa "+ nowInMillisecondi, "Via dei ", new SimpleDateFormat("DD-MM-YYYY").parse("14-11-2022"));
		societaService.inserisciNuovo(nuovaSocieta);
		
		//Inserisco Dipendente
		Dipendente nuovoDipendente = new Dipendente("Pippo", "Baudo", new SimpleDateFormat("DD-MM-YYYY").parse("14-11-2022"), 50000);
		nuovoDipendente.setSocieta(nuovaSocieta);
		dipendenteService.inserisciNuovo(nuovoDipendente);
		
		nuovaSocieta.getDipendenti().add(nuovoDipendente);
		societaService.aggiorna(nuovaSocieta);
		
		//Aggiornamento Dipendente
		nuovoDipendente.setRedditoAnnuoLordo(10000000);
		dipendenteService.aggiorna(nuovoDipendente);
		Long idDipendente = nuovoDipendente.getId();
		
		if(dipendenteService.caricaSingoloDipendente(idDipendente).getRedditoAnnuoLordo()!=10000000)
			throw new RuntimeException("testModificaDipendente...failed: inserimento fallito");
		
		System.out.println("testModificaDipendente........OK \n");
		
	}

	public void testListaSocietaConDipendenteRalMaggioreTrentaMila()  throws Exception{
		// TODO Auto-generated method stub
		Long nowInMillisecondi = new Date().getTime();	
		Societa nuovaSocieta = new Societa("Societa "+ nowInMillisecondi, "Via dei ", new SimpleDateFormat("DD-MM-YYYY").parse("14-11-2022"));
		societaService.inserisciNuovo(nuovaSocieta);
		
		//Inserisco Dipendente
		Dipendente nuovoDipendente = new Dipendente("Pippo", "Baudo", new SimpleDateFormat("DD-MM-YYYY").parse("14-11-2022"), 50000);
		nuovoDipendente.setSocieta(nuovaSocieta);
		dipendenteService.inserisciNuovo(nuovoDipendente);
		
		nuovaSocieta.getDipendenti().add(nuovoDipendente);
		societaService.aggiorna(nuovaSocieta);
		
		//Propper Test		
		if(societaService.listaSocietaConAlmenoUnDipentenConRALMaggioreA(30000).size()!= 3)
			throw new RuntimeException("testListaSocietaConDipendenteRalMaggioreTrentaMila...failed: inserimento fallito");
		
		System.out.println("testListaSocietaConDipendenteRalMaggioreTrentaMila........OK \n");


	}

	public void testDipendenteAnzianoSocietaPrima1990() {
		// TODO Auto-generated method stub
		
	}

}
