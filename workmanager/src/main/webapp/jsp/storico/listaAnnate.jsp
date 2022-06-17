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
   		<h3>Annate concluse</h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
			<table id="example" class="display">
			
	        <thead>
	            <tr>
	                <th>Anno</th>
	                <th>Note</th>
	                <th>Funzioni</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="annata" items="${annate }">
	            	<tr>
		            	<td><fmt:formatDate value="${annata.anno}" pattern="yyyy" /></td>
		            	<td>${annata.note}</td>
			            <td><a class="btn btn-sm btn-primary" href="/storico/vaiHomeAnnata?idAnnata=${annata.id}"><i class="fa-solid fa-eye"></i></i></a></td>
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
	 $('#example').DataTable({});

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
<jsp:include page="../general/footer.jsp"></jsp:include>