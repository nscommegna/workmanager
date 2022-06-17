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
public class AcquistoStorico implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false,updatable = false)
	private Long id;
	private Date dataAcquisto;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_fornitore")
	private Fornitore fornitore;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_qualita_prodotto")
	private QualitaProdotto prodotto;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_mezzo")
	private Mezzo mezzo;
	private Double quantita;
	private Double prezzo;
	private Double totale;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_annata")
	private AnnataConclusa annata;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_cantinaScarico")
	private Cliente cantinaScarico;
	
	
	public AcquistoStorico() {
		
	}

	public AcquistoStorico(Date dataAcquisto, Fornitore fornitore, QualitaProdotto prodotto, Mezzo mezzo,
			Double quantita, Double prezzo, Double totale, Cliente cantinaScarico,AnnataConclusa annata) {
		super();
		this.dataAcquisto = dataAcquisto;
		this.fornitore = fornitore;
		this.prodotto = prodotto;
		this.mezzo = mezzo;
		this.quantita = quantita;
		this.prezzo = prezzo;
		this.totale = totale;
		this.cantinaScarico = cantinaScarico;
		this.annata = annata;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataAcquisto() {
		return dataAcquisto;
	}

	public void setDataAcquisto(Date dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public Fornitore getFornitore() {
		return fornitore;
	}

	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	public QualitaProdotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(QualitaProdotto prodotto) {
		this.prodotto = prodotto;
	}


	public Mezzo getMezzo() {
		return mezzo;
	}

	public void setMezzo(Mezzo mezzo) {
		this.mezzo = mezzo;
	}

	public Double getQuantita() {
		return quantita;
	}

	public void setQuantita(Double quantita) {
		this.quantita = quantita;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public Double getTotale() {
		return totale;
	}

	public void setTotale(Double totale) {
		this.totale = totale;
	}

	public Cliente getCantinaScarico() {
		return cantinaScarico;
	}

	public void setCantinaScarico(Cliente cantinaScarico) {
		this.cantinaScarico = cantinaScarico;
	}

	
	public AnnataConclusa getAnnata() {
		return annata;
	}

	public void setAnnata(AnnataConclusa annata) {
		this.annata = annata;
	}

	@Override
	public boolean equals(Object obj) {
		Acquisto v = (Acquisto) obj;
		// TODO Auto-generated method stub
		return this.getId().equals(v.getId());
	}
	
	
}
