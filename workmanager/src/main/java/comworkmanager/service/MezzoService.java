package comworkmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comworkmanager.model.Prodotto;
import comworkmanager.model.Mezzo;
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
	
	
	public void updateProdotto(Prodotto prodotto) {
		// prodottoRepo.updateProdotto(prodotto.getTipo(),prodotto.getQualita());
		return;
	}
	
}
