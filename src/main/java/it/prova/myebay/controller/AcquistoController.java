package it.prova.myebay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.AcquistoDTO;
import it.prova.myebay.exception.AnnuncioChiusoException;
import it.prova.myebay.exception.CreditoInsufficienteException;
import it.prova.myebay.exception.UtenteNonTrovatoException;
import it.prova.myebay.model.Acquisto;
import it.prova.myebay.service.AcquistoService;

@Controller
@RequestMapping("/acquisto")
public class AcquistoController {
	
	@Autowired
	private AcquistoService acquistoService;
	
	
	
	@GetMapping("/listaacquisti/{utenteInPagina}")
	public String gestioneAcquisti (@PathVariable(required = true) String utenteInPagina, Model model) {
		model.addAttribute("acquisto_list_attr",AcquistoDTO.createAcquistoDTOListFromModelList(acquistoService.gestioneAcquisti(utenteInPagina)));
		return "/acquisto/list";
	}
	
	@PostMapping("/compra")
	public String compra(Long idAnnuncio, RedirectAttributes redirectAttrs) {
		try {
			acquistoService.registraAcquisto(idAnnuncio);
			redirectAttrs.addFlashAttribute("successMessage", "Acquisto effettuato.");
		} catch (AnnuncioChiusoException e) {
			redirectAttrs.addFlashAttribute("errorMessage","Questo articolo è già stato acquistato da un altro utente.");
			return "redirect:/annuncio";
		} catch (UtenteNonTrovatoException e) {
			redirectAttrs.addFlashAttribute("infoMessage", "Prima di acquistare, effettua il login.");
			return "redirect:/login";
		} catch (CreditoInsufficienteException e) {
			redirectAttrs.addFlashAttribute("errorMessage", "Credito insufficiente.");
			return "redirect:/annuncio/show/"+String.valueOf(idAnnuncio);
		}
		
		return "redirect:/acquisto";
	}
	
	@GetMapping
	public ModelAndView listaAcquisti() {
		ModelAndView mv = new ModelAndView();
		List<Acquisto> acquisto = acquistoService.cercaPerUtente_Username();
		mv.addObject("acquisto_list_attr",AcquistoDTO.createAcquistoDTOListFromModelList(acquisto));
		mv.setViewName("acquisto/list");
		return mv;
	}
	
}