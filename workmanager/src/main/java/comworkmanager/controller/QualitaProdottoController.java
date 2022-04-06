package comworkmanager.controller;

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
import comworkmanager.model.Prodotto;
import comworkmanager.model.QualitaProdotto;
import comworkmanager.service.ProdottoService;
import comworkmanager.service.QualitaProdottoService;
import comworkmanager.util.Messaggio;

@Controller
@RequestMapping("/qualitaProdotto")
public class QualitaProdottoController {
	//page
	private final String NUOVA_QUALITA = "/prodotto/qualitaProdotto/nuovaQualitaProdotto";
	private final String MODIFICA_QUALITA = "/prodotto/qualitaProdotto/modificaQualitaProdotto";
	//service
	private final ProdottoService prodottoService;
	private final QualitaProdottoService qualitaProdottoService;
	//constant
	private final String TITLE_PAGE = "titlePage";
	
	//MM KEY
	private static Messaggio msgCorrente;
	private final String MESSAGGIO_KEY = "msg";
	
	public QualitaProdottoController(ProdottoService prodottoService,QualitaProdottoService qualitaProdottoService) {
		this.prodottoService = prodottoService;
		this.qualitaProdottoService = qualitaProdottoService;
	
	}
	

	
	
	
	@GetMapping("/vaiAggiungiQualita")
	public String vaiAggiungiQualita(ModelMap model,
			@RequestParam String idProdotto) {
		if(msgCorrente != null) {
			model.addAttribute(MESSAGGIO_KEY,msgCorrente);
		}
		Prodotto p = prodottoService.findProdottoById(Long.valueOf(idProdotto));
		model.addAttribute(TITLE_PAGE, "Nuova qualità prodotto");
		model.addAttribute("prodotto", p);
		return NUOVA_QUALITA;
		
	}
	
	@PostMapping("/salvaQualitaProdotto")
	public ModelAndView salvaProdotto(ModelMap model,
			@RequestParam (required = true) String idProdotto,
			@RequestParam (required = true) String  qualita
			) {
		Prodotto p = new Prodotto();
		p.setId(Long.valueOf(idProdotto));
		String[] qualitaInserite = qualita.split("#");
		QualitaProdotto qp = new QualitaProdotto(qualita);
		qp.setProdotto(p);
		qualitaProdottoService.addQualitaProdotto(qp);

		Messaggio msg =  new Messaggio("Qualita aggiunta con successo", EnumTipoMessaggio.SUCCESS.getTipo());
		msgCorrente = msg;
		return new ModelAndView("redirect:/qualitaProdotto/vaiAggiungiQualita?idProdotto="+idProdotto);
		
	}
	
	@GetMapping("/vaiQualitaProdotto")
	public String vaiModificaProdotto(ModelMap model,
			@RequestParam(required = true) String idQualitaProdotto) {
		QualitaProdotto qp = qualitaProdottoService.findQualitaProdottoById(Long.valueOf(idQualitaProdotto));
		model.addAttribute(TITLE_PAGE, "Modifica qualità prodotto");
		model.addAttribute("qualitaProdotto", qp);
		return MODIFICA_QUALITA;
		
	}
	
	@PostMapping("/modificaNomeProdotto")
	public ModelAndView modificaNomeProdotto(ModelMap model,
			@RequestParam (required = true) String idProdotto,
			@RequestParam (required = true) String tipo) {
		
		Prodotto p = new Prodotto(tipo);
		p.setId(Long.valueOf(idProdotto));
		prodottoService.updateProdotto(p);
		Messaggio msg =  new Messaggio("Prodotto modificato con successo", EnumTipoMessaggio.SUCCESS.getTipo());
		msgCorrente = msg;
		return new ModelAndView("redirect:/prodotto/vaiAggiungiQualita?");
		
	}
	
	@RequestMapping(value = "/removeMessage", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String postEmployeeData(ModelMap model) {
		msgCorrente = null;
		return "200";
	}
}
