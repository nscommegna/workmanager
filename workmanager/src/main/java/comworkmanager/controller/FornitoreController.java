package comworkmanager.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import comworkmanager.enums.EnumTipoMessaggio;
import comworkmanager.model.Cliente;
import comworkmanager.model.Fornitore;
import comworkmanager.model.Prodotto;
import comworkmanager.model.QualitaProdotto;
import comworkmanager.service.ClienteService;
import comworkmanager.service.FornitoreService;
import comworkmanager.service.ProdottoService;
import comworkmanager.util.Messaggio;
import comworkmanager.util.Util;

@Controller
@RequestMapping("/fornitore")
public class FornitoreController {
	//page
	private final String LISTA_FORNITORI = "/fornitore/listaFornitori";
	private final String NUOVO_FORNITORE = "/fornitore/nuovoFornitore";
	private final String MODIFICA_FORNITORE = "/fornitore/modificaFornitore";
	//service
	private final FornitoreService fornitoreService;
	//constant
	private final String TITLE_PAGE = "titlePage";
	
	//MM KEY
	private final String MESSAGGIO_KEY = "msg";
	
	private static Messaggio msgCorrente;
	
	public FornitoreController(FornitoreService fornitoreService) {
		this.fornitoreService = fornitoreService;
	
	}
	

	
	@GetMapping("/all")
	public String all(ModelMap model) {
		if(msgCorrente != null) {
			model.addAttribute(MESSAGGIO_KEY,msgCorrente);
		}
		List<Fornitore> fornitori = fornitoreService.findAllFornitori();
		model.addAttribute("fornitori", fornitori);
		model.addAttribute(TITLE_PAGE, "Elenco fornitori");
		return LISTA_FORNITORI;
		
	}
	
	@GetMapping("/vaiAggiungiFornitore")
	public String vaiAggiungiFornitore(ModelMap model) {
		model.addAttribute(TITLE_PAGE, "Nuovo fornitore");
		return NUOVO_FORNITORE;
		
	}
	
	@PostMapping("/salvaFornitore")
	public ModelAndView salvaFornitore(ModelMap model,
			@RequestParam (required = true) String nome,
			@RequestParam (required = true) String cognome,
			@RequestParam String indirizzo,
			@RequestParam String partitaIva,
			@RequestParam String telefono,
			@RequestParam String codiceFiscale,
			@RequestParam String citta,
			@RequestParam String provincia,
			@RequestParam String cap
			) {
		Fornitore f = new Fornitore(Util.capitalizeString(nome), Util.capitalizeString(cognome), Util.capitalizeString(indirizzo), partitaIva, telefono, codiceFiscale.toUpperCase(), Util.capitalizeString(citta), Util.capitalizeString(provincia), cap);
		fornitoreService.addFornitore(f);
		Messaggio msg =  new Messaggio("Fornitore aggiunto con successo", EnumTipoMessaggio.SUCCESS.getTipo());
		msgCorrente = msg;
		return new ModelAndView("redirect:/fornitore/all");
		
	}
	
	@GetMapping("/vaiModificaFornitore")
	public String vaiModificaFornitore(ModelMap model,
			@RequestParam(required = true) String idFornitore) {
		Fornitore f = fornitoreService.findFornitoreById(Long.valueOf(idFornitore));
		model.addAttribute(TITLE_PAGE, "Modifica Fornitore");
		model.addAttribute("fornitore", f);
		return MODIFICA_FORNITORE;
		
	}
	
	@PostMapping("/modificaFornitore")
	public ModelAndView modificaCliente(ModelMap model,
			@RequestParam (required = true) String idFornitore,
			@RequestParam (required = true) String nome,
			@RequestParam (required = true) String cognome,
			@RequestParam String indirizzo,
			@RequestParam String partitaIva,
			@RequestParam String telefono,
			@RequestParam String codiceFiscale,
			@RequestParam String citta,
			@RequestParam String provincia,
			@RequestParam String cap) {
		
		Fornitore f = new Fornitore(Util.capitalizeString(nome), Util.capitalizeString(cognome), Util.capitalizeString(indirizzo), partitaIva, telefono, codiceFiscale.toUpperCase(), Util.capitalizeString(citta), Util.capitalizeString(provincia), cap);
		f.setId(Long.valueOf(idFornitore));
		fornitoreService.updateFornitore(f);
		Messaggio msg =  new Messaggio("Fornitore modificato con successo", EnumTipoMessaggio.SUCCESS.getTipo());
		msgCorrente = msg;
		return new ModelAndView("redirect:/fornitore/all");
		
	}
	
	@RequestMapping(value = "/removeMessage", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String postEmployeeData(ModelMap model) {
		msgCorrente = null;
		return "200";
	}
}
