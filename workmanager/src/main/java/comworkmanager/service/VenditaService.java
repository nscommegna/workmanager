package comworkmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comworkmanager.model.Vendita;
import comworkmanager.modelSpecifications.VenditaSpecs;
import comworkmanager.repo.VenditaRepo;

@Service
public class VenditaService {
	
	private final VenditaRepo venditaRepo;
	@Autowired
	public VenditaService(VenditaRepo venditaRepo) {
		this.venditaRepo = venditaRepo;
	}
	
	public Vendita addVendita(Vendita vendita) {
		return venditaRepo.save(vendita);
	}
	
	public List<Vendita> findAllVendite(){
		return venditaRepo.findAll();
	}
	
	public List<Vendita> findAllVendite(VenditaSpecs as){
		return venditaRepo.findAll(as);
	}

	public Vendita findVenditaById(Long idVendita) {
		return venditaRepo.findById(idVendita).orElse(null);
	}

	public Double getSumImportoAcquistato() {
		List<Double> importi = new ArrayList<Double>();
		Double tot = 0.00;
		for(Vendita a:this.findAllVendite()) {
			importi.add(Double.valueOf(a.getTotale()));
		}
		for(Double importo : importi) {
			tot += importo;
		}
		return tot;
	}
}
