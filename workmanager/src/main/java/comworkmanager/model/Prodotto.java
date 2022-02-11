package comworkmanager.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Prodotto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false,updatable = false,name = "id")
	private Long id;
	@Column(nullable = false)
	private String tipo;
	
	@OneToMany(mappedBy = "prodotto",fetch = FetchType.EAGER)
	private Set<QualitaProdotto> qualita;
	
	public Prodotto() {
		
	}
	
	
	public Prodotto(String tipo, Set<QualitaProdotto> qualita) {
		super();
		this.tipo = tipo;
		this.qualita = qualita;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Set<QualitaProdotto> getQualita() {
		return qualita;
	}
	public void setQualita(Set<QualitaProdotto> qualita) {
		this.qualita = qualita;
	}


	@Override
	public String toString() {
		return "Prodotto [id=" + id + ", tipo=" + tipo + ", qualita=" + qualita + "]";
	}
	
	
	
}
