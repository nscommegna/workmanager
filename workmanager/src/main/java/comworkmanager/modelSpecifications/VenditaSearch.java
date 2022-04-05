package comworkmanager.modelSpecifications;

import java.util.Date;

import comworkmanager.model.Cliente;
import comworkmanager.model.QualitaProdotto;

public class VenditaSearch {
	private Date dataInizio;
	private Date dataFine;
	private Cliente cliente;
	private QualitaProdotto qualitaProdotto;
	
	public VenditaSearch(Date dataInizio, Date dataFine, Cliente cliente, QualitaProdotto qualitaProdotto) {
		super();
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.cliente = cliente;
		this.qualitaProdotto = qualitaProdotto;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public QualitaProdotto getQualitaProdotto() {
		return qualitaProdotto;
	}

	public void setQualitaProdotto(QualitaProdotto qualitaProdotto) {
		this.qualitaProdotto = qualitaProdotto;
	}
	
	

}
