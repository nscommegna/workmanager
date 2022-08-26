<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../general/header.jsp"></jsp:include>

<div class="container">
  <!-- Content here -->
   <div class="row">
   		<h3>Nuova vendita</h3>
   </div>
    <div class="row">
   		<h5>Kg di prodotto da vendere totale: ${totaleQuantitaProdottoDaVendere}</h5>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
		   	<form class="row g-3" action="/vendita/salvaVendita" method="POST">
				<div class="col-md-3">
					<label for="numeroDoc" class="form-label">Numero documento</label><br>
					<input type="text" class="form-control" maxlength="10" id="numeroDoc" name="numeroDoc" placeholder="Numero doc" required>
				</div>
				<div class="mb-3 col-md-3">
				  <label for="dataVendita" class="form-label">Data vendita</label>
				  <input type="date" class="form-control" id="dataVendita" name="dataVendita" placeholder="dd/MM/yyyy" required>
				</div>
				<div class="col-md-6">
					<label for="cliente" class="form-label">Cliente</label><br>
					<select class="select-cliente col-md-12" data-live-search="true" name="cliente" required>
						<c:forEach var="cliente" items="${clienti}">
							<option value="${cliente.id}" data-tokens="${cliente.ragioneSociale}">${cliente.ragioneSociale}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-6">
					<label for="prodottoQualita" class="form-label">Prodotto e Qualita</label><br>
					<select class="select-prodotto col-md-12" data-live-search="true" name="prodottoQualita" required>
						<c:forEach var="qualitaProdotto" items="${qualitaProdotti}">
							<option value="${qualitaProdotto.id}" data-tokens="${qualitaProdotto.prodotto.tipo} - ${qualitaProdotto.qualita }">${qualitaProdotto.prodotto.tipo} - ${qualitaProdotto.qualita }</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-2">
					<label for="kili" class="form-label">Quantit&agrave;(kg)</label><br>
					<input type="number" max="${totaleQuantitaProdottoDaVendere}" step=".1" min="0" class="form-control" id="kili" name="kili" placeholder="Kg prodotto" required>
				</div>
				<div class="col-md-2">
					<label for="prezzo" class="form-label">Prezzo di vendita (&euro;)</label><br>
					<input  type="number" step=".0001" min="0" class="form-control" id="prezzo" name="prezzo" placeholder="Prezzo al kg" required>
				</div>
				<div class="col-md-2">
					<label for="totaleParziale" class="form-label">Totale parziale</label><br>
					<input type="number"  step=".01" min="0" class="form-control" id="totaleParziale" name="totaleParziale" placeholder="Totale parziale">
				</div>
				<div class="col-md-6">
					<label for="mezzo" class="form-label">Mezzo e Trasportatore</label><br>
					<select class="select-mezzo col-md-12" data-live-search="true" name="mezzo">
						<option value="-1" data-tokens="Non specificare il mezzo">Non specificare il mezzo</option>
						<c:forEach var="mezzo" items="${mezzi}">
							<option value="${mezzo.id}" data-tokens="${mezzo.trasportatore.nome} - ${mezzo.targa}">${mezzo.trasportatore.nome} - ${mezzo.targa}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-2">
					<label for="costoTrasporto" class="form-label">Costo trasporto al kg (&euro;)</label><br>
					<input  type="number" step=".0001" min="0" class="form-control" id="costoTrasporto" name="costoTrasporto" placeholder="Costo trasporto" required>
				</div>
				<div class="col-md-2">
					<label for="totale" class="form-label">Totale trasporto</label><br>
					<input type="number"  step=".01" min="0" class="form-control" id="totaleTrasporto" name="totaleTrasporto" placeholder="Totale trasporto">
				</div>
				<div class="col-md-2">
					<label for="totale" class="form-label">Totale</label><br>
					<input type="number"  step=".01" min="0" class="form-control" id="totale" name="totale" placeholder="Tot da incassare">
				</div>
				<div class="col-12">
				<br>
				    <button type="submit"  class="btn btn-primary">Registra vendita</button>
				 </div>
			 </form>
	    </div>
   </div>
</div>
<jsp:include page="../general/subfooter.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function() {

	$("#totaleParziale").attr("readonly", true);
	$("#totale").attr("readonly", true);
	$("#totaleTrasporto").attr("readonly", true);
	
	$('.select-cliente').selectpicker();
	$('.select-prodotto').selectpicker();
	$('.select-cantina').selectpicker();
	$('.select-mezzo').selectpicker();
});
//gestione totaleg
$( "#kili" ).change(function() {
	calcolaTotaleParziale();
	calcolaTotaleTrasporto();
	calcolaTotale();
	});
$( "#prezzo" ).change(function() {
	calcolaTotaleParziale()
	calcolaTotale();
	});
$( "#costoTrasporto" ).change(function() {
	calcolaTotaleTrasporto();
	calcolaTotale();
	});

function calcolaTotaleParziale(){
	var prezzo = Number($( "#prezzo" ).val());
	var kili = Number($( "#kili" ).val());
		var totale = prezzo * kili;
		$("#totaleParziale").attr("readonly", false);
		$( "#totaleParziale" ).val(totale.toFixed(2));
		$("#totaleParziale").attr("readonly", true);
		return;
}

function calcolaTotale(){
	var totaleTrasporto = Number($( "#totaleTrasporto" ).val());
	var totaleParziale = Number($( "#totaleParziale" ).val());
	if(totaleTrasporto != 0){
		var totale = totaleTrasporto + totaleParziale;
		$("#totale").attr("readonly", false);
		$( "#totale" ).val(totale.toFixed(2));
		$("#totale").attr("readonly", true);
		return;
	}
	else{
		$( "#totale" ).val('');
	}
	
}

function calcolaTotaleTrasporto(){
	var costoTrasporto = Number($( "#costoTrasporto" ).val());
	var kili = Number($( "#kili" ).val());
	if(kili != 0 && costoTrasporto != 0){
		var totaleNoIva = costoTrasporto * kili;
		var iva = totaleNoIva * 0.22;
		var totale = totaleNoIva + iva;
		$("#totaleTrasporto").attr("readonly", false);
		$( "#totaleTrasporto" ).val(totale.toFixed(2));
		$("#totaleTrasporto").attr("readonly", true);
		return;
	}
	else{
		$( "#totaleTrasporto" ).val('');
	}
}
</script>
<jsp:include page="../general/footer.jsp"></jsp:include>