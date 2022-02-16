package comworkmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comworkmanager.model.Trasportatore;
import comworkmanager.repo.TrasportatoreRepo;

@Service
public class TrasportatoreService {
	
	private final TrasportatoreRepo trasportatoreRepo;
	@Autowired
	public TrasportatoreService(TrasportatoreRepo trasportatoreRepo) {
		this.trasportatoreRepo = trasportatoreRepo;
	}
	
	public Trasportatore addTrasportatore(Trasportatore trasportatore) {
		return trasportatoreRepo.save(trasportatore);
	}
	
	public List<Trasportatore> findAllTrasportatori(){
		return trasportatoreRepo.findAll();
	}
	
	public Trasportatore findTrasportatoreById(Long idTrasportatore) {
		 return trasportatoreRepo.findTrasportatoreById(idTrasportatore);
	}
	
	public void updateTrasportatore(Trasportatore trasportatore) {
		trasportatoreRepo.updateTrasportatore(trasportatore.getNome(),trasportatore.getId());
		return;
	}
	
}
