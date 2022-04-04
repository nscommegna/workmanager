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
   		<h3>Lista acquisti effettuati dal fornitore:  </h3>
   		<h4>${fornitore.cognome } ${fornitore.nome } - ${fornitore.codiceFiscale }</h4>
   </div>
     <div class="row mt-2">
   		<h5>Pagati &euro; ${totalePagato } in ${nPagamenti} soluzioni</h5>
   </div>
    <div class="row mt-2">
   		<h5>Restante da pagare: &euro; ${restanteDaPagare }</h5>
   </div>
   <br>
   <div class="row">
	   	<div class="col-md-12">
			<table id="example">
			
	        <thead>
	            <tr>
	                <th>Data Acquisto</th>
	                <th>Quantita</th>
	                <th>Prezzo d'acquisto</th>
	                <th>Totale da pagare</th>
	                
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="acquisto" items="${fornitore.acquisti }">
	            	<tr>
		            	<td><fmt:formatDate value="${acquisto.dataAcquisto}" pattern="dd/MM/yyyy" /></td>
			            <td>${acquisto.quantita} kg</td>
			            <td>&euro; ${acquisto.prezzo}</td>
			            <td>${acquisto.totale}</td>
		           </tr>
	            </c:forEach>
	        </tbody>
	        <tfoot>
            <tr>
                <th colspan="3" style="text-align:right">Totale :</th>
                <th></th>
            </tr>
        </tfoot>
	    </table>
	    </div>
   </div>
</div>

<!-- Modal pagamento-->
<div class="modal fade" id="modalPagamento" tabindex="-1" aria-labelledby="modalPagamentoLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
       <form class="row g-3" action="/fornitore/effettuaPagamento" method="POST">
	      <div class="modal-header">
	        <h5 class="modal-title" id="modalPagamentoLabel">Effettua pagamento</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        	<div class="col-md-12">
						<label for="importo" class="form-label">Importo da pagare (&euro;)</label><br>
						<input type="number" max="${restanteDaPagare}" step=".1" min="0" class="form-control" id="importo" name="importo" placeholder="Importo in euro" required>
				</div>
				<input type="text"  class="form-control" id="idFornitore" name="idFornitore" value="${fornitore.id }" hidden>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Save changes</button>
	      </div>
       </form>
    </div>
  </div>
</div>

<jsp:include page="../general/subfooter.jsp"></jsp:include>
<script>
$(document).ready(function() {
	
	jQuery.fn.dataTable.Api.register( 'sum()', function ( ) {
	    return this.flatten().reduce( function ( a, b ) {
	        if ( typeof a === 'string' ) {
	            a = a.replace(/[^\d.-]/g, '') * 1;
	        }
	        if ( typeof b === 'string' ) {
	            b = b.replace(/[^\d.-]/g, '') * 1;
	        }
	 
	        return a + b;
	    }, 0 );
	} );
	
	 $('#example').DataTable({
		 "columnDefs": [
             { className: "crdr", "targets": [3] }
           ],
	        dom: 'Bfrtip',
	        buttons: [
	            {
	                text: 'Effettua pagamento',
	                action: function ( e, dt, node, config ) {
	                	$('#modalPagamento').modal('show');
	                }
	            }
	        ],
	        "footerCallback": function ( row, data, start, end, display ) {
	            var api = this.api(), data;
	 
	            // Remove the formatting to get integer data for summation
	            var intVal = function ( i ) {
	                return typeof i === 'string' ?
	                    i.replace(/[\$,]/g, '')*1 :
	                    typeof i === 'number' ?
	                        i : 0;
	            };
	 
	            // Total over all pages
	            total = api
	                .column( 3 )
	                .data()
	                .reduce( function (a, b) {
	                    return intVal(a) + intVal(b);
	                } );
	 
	            /* Total over this page
	            pageTotal = api
	                .column( 4, { page: 'current'} )
	                .data()
	                .reduce( function (a, b) {
	                    return intVal(a) + intVal(b);
	                }, 0 );*/
	 
	            // Update footer
	            $( api.column( 3 ).footer() ).html(
	                '&euro; '+ total
	            );
	        }
	    });

	 $('#btn_close_msg').click(function ()
	            {
	                $.ajax({
	                    type: "post",
	                    url: "/fornitore/removeMessage",
	                    success: function(msg){      
	                            console.log(msg);
	                            $('#cardMsg').hide(); 
	                    }
	                });
	            });
} );
</script>
<jsp:include page="../general/footer.jsp"></jsp:include>