package comworkmanager.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Acquisto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false,updatable = false)
	private Long id;
	private Date dataAcquisto;
	@ManyToOne
    @JoinColumn(name="id_fornitore")
	private Fornitore fornitore;
	private Prodotto prodotto;
	@ManyToOne
    @JoinColumn(name="id_trasportatore")
	private Trasportatore trasportatore;
	@ManyToOne
    @JoinColumn(name="id_mezzo")
	private Mezzo mezzo;
	private String quantita;
	private String prezzo;
	private String totale;
	@ManyToOne
    @JoinColumn(name="id_cantinaScarico")
	private Cliente cantinaScarico;
	
	public Acquisto() {
		
	}

	public Acquisto(Date dataAcquisto, Fornitore fornitore, Prodotto prodotto, Trasportatore trasportatore, Mezzo mezzo,
			String quantita, String prezzo, String totale, Cliente cantinaScarico) {
		super();
		this.dataAcquisto = dataAcquisto;
		this.fornitore = fornitore;
		this.prodotto = prodotto;
		this.trasportatore = trasportatore;
		this.mezzo = mezzo;
		this.quantita = quantita;
		this.prezzo = prezzo;
		this.totale = totale;
		this.cantinaScarico = cantinaScarico;
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

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public Trasportatore getTrasportatore() {
		return trasportatore;
	}

	public void setTrasportatore(Trasportatore trasportatore) {
		this.trasportatore = trasportatore;
	}

	public Mezzo getMezzo() {
		return mezzo;
	}

	public void setMezzo(Mezzo mezzo) {
		this.mezzo = mezzo;
	}

	public String getQuantita() {
		return quantita;
	}

	public void setQuantita(String quantita) {
		this.quantita = quantita;
	}

	public String getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}

	public String getTotale() {
		return totale;
	}

	public void setTotale(String totale) {
		this.totale = totale;
	}

	public Cliente getCantinaScarico() {
		return cantinaScarico;
	}

	public void setCantinaScarico(Cliente cantinaScarico) {
		this.cantinaScarico = cantinaScarico;
	}

	@Override
	public String toString() {
		return "Acquisto [id=" + id + ", dataAcquisto=" + dataAcquisto + ", fornitore=" + fornitore + ", prodotto="
				+ prodotto + ", trasportatore=" + trasportatore + ", mezzo=" + mezzo + ", quantita=" + quantita
				+ ", prezzo=" + prezzo + ", totale=" + totale + ", cantinaScarico=" + cantinaScarico + "]";
	}
	
	
	
	
}
