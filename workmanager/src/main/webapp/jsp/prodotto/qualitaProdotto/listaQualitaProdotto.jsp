<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<jsp:include page="../../general/header.jsp"></jsp:include>

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
   		<h3>Lista prodotti gestiti</h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
			<table id="example" class="display">
			
	        <thead>
	            <tr>
	                <th>Tipo</th>
	                <th>Qualita</th>
	                <th>Funzioni</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="prodotto" items="${prodotti }">
	            	<tr>
		            	<td>${prodotto.tipo}</td>
		            	<td>
		            		<c:forEach var="qualita" items="${prodotto.qualita }">
			            		${qualita.qualita}<br>
			           		</c:forEach>
			            </td>
			           
			            <td><a class="btn btn-sm btn-primary" href="/prodotto/vaiModificaProdotto?idProdotto=${prodotto.id}"><i class="fa-solid fa-pen-to-square"></i></a></td>
		           </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    </div>
   </div>
</div>
<jsp:include page="../../general/subfooter.jsp"></jsp:include>
<script>
$(document).ready(function() {
	 $('#example').DataTable({
	        dom: 'Bfrtip',
	        buttons: [
	            {
	                text: 'Aggiungi nuovo',
	                action: function ( e, dt, node, config ) {
	                	window.location.href = "/prodotto/vaiAggiungiProdotto";
	                }
	            }
	        ]
	    });

	 $('#btn_close_msg').click(function ()
	            {
	                $.ajax({
	                    type: "post",
	                    url: "/prodotto/removeMessage",
	                    success: function(msg){      
	                            console.log(msg);
	                            $('#cardMsg').hide(); 
	                    }
	                });
	            });
} );
</script>
<jsp:include page="../../general/footer.jsp"></jsp:include>