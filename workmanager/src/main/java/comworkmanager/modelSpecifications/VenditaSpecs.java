package comworkmanager.modelSpecifications;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import comworkmanager.model.Acquisto;
import comworkmanager.model.Cliente;
import comworkmanager.model.QualitaProdotto;
import comworkmanager.model.Vendita;

public class VenditaSpecs  implements Specification<Vendita>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VenditaSearch criteria;


    public VenditaSpecs(VenditaSearch ts) {
        criteria= ts;
    }
	@Override
	public Predicate toPredicate(Root<Vendita> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		final List<Predicate> predicates = new ArrayList<Predicate>();
		if(criteria.getDataInizio()!=null && criteria.getDataFine() != null) {
			if(criteria.getDataFine().after(criteria.getDataInizio())) {
				predicates.add(cb.between(root.<Date>get("dataVendita"), criteria.getDataInizio(), criteria.getDataFine()));
			}
		}
		if(criteria.getCliente()!=null ) {
			Join<Vendita, Cliente> cliente = root.join("cliente");
			predicates.add(cb.equal(cliente.get("id"), criteria.getCliente().getId()));
		}
		if(criteria.getQualitaProdotto()!=null ) {
			Join<Acquisto, QualitaProdotto> prodotto = root.join("prodotto");
			predicates.add(cb.equal(prodotto.get("id"), criteria.getQualitaProdotto().getId()));
		}
		return cb.and(predicates.toArray(new Predicate[0]));
	}
	

	
	
}
