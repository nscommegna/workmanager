package comworkmanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import comworkmanager.model.Cliente;
import comworkmanager.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	private final String LISTA_CLIENTI = "/cliente/listaClienti";
	
	private final ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
		
	}
	
	@GetMapping("/all")
	public String getAllClienti(Model model) {
		List<Cliente> clienti = clienteService.findAllClienti();
		model.addAttribute("clienti", clienti);
		return LISTA_CLIENTI;
		
	}
}
