package comworkmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private String tipo;
	private String qualita;
	
	public Prodotto() {
		
	}
	
	
	public Prodotto(String tipo, String qualita) {
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
	public String getQualita() {
		return qualita;
	}
	public void setQualita(String qualita) {
		this.qualita = qualita;
	}


	@Override
	public String toString() {
		return "Prodotto [id=" + id + ", tipo=" + tipo + ", qualita=" + qualita + "]";
	}
	
	
	
}
