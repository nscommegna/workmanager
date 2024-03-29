<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../general/header.jsp"></jsp:include>

<div class="container">
  <!-- Content here -->
   <div class="row">
   		<h3>Nuovo acquisto</h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
		   	<form class="row g-3" action="/acquisto/salvaAcquisto" method="POST">
				<div class="mb-3 col-md-6">
				  <label for="dataAcquisto" class="form-label">Data acquisto</label>
				  <input type="date" class="form-control" id="dataAcquisto" name="dataAcquisto" placeholder="dd/MM/yyyy" required>
				</div>
				<div class="col-md-6">
					<label for="fornitore" class="form-label">Fornitore</label><br>
					<select class="select-cliente col-md-12" data-live-search="true" name="fornitore" required>
						<c:forEach var="fornitore" items="${fornitori }">
							<option value="${fornitore.id}" data-tokens="${fornitore.cognome} ${fornitore.nome }">${fornitore.cognome} ${fornitore.nome } - ${fornitore.indirizzo}</option>
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
					<input type="number" step=".1" min="0" class="form-control" id="kili" name="kili" placeholder="Kg prodotto" required>
				</div>
				<div class="col-md-2">
					<label for="prezzo" class="form-label">Prezzo d'acquisto (&euro;)</label><br>
					<input  type="number" step=".0001" min="0" class="form-control" id="prezzo" name="prezzo" placeholder="Prezzo al kg" required>
				</div>
				<div class="col-md-2">
					<label for="totale" class="form-label">Totale</label><br>
					<input type="number"  step=".01" min="0" class="form-control" id="totale" name="totale" placeholder="Tot da pagare">
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
				<br>
				    <button type="submit"   class="btn btn-primary">Registra acquisto</button>
				 </div>
			 </form>
	    </div>
   </div>
</div>
<jsp:include page="../general/subfooter.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function() {

	$("#totale").attr("readonly", true);
	
	$('.select-cliente').selectpicker();
	$('.select-prodotto').selectpicker();
	$('.select-cantina').selectpicker();
	$('.select-mezzo').selectpicker();
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
		var totale = prezzo * kili;
		$("#totale").attr("readonly", false);
		$( "#totale" ).val(totale.toFixed(2));
		$("#totale").attr("readonly", true);
		return;
}
</script>
<jsp:include page="../general/footer.jsp"></jsp:include>