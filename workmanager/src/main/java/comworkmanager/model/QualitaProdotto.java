package comworkmanager.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class QualitaProdotto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false,updatable = false,name = "id")
	private Long id;
	@Column(nullable = false)
	private String qualita;
	
	 @ManyToOne(fetch = FetchType.EAGER)
	 private Prodotto prodotto;
	 @OneToMany(mappedBy="prodotto")
	 private List<Acquisto> acquisti;
	 
	 @OneToMany(mappedBy="prodotto")
	 private List<Vendita> vendita;
	 
	public QualitaProdotto() {
		
	}
	
	
	public QualitaProdotto(String qualita) {
		super();
		this.qualita = qualita;
		
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public String getQualita() {
		return qualita;
	}


	public void setQualita(String qualita) {
		this.qualita = qualita;
	}


	public Prodotto getProdotto() {
		return prodotto;
	}


	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}




	  @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof QualitaProdotto )) return false;
	        return id != null && id.equals(((QualitaProdotto) o).getId());
	    }
	    @Override
	    public int hashCode() {
	        return getClass().hashCode();
	    }


	
}
