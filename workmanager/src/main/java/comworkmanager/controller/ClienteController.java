package comworkmanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import comworkmanager.model.Cliente;
import comworkmanager.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	private final ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Cliente>> getAllClienti(){
		List<Cliente> clienti = clienteService.findAllClienti();
		return new ResponseEntity<>(clienti,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Cliente> addCliente(@RequestBody Cliente cliente){
		Cliente clienteRepo = clienteService.addCliente(cliente);
		return new ResponseEntity<>(clienteRepo,HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateCliente(@RequestBody Cliente cliente){
		clienteService.updateCliente(cliente);
		return new ResponseEntity<>("updated..",HttpStatus.OK);
	}
}
