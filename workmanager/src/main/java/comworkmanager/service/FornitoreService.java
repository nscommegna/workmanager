package comworkmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import comworkmanager.model.Fornitore;
import comworkmanager.repo.FornitoreRepo;

@Service
public class FornitoreService {
	
	private final FornitoreRepo fornitoreRepo;
	@Autowired
	public FornitoreService(FornitoreRepo fornitoreRepo) {
		this.fornitoreRepo = fornitoreRepo;
	}
	
	public Fornitore addFornitore(Fornitore fornitore) {
		return fornitoreRepo.save(fornitore);
	}
	
	public List<Fornitore> findAllFornitori(){
		return fornitoreRepo.findAll();
	}
	
	public Fornitore findFornitoreById(Long idFornitore) {
		 return fornitoreRepo.findFornitoreById(idFornitore);
	}
	
	public void updateFornitore(Fornitore fornitore) {
		 fornitoreRepo.updateFornitore(fornitore.getNome(),fornitore.getCognome(),fornitore.getCodiceFiscale(),fornitore.getPartitaIva(),
				 fornitore.getTelefono(),fornitore.getIndirizzo(),fornitore.getCitta(),fornitore.getProvincia(),fornitore.getCap(),fornitore.getId());
	}
	
	public List<Fornitore> findAllFornitoriOrderByCognomeAsc(){
		return fornitoreRepo.findAll(Sort.by(Sort.Direction.ASC,"cognome"));
	}
}
