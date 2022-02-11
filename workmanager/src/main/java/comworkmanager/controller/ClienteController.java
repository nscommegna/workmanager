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
import comworkmanager.model.Cliente;
import comworkmanager.service.ClienteService;
import comworkmanager.util.Messaggio;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	//page
	private final String LISTA_CLIENTI = "/cliente/listaClienti";
	private final String NUOVO_CLIENTE = "/cliente/nuovoCliente";
	private final String MODIFICA_CLIENTE = "/cliente/modificaCliente";
	//service
	private final ClienteService clienteService;
	//constant
	private final String TITLE_PAGE = "titlePage";
	
	//MM KEY
	private final String MESSAGGIO_KEY = "msg";
	
	private static Messaggio msgCorrente;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	
	}
	

	
	@GetMapping("/all")
	public String getAllClienti(ModelMap model) {
		if(msgCorrente != null) {
			model.addAttribute(MESSAGGIO_KEY,msgCorrente);
		}
		List<Cliente> clienti = clienteService.findAllClienti();
		model.addAttribute("clienti", clienti);
		model.addAttribute(TITLE_PAGE, "Elenco clienti");
		return LISTA_CLIENTI;
		
	}
	
	@GetMapping("/vaiAggiungiCliente")
	public String vaiAggiungiCliente(ModelMap model) {
		model.addAttribute(TITLE_PAGE, "Nuovo cliente");
		return NUOVO_CLIENTE;
		
	}
	
	@PostMapping("/salvaCliente")
	public ModelAndView salvaCliente(ModelMap model,
			@RequestParam (required = true) String ragioneSociale,
			@RequestParam String citta,
			@RequestParam String indirizzo,
			@RequestParam String partitaIva,
			@RequestParam String telefono,
			@RequestParam String luogoConsegna) {
		
		Cliente c = new Cliente(ragioneSociale, citta, indirizzo, partitaIva, telefono, luogoConsegna);
		clienteService.addCliente(c);
		Messaggio msg =  new Messaggio("Cliente aggiunto con successo", EnumTipoMessaggio.SUCCESS.getTipo());
		msgCorrente = msg;
		return new ModelAndView("redirect:/cliente/all");
		
	}
	
	@GetMapping("/vaiModificaCliente")
	public String vaiModificaCliente(ModelMap model,
			@RequestParam(required = true) String idCliente) {
		Cliente c = clienteService.findClienteById(Long.valueOf(idCliente));
		model.addAttribute(TITLE_PAGE, "Modifica Cliente");
		model.addAttribute("cliente", c);
		return MODIFICA_CLIENTE;
		
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
		
		Cliente c = new Cliente(ragioneSociale, citta, indirizzo, partitaIva, telefono, luogoConsegna);
		c.setId(Long.valueOf(idCliente));
		clienteService.updateCliente(c);
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
