package comworkmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comworkmanager.model.Prodotto;
import comworkmanager.repo.ProdottoRepo;

@Service
public class ProdottoService {
	
	private final ProdottoRepo prodottoRepo;
	@Autowired
	public ProdottoService(ProdottoRepo prodottoRepo) {
		this.prodottoRepo = prodottoRepo;
	}
	
	public Prodotto addProdotto(Prodotto prodotto) {
		return prodottoRepo.save(prodotto);
	}
	
	public List<Prodotto> findAllProdotti(){
		return prodottoRepo.findAll();
	}
	
	public Prodotto findProdottoById(Long idProdotto) {
		 return prodottoRepo.findProdottoById(idProdotto);
	}
	
	public void updateProdotto(Prodotto prodotto) {
		prodottoRepo.updateProdotto(prodotto.getTipo(),prodotto.getId());
		return;
	}
	
}
