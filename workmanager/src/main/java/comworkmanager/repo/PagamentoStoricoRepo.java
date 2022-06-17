package comworkmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import comworkmanager.model.PagamentoStorico;

public interface PagamentoStoricoRepo extends JpaRepository<PagamentoStorico, Long>,JpaSpecificationExecutor<PagamentoStorico>{
	
	
}
