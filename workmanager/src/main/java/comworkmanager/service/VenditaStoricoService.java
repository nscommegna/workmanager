package comworkmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import comworkmanager.model.VenditaStorico;
import comworkmanager.modelSpecifications.VenditaStoricoSpecs;
import comworkmanager.repo.VenditaStoricoRepo;

@Service
public class VenditaStoricoService {
	
	private final VenditaStoricoRepo venditaRepo;
	@Autowired
	public VenditaStoricoService(VenditaStoricoRepo venditaRepo) {
		this.venditaRepo = venditaRepo;
	}
	
	public VenditaStorico addVendita(VenditaStorico vendita) {
		return venditaRepo.save(vendita);
	}
	
	public List<VenditaStorico> findAllVendite(){
		return venditaRepo.findAll(Sort.by(Sort.Direction.DESC,"dataVendita"));
	}
	
	public List<VenditaStorico> findAllVendite(VenditaStoricoSpecs as){
		return venditaRepo.findAll(as);
	}

}
