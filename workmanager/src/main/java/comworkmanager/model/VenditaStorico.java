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
public class VenditaStorico implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false,updatable = false)
	private Long id;
	private Date dataVendita;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_cliente")
	private Cliente cliente;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_qualita_prodotto")
	private QualitaProdotto prodotto;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_mezzo")
	private Mezzo mezzo;
	private String numeroDocumento;
	private Double quantita;
	private Double prezzo;
	private Double totaleParziale;
	private Double costoTrasporto;
	private Double totale;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_annata")
	private AnnataConclusa annata;
	
	public VenditaStorico() {
		
	}


	public VenditaStorico(Date dataVendita, Cliente cliente, QualitaProdotto prodotto, Mezzo mezzo, String numeroDocumento,
			Double quantita, Double prezzo, Double totaleParziale, Double costoTrasporto, 
			Double totale,AnnataConclusa annata) {
		super();
		this.dataVendita = dataVendita;
		this.cliente = cliente;
		this.prodotto = prodotto;
		this.mezzo = mezzo;
		this.numeroDocumento = numeroDocumento;
		this.quantita = quantita;
		this.prezzo = prezzo;
		this.totaleParziale = totaleParziale;
		this.costoTrasporto = costoTrasporto;
		this.totale = totale;
		this.annata = annata;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDataVendita() {
		return dataVendita;
	}


	public void setDataVendita(Date dataVendita) {
		this.dataVendita = dataVendita;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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


	public String getNumeroDocumento() {
		return numeroDocumento;
	}


	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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


	public Double getTotaleParziale() {
		return totaleParziale;
	}


	public void setTotaleParziale(Double totaleParziale) {
		this.totaleParziale = totaleParziale;
	}


	public Double getCostoTrasporto() {
		return costoTrasporto;
	}


	public void setCostoTrasporto(Double costoTrasporto) {
		this.costoTrasporto = costoTrasporto;
	}


	public Double getTotale() {
		return totale;
	}


	public void setTotale(Double totale) {
		this.totale = totale;
	}
	
	

	public AnnataConclusa getAnnata() {
		return annata;
	}


	public void setAnnata(AnnataConclusa annata) {
		this.annata = annata;
	}


	@Override
	public String toString() {
		return "Vendita [id=" + id + ", dataVendita=" + dataVendita + ", cliente=" + cliente + ", prodotto=" + prodotto
				+ ", mezzo=" + mezzo + ", numeroDocumento=" + numeroDocumento + ", quantita=" + quantita + ", prezzo="
				+ prezzo + ", totaleParziale=" + totaleParziale + ", costoTrasporto=" + costoTrasporto + ", totale="
				+ totale + "]";
	}


	@Override
	public boolean equals(Object obj) {
		VenditaStorico v = (VenditaStorico) obj;
		return this.getId().equals(v.getId());
	}

	
	
	
	
	
}
