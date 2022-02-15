package comworkmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comworkmanager.model.Prodotto;
import comworkmanager.model.QualitaProdotto;
import comworkmanager.repo.QualitaProdottoRepo;

@Service
public class QualitaProdottoService {
	
	private final QualitaProdottoRepo qualitaProdottoRepo;
	@Autowired
	public QualitaProdottoService(QualitaProdottoRepo qualitaProdottoRepo) {
		this.qualitaProdottoRepo = qualitaProdottoRepo;
	}
	
	public QualitaProdotto addQualitaProdotto(QualitaProdotto qualitaProdotto) {
		return qualitaProdottoRepo.save(qualitaProdotto);
	}
	
	
	public void updateProdotto(Prodotto prodotto) {
		// prodottoRepo.updateProdotto(prodotto.getTipo(),prodotto.getQualita());
		return;
	}
	
}
