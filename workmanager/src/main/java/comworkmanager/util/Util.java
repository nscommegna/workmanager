package comworkmanager.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import comworkmanager.model.Acquisto;
import comworkmanager.model.Pagamento;
import comworkmanager.model.Vendita;

public class Util {

	public static String capitalizeString(String stringa) {
		if(stringa.length()<=0) {
			return "";
		}
		String str = stringa.toLowerCase();
		String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
		return cap;
	}
	
	public static Double calcolaTotalePagamenti(List<Pagamento> pagamenti) {
		Double tot = 0.00;
		for(Pagamento p : pagamenti) {
			tot += p.getImporto();
		}
		return roundTo2Digit(tot);
		
	}
	
	public static Double calcolaTotaleAcquisti(List<Acquisto> acquisto) {
		Double tot = 0.00;
		for(Acquisto p : acquisto) {
			tot += p.getTotale();
		}
	    return roundTo2Digit(tot);
		
	}
	
	public static Double calcolaTotaleQuantitaProdottoAcquistata(List<Acquisto> acquisto) {
		Double tot = 0.00;
		for(Acquisto p : acquisto) {
			tot += p.getQuantita();
		}
	    return roundTo2Digit(tot);
		
	}
	
	public static Double calcolaTotaleVendite(List<Vendita> vendite) {
		Double tot = 0.00;
		for(Vendita p : vendite) {
			tot += p.getTotaleParziale();
		}
	    return roundTo2Digit(tot);
		
	}
	
	public static Double calcolaTotaleQuantitaProdottoVenduta(List<Vendita> vendita) {
		Double tot = 0.00;
		for(Vendita p : vendita) {
			tot += p.getQuantita();
		}
	    return roundTo2Digit(tot);
	}
	
	public static Double roundTo2Digit(Double value) {
		BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public static Double roundTo4Digit(Double value) {
		BigDecimal bd = new BigDecimal(value).setScale(4, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
