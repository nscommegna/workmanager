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
   		<h3>Lista fornitori</h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
			<table id="example">
			
	        <thead>
	            <tr>
	                <th>Nome</th>
	                <th>Cognome</th>
	                <th>Indirizzo</th>
	                <th>Partita IVA</th>
	                <th>Telefono</th>
	                <th>Codice fiscale</th>
	                <th>Citta</th>
	                <th>Provincia</th>
	                <th>CAP</th>
	                <th>Funzioni</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="fornitore" items="${fornitori }">
	            	<tr>
		            	<td>${fornitore.nome}</td>
			            <td>${fornitore.cognome}</td>
			            <td>${fornitore.indirizzo}</td>
			            <td>${fornitore.partitaIva}</td>
			            <td>${fornitore.telefono}</td>
			            <td>${fornitore.codiceFiscale}</td>
			            <td>${fornitore.citta}</td>
			            <td>${fornitore.provincia}</td>
			            <td>${fornitore.cap}</td>
			            <td><a class="btn btn-sm btn-primary" href="/fornitore/vaiModificaFornitore?idFornitore=${fornitore.id}"><i class="fa-solid fa-pen-to-square"></i></a></td>
		           </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    </div>
   </div>
</div>
<jsp:include page="../general/footer.jsp"></jsp:include>
<script>
$(document).ready(function() {
	 $('#example').DataTable({
	        dom: 'Bfrtip',
	        buttons: [
	            {
	                text: 'Aggiungi nuovo',
	                action: function ( e, dt, node, config ) {
	                	window.location.href = "/fornitore/vaiAggiungiFornitore";
	                }
	            }
	        ]
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