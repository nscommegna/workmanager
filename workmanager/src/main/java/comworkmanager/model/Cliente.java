package comworkmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false,updatable = false)
	private Long id;
	@Column(nullable = false,updatable = false)
	private String ragioneSociale;
	private String citta;
	private String indirizzo;
	private String partitaIva;
	private String telefono;
	private String luogoConsegna;
	
	public Cliente() {
		
	}
	
	public Cliente(String ragioneSociale, String citta, String indirizzo, String partitaIva, String telefono,
			String luogoConsegna) {
		super();
		this.ragioneSociale = ragioneSociale;
		this.citta = citta;
		this.indirizzo = indirizzo;
		this.partitaIva = partitaIva;
		this.telefono = telefono;
		this.luogoConsegna = luogoConsegna;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
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
	public String getLuogoConsegna() {
		return luogoConsegna;
	}
	public void setLuogoConsegna(String luogoConsegna) {
		this.luogoConsegna = luogoConsegna;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", ragioneSociale=" + ragioneSociale + ", citta=" + citta + ", indirizzo="
				+ indirizzo + ", partitaIva=" + partitaIva + ", telefono=" + telefono + ", luogoConsegna="
				+ luogoConsegna + "]";
	}
	
	
}
