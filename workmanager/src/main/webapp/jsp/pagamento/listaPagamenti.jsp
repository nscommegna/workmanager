<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
   		<h3>Elenco pagamenti effettuati</h3>
   </div>
   <br>
   <div class ="row">
   	<div class="col-md-4">
   		<h5>Totale importo acquistato : &euro; ${totaleImportoAcquistato}</h5>
   	</div>
   	<div class="col-md-4">
   		<h5>Totale importo pagato : &euro; ${totaleImportoPagato}</h5>
   	</div>
   	<div class="col-md-4">
   		<h5>Totale restante da pagare  : &euro; ${totaleRestanteDaPagare}</h5>
   	</div>
   </div>
   <div class="row row mt-4">
	   	<div class="col-12">
			<table id="example" class="display">
			
	        <thead>
	            <tr>
	                <th>Data pagamento</th>
	                <th>Importo</th>
	                <th>Fornitore</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="pagamento" items="${pagamenti }">
	            	<tr>
		            	<td><fmt:formatDate value="${pagamento.dataPagamento}" pattern="dd/MM/yyyy" /></td>
			            <td>&euro; ${pagamento.importo}</td>
			            <td>${pagamento.fornitore.cognome} ${pagamento.fornitore.nome} - ${pagamento.fornitore.codiceFiscale}</td>
		           </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    </div>
   </div>
</div>


<jsp:include page="../general/subfooter.jsp"></jsp:include>
<script>
$(document).ready(function() {
	 $('#example').DataTable({
	        dom: 'Bfrtip',
	        /*buttons: [
	            {
	                text: 'Aggiungi nuovo',
	                action: function ( e, dt, node, config ) {
	                	window.location.href = "/cliente/vaiAggiungiCliente";
	                }
	            }
	        ]*/
	    });

	 $('#btn_close_msg').click(function ()
	            {
	                $.ajax({
	                    type: "post",
	                    url: "/pagamento/removeMessage",
	                    success: function(msg){      
	                            console.log(msg);
	                            $('#cardMsg').hide(); 
	                    }
	                });
	            });
} );
</script>

<jsp:include page="../general/footer.jsp"></jsp:include>