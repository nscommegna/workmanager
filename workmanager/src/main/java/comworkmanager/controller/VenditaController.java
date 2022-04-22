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
import comworkmanager.modelSpecifications.VenditaSearch;
import comworkmanager.modelSpecifications.VenditaSpecs;
import comworkmanager.service.AcquistoService;
import comworkmanager.service.ClienteService;
import comworkmanager.service.FornitoreService;
import comworkmanager.service.MezzoService;
import comworkmanager.service.QualitaProdottoService;
import comworkmanager.service.VenditaService;
import comworkmanager.util.Messaggio;
import comworkmanager.util.Util;

@Controller
@RequestMapping("/vendita")
public class VenditaController {
	// page
	private final String LISTA_VENDITE = "/vendita/listaVendite";
	private final String NUOVA_VENDITA = "/vendita/registraVendita";
	private final String MODIFICA_VENDITA = "/vendita/modificaVendita";
	// service
	private final AcquistoService acquistoService;
	private final FornitoreService fornitoreService;
	private final QualitaProdottoService qualitaProdottoService;
	private final ClienteService clienteService;
	private final MezzoService mezzoService;
	private final VenditaService venditaService;

	// constant
	private final String TITLE_PAGE = "titlePage";

	// MM KEY
	private final String MESSAGGIO_KEY = "msg";

	private static Messaggio msgCorrente;

	public VenditaController(AcquistoService acquistoService, FornitoreService fornitoreService,
			QualitaProdottoService qualitaProdottoService, ClienteService clienteService, MezzoService mezzoService,
			VenditaService venditaService) {
		this.acquistoService = acquistoService;
		this.fornitoreService = fornitoreService;
		this.qualitaProdottoService = qualitaProdottoService;
		this.clienteService = clienteService;
		this.mezzoService = mezzoService;
		this.venditaService = venditaService;
	}

	@GetMapping("/all")
	public String all(ModelMap model) {
		if (msgCorrente != null) {
			model.addAttribute(MESSAGGIO_KEY, msgCorrente);
		}
		List<Vendita> vendite = venditaService.findAllVendite();
		model.addAttribute("vendite", vendite);

		List<Cliente> clienti = clienteService.findAllClienti();
		model.addAttribute("clienti", clienti);

		List<QualitaProdotto> qualitaProdotti = qualitaProdottoService.findAllQualitaProdotto();
		model.addAttribute("qualitaProdotti", qualitaProdotti);

		if(vendite.size() != 0) {
			Double importoTotaleVenduto = Util.calcolaTotaleVendite(vendite);
			Double quantitaTotaleVenduta = Util.calcolaTotaleQuantitaProdottoVenduta(vendite);
			Double mediaPrezzo = Util.roundTo2Digit(importoTotaleVenduto/quantitaTotaleVenduta);
			
			model.addAttribute("importoTotaleVenduto",importoTotaleVenduto);
			model.addAttribute("quantitaTotaleVenduta", quantitaTotaleVenduta);
			model.addAttribute("mediaPrezzo", mediaPrezzo);
		}else {
			model.remove("importoTotaleVenduto");
			model.remove("quantitaTotaleVenduta");
			model.remove("mediaPrezzo");
		}
		
		model.addAttribute(TITLE_PAGE, "Elenco vendite");
		
		
		
		return LISTA_VENDITE;

	}

