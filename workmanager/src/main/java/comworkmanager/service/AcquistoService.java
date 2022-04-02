package comworkmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comworkmanager.model.Acquisto;
import comworkmanager.repo.AcquistoRepo;

@Service
public class AcquistoService {
	
	private final AcquistoRepo acquistoRepo;
	@Autowired
	public AcquistoService(AcquistoRepo acquistoRepo) {
		this.acquistoRepo = acquistoRepo;
	}
	
	public Acquisto addAcquisto(Acquisto acquisto) {
		return acquistoRepo.save(acquisto);
	}
	
	public List<Acquisto> findAllAcquisti(){
		return acquistoRepo.findAll();
	}
	
}
