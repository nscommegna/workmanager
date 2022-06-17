package comworkmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import comworkmanager.model.PagamentoStorico;
import comworkmanager.modelSpecifications.PagamentoStoricoSpecs;
import comworkmanager.repo.PagamentoStoricoRepo;

@Service
public class PagamentoStoricoService {
	
	private final PagamentoStoricoRepo pagamentoRepo;
	@Autowired
	public PagamentoStoricoService(PagamentoStoricoRepo pagamentoRepo) {
		this.pagamentoRepo = pagamentoRepo;
	}
	
	public PagamentoStorico addPagamento(PagamentoStorico pagamento) {
		return pagamentoRepo.save(pagamento);
	}
	
	public List<PagamentoStorico> findAll(){
		return pagamentoRepo.findAll(Sort.by(Sort.Direction.DESC, "dataPagamento"));
	}
	
	public List<PagamentoStorico> findAll(PagamentoStoricoSpecs as){
		return pagamentoRepo.findAll(as);
	}
	
}
