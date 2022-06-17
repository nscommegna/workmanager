package comworkmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import comworkmanager.model.Acquisto;
import comworkmanager.model.AcquistoStorico;

public interface AcquistoStoricoRepo extends JpaRepository<AcquistoStorico, Long>,JpaSpecificationExecutor<AcquistoStorico> {
	

	
}
