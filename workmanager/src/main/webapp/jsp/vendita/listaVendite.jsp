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
   		<h3>Lista vendite</h3>
   		<div class="row">
	   		<div class="col-md-2">
	   			<button id="btnRicercaAvanzata" class="btn btn-primary pull-right"><i class="fa-solid fa-magnifying-glass"></i>Ricerca avanzata</button>
	   		</div>
   		</div>
   		<c:if test="${not empty importoTotaleVenduto }">
	   		<div class="row">
		   			<div class="col-md-4">
		   				<h5>Totale importo venduto : &euro; ${importoTotaleVenduto}</h5>
		   			</div>
		   			<div class="col-md-4">
		   				<h5>Totale quantita venduta : ${quantitaTotaleVenduta}</h5>
		   			</div>
		   			<div class="col-md-4">
		   				<h5>Media prezzo : &euro; ${mediaPrezzo}</h5>
		   			</div>
	   		</div>
   		</c:if>
   		<div id="rowRicercaAvanzata" class="row mt-2">
	   		<form class="row g-3" action="/vendita/ricercaAvanzata" method="POST">
	   			<div class="mb-3 col-md-2">
					  <label for="dataInizio" class="form-label">Data inizio</label>
					  <input name = "dataInizio" type="date" class="form-control" id="dataInizio" placeholder="dd/MM/yyyy">
				</div>
				<div class="mb-3 col-md-2">
					  <label for="dataFine" class="form-label">Data fine</label>
					  <input name = "dataFine" type="date" class="form-control" id="dataFine" placeholder="dd/MM/yyyy">
				</div>
				<div class="col-md-3">
						<label for="fornitore" class="form-label">Cliente</label><br>
						<select class="select-fornitore col-md-12" data-live-search="true" name="fornitore" required>
						<option value="-1" data-tokens="Non specificare il cliente">Non specificare il cliente</option>
							<c:forEach var="cliente" items="${clienti }">
								<option value="${cliente.id}" data-tokens="${cliente.ragioneSociale}">${cliente.ragioneSociale}</option>
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
	            	<th>Numero doc</th>
	                <th>Data Vendita</th>
	                <th>Cliente</th>
	                <th>Prodotto/Qualit&agrave;</th>
	                <th>Trasportatore</th>
	                <th>Mezzo</th>
	                <th>Kg</th>
	                <th>Prezzo di vendita</th>
	                <th>Totale parziale</th>
	                <th>Costo trasporto</th>
	                <th>Totale</th>
	                <th>Funzioni</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="vendita" items="${vendite }">
	            	<tr>
	            		<td>${vendita.numeroDocumento}</td>
		            	<td><fmt:formatDate value="${vendita.dataVendita}" pattern="dd/MM/yyyy" /></td>
		            	<td>${vendita.cliente.ragioneSociale}</td>
		            	<td>${vendita.prodotto.prodotto.tipo} - ${vendita.prodotto.qualita}</td>
		            	<td>${vendita.mezzo.trasportatore.nome}</td>
		            	<td>${vendita.mezzo.targa}</td>
		            	<td>${vendita.quantita}</td>
		            	<td>&euro; ${vendita.prezzo}</td>
		            	<td>&euro; ${vendita.totaleParziale}</td>
		            	<td>&euro; ${vendita.costoTrasporto}</td>
		            	<td>&euro; ${vendita.totale}</td>
			            <td><a class="btn btn-sm btn-primary" href="/vendita/vaiModificaVendita?idVendita=${vendita.id}"><i class="fa-solid fa-pen-to-square"></i></a></td>
		           </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    </div>
   </div>
</div>
<jsp:include page="../general/subfooter.jsp"></jsp:include>

<script>

var ricercaAvanzataHidden = true;
$(document).ready(function() {
	$("#rowRicercaAvanzata").attr("hidden", true);
	
	$('.select-fornitore').selectpicker();
	$('.select-prodotto').selectpicker();
	
	 $('#example').DataTable({
	        dom: 'Bfrtip',
	        buttons: [
	            {
	                text: 'Nuova vendita',
	                action: function ( e, dt, node, config ) {
	                	window.location.href = "/vendita/vaiAggiungiVendita";
	                }
	            },
	            {
	        		extend : 'print',
	        		text: 'Stampa',
	        		title : 'Elenco Vendite',
		            exportOptions: {
	        		    columns: function (idx, data, node) {
	    		            if (idx == 11)
	    		                return false;
	    		            return true;
	    		        }
	    			},
	        		customize: function ( doc ) {
	        		     $(doc.document.body).find('h1').css('font-size', '18pt');
	        		     $(doc.document.body).find('h1').css('text-align', 'center'); 
	        		     $(doc.document.body).css( 'font-size', '10pt' )
	        		 
	        		
	        		},
	        		orientation: 'landscape'
	            }
	        ]
	    });

	 $('#btn_close_msg').click(function ()
	            {
	                $.ajax({
	                    type: "post",
	                    url: "/vendita/removeMessage",
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