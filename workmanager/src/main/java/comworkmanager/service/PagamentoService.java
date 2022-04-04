package comworkmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import comworkmanager.model.Acquisto;
import comworkmanager.model.Pagamento;
import comworkmanager.repo.PagamentoRepo;

@Service
public class PagamentoService {
	
	private final PagamentoRepo pagamentoRepo;
	@Autowired
	public PagamentoService(PagamentoRepo pagamentoRepo) {
		this.pagamentoRepo = pagamentoRepo;
	}
	
	public Pagamento addPagamento(Pagamento pagamento) {
		return pagamentoRepo.save(pagamento);
	}
	
	public List<Pagamento> findAll(){
		return pagamentoRepo.findAll(Sort.by(Sort.Direction.DESC, "dataPagamento"));
	}
	

	public Pagamento findPagamentoById(Long idPagamento) {
		return pagamentoRepo.findById(idPagamento).orElse(null);
	}

	public Double getSumImportoPagato() {
		List<Double> importi = new ArrayList<Double>();
		Double tot = 0.00;
		for(Pagamento a:this.findAll()) {
			importi.add(Double.valueOf(a.getImporto()));
		}
		for(Double importo : importi) {
			tot += importo;
		}
		return tot;
	}
	
}
