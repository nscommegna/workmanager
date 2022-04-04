package comworkmanager.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import comworkmanager.model.Acquisto;
import comworkmanager.model.Cliente;
import comworkmanager.model.Fornitore;
import comworkmanager.model.Mezzo;
import comworkmanager.model.Pagamento;
import comworkmanager.model.QualitaProdotto;
import comworkmanager.modelSpecifications.AcquistoSearch;
import comworkmanager.modelSpecifications.AcquistoSpecs;
import comworkmanager.service.AcquistoService;
import comworkmanager.service.ClienteService;
import comworkmanager.service.FornitoreService;
import comworkmanager.service.MezzoService;
import comworkmanager.service.PagamentoService;
import comworkmanager.service.QualitaProdottoService;
import comworkmanager.util.Messaggio;
import comworkmanager.util.Util;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {
	//page
	private final String LISTA_PAGAMENTI = "/pagamento/listaPagamenti";
	
	//service
	private final PagamentoService pagamentoService;
	private final AcquistoService acquistoService;
	//constant
	private final String TITLE_PAGE = "titlePage";
	
	//MM KEY
	private final String MESSAGGIO_KEY = "msg";
	
	private static Messaggio msgCorrente;
	
	public PagamentoController(PagamentoService pagamentoService,AcquistoService acquistoService) {
		this.pagamentoService = pagamentoService;
		this.acquistoService = acquistoService;
	}
	

	
	@GetMapping("/all")
	public String all(ModelMap model) {
		if(msgCorrente != null) {
			model.addAttribute(MESSAGGIO_KEY,msgCorrente);
		}
		List<Pagamento> pagamenti = pagamentoService.findAll();
		Double totaleImportoAcquistato = acquistoService.getSumImportoAcquistato();
		Double totaleImportoPagato = pagamentoService.getSumImportoPagato();
		
		model.addAttribute("pagamenti", pagamenti);
		model.addAttribute("totaleImportoAcquistato", totaleImportoAcquistato);
		model.addAttribute("totaleImportoPagato", totaleImportoPagato);
		model.addAttribute(TITLE_PAGE, "Elenco pagamenti");
		return LISTA_PAGAMENTI;
		
	}
	
	
	
	@RequestMapping(value = "/removeMessage", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String postEmployeeData(ModelMap model) {
		msgCorrente = null;
		return "200";
	}
}
