package comworkmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comworkmanager.model.Acquisto;
import comworkmanager.modelSpecifications.AcquistoSpecs;
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
	
	public List<Acquisto> findAllAcquisti(AcquistoSpecs as){
		return acquistoRepo.findAll(as);
	}

	public Acquisto findAcquistoById(Long idAcquisto) {
		return acquistoRepo.findById(idAcquisto).orElse(null);
	}

	public Double getSumImportoAcquistato() {
		List<Double> importi = new ArrayList<Double>();
		Double tot = 0.00;
		for(Acquisto a:this.findAllAcquisti()) {
			importi.add(Double.valueOf(a.getTotale()));
		}
		for(Double importo : importi) {
			tot += importo;
		}
		return tot;
	}
}
