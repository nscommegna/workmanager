<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../general/header.jsp"></jsp:include>

<div class="container">
  <!-- Content here -->
   <div class="row">
   		<h3>Nuovo prodotto</h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
		   	<form class="row g-3" action="/prodotto/salvaProdotto" method="POST">
				<div class="mb-3">
				  <label for="ragioneSociale" class="form-label">Tipo</label>
				  <input type="text" class="form-control" id="tipo" name="tipo" placeholder="Tipo" required>
				</div>
				
				<div class="mb-3">
				  <label for="citta" class="form-label">Aggiungi qualità 
					  <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#modalQualita">
						  <i class="fa-solid fa-plus"></i>
					  </button>
				  </label>
				  <div id = "divQualitaInserite">
				  
				  </div>
				  <input type="text" class="form-control" id="qualitaAggregate" name="qualita" placeholder="Qualita" hidden="true">
				</div>
				
				<div class="col-12">
				    <button type="submit" onclick="this.disabled=true;this.value='Submitting...'; this.form.submit();" class="btn btn-primary">Salva</button>
				 </div>
			 </form>
	    </div>
   </div>
</div>

<div class="modal fade" id="modalQualita" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Aggiungi una qualità del prodotto</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
			<input type="text" class="form-control" id="qualita" placeholder="Qualità">
      </div>
      <div class="modal-footer">
        <button type="button" id="btnAddQualita" class="btn btn-primary">Aggiungi</button>
      </div>
    </div>
  </div>
</div>
<jsp:include page="../general/subfooter.jsp"></jsp:include>

<script>
$( "#btnAddQualita" ).click(function() {
	var qualita = $( "#qualita" ).val();
	var qualitaAggregatelenght = $( "#qualitaAggregate" ).val().length;
	
	if(qualitaAggregatelenght == 0){
		$( "#qualitaAggregate" ).val(qualita);
	}
	else{
		$( "#qualitaAggregate" ).val($( "#qualitaAggregate" ).val()+"#"+qualita);
	}
	console.log($( "#qualitaAggregate" ).val());
	
	$( "#divQualitaInserite" ).append("<div class='newLine row'><div class='col-md-2'><p>"+qualita+"</div><div class='col-md-1'><button data-qualita='"+qualita+"'  class='btn btn-sm btn-danger mr-2 eliminaQualita' type='button'>Elimina</button></p></div></div>");
	var modal = $( "#modalQualita" ).modal('hide');
	});
	
$('#divQualitaInserite').on('click', '.eliminaQualita', function() {
	var qualitaDaEliminare = this.getAttribute('data-qualita').trim();
	var qualitaPresenti = $( "#qualitaAggregate" ).val();
	var qualitaAggiornateSporche = qualitaPresenti.replace(qualitaDaEliminare,"");
	var qualitaAggiornatePulite = qualitaAggiornateSporche.replace("##","");
	 $( "#qualitaAggregate" ).val(qualitaAggiornatePulite);
	var parentDiv = this.closest( ".newLine" );
	parentDiv.remove();
	console.log($( "#qualitaAggregate" ).val());
});


</script>
<jsp:include page="../general/footer.jsp"></jsp:include>