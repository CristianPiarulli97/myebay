package it.prova.myebay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import it.prova.myebay.dto.AnnuncioDTO;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.AnnuncioService;

@Controller
@RequestMapping(value= "/annuncio")
public class AnnuncioController {

	@Autowired
	private AnnuncioService annuncioService;
	
	@PostMapping("/list")
	public String listAnnuncio(AnnuncioDTO annuncioExample, ModelMap model) {
		List<Annuncio> annunci = annuncioService.findByExample(annuncioExample.buildAnnuncioModel(true, true));
		model.addAttribute("annuncio_list_attribute", AnnuncioDTO.createAnnuncioDTOListFromModelList(annunci, true, false));
		return "annuncio/list";
	}
}
