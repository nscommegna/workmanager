<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../general/header.jsp"></jsp:include>

<div class="container">
  <!-- Content here -->
   <div class="row">
   		<h3>Nuovo trasportatore</h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
		   	<form class="row g-3" action="/trasportatore/salvaTrasportatore" method="POST">
				<div class="mb-3">
				  <label for="nome" class="form-label">Nome</label>
				  <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome trasportatore" required>
				</div>
				
				<div class="mb-3">
				  <label for="targa" class="form-label">Aggiungi targa 
					  <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#modalTarga">
						  <i class="fa-solid fa-plus"></i>
					  </button>
				  </label>
				  <div id = "divTargheInserite">
				  
				  </div>
				  <input type="text" class="form-control" id="targheAggregate" name="targhe" hidden="true">
				</div>
				
				<div class="col-12">
				    <button type="submit" onclick="this.disabled=true;this.value='Submitting...'; this.form.submit();" class="btn btn-primary">Salva</button>
				 </div>
			 </form>
	    </div>
   </div>
</div>

<div class="modal fade" id="modalTarga" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Aggiungi una targa del trasportatore</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
			<input type="text" class="form-control" id="targa" placeholder="Targa">
      </div>
      <div class="modal-footer">
        <button type="button" id="btnAddTarga" class="btn btn-primary">Aggiungi</button>
      </div>
    </div>
  </div>
</div>
<jsp:include page="../general/subfooter.jsp"></jsp:include>

<script>
$( "#btnAddTarga" ).click(function() {
	var targa = $( "#targa" ).val();
	var targheAggregatelenght = $( "#targheAggregate" ).val().length;
	
	if(targheAggregatelenght == 0){
		$( "#targheAggregate" ).val(targa);
	}
	else{
		$( "#targheAggregate" ).val($( "#targheAggregate" ).val()+"#"+targa);
	}
	console.log($( "#targheAggregate" ).val());
	
	$( "#divTargheInserite" ).append("<div class='newLine row'><div class='col-md-2'><p>"+targa+"</div><div class='col-md-1'><button data-targa='"+targa+"'  class='btn btn-sm btn-danger mr-2 eliminaQualita' type='button'>Elimina</button></p></div></div>");
	var modal = $( "#modalQualita" ).modal('hide');
	});
	
$('#divTargheInserite').on('click', '.eliminaQualita', function() {
	var targaDaEliminare = this.getAttribute('data-targa').trim();
	var targaPresenti = $( "#targheAggregate" ).val();
	var targaAggiornateSporche = targaPresenti.replace(targaDaEliminare,"");
	var targaAggiornatePulite = targaAggiornateSporche.replace("##","");
	 $( "#targheAggregate" ).val(targaAggiornatePulite);
	var parentDiv = this.closest( ".newLine" );
	parentDiv.remove();
	console.log($( "#targheAggregate" ).val());
});


</script>
<jsp:include page="../general/footer.jsp"></jsp:include>