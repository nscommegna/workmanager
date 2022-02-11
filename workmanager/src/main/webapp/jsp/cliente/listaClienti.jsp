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
   		<h3>Lista clienti</h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
			<table id="example">
			
	        <thead>
	            <tr>
	                <th>Ragione Sociale</th>
	                <th>Citta</th>
	                <th>Indirizzo</th>
	                <th>Partita IVA</th>
	                <th>Telefono</th>
	                <th>Luogo consegna</th>
	                <th>Funzioni</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="cliente" items="${clienti }">
	            	<tr>
		            	<td>${cliente.ragioneSociale}</td>
			            <td>${cliente.citta}</td>
			            <td>${cliente.indirizzo}</td>
			            <td>${cliente.partitaIva}</td>
			            <td>${cliente.telefono}</td>
			            <td>${cliente.luogoConsegna}</td>
			            <td><a class="btn btn-sm btn-primary" href="/cliente/vaiModificaCliente?idCliente=${cliente.id}"><i class="fa-solid fa-pen-to-square"></i></a></td>
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
	                	window.location.href = "/cliente/vaiAggiungiCliente";
	                }
	            }
	        ]
	    });

	 $('#btn_close_msg').click(function ()
	            {
	                $.ajax({
	                    type: "post",
	                    url: "/cliente/removeMessage",
	                    success: function(msg){      
	                            console.log(msg);
	                            $('#cardMsg').hide(); 
	                    }
	                });
	            });
} );
</script>