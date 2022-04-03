package comworkmanager.modelSpecifications;

import java.util.Date;

import comworkmanager.model.Fornitore;
import comworkmanager.model.QualitaProdotto;

public class AcquistoSearch {
	private Date dataInizio;
	private Date dataFine;
	private Fornitore fornitore;
	private QualitaProdotto qualitaProdotto;
	
	public AcquistoSearch(Date dataInizio, Date dataFine, Fornitore fornitore, QualitaProdotto qualitaProdotto) {
		super();
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.fornitore = fornitore;
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

	public Fornitore getFornitore() {
		return fornitore;
	}

	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	public QualitaProdotto getQualitaProdotto() {
		return qualitaProdotto;
	}

	public void setQualitaProdotto(QualitaProdotto qualitaProdotto) {
		this.qualitaProdotto = qualitaProdotto;
	}
	
	

}
