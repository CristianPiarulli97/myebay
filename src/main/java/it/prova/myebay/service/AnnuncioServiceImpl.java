package it.prova.myebay.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.repository.AnnuncioRepository;
@Service
public class AnnuncioServiceImpl implements AnnuncioService {

	@Autowired
	private AnnuncioRepository repository;
	
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
		repository.save(annuncioInstance);
		
	}

	@Transactional
	public void inserisciNuovo(Annuncio annuncioInstance) {
		repository.save(annuncioInstance);		
	}

	@Transactional
	public void rimuovi(Long idAnnuncioToDelete) {
		repository.deleteById(idAnnuncioToDelete);;
		
	}

	
	@Transactional(readOnly = true)
	public List<Annuncio> findByExample(Annuncio example) {
		// da implementare
				return this.listAllElements();
	}

	

}
