package comworkmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comworkmanager.model.AcquistoStorico;
import comworkmanager.modelSpecifications.AcquistoStoricoSpecs;
import comworkmanager.repo.AcquistoStoricoRepo;

@Service
public class AcquistoStoricoService {
	
	private final AcquistoStoricoRepo acquistoStoricoRepo;
	@Autowired
	public AcquistoStoricoService(AcquistoStoricoRepo acquistoStoricoRepo) {
		this.acquistoStoricoRepo = acquistoStoricoRepo;
	}
	
	public AcquistoStorico addAcquisto(AcquistoStorico acquisto) {
		return acquistoStoricoRepo.save(acquisto);
	}
	
	public List<AcquistoStorico> findAllAcquisti(AcquistoStoricoSpecs as){
		return acquistoStoricoRepo.findAll(as);
	}

	
}
