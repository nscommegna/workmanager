package comworkmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comworkmanager.model.Acquisto;
import comworkmanager.model.AnnataConclusa;
import comworkmanager.repo.AnnataRepo;

@Service
public class AnnataService {
	
	private final AnnataRepo annataRepo;
	@Autowired
	public AnnataService(AnnataRepo annataRepo) {
		this.annataRepo = annataRepo;
	}
	
	public AnnataConclusa addAnnataConclusa(AnnataConclusa annata) {
		return annataRepo.save(annata);
	}
	
	public List<AnnataConclusa> findAllAnnate(){
		return annataRepo.findAll();
	}
	
	public AnnataConclusa findAnnataById(Long idAnnata) {
		return annataRepo.findById(idAnnata).orElse(null);
	}
	
//	public List<Acquisto> findAllAcquisti(Annta as){
//		return annataRepo.findAll(as);
//	}

	
}
