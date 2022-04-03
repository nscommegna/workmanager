<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../general/header.jsp"></jsp:include>

<div class="container">
  <!-- Content here -->
   <div class="row">
   		<h3>Modifica acquisto</h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
		   	<form class="row g-3" action="/acquisto/salvaAcquisto" method="POST">
				<div class="mb-3 col-md-6">
				  <label for="dataAcquisto" class="form-label">Data acquisto</label>
				  <input name = "data" type="date" class="form-control" id="dataAcquisto" name="dataAcquisto" value="${acquisto.dataAcquisto}" placeholder="dd/MM/yyyy">
				</div>
				<div class="col-md-6">
					<label for="fornitore" class="form-label">Fornitore</label><br>
					<select class="select-cliente col-md-12" data-live-search="true" name="fornitore" readonly>
							<option value="${acquisto.fornitore.id}" data-tokens="${acquisto.fornitore.cognome} ${acquisto.fornitore.nome }">${acquisto.fornitore.cognome} ${acquisto.fornitore.nome } - ${acquisto.fornitore.codiceFiscale}</option>
					</select>
				</div>
				<div class="col-md-6">
					<label for="prodottoQualita" class="form-label">Prodotto e Qualita</label><br>
					<select class="select-prodotto col-md-12" data-live-search="true" name="prodottoQualita" readonly required>
							<option value="${acquisto.prodotto.id}" data-tokens="${acquisto.prodotto.prodotto.tipo} - ${acquisto.prodotto.qualita }">${acquisto.prodotto.prodotto.tipo} - ${acquisto.prodotto.qualita }</option>
					</select>
				</div>
				<div class="col-md-2">
					<label for="kili" class="form-label">Quantit&agrave;(kg)</label><br>
					<input type="number" step=".1" min="0" class="form-control" id="kili" name="kili" placeholder="Kg prodotto" value="${acquisto.quantita}" readonly required>
				</div>
				<div class="col-md-2">
					<label for="prezzo" class="form-label">Prezzo (&euro;)</label><br>
					<input  type="number" step=".01" min="0" class="form-control" id="prezzo" name="prezzo" value="${acquisto.prezzo}" placeholder="Prezzo al kg" readonly required>
				</div>
				<div class="col-md-2">
					<label for="totale" class="form-label">Totale</label><br>
					<input type="number"  step=".01" min="0" class="form-control" id="totale" name="totale" value="${acquisto.totale}" placeholder="Tot da pagare">
				</div>
				<div class="col-md-6">
					<label for="cantinaDestinazione" class="form-label">Cantina di destinazione</label><br>
					<select class="select-cantina col-md-12" data-live-search="true" name="cantinaDestinazione">
						<option value="-1" data-tokens="Non specificare la cantina">Non specificare la cantina</option>
						<c:forEach var="cliente" items="${clienti}">
							<option value="${cliente.id}" data-tokens="${cliente.ragioneSociale}">${cliente.ragioneSociale}</option>
						</c:forEach>
					</select>
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
				
				
				<div class="col-12">
				    <button type="submit" class="btn btn-primary">Registra acquisto</button>
				 </div>
			 </form>
	    </div>
   </div>
</div>
<jsp:include page="../general/subfooter.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#totale").attr("readonly", true);
	calcolaTotale();
	
	$('.select-cliente').selectpicker();
	$('.select-prodotto').selectpicker();
	$('.select-cantina').selectpicker();
	$('.select-mezzo').selectpicker();

	$('.select-cantina').selectpicker('val',"${acquisto.cantinaScarico.id}");
	$('.select-mezzo').selectpicker('val',"${acquisto.mezzo.id}");
});
//gestione totaleg
$( "#kili" ).change(function() {
	  calcolaTotale();
	});
$( "#prezzo" ).change(function() {
		calcolaTotale()
	});

function calcolaTotale(){
	var prezzo = Number($( "#prezzo" ).val());
	var kili = Number($( "#kili" ).val());
	if(prezzo != 0 && kili != 0 ){
		var totale = prezzo * kili;
		$("#totale").attr("readonly", false);
		$( "#totale" ).val(totale.toFixed(2));
		$("#totale").attr("readonly", true);
		return;
	}
	if(prezzo == 0 || kili != 0 ){
		$("#totale").attr("readonly", false);
		$( "#totale" ).val('');
		$("#totale").attr("readonly", true);
		return;
	}
	if(prezzo != 0 || kili == 0 ){
		$("#totale").attr("readonly", false);
		$( "#totale" ).val('');
		$("#totale").attr("readonly", true);
		return;
	}
}
</script>
<jsp:include page="../general/footer.jsp"></jsp:include>