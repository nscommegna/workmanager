package comworkmanager.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Fornitore implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false,updatable = false)
	private Long id;
	@Column(nullable = false,updatable = false)
	private String nome;
	@Column(nullable = false,updatable = false)
	private String cognome;
	private String indirizzo;
	private String partitaIva;
	private String telefono;
	private String codiceFiscale;
	private String citta;
	private String provincia;
	private String cap;
	@OneToMany(mappedBy="fornitore")
    private List<Acquisto> acquisti;
	
	@OneToMany(mappedBy="fornitore")
    private List<Pagamento> pagamenti;
	
	public Fornitore() {
		
	}

	public Fornitore(String nome, String cognome, String indirizzo, String partitaIva, String telefono,
			String codiceFiscale, String citta, String provincia, String cap) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.partitaIva = partitaIva;
		this.telefono = telefono;
		this.codiceFiscale = codiceFiscale;
		this.citta = citta;
		this.provincia = provincia;
		this.cap = cap;
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	
	
	public List<Acquisto> getAcquisti() {
		return acquisti;
	}

	public void setAcquisti(List<Acquisto> acquisti) {
		this.acquisti = acquisti;
	}
	
	
	
	public List<Pagamento> getPagamenti() {
		return pagamenti;
	}

	public void setPagamenti(List<Pagamento> pagamenti) {
		this.pagamenti = pagamenti;
	}

	@Override
	public String toString() {
		return "Fornitore [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", indirizzo=" + indirizzo
				+ ", partitaIva=" + partitaIva + ", telefono=" + telefono + ", codiceFiscale=" + codiceFiscale
				+ ", citta=" + citta + ", provincia=" + provincia + ", cap=" + cap + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Fornitore f = (Fornitore) obj;
		return this.getId().equals(f.getId());
	}
	


	
	
}
