package comworkmanager.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pagamento implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false,updatable = false)
	private Long id;
	private Date dataPagamento;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_fornitore")
	private Fornitore fornitore;
	
	private Double importo;
	
	
	public Pagamento() {
		
	}


	public Pagamento(Date dataPagamento, Fornitore fornitore, Double importo) {
		super();
		this.dataPagamento = dataPagamento;
		this.fornitore = fornitore;
		this.importo = importo;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDataPagamento() {
		return dataPagamento;
	}


	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}


	public Fornitore getFornitore() {
		return fornitore;
	}


	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}


	public Double getImporto() {
		return importo;
	}


	public void setImporto(Double importo) {
		this.importo = importo;
	}

	
	
	@Override
	public String toString() {
		return "Pagamento [id=" + id + ", dataPagamento=" + dataPagamento + ", fornitore=" + fornitore + ", importo="
				+ importo + "]";
	}

	
	
	
	
}
