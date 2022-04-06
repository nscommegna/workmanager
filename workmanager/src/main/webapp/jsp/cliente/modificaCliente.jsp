<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../general/header.jsp"></jsp:include>

<div class="container">
  <!-- Content here -->
   <div class="row">
   		<h3>Modifica cliente</h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
		   	<form class="row g-3" action="/cliente/modificaCliente" method="POST">
				<div class="mb-3">
				  <label for="ragioneSociale" class="form-label">Ragione Sociale</label>
				  <input type="text" class="form-control" id="ragioneSociale" name="ragioneSociale" 
				  placeholder="Ragione Sociale" value ="${cliente.ragioneSociale }" required>
				</div>
				<div class="mb-3">
				  <label for="citta" class="form-label">Città</label>
				  <input type="text" class="form-control" id="citta" name="citta" value ="${cliente.citta }" placeholder="Città">
				</div>
				<div class="mb-3">
				  <label for="indirizzo" class="form-label">Indirizzo</label>
				  <input type="text" class="form-control" id="indirizzo" name="indirizzo" value ="${cliente.indirizzo }" placeholder="Indirizzo">
				</div>
				<div class="mb-3">
				  <label for="partitaIva" class="form-label">Partita Iva</label>
				  <input type="text" class="form-control" id="partitaIva" name="partitaIva" value ="${cliente.partitaIva }" placeholder="Partita Iva">
				</div>
				<div class="mb-3">
				  <label for="telefono" class="form-label">Telefono</label>
				  <input type="text" class="form-control" id="telefono" name="telefono" value ="${cliente.telefono }"  placeholder="Telefono">
				</div>
				<div class="mb-3">
				  <label for="luogoConsegna" class="form-label">Luogo di consegna</label>
				  <input type="text" class="form-control" id="luogoConsegna" name="luogoConsegna" value ="${cliente.luogoConsegna }" placeholder="Luogo di consegna">
				</div> 
				  <input type="text" class="form-control" id="idCliente" name="idCliente" value ="${cliente.id }" hidden="true">
				<div class="col-12">
				    <button type="submit" class="btn btn-primary">Salva</button>
				 </div>
			 </form>
	    </div>
   </div>
</div>
<jsp:include page="../general/subfooter.jsp"></jsp:include>
<jsp:include page="../general/footer.jsp"></jsp:include>
