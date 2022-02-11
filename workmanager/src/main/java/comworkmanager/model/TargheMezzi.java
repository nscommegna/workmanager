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
public class TargheMezzi implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false,updatable = false,name = "id")
	private Long id;
	
	private String targa;
	
	 @ManyToOne
	 @JoinColumn(name="id_trasportatore", nullable=false,insertable = false,updatable = false)
	 private Trasportatore trasportatore;
	
	public TargheMezzi() {
		
	}
	
	
	public TargheMezzi(String targa ) {
		super();
		this.targa = targa;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public String getTarga() {
		return targa;
	}


	public void setTarga(String targa) {
		this.targa = targa;
	}


	@Override
	public String toString() {
		return "TargheMezzi [id=" + id + ", targa=" + targa + "]";
	}


	
	
	
	
	
}
