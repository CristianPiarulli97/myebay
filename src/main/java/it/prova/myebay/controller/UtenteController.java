package it.prova.myebay.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.RuoloDTO;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.RuoloService;
import it.prova.myebay.service.UtenteService;
import it.prova.myebay.validation.ValidationNoPassword;

@Controller
@RequestMapping(value = "/utente")
public class UtenteController {

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private RuoloService ruoloService;

	@GetMapping
	public ModelAndView listAllUtenti() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("utente_list_attribute",
				UtenteDTO.createUtenteDTOListFromModelList(utenteService.listAll(), false));
		mv.setViewName("utente/list");
		return mv;
	}

	@GetMapping("/search")
	public String searchUtente(Model model) {
		model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
		model.addAttribute("search_utente_attr", new UtenteDTO());
		return "utente/search";
	}

	@PostMapping("/list")
	public String listUtenti(UtenteDTO utenteExample, ModelMap model) {
		model.addAttribute("utente_list_attribute", UtenteDTO.createUtenteDTOListFromModelList(
				utenteService.findByExample(utenteExample.buildUtenteModel(true)), true));
		return "utente/list";
	}

	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
		model.addAttribute("insert_utente_attr", new UtenteDTO());
		return "utente/insert";
	}

	@PostMapping("/save")
	public String save(
			@Validated({ ValidationNoPassword.class }) @ModelAttribute("insert_utente_attr") UtenteDTO utenteDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		if (!result.hasFieldErrors("password") && !utenteDTO.getPassword().equals(utenteDTO.getConfermaPassword()))
			result.rejectValue("confermaPassword", "password.diverse");

		if (result.hasErrors()) {
			model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
			return "utente/insert";
		}
		utenteService.inserisciNuovo(utenteDTO.buildUtenteModel(true));

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/utente";
	}

	@GetMapping("/edit/{idUtente}")
	public String edit(@PathVariable(required = true) Long idUtente, Model model) {
		Utente utenteModel = utenteService.caricaSingoloUtenteConRuoli(idUtente);
		model.addAttribute("edit_utente_attr", UtenteDTO.buildUtenteDTOFromModel(utenteModel, true));
		model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
		return "utente/edit";
	}

	@PostMapping("/update")
	public String update(@Validated(ValidationNoPassword.class) @ModelAttribute("edit_utente_attr") UtenteDTO utenteDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
			return "utente/edit";
		}
		utenteService.aggiorna(utenteDTO.buildUtenteModel(true));

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/utente";
	}

	@PostMapping("/cambiaStato")
	public String cambiaStato(@RequestParam(name = "idUtenteForChangingStato", required = true) Long idUtente,
			RedirectAttributes redirectAttrs) {
		utenteService.changeUserAbilitation(idUtente);
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/utente";
	}

//	@PostMapping("/cambiaPassword")
//	public String cambiaPassword(@RequestParam(name = "idUtenteResetPassword", required = true)Long idUtenteResetPassword,RedirectAttributes redirectAttrs) {
//		utenteService.resetPasswordByAdmin(idUtenteResetPassword);
//		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
//		return "redirect:/utente";
//	}

	@GetMapping("/show/{idUtente}")
	public String show(@PathVariable(required = true) Long idUtente, Model model) {
		Utente utenteModel = utenteService.caricaSingoloUtenteConRuoli(idUtente);
		UtenteDTO utenteDto = UtenteDTO.buildUtenteDTOFromModel(utenteModel, true);
		model.addAttribute("show_utente_attr", utenteDto);
		model.addAttribute("ruoli_totali_attr", utenteModel.getRuoli());
		return "utente/show";
	}

	@GetMapping("/registrati")
	public String registrati(Model model) {
		model.addAttribute("insert_utente_attr", new UtenteDTO());
		return "signup";
	}

	@PostMapping("/signup")
	public String signup(
			@Validated({ ValidationNoPassword.class }) @ModelAttribute("insert_utente_attr") UtenteDTO utenteDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		if (!result.hasFieldErrors("password") && !utenteDTO.getPassword().equals(utenteDTO.getConfermaPassword()))
			result.rejectValue("confermaPassword", "password.diverse");

		if (result.hasErrors()) {
			return "utente/insert";
		}
		utenteService.registrati(utenteDTO.buildUtenteModel(true));

		redirectAttrs.addFlashAttribute("infoMessage",
				"Sei stato registrato! Attendi che un admin abiliti il tuo account.");
		return "redirect:/login";
	}

//	@GetMapping("/ricarica/{utenteInPagina}")
//	public String ricarica(@PathVariable(required = true) String utenteInPagina, Model model) {
//		model.addAttribute("credito_utente_attr",
//				UtenteDTO.buildUtenteDTOFromModel(utenteService.findByUsername(utenteInPagina),false));
//		return "utente/ricarica";
//	}
//
//	@PostMapping("/ricarica")
//	public String ricaricaCredito(@ModelAttribute("credito_utente_attr") UtenteDTO utenteDTO, BindingResult result,
//			Model model, RedirectAttributes redirectAttrs) {
//
//		utenteService.ricarica(utenteDTO.buildUtenteModel(true));
//
//		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
//		return "redirect:/home";
//	}
}
