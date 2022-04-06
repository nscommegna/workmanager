package comworkmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import comworkmanager.model.Cliente;
import comworkmanager.repo.ClienteRepo;

@Service
public class ClienteService {
	
	private final ClienteRepo clienteRepo;
	@Autowired
	public ClienteService(ClienteRepo clienteRepo) {
		this.clienteRepo = clienteRepo;
	}
	
	public Cliente addCliente(Cliente cliente) {
		return clienteRepo.save(cliente);
	}
	
	public List<Cliente> findAllClienti(){
		return clienteRepo.findAll(Sort.by(Sort.Direction.ASC, "ragioneSociale"));
	}
	
	public Cliente findClienteById(Long idCliente) {
		 return clienteRepo.findClienteById(idCliente);
	}
	
	public void updateCliente(Cliente cliente) {
		 clienteRepo.updateCliente(cliente.getRagioneSociale(),cliente.getCitta(),cliente.getIndirizzo(),
				cliente.getPartitaIva(),cliente.getTelefono(),cliente.getLuogoConsegna(),cliente.getId());
	}
	
}
