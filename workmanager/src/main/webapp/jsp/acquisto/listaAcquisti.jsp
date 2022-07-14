<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../general/header.jsp"></jsp:include>

<div class="container">
  <!-- Content here -->
  <c:if test="${not empty msg}">
   <div id="cardMsg" class="card ${msg.tipo}">
	  <div class="card-body text-light">
	  	<button class="btn text-light" id="btn_close_msg">X</button>
	    ${msg.messaggio }
	  </div>
	</div>
  </c:if>
   <div class="row">
   		<h3>Lista acquisti</h3>
   		<c:if test="${not empty importoTotaleAcquistato }">
	   		<div class="row">
		   			<div class="col-md-4">
		   				<h5>Totale importo acquistato : &euro; ${importoTotaleAcquistato}</h5>
		   			</div>
		   			<div class="col-md-4">
		   				<h5>Totale quantita acquistata : ${quantitaTotaleAcquistata}</h5>
		   			</div>
		   			<div class="col-md-4">
		   				<h5>Media prezzo : &euro; ${mediaPrezzo}</h5>
		   			</div>
	   		</div>
   		</c:if>
   		<div class="col-md-2">
   			<button id="btnRicercaAvanzata" class="btn btn-primary pull-right"><i class="fa-solid fa-magnifying-glass"></i>Ricerca avanzata</button>
   		</div>
   		<div id="rowRicercaAvanzata" class="row mt-2">
	   		<form class="row g-3" action="/acquisto/ricercaAvanzata" method="POST">
	   			<div class="mb-3 col-md-2">
					  <label for="dataInizio" class="form-label">Data inizio</label>
					  <input name = "dataInizio" type="date" class="form-control" id="dataInizio" placeholder="dd/MM/yyyy">
				</div>
				<div class="mb-3 col-md-2">
					  <label for="dataFine" class="form-label">Data fine</label>
					  <input name = "dataFine" type="date" class="form-control" id="dataFine" placeholder="dd/MM/yyyy">
				</div>
				<div class="col-md-3">
						<label for="fornitore" class="form-label">Fornitore</label><br>
						<select class="select-cliente col-md-12" data-live-search="true" name="fornitore" required>
						<option value="-1" data-tokens="Non specificare il fornitore">Non specificare il fornitore</option>
							<c:forEach var="fornitore" items="${fornitori }">
								<option value="${fornitore.id}" data-tokens="${fornitore.cognome} ${fornitore.nome }">${fornitore.cognome} ${fornitore.nome } - ${fornitore.codiceFiscale}</option>
							</c:forEach>
						</select>
				</div>
				<div class="col-md-3">
						<label for="prodottoQualita" class="form-label">Prodotto e Qualita</label><br>
						<select class="select-prodotto col-md-12" data-live-search="true" name="prodottoQualita" required>
							<option value="-1" data-tokens="Non specificare il prodotto">Non specificare il prodotto</option>
							<c:forEach var="qualitaProdotto" items="${qualitaProdotti}">
								<option value="${qualitaProdotto.id}" data-tokens="${qualitaProdotto.prodotto.tipo} - ${qualitaProdotto.qualita }">${qualitaProdotto.prodotto.tipo} - ${qualitaProdotto.qualita }</option>
							</c:forEach>
						</select>
				</div>
				<div class="col-md-2">
				<br>
				    <button type="submit" class="btn btn-primary">Mostra risultati</button>
				 </div>
			</form>
   		</div>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
	   	
			<table id="example" class="display">
			
	        <thead>
	            <tr>
	                <th>Data</th>
	                <th>Fornitore</th>
	                <th>Prodotto/Qualit&agrave;</th>
	                <th>Trasportatore</th>
	                <th>Mezzo</th>
	                <th>Kg</th>
	                <th>Prezzo d'acquisto</th>
	                <th>Totale</th>
	                <th>Cantina scarico</th>
	                <th>Funzioni</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="acquisto" items="${acquisti }">
	            	<tr>
		            	<td><fmt:formatDate value="${acquisto.dataAcquisto}" pattern="dd/MM/yyyy" /></td>
		            	<td>${acquisto.fornitore.cognome} ${acquisto.fornitore.nome}</td>
		            	<td>${acquisto.prodotto.prodotto.tipo} - ${acquisto.prodotto.qualita}</td>
		            	<td>${acquisto.mezzo.trasportatore.nome}</td>
		            	<td>${acquisto.mezzo.targa}</td>
		            	<td>${acquisto.quantita}</td>
		            	<td>${acquisto.prezzo}</td>
		            	<td>${acquisto.totale}</td>
		            	<td>${acquisto.cantinaScarico.ragioneSociale}</td>
			            <td>
				            <a class="btn btn-sm btn-primary" href="/acquisto/vaiModificaAcquisto?idAcquisto=${acquisto.id}"><i class="fa-solid fa-pen-to-square"></i></a>
				            <a class="btn btn-sm btn-danger" id="btnDelte" data-id="${acquisto.id}"><i class="fa-solid fa-trash-can"></i></a>
			            </td>
		           </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    </div>
   </div>
</div>

<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    <form action="/acquisto/eliminaAcquisto" method="POST">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Elimina acquisto</h5>
      </div>
      <div class="modal-body">
        <p>Sei sicuro di voler eliminare questo acquisto? Attenzione, l'azione è irreversibile.</p>
        <input type="text" id="idAcquistoElimina" name="idAcquisto" hidden >
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-primary">Elimina</button>
      </div>
      </form>
    </div>
  </div>
</div>

<jsp:include page="../general/subfooter.jsp"></jsp:include>

<script>

var ricercaAvanzataHidden = true;
$(document).ready(function() {
	
	$( "#btnDelte" ).click(function() {
		$( "#idAcquistoElimina" ).val($(this).attr("data-id"));
		$('#deleteModal').modal('toggle')
	});
	
	
	$("#rowRicercaAvanzata").attr("hidden", true);
	
	$('.select-cliente').selectpicker();
	$('.select-prodotto').selectpicker();
	
	 $('#example').DataTable({
	        dom: 'Bfrtip',
	        buttons: [
	            {
	                text: 'Nuovo acquisto',
	                action: function ( e, dt, node, config ) {
	                	window.location.href = "/acquisto/vaiAggiungiAcquisto";
	                }
	            },
	            {
	        		extend : 'print',
	        		text: 'Stampa',
	        		title : 'Elenco Acquisti',
	        		customize: function ( doc ) {
	        		     $(doc.document.body).find('h1').css('font-size', '18pt');
	        		     $(doc.document.body).find('h1').css('text-align', 'center'); 
	        		 
	        		
	        		}
	            }
	        ]
	    });

	 $('#btn_close_msg').click(function ()
	            {
	                $.ajax({
	                    type: "post",
	                    url: "/acquisto/removeMessage",
	                    success: function(msg){      
	                            console.log(msg);
	                            $('#cardMsg').hide(); 
	                    }
	                });
	            });
	 	
	 $('#btnRicercaAvanzata').click(function (){
		 if(ricercaAvanzataHidden){
	 		$("#rowRicercaAvanzata").attr("hidden", false);
	 		ricercaAvanzataHidden = false;
		 }
		 else{
			 $("#rowRicercaAvanzata").attr("hidden", true);
			 ricercaAvanzataHidden = true;
		}
	  });
} );
</script>

<jsp:include page="../general/footer.jsp"></jsp:include>