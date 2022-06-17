package comworkmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import comworkmanager.model.Vendita;
import comworkmanager.model.VenditaStorico;

public interface VenditaStoricoRepo extends JpaRepository<VenditaStorico, Long>,JpaSpecificationExecutor<VenditaStorico> {
	

	
}
