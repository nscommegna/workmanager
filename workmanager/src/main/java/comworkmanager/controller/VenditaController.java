package comworkmanager.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import comworkmanager.model.QualitaProdotto;
import comworkmanager.model.Vendita;
import comworkmanager.modelSpecifications.AcquistoSearch;
import comworkmanager.modelSpecifications.AcquistoSpecs;
import comworkmanager.service.AcquistoService;
import comworkmanager.service.ClienteService;
import comworkmanager.service.FornitoreService;
import comworkmanager.service.MezzoService;
import comworkmanager.service.QualitaProdottoService;
import comworkmanager.service.VenditaService;
import comworkmanager.util.Messaggio;

@Controller
@RequestMapping("/vendita")
public class VenditaController {
	//page
	private final String LISTA_ACQUISTI = "/vendita/listaVendite";
	private final String NUOVO_ACQUISTO = "/vendita/registraVendita";
	private final String MODIFICA_ACQUISTO = "/vendita/modificaVendita";
	//service
	private final AcquistoService acquistoService;
	private final FornitoreService fornitoreService;
	private final QualitaProdottoService qualitaProdottoService;
	private final ClienteService clienteService;
	private final MezzoService mezzoService;
	private final VenditaService venditaService;
	
	//constant
	private final String TITLE_PAGE = "titlePage";
	
	//MM KEY
	private final String MESSAGGIO_KEY = "msg";
	
	private static Messaggio msgCorrente;
	
	public VenditaController(AcquistoService acquistoService,FornitoreService fornitoreService,QualitaProdottoService qualitaProdottoService
			,ClienteService clienteService,MezzoService mezzoService, VenditaService venditaService) {
		this.acquistoService = acquistoService;
		this.fornitoreService = fornitoreService;
		this.qualitaProdottoService = qualitaProdottoService;
		this.clienteService = clienteService;
		this.mezzoService = mezzoService;
		this.venditaService = venditaService;
	}
	

	
	@GetMapping("/all")
	public String all(ModelMap model) {
		if(msgCorrente != null) {
			model.addAttribute(MESSAGGIO_KEY,msgCorrente);
		}
		List<Vendita> vendite = venditaService.findAllVendite();
		model.addAttribute("vendite", vendite);
		
		List<Cliente> clienti = clienteService.findAllClienti();
		model.addAttribute("clienti", clienti);
		
		List<QualitaProdotto> qualitaProdotti = qualitaProdottoService.findAllQualitaProdotto();
		model.addAttribute("qualitaProdotti", qualitaProdotti);
		
		model.addAttribute(TITLE_PAGE, "Elenco vendite");
		return LISTA_ACQUISTI;
		
	}
	
