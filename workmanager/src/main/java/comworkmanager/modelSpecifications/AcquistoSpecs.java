package comworkmanager.modelSpecifications;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import comworkmanager.model.Acquisto;
import comworkmanager.model.Fornitore;
import comworkmanager.model.QualitaProdotto;

public class AcquistoSpecs  implements Specification<Acquisto>{
    private AcquistoSearch criteria;


    public AcquistoSpecs(AcquistoSearch ts) {
        criteria= ts;
    }
	@Override
	public Predicate toPredicate(Root<Acquisto> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		final List<Predicate> predicates = new ArrayList<Predicate>();
		if(criteria.getDataInizio()!=null && criteria.getDataFine() != null) {
			if(criteria.getDataFine().after(criteria.getDataInizio())) {
				Path<Date> data = root.get("dataAcquisto");
				predicates.add(cb.between(root.<Date>get("dataAcquisto"), criteria.getDataInizio(), criteria.getDataFine()));
			}
		}
		if(criteria.getFornitore()!=null ) {
			Join<Acquisto, Fornitore> fornitore = root.join("fornitore");
			predicates.add(cb.equal(fornitore.get("id"), criteria.getFornitore().getId()));
		}
		if(criteria.getQualitaProdotto()!=null ) {
			Join<Acquisto, QualitaProdotto> prodotto = root.join("prodotto");
			predicates.add(cb.equal(prodotto.get("id"), criteria.getQualitaProdotto().getId()));
		}
		return cb.and(predicates.toArray(new Predicate[0]));
	}
	

	
	
}
