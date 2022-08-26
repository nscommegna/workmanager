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
		   	<div class="row">
			   	<form class="row g-3" action="/prodotto/modificaNomeProdotto" method="POST">
					<div class="mb-3">
					  <label for="ragioneSociale" class="form-label">Tipo</label>
					  <input type="text" class="form-control" id="tipo" name="tipo" placeholder="Tipo" value="${prodotto.tipo }" required>
					</div>
					<input type="text" class="form-control" id="idProdotto" name="idProdotto"  value="${prodotto.id }" hidden="true">
					<div class="col-12">
					    <button type="submit"   class="btn btn-primary">Salva</button>
					 </div>
				 </form>
			 </div>
			 <div class="row mt-2">
			 <div class="col-md-3">
			 	<a class="btn btn-primary" href="/qualitaProdotto/vaiAggiungiQualita?idProdotto=${prodotto.id }">Nuova qualit&agrave;</a>
			 </div>
			 	<div class="mb-3">
					  <table class="table">
					  	 <thead>
						    <tr>
						      <th scope="col">Qualit&agrave;</th>
						      <th scope="col">Modifica</th>
						    </tr>
						   
						  </thead>
						 <tbody>
					  		<c:forEach items="${prodotto.qualita }" var="qualita">
						  		<tr>
						  			<td>${qualita.qualita}</td>
						  			<td><a class="btn btn-sm btn-primary" href="/qualitaProdotto/vaiModificaQualitaProdotto?idQualita=${qualita.id}"><i class="fa-solid fa-pen-to-square"></i></a></td>
						  		</tr>
					  		</c:forEach>
					  	</tbody>
					  </table>
				</div>
			 </div>
	    </div>
   </div>
</div>
<jsp:include page="../general/subfooter.jsp"></jsp:include>
<jsp:include page="../general/footer.jsp"></jsp:include>
