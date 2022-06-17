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
import comworkmanager.model.AcquistoStorico;
import comworkmanager.model.AnnataConclusa;
import comworkmanager.model.Fornitore;
import comworkmanager.model.QualitaProdotto;

public class AcquistoStoricoSpecs  implements Specification<AcquistoStorico>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AcquistoStoricoSearch criteria;


    public AcquistoStoricoSpecs(AcquistoStoricoSearch ts) {
        criteria= ts;
    }
	@Override
	public Predicate toPredicate(Root<AcquistoStorico> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(criteria.getIdAnnata()!=null) {
			Join<AcquistoStorico, AnnataConclusa> annata = root.join("annata");
				return cb.equal(annata.get("id"), criteria.getIdAnnata());
		}
		else {
			return null;
		}
		
	}
	
	
	
}
