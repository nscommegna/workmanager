package comworkmanager.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import comworkmanager.model.Prodotto;
import comworkmanager.model.QualitaProdotto;
import comworkmanager.modelSpecifications.AcquistoSearch;
import comworkmanager.modelSpecifications.AcquistoSpecs;
import comworkmanager.service.AcquistoService;
import comworkmanager.service.ClienteService;
import comworkmanager.service.FornitoreService;
import comworkmanager.service.MezzoService;
import comworkmanager.service.QualitaProdottoService;
import comworkmanager.util.Messaggio;
import comworkmanager.util.Util;

@Controller
@RequestMapping("/acquisto")
public class AcquistoController {
	//page
	private final String LISTA_ACQUISTI = "/acquisto/listaAcquisti";
	private final String NUOVO_ACQUISTO = "/acquisto/registraAcquisto";
	private final String MODIFICA_ACQUISTO = "/acquisto/modificaAcquisto";
	//service
	private final AcquistoService acquistoService;
	private final FornitoreService fornitoreService;
	private final QualitaProdottoService qualitaProdottoService;
	private final ClienteService clienteService;
	private final MezzoService mezzoService;
	
	//constant
	private final String TITLE_PAGE = "titlePage";
	
	//MM KEY
	private final String MESSAGGIO_KEY = "msg";
	
	private static Messaggio msgCorrente;
	
	public AcquistoController(AcquistoService acquistoService,FornitoreService fornitoreService,QualitaProdottoService qualitaProdottoService
			,ClienteService clienteService,MezzoService mezzoService) {
		this.acquistoService = acquistoService;
		this.fornitoreService = fornitoreService;
		this.qualitaProdottoService = qualitaProdottoService;
		this.clienteService = clienteService;
		this.mezzoService = mezzoService;
	}
	

	
	@GetMapping("/all")
	public String all(ModelMap model) {
		if(msgCorrente != null) {
			model.addAttribute(MESSAGGIO_KEY,msgCorrente);
		}
		List<Acquisto> acquisti = acquistoService.findAllAcquisti();
		model.addAttribute("acquisti", acquisti);
		
		List<Fornitore> fornitori = fornitoreService.findAllFornitoriOrderByCognomeAsc();
		model.addAttribute("fornitori", fornitori);
		
		List<QualitaProdotto> qualitaProdotti = qualitaProdottoService.findAllQualitaProdotto();
		model.addAttribute("qualitaProdotti", qualitaProdotti);
		
		if(acquisti.size() != 0) {
			Double importoTotaleAcquistato = Util.calcolaTotaleAcquisti(acquisti);
			Double quantitaTotaleAcquistata = Util.calcolaTotaleQuantitaProdottoAcquistata(acquisti);
			Double mediaPrezzo = Util.roundTo4Digit(importoTotaleAcquistato/quantitaTotaleAcquistata);
			
			model.addAttribute("importoTotaleAcquistato",importoTotaleAcquistato);
			model.addAttribute("quantitaTotaleAcquistata", quantitaTotaleAcquistata);
			model.addAttribute("mediaPrezzo", mediaPrezzo);
		}else {
			model.remove("importoTotaleAcquistato");
			model.remove("quantitaTotaleAcquistata");
			model.remove("mediaPrezzo");
		}
		
		model.addAttribute(TITLE_PAGE, "Elenco acquisti");
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
		
		if(acquisti.size() != 0) {
			Double importoTotaleAcquistato = Util.calcolaTotaleAcquisti(acquisti);
			Double quantitaTotaleAcquistata = Util.calcolaTotaleQuantitaProdottoAcquistata(acquisti);
			Double mediaPrezzo = Util.roundTo4Digit(importoTotaleAcquistato/quantitaTotaleAcquistata);
			
			model.addAttribute("importoTotaleAcquistato",importoTotaleAcquistato);
			model.addAttribute("quantitaTotaleAcquistata", quantitaTotaleAcquistata);
			model.addAttribute("mediaPrezzo", mediaPrezzo);
		}else {
			model.remove("importoTotaleAcquistato");
			model.remove("quantitaTotaleAcquistata");
			model.remove("mediaPrezzo");
		}
		
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
			@RequestParam (required = false) String dataAcquisto,
			@RequestParam (required = true) String fornitore,
			@RequestParam (required = true) String prodottoQualita,
			@RequestParam (required = true) Double kili,
			@RequestParam (required = true) Double prezzo,
			@RequestParam (required = false) String cantinaDestinazione,
			@RequestParam (required = false) String mezzo) throws NumberFormatException, ParseException {
		
		//converto la stringa in data
		Date date = null;
		try {
			if(dataAcquisto!= null && !dataAcquisto.isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				date = sdf.parse(dataAcquisto);
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
		BigDecimal bd = new BigDecimal(tot).setScale(4, RoundingMode.HALF_UP);
	    double totFormatted = bd.doubleValue();
		Acquisto acquisto = new Acquisto(date, objFornitore, qualitaProdotto, objMezzo, kili, prezzo, totFormatted, cantinaScarico);
		acquistoService.addAcquisto(acquisto);
		
		Messaggio msg =  new Messaggio("Acquisto registrato con successo", EnumTipoMessaggio.SUCCESS.getTipo());
		msgCorrente = msg;
		return new ModelAndView("redirect:/acquisto/all");
		
	}
	
	@PostMapping("/eliminaAcquisto")
	public ModelAndView eliminaAcquisto(ModelMap model,
			@RequestParam (required = false) String idAcquisto) throws NumberFormatException, ParseException {
		Acquisto acquisto = acquistoService.findAcquistoById(Long.valueOf(idAcquisto));
		acquistoService.storicizza(acquisto);
		Messaggio msg =  new Messaggio("Acquisto eliminato con successo", EnumTipoMessaggio.SUCCESS.getTipo());
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
		
		List<Fornitore> fornitori = fornitoreService.findAllFornitori();
		model.addAttribute("fornitori", fornitori);
		
		List<QualitaProdotto> qualitaProdotti = qualitaProdottoService.findAllQualitaProdotto();
		model.addAttribute("qualitaProdotti", qualitaProdotti);
		
		List<Mezzo> mezzi = mezzoService.findAll();
		model.addAttribute("mezzi", mezzi);
		return MODIFICA_ACQUISTO;
		
	}
	
	@PostMapping("/modificaAcquisto")
	public ModelAndView modificaAcquisto(ModelMap model,
			@RequestParam (required = true) String dataAcquisto,
			@RequestParam (required = false) String cantinaDestinazione,
			@RequestParam (required = false) String mezzo,
			@RequestParam (required = true) String idAcquisto,
			@RequestParam (required = true) String fornitore,
			@RequestParam (required = true) Double kili,
			@RequestParam (required = true) Double prezzo,
			@RequestParam (required = true) String prodottoQualita
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
		if(fornitore != null && !fornitore.equals("")) {
			if(fornitore.equals("-1")) {
				a.setFornitore(null);
			}
			else {
				Fornitore f = new Fornitore();
				f.setId(Long.valueOf(fornitore));
				a.setFornitore(f);
			}
		}
		a.setQuantita(kili);
		a.setPrezzo(prezzo);
		BigDecimal bd = new BigDecimal(kili * prezzo).setScale(4, RoundingMode.HALF_UP);
	    double totFormatted = bd.doubleValue();
		a.setTotale(totFormatted);
		QualitaProdotto qualitaProdotto = qualitaProdottoService.findQualitaProdottoById(Long.valueOf(prodottoQualita));
		a.setProdotto(qualitaProdotto);
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
