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
import comworkmanager.model.Prodotto;
import comworkmanager.model.QualitaProdotto;
import comworkmanager.service.ClienteService;
import comworkmanager.service.ProdottoService;
import comworkmanager.util.Messaggio;
import comworkmanager.util.Util;

@Controller
@RequestMapping("/prodotto")
public class ProdottoController {
	//page
	private final String LISTA_PRODOTTI = "/prodotto/listaProdotti";
	private final String NUOVO_PRODOTTO = "/prodotto/nuovoProdotto";
	private final String MODIFICA_PRODOTTO = "/prodotto/modificaProdotto";
	//service
	private final ProdottoService prodottoService;
	//constant
	private final String TITLE_PAGE = "titlePage";
	
	//MM KEY
	private final String MESSAGGIO_KEY = "msg";
	
	private static Messaggio msgCorrente;
	
	public ProdottoController(ProdottoService prodottoService) {
		this.prodottoService = prodottoService;
	
	}
	

	
	@GetMapping("/all")
	public String getAllClienti(ModelMap model) {
		if(msgCorrente != null) {
			model.addAttribute(MESSAGGIO_KEY,msgCorrente);
		}
		List<Prodotto> prodotti = prodottoService.findAllProdotti();
		model.addAttribute("prodotti", prodotti);
		model.addAttribute(TITLE_PAGE, "Elenco prodotti");
		return LISTA_PRODOTTI;
		
	}
	
	@GetMapping("/vaiAggiungiProdotto")
	public String vaiAggiungiCliente(ModelMap model) {
		model.addAttribute(TITLE_PAGE, "Nuovo prodotto");
		return NUOVO_PRODOTTO;
		
	}
	
	@PostMapping("/salvaProdotto")
	public ModelAndView salvaCliente(ModelMap model,
			@RequestParam (required = true) String tipo,
			@RequestParam (required = true) String  qualita
			) {
		Set<QualitaProdotto> qualitaSet = new HashSet<QualitaProdotto>();
		Prodotto p = new Prodotto(tipo,qualitaSet);
		prodottoService.addProdotto(p);
		Messaggio msg =  new Messaggio("Prodotto aggiunto con successo", EnumTipoMessaggio.SUCCESS.getTipo());
		msgCorrente = msg;
		return new ModelAndView("redirect:/prodotto/all");
		
	}
	
	@GetMapping("/vaiModificaProdotto")
	public String vaiModificaCliente(ModelMap model,
			@RequestParam(required = true) String idProdotto) {
		Prodotto p = prodottoService.findProdottoById(Long.valueOf(idProdotto));
		model.addAttribute(TITLE_PAGE, "Modifica Prodotto");
		model.addAttribute("prodotto", p);
		return MODIFICA_PRODOTTO;
		
	}
	
	@PostMapping("/modificaProdotto")
	public ModelAndView modificaCliente(ModelMap model,
			@RequestParam (required = true) String idProdotto,
			@RequestParam (required = true) String tipo,
			@RequestParam (required = true) String qualita) {
		
		/*Prodotto p = new Prodotto(Util.capitalizeString(tipo),Util.capitalizeString(qualita));
		p.setId(Long.valueOf(idProdotto));
		prodottoService.updateProdotto(p);
		Messaggio msg =  new Messaggio("Prodotto modificato con successo", EnumTipoMessaggio.SUCCESS.getTipo());
		msgCorrente = msg;*/
		return new ModelAndView("redirect:/prodotto/all");
		
	}
	
	@RequestMapping(value = "/removeMessage", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String postEmployeeData(ModelMap model) {
		msgCorrente = null;
		return "200";
	}
}
