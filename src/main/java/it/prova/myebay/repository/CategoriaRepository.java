package it.prova.myebay.repository;


import org.springframework.data.repository.CrudRepository;

import it.prova.myebay.model.Categoria;

public interface CategoriaRepository  extends CrudRepository<Categoria, Long>{

	Categoria findByDescrizioneAndCodice(String descrizione, String codice);
	
	Categoria findByDescrizione(String descrizione);
}