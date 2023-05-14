package it.prova.myebay.service;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.exception.AnnuncioChiusoException;
import it.prova.myebay.exception.UtenteNonTrovatoException;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.repository.AnnuncioRepository;
@Service
public class AnnuncioServiceImpl implements AnnuncioService {

	@Autowired
	private AnnuncioRepository repository;
	
	@Autowired
	private UtenteService utenteService;
	
	@Transactional
	public List<Annuncio> listAllElements() {
		return (List<Annuncio>) repository.findAll();
	}

	@Transactional
	public Annuncio caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);

	}

	@Transactional
	public Annuncio caricaSingoloElementoEager(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public void aggiorna(Annuncio annuncioInstance) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteFromDb = utenteService.findByUsername(username);
		
		if (utenteFromDb == null)
		throw new UtenteNonTrovatoException();
		
		if (!annuncioInstance.isAperto()) {
			throw new AnnuncioChiusoException();
		} 
		annuncioInstance.setAperto(true);
		annuncioInstance.setDataCreazione(LocalDate.now());
		annuncioInstance.setUtente(utenteFromDb);
		repository.save(annuncioInstance);
		
	}

	@Transactional
	public void inserisciNuovo(Annuncio annuncioInstance) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteFromDb = utenteService.findByUsername(username);
		
		if (utenteFromDb == null)
		throw new UtenteNonTrovatoException();
		
		annuncioInstance.setUtente(utenteFromDb);
		annuncioInstance.setAperto(true);
		annuncioInstance.setDataCreazione(LocalDate.now());
		repository.save(annuncioInstance);	
	}

	@Transactional
	public void rimuovi(Long idAnnuncioToDelete) {
		repository.deleteById(idAnnuncioToDelete);;
		
	}


	@Override
	public Annuncio caricaElementoConUtente(Long id) {
		return repository.findByIdConUtente(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public List<Annuncio> findByExampleRicerca(Annuncio example) {
		return repository.findByExampleRicerca(example);
	}

	@Transactional(readOnly = true)
	public Annuncio caricaSingoloElementoConCategorie(Long id) {
		return repository.findByIdConCategorie(id).orElse(null);
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<Annuncio> gestioneAnnunci(String username) {
		
		return repository.findAllByUtente_Username(username);
	}
	

}
