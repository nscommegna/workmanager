<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../general/header.jsp"></jsp:include>

<div class="container">
  <!-- Content here -->
   <div class="row">
   		<h3>Modifica trasportatore</h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
		   	<div class="row">
			   	<form class="row g-3" action="/trasportatore/modificaNomeTrasportatore" method="POST">
					<div class="mb-3">
					  <label for="ragioneSociale" class="form-label">Nome</label>
					  <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome" value="${trasportatore.nome }" required>
					</div>
					<input type="text" class="form-control" id="idTrasportatore" name="idTrasportatore"  value="${trasportatore.id }" hidden="true">
					<div class="col-12">
					    <button type="submit" class="btn btn-primary">Salva</button>
					 </div>
				 </form>
			 </div>
			 <div class="row mt-2">
			 <div class="col-md-3">
			 	<a class="btn btn-primary" href="/trasportatore/vaiAggiungiMezzo?idTrasportatore=${trasportatore.id }">Nuovo mezzo</a>
			 </div>
			 	<div class="mb-3">
					  <table class="table">
					  	 <thead>
						    <tr>
						      <th scope="col">Mezzo</th>
						      <th scope="col">Modifica</th>
						    </tr>
						   
						  </thead>
						 <tbody>
					  		<c:forEach items="${trasportatore.mezzi }" var="mezzo">
						  		<tr>
						  			<td>${mezzo.targa}</td>
						  			<td><a class="btn btn-sm btn-primary" href="/trasportatore/vaiModificaMezzo?idMezzo=${mezzo.id}"><i class="fa-solid fa-pen-to-square"></i></a></td>
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
