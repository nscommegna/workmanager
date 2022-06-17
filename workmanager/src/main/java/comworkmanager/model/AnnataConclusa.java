package comworkmanager.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class AnnataConclusa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false,updatable = false)
	private Long id;
	@Column(nullable = false,updatable = false)
	private Date anno;
	@Column(nullable = false,updatable = false)
	private String note;
	
	@OneToMany(mappedBy="annata")
	private List<AcquistoStorico> acquisti;
	
	@OneToMany(mappedBy="annata")
	private List<VenditaStorico> vendite;
	
	@OneToMany(mappedBy="annata")
	private List<PagamentoStorico> pagamenti;
	 
	 @OneToMany(mappedBy="mezzo")
	 private List<Vendita> vendita;
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getAnno() {
		return anno;
	}
	public void setAnno(Date anno) {
		this.anno = anno;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "AnnataConclusa [id=" + id + ", anno=" + anno + ", note=" + note + "]";
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		AcquistoStorico a = (AcquistoStorico) obj;
		return this.getId().equals(a.getId());
	}
	
	
	
}
