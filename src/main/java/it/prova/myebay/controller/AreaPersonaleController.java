package it.prova.myebay.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.prova.myebay.service.UtenteService;

@Controller
@RequestMapping(value = "/areaPersonaleController")
public class AreaPersonaleController {

	@Autowired
	private UtenteService utenteService;
	
	@GetMapping(value ="/preparereareapersonale")
	public String cambioPassword(HttpServletRequest request,HttpServletResponse response , Model model) {
		if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
		model.addAttribute("area_personale_attr");
		return "areapersonale";
		}
	return "index";	
	}
}
