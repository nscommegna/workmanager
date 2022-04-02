<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../general/header.jsp"></jsp:include>

<div class="container">
  <!-- Content here -->
   <div class="row">
   		<h3>Modifica prodotto</h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
		   	<form class="row g-3" action="/prodotto/modificaNomeProdotto" method="POST">
				<div class="mb-3">
				  <label for="ragioneSociale" class="form-label">Tipo</label>
				  <input type="text" class="form-control" id="tipo" name="tipo" placeholder="Tipo" value="${prodotto.tipo }" required>
				</div>
				<div class="mb-3">
				  <label for="citta" class="form-label">Qualità</label>
				  <c:forEach items="${prodotto.qualita }" var="qualita">
				  		<p>${qualita.qualita}</p>
				  </c:forEach>
				</div>
				<input type="text" class="form-control" id="idProdotto" name="idProdotto"  value="${prodotto.id }" hidden="true">
				<div class="col-12">
				    <button type="submit" class="btn btn-primary">Salva</button>
				 </div>
			 </form>
	    </div>
   </div>
</div>
<jsp:include page="../general/subfooter.jsp"></jsp:include>
<jsp:include page="../general/footer.jsp"></jsp:include>
