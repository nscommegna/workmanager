<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../general/header.jsp"></jsp:include>

<div class="container">
  <!-- Content here -->
   <div class="row">
   		<h1 class="display-4 text-center mt-4">Annata <fmt:formatDate value="${annata.anno}" pattern="yyyy" /></h1>
   </div>
   <br>
   <div class="row justify-content-center mt-3">
	   	<div class="col-md-2">
		   	<div class="card card-home" style="width: 100%;">
			  <img src="/assets/icons/acquisti.png" class="card-img-top img-home">
			  <div class="card-body">
			    <h5 class="card-title text-center">Acquisti</h5>
			    <a href="/storico/vaiListaAcquisti?idAnnata=${annata.id }" class="stretched-link"></a>
			  </div>
			</div>
	    </div>
	    
	    <div class="col-md-2">
		   	<div class="card card-home" style="width: 100%;">
			  <img style="height: 90%;" src="/assets/icons/vendite.png" class="card-img-top img-home">
			  <div class="card-body">
			    <h5 class="card-title text-center">Vendite</h5>
			    <a href="/storico/vaiListaVendite?idAnnata=${annata.id }" class="stretched-link"></a>
			  </div>
			</div>
	    </div>
	    
	    <div class="col-md-2">
		   	<div class="card card-home" style="width: 100%;">
			  <img style="height: 90%;" src="/assets/icons/pagamenti.png" class="card-img-top img-home">
			  <div class="card-body">
			    <h5 class="card-title text-center">Pagamenti</h5>
			    <a href="/storico/vaiListaPagamenti?idAnnata=${annata.id }" class="stretched-link"></a>
			  </div>
			</div>
	    </div>
   </div>
   
</div>
<jsp:include page="../general/subfooter.jsp"></jsp:include>
<jsp:include page="../general/footer.jsp"></jsp:include>