	@PostMapping("/ricercaAvanzata")
	public String ricercaAvanzata(ModelMap model, @RequestParam(required = false) String dataInizio,
			@RequestParam(required = false) String dataFine, @RequestParam(required = false) String cliente,
			@RequestParam(required = false) String prodottoQualita) throws ParseException {
		if (msgCorrente != null) {
			model.addAttribute(MESSAGGIO_KEY, msgCorrente);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dataInizioDate = null;
		Date dataFineDate = null;
		Cliente c = null;
		QualitaProdotto qp = null;

		if (dataInizio != null && !dataInizio.equals("")) {
			dataInizioDate = sdf.parse(dataInizio);
		}
		if (dataFine != null && !dataFine.equals("")) {
			dataFineDate = sdf.parse(dataFine);
		}

		if (cliente != null && !cliente.equals("") && !cliente.equals("-1")) {
			c = clienteService.findClienteById(Long.valueOf(cliente));
		}

		if (prodottoQualita != null && !prodottoQualita.equals("") && !prodottoQualita.equals("-1")) {
			qp = qualitaProdottoService.findQualitaProdottoById(Long.valueOf(prodottoQualita));
		}
		VenditaSearch vs = new VenditaSearch(dataInizioDate, dataFineDate, c, qp);
		VenditaSpecs vspec = new VenditaSpecs(vs);
		List<Vendita> vendite = venditaService.findAllVendite(vspec);
		model.addAttribute("vendite", vendite);

		List<Cliente> clienti = clienteService.findAllClienti();
		model.addAttribute("clienti", clienti);

		List<QualitaProdotto> qualitaProdotti = qualitaProdottoService.findAllQualitaProdotto();
		model.addAttribute("qualitaProdotti", qualitaProdotti);
		if(vendite.size() != 0) {
			Double importoTotaleVenduto = Util.calcolaTotaleVendite(vendite);
			Double quantitaTotaleVenduta = Util.calcolaTotaleQuantitaProdottoVenduta(vendite);
			Double mediaPrezzo = Util.roundTo2Digit(importoTotaleVenduto/quantitaTotaleVenduta);
			
			model.addAttribute("importoTotaleVenduto",importoTotaleVenduto);
			model.addAttribute("quantitaTotaleVenduta", quantitaTotaleVenduta);
			model.addAttribute("mediaPrezzo", mediaPrezzo);
		}else {
			model.remove("importoTotaleVenduto");
			model.remove("quantitaTotaleVenduta");
			model.remove("mediaPrezzo");
		}
		model.addAttribute(TITLE_PAGE, "Elenco vendite");
		return LISTA_VENDITE;

	}

	@GetMapping("/vaiAggiungiVendita")
	public String vaiAggiungiVendita(ModelMap model) {
		model.addAttribute(TITLE_PAGE, "Nuova vendita");

		List<Cliente> clienti = clienteService.findAllClienti();
		model.addAttribute("clienti", clienti);

		List<QualitaProdotto> qualitaProdotti = qualitaProdottoService.findAllQualitaProdotto();
		model.addAttribute("qualitaProdotti", qualitaProdotti);

		List<Mezzo> mezzi = mezzoService.findAll();
		model.addAttribute("mezzi", mezzi);

		List<Vendita> venditeEffettuate = venditaService.findAllVendite();
		List<Acquisto> acquistiEffettuati = acquistoService.findAllAcquisti();

		double quantitaVenduta = Util.calcolaTotaleQuantitaProdottoVenduta(venditeEffettuate);
		double quantitaAcquistata = Util.calcolaTotaleQuantitaProdottoAcquistata(acquistiEffettuati);
		double totaleQuantitaProdottoDaVendere = quantitaAcquistata - quantitaVenduta;

		model.addAttribute("totaleQuantitaProdottoDaVendere", totaleQuantitaProdottoDaVendere);
		return NUOVA_VENDITA;

	}

	@PostMapping("/salvaVendita")
	public ModelAndView salvaVendita(ModelMap model, @RequestParam(required = true) String numeroDoc,
			@RequestParam(required = false) String dataVendita, @RequestParam(required = true) String cliente,
			@RequestParam(required = true) String prodottoQualita, @RequestParam(required = true) Double kili,
			@RequestParam(required = true) Double prezzo, @RequestParam(required = true) Double costoTrasporto,
			@RequestParam(required = false) String mezzo) throws NumberFormatException, ParseException {

		// converto la stringa in data
		Date date = null;
		try {
			if (!dataVendita.isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				date = sdf.parse(dataVendita);
			}
		} catch (ParseException e) {
			Messaggio msg = new Messaggio("Qualcosa è andato storto..", EnumTipoMessaggio.WARNING.getTipo());
			msgCorrente = msg;
			return new ModelAndView("redirect:/vendita/all");
		}
		QualitaProdotto qualitaProdotto = qualitaProdottoService.findQualitaProdottoById(Long.valueOf(prodottoQualita));
		Cliente objCliente = clienteService.findClienteById(Long.valueOf(cliente));

		// opzionale
		Mezzo objMezzo = null;
		if (!mezzo.equals("-1")) {
			objMezzo = mezzoService.findMezzoById(Long.valueOf(mezzo));
		}
		// calcolo totale e approssimo a 2 cifre dopo la virgola
		Double totParziale = prezzo * kili;
		BigDecimal bd = new BigDecimal(totParziale).setScale(2, RoundingMode.HALF_UP);
		double totParzialeFormatted = bd.doubleValue();
		double prezzoTrasporto = (costoTrasporto * kili) + (costoTrasporto*kili*0.22);
		Double totale = totParziale + prezzoTrasporto;
		BigDecimal bd2 = new BigDecimal(totale).setScale(2, RoundingMode.HALF_UP);
		double totFormatted = bd2.doubleValue();

		Vendita v = new Vendita(date, objCliente, qualitaProdotto, objMezzo, numeroDoc, kili, prezzo,
				totParzialeFormatted, costoTrasporto, totFormatted);
		venditaService.addVendita(v);
		Messaggio msg = new Messaggio("Vendita registrata con successo", EnumTipoMessaggio.SUCCESS.getTipo());
		msgCorrente = msg;
		return new ModelAndView("redirect:/vendita/all");

	}

	@GetMapping("/vaiModificaVendita")
	public String vaiModificaAcquisto(ModelMap model, @RequestParam(required = true) String idVendita) {
		Vendita vendita = venditaService.findVenditaById(Long.valueOf(idVendita));
		model.addAttribute(TITLE_PAGE, "Modifica vendita");

		model.addAttribute("vendita", vendita);

		List<Cliente> clienti = clienteService.findAllClienti();
		model.addAttribute("clienti", clienti);

		List<Mezzo> mezzi = mezzoService.findAll();
		model.addAttribute("mezzi", mezzi);

		List<QualitaProdotto> qualitaProdotti = qualitaProdottoService.findAllQualitaProdotto();
		model.addAttribute("qualitaProdotti", qualitaProdotti);

		List<Vendita> venditeEffettuate = venditaService.findAllVendite();
		List<Acquisto> acquistiEffettuati = acquistoService.findAllAcquisti();

		double quantitaVenduta = Util.calcolaTotaleQuantitaProdottoVenduta(venditeEffettuate);
		double quantitaAcquistata = Util.calcolaTotaleQuantitaProdottoAcquistata(acquistiEffettuati);
		double totaleQuantitaProdottoDaVendere = quantitaAcquistata - quantitaVenduta;
		model.addAttribute("totaleQuantitaProdottoDaVendere", totaleQuantitaProdottoDaVendere);
		return MODIFICA_VENDITA;

	}

	@PostMapping("/modificaVendita")
	public ModelAndView modificaAcquisto(ModelMap model, @RequestParam(required = true) String numeroDoc,
			@RequestParam(required = false) String dataVendita, @RequestParam(required = true) String cliente,
			@RequestParam(required = true) String prodottoQualita, @RequestParam(required = true) Double kili,
			@RequestParam(required = true) Double prezzo, @RequestParam(required = true) Double costoTrasporto,
			@RequestParam(required = false) String mezzo, @RequestParam(required = true) String idVendita)
			throws ParseException {

		Vendita v = venditaService.findVenditaById(Long.valueOf(idVendita));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (dataVendita != null && !dataVendita.equals("")) {
			v.setDataVendita(sdf.parse(dataVendita));
		}
		if (mezzo != null && !mezzo.equals("")) {
			if (mezzo.equals("-1")) {
				v.setMezzo(null);
			} else {
				Mezzo m = new Mezzo();
				m.setId(Long.valueOf(mezzo));
				v.setMezzo(m);
			}
		}

		QualitaProdotto qualitaProdotto = qualitaProdottoService.findQualitaProdottoById(Long.valueOf(prodottoQualita));
		v.setProdotto(qualitaProdotto);
		Cliente objCliente = clienteService.findClienteById(Long.valueOf(cliente));
		v.setCliente(objCliente);

		// calcolo totale e approssimo a 2 cifre dopo la virgola
		Double totParziale = prezzo * kili;
		BigDecimal bd = new BigDecimal(totParziale).setScale(2, RoundingMode.HALF_UP);
		double totParzialeFormatted = bd.doubleValue();
		v.setTotaleParziale(totParzialeFormatted);
		
		double prezzoTrasporto = (costoTrasporto * kili) + (costoTrasporto*kili*0.22);
		Double totale = totParziale + prezzoTrasporto;;
		BigDecimal bd2 = new BigDecimal(totale).setScale(2, RoundingMode.HALF_UP);
		double totFormatted = bd2.doubleValue();
		v.setTotale(totFormatted);
		
		v.setNumeroDocumento(numeroDoc);
		v.setQuantita(kili);
		v.setPrezzo(prezzo);
		v.setCostoTrasporto(costoTrasporto);
		venditaService.addVendita(v);
		Messaggio msg = new Messaggio("Vendita modificata con successo", EnumTipoMessaggio.SUCCESS.getTipo());
		msgCorrente = msg;
		return new ModelAndView("redirect:/vendita/all");

	}

	@RequestMapping(value = "/removeMessage", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String postEmployeeData(ModelMap model) {
		msgCorrente = null;
		return "200";
	}
}
