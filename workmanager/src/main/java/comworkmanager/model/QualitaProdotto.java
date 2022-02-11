package comworkmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
	 @ManyToOne
	 @JoinColumn(name="id_prodotto", nullable=false,insertable = false,updatable = false)
	 private Prodotto prodotto;
	
	public QualitaProdotto() {
		
	}
	
	
	public QualitaProdotto(String qualita,Prodotto p ) {
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
	public String toString() {
		return "QualitaProdotto [id=" + id + ", qualita=" + qualita + ", prodotto=" + prodotto + "]";
	}


	



	
	
	
	
	
}
