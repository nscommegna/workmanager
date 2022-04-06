<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../general/header.jsp"></jsp:include>

<div class="container">
  <!-- Content here -->
   <div class="row">
   		<h3>Modifica fornitore</h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
		   	<form class="row g-3" action="/fornitore/modificaFornitore" method="POST">
				<div class="mb-3">
				  <label for="ragioneSociale" class="form-label">Nome</label>
				  <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome" value="${fornitore.nome}" required>
				</div>
				<div class="mb-3">
				  <label for="cognome" class="form-label">Cognome</label>
				  <input type="text" class="form-control" id="cognome" name="cognome" placeholder="Cognome" value="${fornitore.cognome}" required>
				</div>
				<div class="mb-3">
				  <label for="indirizzo" class="form-label">Indirizzo</label>
				  <input type="text" class="form-control" id="indirizzo" name="indirizzo" placeholder="Indirizzo" value="${fornitore.indirizzo}">
				</div>
				<div class="mb-3">
				  <label for="partitaIva" class="form-label">Partita Iva</label>
				  <input type="text" class="form-control" id="partitaIva" name="partitaIva" placeholder="Partita Iva" value="${fornitore.partitaIva}">
				</div>
				<div class="mb-3">
				  <label for="telefono" class="form-label">Telefono</label>
				  <input type="text" class="form-control" id="telefono" name="telefono" placeholder="Telefono" value="${fornitore.telefono}">
				</div>
				<div class="mb-3">
				  <label for="codiceFiscale" class="form-label">Codice Fiscale</label>
				  <input type="text" class="form-control" id="codiceFiscale" name="codiceFiscale" placeholder="Codice fiscale" value="${fornitore.codiceFiscale}">
				</div> 
				<div class="mb-3">
				  <label for="citta" class="form-label">Città</label>
				  <input type="text" class="form-control" id="citta" name="citta" placeholder="Città" value="${fornitore.citta}">
				</div>
				<div class="mb-3">
				  <label for="provincia" class="form-label">Provincia</label>
				  <input type="text" class="form-control" id="provincia" name="provincia" placeholder="provincia" value="${fornitore.provincia}">
				</div>
				<div class="mb-3">
				  <label for="cap" class="form-label">CAP</label>
				  <input type="text" class="form-control" id="cap" name="cap" placeholder="cap" value="${fornitore.cap}">
				</div>
				 <input type="text" class="form-control" id="idFornitore" name="idFornitore" value ="${fornitore.id }" hidden="true">
				<div class="col-12">
				    <button type="submit" class="btn btn-primary">Salva</button>
				 </div>
			 </form>
	    </div>
   </div>
</div>
<jsp:include page="../general/subfooter.jsp"></jsp:include>
<jsp:include page="../general/footer.jsp"></jsp:include>
