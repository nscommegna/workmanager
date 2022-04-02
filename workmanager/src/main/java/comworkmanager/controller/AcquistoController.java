package comworkmanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import comworkmanager.enums.EnumTipoMessaggio;
import comworkmanager.model.Acquisto;
import comworkmanager.model.Cliente;
import comworkmanager.service.AcquistoService;
import comworkmanager.service.ClienteService;
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
	//constant
	private final String TITLE_PAGE = "titlePage";
	
	//MM KEY
	private final String MESSAGGIO_KEY = "msg";
	
	private static Messaggio msgCorrente;
	
	public AcquistoController(AcquistoService acquistoService) {
		this.acquistoService = acquistoService;
	
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
		return NUOVO_ACQUISTO;
		
	}
	
	@PostMapping("/salvaAcquisto")
	public ModelAndView salvaAcquisto(ModelMap model,
			@RequestParam (required = true) String ragioneSociale,
			@RequestParam String citta,
			@RequestParam String indirizzo,
			@RequestParam String partitaIva,
			@RequestParam String telefono,
			@RequestParam String luogoConsegna) {
		
		Cliente c = new Cliente(ragioneSociale, Util.capitalizeString(citta), Util.capitalizeString(indirizzo), partitaIva, telefono, luogoConsegna);
		//clienteService.addCliente(c);
		Messaggio msg =  new Messaggio("Cliente aggiunto con successo", EnumTipoMessaggio.SUCCESS.getTipo());
		msgCorrente = msg;
		return new ModelAndView("redirect:/cliente/all");
		
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
