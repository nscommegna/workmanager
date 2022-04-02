package comworkmanager.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Trasportatore implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false,updatable = false,name = "id")
	private Long id;
	@Column(nullable = false)
	private String nome;
	
	@OneToMany(mappedBy = "trasportatore",fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST})
	private Set<Mezzo> mezzi  = new HashSet<>();
	
	@OneToMany(mappedBy="trasportatore")
    private List<Acquisto> acquisti;
	
	public Trasportatore() {
		
	}
	
	
	public Trasportatore(String nome) {
		super();
		this.nome = nome;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Set<Mezzo> getMezzi() {
		return mezzi;
	}
	public void setMezzi(Set<Mezzo> mezzi) {
		this.mezzi = mezzi;
	}
	
	public void addMezzo(Mezzo mezzo) {
		mezzo.setTrasportatore(this);
		this.mezzi.add(mezzo);
	}
	
	public void removeMezzo(Mezzo mezzo) {
		mezzo.setTrasportatore(null);
		this.mezzi.remove(mezzo);
	}

	
	
	
}
