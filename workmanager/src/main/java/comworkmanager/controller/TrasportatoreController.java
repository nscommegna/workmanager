package comworkmanager.controller;

import java.util.List;

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
import comworkmanager.model.Mezzo;
import comworkmanager.model.Prodotto;
import comworkmanager.model.QualitaProdotto;
import comworkmanager.model.Trasportatore;
import comworkmanager.service.MezzoService;
import comworkmanager.service.TrasportatoreService;
import comworkmanager.util.Messaggio;

@Controller
@RequestMapping("/trasportatore")
public class TrasportatoreController {
	//page
	private final String LISTA_TRASPORTATORI = "/trasportatore/listaTrasportatori";
	private final String NUOVO_TRASPORTATORE = "/trasportatore/nuovoTrasportatore";
	private final String MODIFICA_TRASPORTATORE = "/trasportatore/modificaTrasportatore";
	private final String NUOVO_MEZZO = "/trasportatore/mezzo/nuovoMezzo";
	private final String MODIFICA_MEZZO = "/trasportatore/mezzo/modificaMezzo";
		
	//service
	private final TrasportatoreService trasportatoreService;
	
	//service
	private final MezzoService mezzoService;
	//constant
	private final String TITLE_PAGE = "titlePage";
	
	//MM KEY
	private final String MESSAGGIO_KEY = "msg";
	
	private static Messaggio msgCorrente;
	
	public TrasportatoreController(TrasportatoreService trasportatoreService,MezzoService mezzoService) {
		this.trasportatoreService = trasportatoreService;
		this.mezzoService = mezzoService;
	
	}
	

	
	@GetMapping("/all")
	public String getAllTrasportatori(ModelMap model) {
		if(msgCorrente != null) {
			model.addAttribute(MESSAGGIO_KEY,msgCorrente);
		}
		List<Trasportatore> trasportatori = trasportatoreService.findAllTrasportatori();
		model.addAttribute("trasportatori", trasportatori);
		model.addAttribute(TITLE_PAGE, "Elenco trasportatori");
		return LISTA_TRASPORTATORI;
		
	}
	
	@GetMapping("/vaiAggiungiTrasportatore")
	public String vaiAggiungiCliente(ModelMap model) {
		model.addAttribute(TITLE_PAGE, "Nuovo trasportatore");
		return NUOVO_TRASPORTATORE;
		
	}
	
	@PostMapping("/salvaTrasportatore")
	public ModelAndView salvaTrasportatore(ModelMap model,
			@RequestParam (required = true) String nome,
			@RequestParam (required = true) String  targhe
			) {
		Trasportatore p = new Trasportatore(nome);
		String[] mezziInseriti = targhe.split("#");
		for(int i = 0;i<mezziInseriti.length;i++) {
			if(mezziInseriti[i].length() != 0) {
				Mezzo mezzo = new Mezzo(mezziInseriti[i]);
				p.addMezzo(mezzo);
			}
		}
		trasportatoreService.addTrasportatore(p);
		Messaggio msg =  new Messaggio("Trasportatore aggiunto con successo", EnumTipoMessaggio.SUCCESS.getTipo());
		msgCorrente = msg;
		return new ModelAndView("redirect:/trasportatore/all");
		
	}
	
	@GetMapping("/vaiModificaTrasportatore")
	public String vaiModificaTrasportatore(ModelMap model,
			@RequestParam(required = true) String idTrasportatore) {
		Trasportatore t = trasportatoreService.findTrasportatoreById(Long.valueOf(idTrasportatore));
		model.addAttribute(TITLE_PAGE, "Modifica Trasportatore");
		model.addAttribute("trasportatore", t);
		return MODIFICA_TRASPORTATORE;
		
	}
	
	@PostMapping("/modificaNomeTrasportatore")
	public ModelAndView modificaNomeTrasportatore(ModelMap model,
			@RequestParam (required = true) String idTrasportatore,
			@RequestParam (required = true) String nome) {
		
		Trasportatore p = new Trasportatore(nome);
		p.setId(Long.valueOf(idTrasportatore));
		trasportatoreService.updateTrasportatore(p);
		Messaggio msg =  new Messaggio("Trasportatore modificato con successo", EnumTipoMessaggio.SUCCESS.getTipo());
		msgCorrente = msg;
		return new ModelAndView("redirect:/trasportatore/all");
		
	}
	
	@GetMapping("/vaiAggiungiMezzo")
	public String vaiAggiungiQualita(ModelMap model,
			@RequestParam String idTrasportatore) {
		if(msgCorrente != null) {
			model.addAttribute(MESSAGGIO_KEY,msgCorrente);
		}
		Trasportatore t = trasportatoreService.findTrasportatoreById(Long.valueOf(idTrasportatore));
		model.addAttribute(TITLE_PAGE, "Nuovo mezzo");
		model.addAttribute("trasportatore", t);
		return NUOVO_MEZZO;
		
	}
	
	@PostMapping("/salvaMezzo")
	public ModelAndView salvaProdotto(ModelMap model,
			@RequestParam (required = true) String idTrasportatore,
			@RequestParam (required = true) String  targa
			) {
		Trasportatore t = new Trasportatore();
		t.setId(Long.valueOf(idTrasportatore));
		Mezzo m = new Mezzo(targa);
		m.setTrasportatore(t);
		mezzoService.addMezzo(m);

		Messaggio msg =  new Messaggio("Mezzo aggiunto con successo", EnumTipoMessaggio.SUCCESS.getTipo());
		msgCorrente = msg;
		return new ModelAndView("redirect:/trasportatore/vaiAggiungiMezzo?idTrasportatore="+idTrasportatore);
		
	}
	
	@GetMapping("/vaiModificaMezzo")
	public String vaiModificaProdotto(ModelMap model,
			@RequestParam(required = true) String idMezzo) {
		Mezzo mezzo = mezzoService.findMezzoById(Long.valueOf(idMezzo));
		model.addAttribute(TITLE_PAGE, "Modifica mezzo trasportatore");
		model.addAttribute("mezzo", mezzo);
		return MODIFICA_MEZZO;
		
	}
	
	@PostMapping("/modificaMezzo")
	public ModelAndView modificaNomeProdotto(ModelMap model,
			@RequestParam (required = true) String idMezzo,
			@RequestParam (required = true) String targa) {
		
		Mezzo m = mezzoService.findMezzoById(Long.valueOf(idMezzo));
		m.setTarga(targa);
		mezzoService.addMezzo(m);
		return new ModelAndView("redirect:/trasportatore/vaiModificaTrasportatore?idTrasportatore="+m.getTrasportatore().getId());
		
	}
	
	@RequestMapping(value = "/removeMessage", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String postEmployeeData(ModelMap model) {
		msgCorrente = null;
		return "200";
	}
}
