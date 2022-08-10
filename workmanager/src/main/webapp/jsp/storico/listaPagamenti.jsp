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
   		<h3>Elenco pagamenti effettuati annata <fmt:formatDate value="${anno}" pattern="yyyy" /></h3>
   </div>
   <br>
   <div class="row row mt-4">
	   	<div class="col-12">
			<table id="example" class="display">
			
	        <thead>
	            <tr>
	                <th>Data pagamento</th>
	                <th>Importo</th>
	                <th>Fornitore</th>
	                <th>Funzioni</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="pagamento" items="${pagamenti }">
	            	<tr>
		            	<td><fmt:formatDate value="${pagamento.dataPagamento}" pattern="dd/MM/yyyy" /></td>
			            <td>&euro; ${pagamento.importo}</td>
			            <td>${pagamento.fornitore.cognome} ${pagamento.fornitore.nome} - ${pagamento.fornitore.indirizzo}</td>
		           		<td></td>
		           </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    </div>
   </div>
</div>
<!-- Modal pagamento-->
<div class="modal fade" id="modalPagamento" tabindex="-1" aria-labelledby="modalPagamentoLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
       <form class="row g-3" action="/pagamento/modificaPagamento" method="POST">
	      <div class="modal-header">
	        <h5 class="modal-title" id="modalPagamentoLabel">Modifica pagamento</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        	<div class="col-md-12">
						<label for="importo" class="form-label">Importo attuale (&euro;)</label><br>
						<input type="number"  step=".01" min="0" class="form-control" id="importo" name="importo" placeholder="Importo in euro" required>
				</div>
				<input type="text"  class="form-control" id="idPagamentoModal" name="idPagamento" hidden>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annulla</button>
	        <button type="submit" onclick="this.disabled=true;this.value='Submitting...'; this.form.submit();" class="btn btn-primary">Modifica pagamento</button>
	      </div>
       </form>
    </div>
  </div>
</div>

<jsp:include page="../general/subfooter.jsp"></jsp:include>
<script>
$(document).ready(function() {
	 $('#example').DataTable({
	        dom: 'Bfrtip',
	        buttons: [
	        	{
	        		extend : 'print',
	        		text: 'Stampa',
	        		title : 'Elenco pagamenti "<fmt:formatDate value="${anno}" pattern="yyyy" />"',
	        		exportOptions: {
	        		    columns: function (idx, data, node) {
	        		            if (idx == 3)
	        		                return false;
	        		            return true;
	        		        }
	        		},
	        		customize: function ( doc ) {
	        		     $(doc.document.body).find('h1').css('font-size', '18pt');
	        		     $(doc.document.body).find('h1').css('text-align', 'center'); 
	        		 }
	        	}
	        ]
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