package comworkmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import comworkmanager.model.Pagamento;

public interface PagamentoRepo extends JpaRepository<Pagamento, Long>{
	
	
}
