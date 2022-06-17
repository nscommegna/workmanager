package comworkmanager.controller;

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
import comworkmanager.model.AcquistoStorico;
import comworkmanager.model.AnnataConclusa;
import comworkmanager.model.Pagamento;
import comworkmanager.model.PagamentoStorico;
import comworkmanager.model.Vendita;
import comworkmanager.model.VenditaStorico;
import comworkmanager.modelSpecifications.AcquistoStoricoSearch;
import comworkmanager.modelSpecifications.AcquistoStoricoSpecs;
import comworkmanager.modelSpecifications.PagamentoStoricoSearch;
import comworkmanager.modelSpecifications.PagamentoStoricoSpecs;
import comworkmanager.modelSpecifications.VenditaStoricoSearch;
import comworkmanager.modelSpecifications.VenditaStoricoSpecs;
import comworkmanager.service.AcquistoService;
import comworkmanager.service.AcquistoStoricoService;
import comworkmanager.service.AnnataService;
import comworkmanager.service.PagamentoService;
import comworkmanager.service.PagamentoStoricoService;
import comworkmanager.service.VenditaService;
import comworkmanager.service.VenditaStoricoService;
import comworkmanager.util.Messaggio;

@Controller
@RequestMapping("/storico")
public class StoricoController {
	//page
	private final String LISTA_ANNATE = "/storico/listaAnnate";
	private final String HOME_ANNATA = "/storico/homeAnnata";
	private final String ACQUISTI = "/storico/listaAcquisti";
	private final String VENDITE = "/storico/listaVendite";
	private final String PAGAMENTI = "/storico/listaPagamenti";
	//service
	private  AnnataService annataService;
	private  AcquistoService acquistoService;
	private  VenditaService venditaService;
	private  PagamentoService pagamentoService;
	
	private  AcquistoStoricoService acquistoStoricoService;
	private  VenditaStoricoService venditaStoricoService;
	private  PagamentoStoricoService pagamentoStoricoService;
	//constant
	private final String TITLE_PAGE = "titlePage";
	
	//MM KEY
	private final String MESSAGGIO_KEY = "msg";
	
	private static Messaggio msgCorrente;
	
	public StoricoController(AnnataService annataService,AcquistoService acquistoService,
			VenditaService venditaService,PagamentoService pagamentoService,
			AcquistoStoricoService acquistoStoricoService,VenditaStoricoService venditaStoricoService,
			PagamentoStoricoService pagamentoStoricoService) {
		this.annataService = annataService;
		this.acquistoService = acquistoService;
		this.venditaService = venditaService;
		this.pagamentoService = pagamentoService;
		this.annataService = annataService;
		this.acquistoStoricoService = acquistoStoricoService;
		this.venditaStoricoService = venditaStoricoService;
		this.pagamentoStoricoService = pagamentoStoricoService;
	}
	

	
	@GetMapping("/all")
	public String all(ModelMap model) {
		if(msgCorrente != null) {
			model.addAttribute(MESSAGGIO_KEY,msgCorrente);
		}
		List<AnnataConclusa> annate = annataService.findAllAnnate();
		model.addAttribute("annate", annate);
		model.addAttribute(TITLE_PAGE, "Elenco annate concluse");
		return LISTA_ANNATE;
		
	}
	
	@GetMapping("/vaiHomeAnnata")
	public String vaiProspettoAnnata(ModelMap model,
			@RequestParam (required = true) String idAnnata) {
		AnnataConclusa annata = annataService.findAnnataById(Long.valueOf(idAnnata));
		model.addAttribute(TITLE_PAGE, "Annata : "+ annata.getAnno());
		model.addAttribute("annata",annata);
		return HOME_ANNATA;
		
	}
	
