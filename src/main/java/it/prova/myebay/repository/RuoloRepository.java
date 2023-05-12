package it.prova.myebay.repository;

import org.springframework.data.repository.CrudRepository;

import it.prova.myebay.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo,Long>{

	// Ruolo fyndByDescrizioneAndCodice(String descrizione,String codice);
}
