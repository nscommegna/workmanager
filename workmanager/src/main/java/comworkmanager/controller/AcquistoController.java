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
import comworkmanager.model.QualitaProdotto;
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
			@RequestParam (required = true) String kili,
			@RequestParam (required = true) String prezzo,
			@RequestParam (required = false) String cantinaDestinazione,
			@RequestParam (required = false) String mezzo) {
		
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
		DecimalFormat df = new DecimalFormat("0.00");
		String tot = df.format(Double.valueOf(prezzo) * Double.valueOf(kili));
		
		Acquisto acquisto = new Acquisto(date, objFornitore, qualitaProdotto, objMezzo, kili, prezzo, tot, cantinaScarico);
		acquistoService.addAcquisto(acquisto);
		
		Messaggio msg =  new Messaggio("Acquisto registrato con successo", EnumTipoMessaggio.SUCCESS.getTipo());
		msgCorrente = msg;
		return new ModelAndView("redirect:/acquisto/all");
		
	}
	
	@GetMapping("/vaiModificaCliente")
	public String vaiModificaCliente(ModelMap model,
			@RequestParam(required = true) String idCliente) {
		//Cliente c = clienteService.findClienteById(Long.valueOf(idCliente));
		model.addAttribute(TITLE_PAGE, "Modifica Cliente");
		model.addAttribute("cliente", 1);
		return MODIFICA_ACQUISTO;
		
	}
	
	@PostMapping("/modificaCliente")
	public ModelAndView modificaCliente(ModelMap model,
			@RequestParam (required = true) String idCliente,
			@RequestParam (required = true) String ragioneSociale,
			@RequestParam String citta,
			@RequestParam String indirizzo,
			@RequestParam String partitaIva,
			@RequestParam String telefono,
			@RequestParam String luogoConsegna) {
		
		Cliente c = new Cliente(ragioneSociale, Util.capitalizeString(citta), Util.capitalizeString(indirizzo), partitaIva, telefono, luogoConsegna);
		c.setId(Long.valueOf(idCliente));
		//clienteService.updateCliente(c);
		Messaggio msg =  new Messaggio("Cliente modificato con successo", EnumTipoMessaggio.SUCCESS.getTipo());
		msgCorrente = msg;
		return new ModelAndView("redirect:/cliente/all");
		
	}
	
	@RequestMapping(value = "/removeMessage", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String postEmployeeData(ModelMap model) {
		msgCorrente = null;
		return "200";
	}
}