	@PostMapping("/chiudiAnnata")
	public ModelAndView chiudiAnnata(ModelMap model,
			@RequestParam (required = false) String dataChiusura,
			@RequestParam (required = true) String note
			) throws NumberFormatException, ParseException {
		
		//converto la stringa in data
		Date date = null;
		try {
			if(dataChiusura!= null && !dataChiusura.isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				date = sdf.parse(dataChiusura);
			}
		} catch (ParseException e) {
			Messaggio msg =  new Messaggio("Qualcosa è andato storto..", EnumTipoMessaggio.WARNING.getTipo());
			msgCorrente = msg;
			return new ModelAndView("redirect:/");
		}
		List<Acquisto> acquisti = acquistoService.findAllAcquisti();
		List<Vendita> vendite = venditaService.findAllVendite();
		List<Pagamento> pagamenti = pagamentoService.findAll();
		
		AnnataConclusa annata = new AnnataConclusa();
		annata.setAnno(date);
		annata.setNote(note);
		annataService.addAnnataConclusa(annata);
		
		for(Acquisto acquisto : acquisti) {
			AcquistoStorico as = new AcquistoStorico(acquisto.getDataAcquisto(), acquisto.getFornitore(), 
					acquisto.getProdotto(), acquisto.getMezzo(), acquisto.getQuantita(), acquisto.getPrezzo(),
					acquisto.getTotale(), acquisto.getCantinaScarico(),annata);
			acquistoStoricoService.addAcquisto(as);
			acquistoService.storicizza(acquisto);
			
		}
		
		for(Vendita v : vendite) {
			VenditaStorico vs = new VenditaStorico(v.getDataVendita(), v.getCliente(), v.getProdotto(), 
					v.getMezzo(), v.getNumeroDocumento(), v.getQuantita(), v.getPrezzo(),
					v.getTotaleParziale(), v.getCostoTrasporto(), v.getTotale(), annata);
			venditaStoricoService.addVendita(vs);
			venditaService.storicizza(v);
			
		}
		
		for(Pagamento p : pagamenti) {
			PagamentoStorico ps = new PagamentoStorico(p.getDataPagamento(), p.getFornitore(), p.getImporto(),annata);
			pagamentoStoricoService.addPagamento(ps);
			pagamentoService.storicizza(p);
		}
		
		return new ModelAndView("redirect:/storico/all");
		
	}
	
	@GetMapping("/vaiListaAcquisti")
	public String vaiListaAcquisti(ModelMap model,
			@RequestParam (required = true) String idAnnata) {
		AcquistoStoricoSearch as = new AcquistoStoricoSearch(Long.valueOf(idAnnata));
		AcquistoStoricoSpecs acsp = new AcquistoStoricoSpecs(as);
		List<AcquistoStorico> acquisti = acquistoStoricoService.findAllAcquisti(acsp);
		model.addAttribute(TITLE_PAGE, "Storico acquisti");
		model.addAttribute("acquisti",acquisti);
		model.addAttribute("anno",acquisti.get(0).getAnnata().getAnno());
		return ACQUISTI;
	}
	
	@GetMapping("/vaiListaVendite")
	public String vaiListaVendite(ModelMap model,
			@RequestParam (required = true) String idAnnata) {
		try {
			VenditaStoricoSearch as = new VenditaStoricoSearch(Long.valueOf(idAnnata));
			VenditaStoricoSpecs acsp = new VenditaStoricoSpecs(as);
			List<VenditaStorico> vendite = venditaStoricoService.findAllVendite(acsp);
			model.addAttribute(TITLE_PAGE, "Storico vendite");
			model.addAttribute("vendite",vendite);
			model.addAttribute("anno",vendite.get(0).getAnnata().getAnno());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return VENDITE;
	}
	
	@GetMapping("/vaiListaPagamenti")
	public String vaiListaPagamenti(ModelMap model,
			@RequestParam (required = true) String idAnnata) {
		PagamentoStoricoSearch as = new PagamentoStoricoSearch(Long.valueOf(idAnnata));
		PagamentoStoricoSpecs acsp = new PagamentoStoricoSpecs(as);
		List<PagamentoStorico> pagamenti = pagamentoStoricoService.findAll(acsp);
		model.addAttribute(TITLE_PAGE, "Storico vendite");
		model.addAttribute("pagamenti",pagamenti);
		model.addAttribute("anno",pagamenti.get(0).getAnnata().getAnno());
		return PAGAMENTI;
	}
	
	@RequestMapping(value = "/removeMessage", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String postEmployeeData(ModelMap model) {
		msgCorrente = null;
		return "200";
	}
}
