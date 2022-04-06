<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="general/header.jsp"></jsp:include>

<div class="container">
  <!-- Content here -->
   <div class="row">
   		<spring:eval expression="@environment.getProperty('azienda.ragioneSociale')" var="azienda" />
   		<h1 class="display-4 text-center mt-4">WorkManger ${azienda}</h1>
   </div>
   <br>
   <div class="row justify-content-center">
	   	<div class="col-md-2">
		   	<div class="card card-home" style="width: 100%;">
			  <img src="/assets/icons/gestioneFornitori.png" class="card-img-top img-home">
			  <div class="card-body">
			    <h5 class="card-title text-center">Gestione fornitori</h5>
			    <a href="/fornitore/all" class="stretched-link"></a>
			  </div>
			</div>
	    </div>
	    
	    <div class="col-md-2">
		   	<div class="card card-home" style="width: 100%;">
			  <img style="height: 90%;" src="/assets/icons/gestioneClienti.png" class="card-img-top img-home">
			  <div class="card-body">
			    <h5 class="card-title text-center">Gestione clienti</h5>
			    <a href="/cliente/all" class="stretched-link"></a>
			  </div>
			</div>
	    </div>
	    
	    <div class="col-md-2">
		   	<div class="card card-home" style="width: 100%;">
			  <img style="height: 90%;" src="/assets/icons/gestioneProdotti.png" class="card-img-top img-home">
			  <div class="card-body">
			    <h5 class="card-title text-center">Gestione prodotti</h5>
			    <a href="/prodotto/all" class="stretched-link"></a>
			  </div>
			</div>
	    </div>
	    
	    <div class="col-md-2">
		   	<div class="card card-home" style="width: 100%;">
			  <img style="height: 90%;" src="/assets/icons/gestioneTrasportatori.png" class="card-img-top img-home">
			  <div class="card-body">
			    <h5 class="card-title text-center">Trasportatori</h5>
			    <a href="/trasportatore/all" class="stretched-link"></a>
			  </div>
			</div>
	    </div>
   </div>
   
   <div class="row justify-content-center mt-3">
	   	<div class="col-md-2">
		   	<div class="card card-home" style="width: 100%;">
			  <img src="/assets/icons/acquisti.png" class="card-img-top img-home">
			  <div class="card-body">
			    <h5 class="card-title text-center">Acquisti</h5>
			    <a href="/acquisto/all" class="stretched-link"></a>
			  </div>
			</div>
	    </div>
	    
	    <div class="col-md-2">
		   	<div class="card card-home" style="width: 100%;">
			  <img style="height: 90%;" src="/assets/icons/vendite.png" class="card-img-top img-home">
			  <div class="card-body">
			    <h5 class="card-title text-center">Vendite</h5>
			    <a href="/vendita/all" class="stretched-link"></a>
			  </div>
			</div>
	    </div>
	    
	    <div class="col-md-2">
		   	<div class="card card-home" style="width: 100%;">
			  <img style="height: 90%;" src="/assets/icons/pagamenti.png" class="card-img-top img-home">
			  <div class="card-body">
			    <h5 class="card-title text-center">Pagamenti</h5>
			    <a href="/pagamento/all" class="stretched-link"></a>
			  </div>
			</div>
	    </div>
   </div>
   
</div>
<jsp:include page="general/subfooter.jsp"></jsp:include>
<jsp:include page="general/footer.jsp"></jsp:include>