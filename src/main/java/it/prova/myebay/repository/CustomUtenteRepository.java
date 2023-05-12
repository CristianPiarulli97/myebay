package it.prova.myebay.repository;

import java.util.List;

import it.prova.myebay.model.Utente;

public interface CustomUtenteRepository {

	List<Utente> findByExample(Utente example);
}