	@PostMapping("/ricercaAvanzata")
	public String ricercaAvanzata(ModelMap model,
			@RequestParam (required = false) String dataInizio,
			@RequestParam (required = false) String dataFine,
			@RequestParam (required = false) String fornitore,
			@RequestParam (required = false) String prodottoQualita) throws ParseException {
		if(msgCorrente != null) {
			model.addAttribute(MESSAGGIO_KEY,msgCorrente);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dataInizioDate = null;
		Date dataFineDate = null;
		Fornitore f = null;
		QualitaProdotto qp = null;
		
		if(dataInizio != null && !dataInizio.equals("")) {
			dataInizioDate = sdf.parse(dataInizio);
		}
		if(dataFine != null && !dataFine.equals("")) {
			dataFineDate = sdf.parse(dataFine);
		}
		
		if(fornitore != null && !fornitore.equals("") && !fornitore.equals("-1")) {
			f = fornitoreService.findFornitoreById(Long.valueOf(fornitore));
		}
		
		if(prodottoQualita != null && !prodottoQualita.equals("") && !prodottoQualita.equals("-1")) {
			qp = qualitaProdottoService.findQualitaProdottoById(Long.valueOf(prodottoQualita));
		}
		AcquistoSearch as = new AcquistoSearch(dataInizioDate, dataFineDate, f, qp);
		AcquistoSpecs aspec = new AcquistoSpecs(as);
		List<Acquisto> acquisti = acquistoService.findAllAcquisti(aspec);
		model.addAttribute("acquisti", acquisti);
		
		List<Fornitore> fornitori = fornitoreService.findAllFornitoriOrderByCognomeAsc();
		model.addAttribute("fornitori", fornitori);
		
		List<QualitaProdotto> qualitaProdotti = qualitaProdottoService.findAllQualitaProdotto();
		model.addAttribute("qualitaProdotti", qualitaProdotti);
		
		model.addAttribute(TITLE_PAGE, "Elenco acquisti");
		return LISTA_ACQUISTI;
		
	}
	
	@GetMapping("/vaiAggiungiAcquisto")
	public String vaiAggiungiAcquisto(ModelMap model) {
		model.addAttribute(TITLE_PAGE, "Nuovo acquisto");
		List<Fornitore> fornitori = fornitoreService.findAllFornitoriOrderByCognomeAsc();
		model.addAttribute("fornitori", fornitori);
		
		List<QualitaProdotto> qualitaProdotti = qualitaProdottoService.findAllQualitaProdotto();
		model.addAttribute("qualitaProdotti", qualitaProdotti);
		
		List<Cliente> clienti = clienteService.findAllClienti();
		model.addAttribute("clienti", clienti);
		
		List<Mezzo> mezzi = mezzoService.findAll();
		model.addAttribute("mezzi", mezzi);
		return NUOVO_ACQUISTO;
		
	}
	
	@PostMapping("/salvaAcquisto")
	public ModelAndView salvaAcquisto(ModelMap model,
			@RequestParam (required = false) String data,
			@RequestParam (required = true) String fornitore,
			@RequestParam (required = true) String prodottoQualita,
			@RequestParam (required = true) Double kili,
			@RequestParam (required = true) Double prezzo,
			@RequestParam (required = false) String cantinaDestinazione,
			@RequestParam (required = false) String mezzo) throws NumberFormatException, ParseException {
		
		//converto la stringa in data
		Date date = null;
		try {
			if(!data.isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				date = sdf.parse(data);
			}
		} catch (ParseException e) {
			Messaggio msg =  new Messaggio("Qualcosa è andato storto..", EnumTipoMessaggio.WARNING.getTipo());
			msgCorrente = msg;
			return new ModelAndView("redirect:/acquisto/all");
		}
		QualitaProdotto qualitaProdotto = qualitaProdottoService.findQualitaProdottoById(Long.valueOf(prodottoQualita));
		Fornitore objFornitore = fornitoreService.findFornitoreById(Long.valueOf(fornitore));
		
		//opzionale
		Cliente cantinaScarico = null;
		if(!cantinaDestinazione.equals("-1")) {
			cantinaScarico = clienteService.findClienteById(Long.valueOf(cantinaDestinazione));
		}
		//opzionale
		Mezzo objMezzo= null;
		if(!mezzo.equals("-1")) {
			objMezzo= mezzoService.findMezzoById(Long.valueOf(mezzo));
		}
		//calcolo totale e approssimo a 2 cifre dopo la virgola
		Double tot = prezzo*kili;
		BigDecimal bd = new BigDecimal(tot).setScale(2, RoundingMode.HALF_UP);
	    double totFormatted = bd.doubleValue();
		Acquisto acquisto = new Acquisto(date, objFornitore, qualitaProdotto, objMezzo, kili, prezzo, totFormatted, cantinaScarico);
		acquistoService.addAcquisto(acquisto);
		
		Messaggio msg =  new Messaggio("Acquisto registrato con successo", EnumTipoMessaggio.SUCCESS.getTipo());
		msgCorrente = msg;
		return new ModelAndView("redirect:/acquisto/all");
		
	}
	
	@GetMapping("/vaiModificaAcquisto")
	public String vaiModificaAcquisto(ModelMap model,
			@RequestParam(required = true) String idAcquisto) {
		Acquisto acquisto = acquistoService.findAcquistoById(Long.valueOf(idAcquisto));
		model.addAttribute(TITLE_PAGE, "Modifica acquisto");
		model.addAttribute("acquisto", acquisto);
		
		List<Cliente> clienti = clienteService.findAllClienti();
		model.addAttribute("clienti", clienti);
		
		List<Mezzo> mezzi = mezzoService.findAll();
		model.addAttribute("mezzi", mezzi);
		return MODIFICA_ACQUISTO;
		
	}
	
	@PostMapping("/modificaAcquisto")
	public ModelAndView modificaAcquisto(ModelMap model,
			@RequestParam (required = false) String dataAcquisto,
			@RequestParam (required = false) String cantinaDestinazione,
			@RequestParam (required = false) String mezzo,
			@RequestParam (required = true) String idAcquisto
			) throws ParseException {
		
		Acquisto a = acquistoService.findAcquistoById(Long.valueOf(idAcquisto));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(dataAcquisto != null && !dataAcquisto.equals("")) {
			a.setDataAcquisto(sdf.parse(dataAcquisto));
		}
		if(mezzo != null && !mezzo.equals("")) {
			if(mezzo.equals("-1")) {
				a.setMezzo(null);
			}
			else {
				Mezzo m = new Mezzo();
				m.setId(Long.valueOf(mezzo));
				a.setMezzo(m);
			}
		}
		if(cantinaDestinazione != null && !cantinaDestinazione.equals("")) {
			if(cantinaDestinazione.equals("-1")) {
				a.setCantinaScarico(null);
			}
			else {
				Cliente c = new Cliente();
				c.setId(Long.valueOf(cantinaDestinazione));
				a.setCantinaScarico(c);
			}
		}
		acquistoService.addAcquisto(a);
		Messaggio msg =  new Messaggio("Acquisto modificato con successo", EnumTipoMessaggio.SUCCESS.getTipo());
		msgCorrente = msg;
		return new ModelAndView("redirect:/acquisto/all");
		
	}
	
	@RequestMapping(value = "/removeMessage", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String postEmployeeData(ModelMap model) {
		msgCorrente = null;
		return "200";
	}
}
