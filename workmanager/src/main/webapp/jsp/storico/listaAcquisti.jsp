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
   		<h3>Lista acquisti annata <fmt:formatDate value="${anno}" pattern="yyyy" /></h3>
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
			            <td><a class="btn btn-sm btn-primary" href="/acquisto/vaiModificaAcquisto?idAcquisto=${acquisto.id}"><i class="fa-solid fa-pen-to-square"></i></a></td>
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
	
	 $('#example').DataTable({
	        dom: 'Bfrtip',
	        buttons: [
	            {
	        		extend : 'print',
	        		text: 'Stampa',
	        		title : 'Elenco Acquisti anno "<fmt:formatDate value="${anno}" pattern="yyyy" />"',
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