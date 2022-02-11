package comworkmanager.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
	private String nome;
	
	@OneToMany(mappedBy = "trasportatore",fetch = FetchType.EAGER)
	private Set<TargheMezzi> targhe; 
	
	public Trasportatore() {
		
	}
	
	
	public Trasportatore(String nome, List<TargheMezzi> targhe ) {
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

	@Override
	public String toString() {
		return "Trasportatore [id=" + id + ", nome=" + nome + ", targhe=" + targhe + "]";
	}
	
	
	
	
	
}
