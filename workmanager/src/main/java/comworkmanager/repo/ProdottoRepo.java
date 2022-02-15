package comworkmanager.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import comworkmanager.model.Prodotto;

public interface ProdottoRepo extends JpaRepository<Prodotto, Long>{
	
	@Modifying
	@Transactional
	@Query("UPDATE Prodotto p SET p.tipo =:tipo where p.id =:id")
	void updateProdotto(@Param("tipo") String tipo,@Param("id") Long id);

	@Query("SELECT p FROM Prodotto p WHERE p.id=:idProdotto")
	Prodotto findProdottoById(@Param("idProdotto") Long idProdotto);

	
}
