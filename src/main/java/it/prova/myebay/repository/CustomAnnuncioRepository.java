package it.prova.myebay.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.prova.myebay.model.Annuncio;

public interface CustomAnnuncioRepository {
	List<Annuncio> findByExampleRicerca (Annuncio example);
}
