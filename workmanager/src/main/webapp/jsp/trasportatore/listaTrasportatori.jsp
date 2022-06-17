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
   		<h3>Lista trasportatori</h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
			<table id="example" class="display">
			
	        <thead>
	            <tr>
	                <th>Nome</th>
	                <th>Mezzi</th>
	                <th>Funzioni</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="trasportatore" items="${trasportatori }">
	            	<tr>
		            	<td>${trasportatore.nome}</td>
		            	<td>
		            		<c:forEach var="mezzo" items="${trasportatore.mezzi }">
			            		${mezzo.targa}<br>
			           		</c:forEach>
			            </td>
			           
			            <td><a class="btn btn-sm btn-primary" href="/trasportatore/vaiModificaTrasportatore?idTrasportatore=${trasportatore.id}"><i class="fa-solid fa-pen-to-square"></i></a></td>
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
	        buttons: [
	            {
	                text: 'Aggiungi nuovo',
	                action: function ( e, dt, node, config ) {
	                	window.location.href = "/trasportatore/vaiAggiungiTrasportatore";
	                }
	            }
	        ]
	    });

	 $('#btn_close_msg').click(function ()
	            {
	                $.ajax({
	                    type: "post",
	                    url: "/trasportatore/removeMessage",
	                    success: function(msg){      
	                            console.log(msg);
	                            $('#cardMsg').hide(); 
	                    }
	                });
	            });
} );
</script>
<jsp:include page="../general/footer.jsp"></jsp:include>