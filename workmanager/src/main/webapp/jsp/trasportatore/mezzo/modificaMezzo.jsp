<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../general/header.jsp"></jsp:include>

<div class="container">
  <!-- Content here -->
   <div class="row">
   		<h3>Modifica mezzo del trasportatore: ${mezzo.trasportatore.nome } </h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
		   	<form class="row g-3" action="/trasportatore/modificaMezzo" method="POST">
				<div class="mb-3">
				  <label for="ragioneSociale" class="form-label">Mezzo</label>
				  <input type="text" class="form-control" id="targa" name="targa" placeholder="Targa" value="${mezzo.targa }" required>
				</div>
				<input type="text" class="form-control" id="idMezzo" name="idMezzo"  value="${mezzo.id }" hidden="true">
				<div class="col-12">
				    <button type="submit" class="btn btn-primary">Salva</button>
				 </div>
			 </form>
	    </div>
   </div>
</div>
<jsp:include page="../../general/subfooter.jsp"></jsp:include>
<jsp:include page="../../general/footer.jsp"></jsp:include>
