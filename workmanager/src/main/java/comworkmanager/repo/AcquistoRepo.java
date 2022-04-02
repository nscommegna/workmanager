package comworkmanager.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import comworkmanager.model.Acquisto;
import comworkmanager.model.Cliente;

public interface AcquistoRepo extends JpaRepository<Acquisto, Long>{
	
//	@Modifying
//	@Transactional
//	@Query("UPDATE Cliente c SET c.ragioneSociale =:ragioneSociale, c.citta =:citta,"
//			+ " c.indirizzo =:indirizzo, c.partitaIva =:partitaIva, "
//			+ "c.telefono =:telefono, c.luogoConsegna =:luogoConsegna WHERE c.id=:id")
//	void updateCliente(@Param("ragioneSociale") String ragioneSociale,@Param("citta") String citta,
//			@Param("indirizzo") String indirizzo,@Param("partitaIva") String partitaIva,
//			@Param("telefono") String telefono,@Param("luogoConsegna") String luogoConsegna,@Param("id") Long id);
//
//	@Query("SELECT c FROM Cliente c WHERE c.id=:idCliente")
//	Cliente findClienteById(@Param("idCliente") Long idCliente);

	
}
