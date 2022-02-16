package comworkmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mezzo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false,updatable = false,name = "id")
	private Long id;
	@Column(nullable = false)
	private String targa;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	 private Trasportatore trasportatore;
	
	public Mezzo() {
		
	}
	
	
	public Mezzo(String targa) {
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


	public Trasportatore getTrasportatore() {
		return trasportatore;
	}


	public void setTrasportatore(Trasportatore trasportatore) {
		this.trasportatore = trasportatore;
	}


	@Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Mezzo )) return false;
	        return id != null && id.equals(((Mezzo) o).getId());
	    }
	    @Override
	    public int hashCode() {
	        return getClass().hashCode();
	    }


	
}
