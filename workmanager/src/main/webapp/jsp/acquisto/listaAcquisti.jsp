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
   		<h3>Lista acquisti</h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
			<table id="example">
			
	        <thead>
	            <tr>
	                <th>Data</th>
	                <th>Cliente</th>
	                <th>Prodotto/Qualit&agrave;</th>
	                <th>Trasportatore</th>
	                <th>Mezzo</th>
	                <th>Kg</th>
	                <th>Prezzo</th>
	                <th>Totale</th>
	                <th>Cantina scarico</th>
	                <th>Funzioni</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="acquisto" items="${acquisti }">
	            	<tr>
		            	<td></td>
		            	<td></td>
		            	<td></td>
		            	<td></td>
		            	<td></td>
		            	<td></td>
		            	<td></td>
		            	<td></td>
		            	<td></td>
			            <td><a class="btn btn-sm btn-primary" href="/acquisto/vaiModificaAcquisto?idAcquisto=${acquisto.id}"><i class="fa-solid fa-pen-to-square"></i></a></td>
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
	                text: 'Nuovo acquisto',
	                action: function ( e, dt, node, config ) {
	                	window.location.href = "/acquisto/vaiAggiungiAcquisto";
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