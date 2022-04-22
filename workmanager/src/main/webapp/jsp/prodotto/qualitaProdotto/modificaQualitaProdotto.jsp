<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../general/header.jsp"></jsp:include>

<div class="container">
  <!-- Content here -->
   <div class="row">
   		<h3>Modifica qualita del prodotto: ${qualitaProdotto.prodotto.tipo } </h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
		   	<form class="row g-3" action="/qualitaProdotto/modificaQualitaProdotto" method="POST">
				<div class="mb-3">
				  <label for="ragioneSociale" class="form-label">Qualit&agrave;</label>
				  <input type="text" class="form-control" id="nome" name="nome" placeholder="Qualita" value="${qualitaProdotto.qualita }" required>
				</div>
				<input type="text" class="form-control" id="idQualita" name="idQualita"  value="${qualitaProdotto.id }" hidden="true">
				<div class="col-12">
				    <button type="submit" class="btn btn-primary">Salva</button>
				 </div>
			 </form>
	    </div>
   </div>
</div>
<jsp:include page="../../general/subfooter.jsp"></jsp:include>
<jsp:include page="../../general/footer.jsp"></jsp:include>
