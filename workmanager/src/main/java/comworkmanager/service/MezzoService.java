package comworkmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import comworkmanager.model.Mezzo;
import comworkmanager.model.Prodotto;
import comworkmanager.repo.MezzoRepo;

@Service
public class MezzoService {
	
	private final MezzoRepo mezzoRepo;
	@Autowired
	public MezzoService(MezzoRepo mezzoRepo) {
		this.mezzoRepo = mezzoRepo;
	}
	
	public Mezzo addMezzo(Mezzo mezzo) {
		return mezzoRepo.save(mezzo);
	}
	
	public List<Mezzo> findAll(){
		return mezzoRepo.findAll(Sort.by(Sort.Direction.ASC, "trasportatore.nome"));
	}
	
	public void updateProdotto(Prodotto prodotto) {
		// prodottoRepo.updateProdotto(prodotto.getTipo(),prodotto.getQualita());
		return;
	}

	public Mezzo findMezzoById(Long idMezzo) {
		return mezzoRepo.findById(idMezzo).orElse(null);
	}
	
}
