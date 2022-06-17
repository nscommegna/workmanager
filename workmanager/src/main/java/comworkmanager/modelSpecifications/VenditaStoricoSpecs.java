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
import comworkmanager.model.PagamentoStorico;
import comworkmanager.model.QualitaProdotto;
import comworkmanager.model.VenditaStorico;

public class VenditaStoricoSpecs  implements Specification<VenditaStorico>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VenditaStoricoSearch criteria;


    public VenditaStoricoSpecs(VenditaStoricoSearch ts) {
        criteria= ts;
    }
	@Override
	public Predicate toPredicate(Root<VenditaStorico> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(criteria.getIdAnnata()!=null) {
			Join<VenditaStorico, AnnataConclusa> annata = root.join("annata");
				return cb.equal(annata.get("id"), criteria.getIdAnnata());
		}
		else {
			return null;
		}
		
	}
	

	
	
}
