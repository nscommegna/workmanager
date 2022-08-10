<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="general/header.jsp"></jsp:include>

<div class="container">
  <!-- Content here -->
   <div class="row">
   		<spring:eval expression="@environment.getProperty('spring.application.name')" var="azienda" />
   		<h1 class="display-4 text-center mt-4">${azienda}</h1>
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
	    
	     <div class="col-md-2">
		   	<div class="card card-home" style="width: 100%;">
			  <img style="height: 90%;" src="/assets/icons/storico.jpg" class="card-img-top img-home">
			  <div class="card-body">
			    <h5 class="card-title text-center">Storico annate</h5>
			    <a href="/storico/all" class="stretched-link"></a>
			  </div>
			</div>
	    </div>
   </div>
   
   <!-- Modal -->
<div class="modal fade" id="chiudiAnnataModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    <form action="/storico/chiudiAnnata" method="POST">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Chiusura annata</h5>
      </div>
      <div class="modal-body">
        <p>Procedendo con la chiusura dell'annata, sposterai i dati relativi a 
        <b>acquisti</b>,<b>vendite</b> e <b>pagamenti</b> nella sezione <i>Storico</i>.
        <br>
         Confermando, questi dati non saranno più modificabili ma solo consultabili dall'apposita sezione.
         Procedere <b>solo se si è sicuri</b> di non dover registrare nessun'altro movimento per l'annata corrente.
        </p>
        <div class="mb-3">
	        <label for="dataChiusura" class="form-label">Data chiusura</label>
	        <input type="date" class="form-control" id="dataChiusura" name="dataChiusura" placeholder="dd/MM/yyyy" required>
        </div>
        <div class="mb-3">
       		<input type="text" class="form-control" id="note" name="note" placeholder="Note aggiuntive" required>
      	</div>
      </div>
      <div class="modal-footer">
        <button type="submit" onclick="this.disabled=true;this.value='Submitting...'; this.form.submit();" class="btn btn-primary">Chiudi annata</button>
      </div>
      </form>
    </div>
  </div>
</div>
   
   
</div>
<jsp:include page="general/subfooter.jsp"></jsp:include>

<script>
$( "#btnChiudiAnnata" ).click(function() {
		$('#chiudiAnnataModal').modal('toggle')
	});
</script>

<jsp:include page="general/footer.jsp"></jsp:include>