<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../general/header.jsp"></jsp:include>

<div class="container">

<c:if test="${not empty msg}">
   <div id="cardMsg" class="card ${msg.tipo}">
	  <div class="card-body text-light">
	  	<button class="btn text-light" id="btn_close_msg">X</button>
	    ${msg.messaggio }
	  </div>
	</div>
  </c:if>
  <!-- Content here -->
   <div class="row">
   		<h3>Nuova qualità per il prodotto: ${prodotto.tipo}</h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
		   	<form class="row g-3" action="/qualitaProdotto/salvaQualitaProdotto" method="POST">
				<div class="mb-3">
				  <label for="qualita" class="form-label">Tipo</label>
				  <input type="text" class="form-control" id="qualita" name="qualita" placeholder="Qualita" required>
				</div>
				<input type="text" class="form-control" id="idProdotto" name="idProdotto" value="${prodotto.id }" required hidden>
				<div class="col-12">
				    <button type="submit" class="btn btn-primary">Salva</button>
				 </div>
			 </form>
	    </div>
   </div>
</div>

<jsp:include page="../../general/subfooter.jsp"></jsp:include>
<script>
$('#btn_close_msg').click(function ()
        {
            $.ajax({
                type: "post",
                url: "/qualitaProdotto/removeMessage",
                success: function(msg){      
                        console.log(msg);
                        $('#cardMsg').hide(); 
                }
            });
        });
</script>
<jsp:include page="../../general/footer.jsp"></jsp:include>