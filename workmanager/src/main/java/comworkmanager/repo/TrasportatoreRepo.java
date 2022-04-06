package comworkmanager.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import comworkmanager.model.Prodotto;
import comworkmanager.model.Trasportatore;

public interface TrasportatoreRepo extends JpaRepository<Trasportatore, Long>{
	
	@Modifying
	@Transactional
	@Query("UPDATE Trasportatore t  SET t.nome =:nome where t.id =:id")
	void updateTrasportatore(@Param("nome") String nome,@Param("id") Long id);

	@Query("SELECT t FROM Trasportatore t WHERE t.id=:idTrasportatore")
	Trasportatore findTrasportatoreById(@Param("idTrasportatore") Long idTrasportatore);

	
}
