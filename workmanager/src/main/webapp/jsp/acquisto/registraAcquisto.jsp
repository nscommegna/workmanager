<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../general/header.jsp"></jsp:include>

<div class="container">
  <!-- Content here -->
   <div class="row">
   		<h3>Nuovo acquisto</h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
		   	<form class="row g-3" action="/acquisto/salvaAcquisto" method="POST">
				<div class="mb-3 col-md-6">
				  <label for="dataAcquisto" class="form-label">Data acquisto</label>
				  <input type="text" class="form-control" id="dataAcquisto" name="dataAcquisto" placeholder="dd/MM/yyyy">
				</div>
				<div class="mb-3 col-md-6">
				  <label for="citta" class="form-label">Cliente</label>
				  <input type="text" class="form-control" id="citta" name="citta" placeholder="Citt�">
				</div>
				<div class="mb-3">
				  <label for="indirizzo" class="form-label">Indirizzo</label>
				  <input type="text" class="form-control" id="indirizzo" name="indirizzo" placeholder="Indirizzo">
				</div>
				<div class="mb-3">
				  <label for="partitaIva" class="form-label">Partita Iva</label>
				  <input type="text" class="form-control" id="partitaIva" name="partitaIva" placeholder="Partita Iva">
				</div>
				<div class="mb-3">
				  <label for="telefono" class="form-label">Telefono</label>
				  <input type="text" class="form-control" id="telefono" name="telefono" placeholder="Telefono">
				</div>
				<div class="mb-3">
				  <label for="luogoConsegna" class="form-label">Luogo di consegna</label>
				  <input type="text" class="form-control" id="luogoConsegna" name="luogoConsegna" placeholder="Luogo di consegna">
				</div> 
				<div class="col-12">
				    <button type="submit" class="btn btn-primary">Salva</button>
				 </div>
			 </form>
	    </div>
   </div>
</div>
<jsp:include page="../general/footer.jsp"></jsp:include>
