package it.prova.myebay.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.myebay.model.Ruolo;
import it.prova.myebay.repository.RuoloRepository;

@Service
public class RuoloServiceImpl implements RuoloService {

	@Autowired
	private RuoloRepository ruoloRepository;

	@Transactional(readOnly = true)
	public List<Ruolo> listAll() {
		return (List<Ruolo>)ruoloRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Ruolo caricaSingoloElemento(Long id) {
		return ruoloRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Ruolo ruoloInstance) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public void inserisciNuovo(Ruolo ruoloInstance) {
		ruoloRepository.save(ruoloInstance);
	}

	@Transactional
	public void rimuovi(Long idToDelete) {
		// TODO Auto-generated method stub

	}

	@Override
	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Transactional(readOnly = true)
//	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) {
//		return ruoloRepository.fyndByDescrizioneAndCodice(descrizione, codice);
//	}


}
