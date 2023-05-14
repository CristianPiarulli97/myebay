package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.model.Annuncio;

public interface AnnuncioService {

	public List<Annuncio> listAllElements();

	public Annuncio caricaSingoloElemento(Long id);
	
	public Annuncio caricaSingoloElementoEager(Long id);

	public void aggiorna(Annuncio annuncioInstance);

	public void inserisciNuovo(Annuncio annuncioInstance);

	public void rimuovi(Long idAnnuncioToDelete);

	public List<Annuncio> findByExampleRicerca(Annuncio example);


	public Annuncio caricaSingoloElementoConCategorie(Long idAnnuncio);

	public Annuncio caricaElementoConUtente(Long id);

	List<Annuncio> gestioneAnnunci(String username);

}
