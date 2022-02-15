package comworkmanager.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import comworkmanager.model.Fornitore;

public interface FornitoreRepo extends JpaRepository<Fornitore, Long>{
	
	@Modifying
	@Transactional
	@Query("UPDATE Fornitore c SET c.nome =:nome, c.cognome =:cognome,"
			+ " c.indirizzo =:indirizzo, c.partitaIva =:partitaIva, "
			+ "c.telefono =:telefono, c.cap =:cap ,c.codiceFiscale =:codiceFiscale ,c.citta =:citta ,"
			+ "c.provincia =:provincia  WHERE c.id=:id")
	void updateFornitore(@Param("nome") String nome,@Param("cognome") String cognome, @Param("codiceFiscale") String codiceFiscale,
			@Param("partitaIva") String partitaIva,@Param("telefono") String telefono,
			@Param("indirizzo") String indirizzo,@Param("citta") String citta,@Param("provincia") String provincia,@Param("cap")String cap,@Param("id")Long id);

	@Query("SELECT c FROM Fornitore c WHERE c.id=:idFornitore")
	Fornitore findFornitoreById(@Param("idFornitore") Long idFornitore);

	
}
