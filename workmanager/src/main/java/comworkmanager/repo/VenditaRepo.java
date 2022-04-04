package comworkmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import comworkmanager.model.Vendita;

public interface VenditaRepo extends JpaRepository<Vendita, Long>,JpaSpecificationExecutor<Vendita> {
	

	
}
