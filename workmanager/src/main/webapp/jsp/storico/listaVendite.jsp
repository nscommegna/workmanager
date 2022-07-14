<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../general/header.jsp"></jsp:include>

<div class="container">
  <!-- Content here -->
  <c:if test="${not empty msg}">
   <div id="cardMsg" class="card ${msg.tipo}">
	  <div class="card-body text-light">
	  	<button class="btn text-light" id="btn_close_msg">X</button>
	    ${msg.messaggio }
	  </div>
	</div>
  </c:if>
   <div class="row">
   		<h3>Lista vendite annata <fmt:formatDate value="${anno}" pattern="yyyy" /></h3>
   </div>
   <br>
   <div class="row">
	   	<div class="col-12">
	   	
			<table id="example" class="display">
			
	        <thead>
	            <tr>
	            	<th>Numero doc</th>
	                <th>Data Vendita</th>
	                <th>Cliente</th>
	                <th>Prodotto/Qualit&agrave;</th>
	                <th>Trasportatore</th>
	                <th>Mezzo</th>
	                <th>Kg</th>
	                <th>Prezzo di vendita</th>
	                <th>Totale parziale</th>
	                <th>Costo trasporto</th>
	                <th>Totale</th>
	                <th>Funzioni</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="vendita" items="${vendite }">
	            	<tr>
	            		<td>${vendita.numeroDocumento}</td>
		            	<td><fmt:formatDate value="${vendita.dataVendita}" pattern="dd/MM/yyyy" /></td>
		            	<td>${vendita.cliente.ragioneSociale}</td>
		            	<td>${vendita.prodotto.prodotto.tipo} - ${vendita.prodotto.qualita}</td>
		            	<td>${vendita.mezzo.trasportatore.nome}</td>
		            	<td>${vendita.mezzo.targa}</td>
		            	<td>${vendita.quantita}</td>
		            	<td>&euro; ${vendita.prezzo}</td>
		            	<td>&euro; ${vendita.totaleParziale}</td>
		            	<td>&euro; ${vendita.costoTrasporto}</td>
		            	<td>&euro; ${vendita.totale}</td>
			            <td></td>
		           </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    </div>
   </div>
</div>
<jsp:include page="../general/subfooter.jsp"></jsp:include>

<script>

var ricercaAvanzataHidden = true;
$(document).ready(function() {
	
	 $('#example').DataTable({
	        dom: 'Bfrtip',
	        buttons: [
	            {
	        		extend : 'print',
	        		text: 'Stampa',
	        		title : 'Elenco Vendite "<fmt:formatDate value="${anno}" pattern="yyyy" />"',
		            exportOptions: {
	        		    columns: function (idx, data, node) {
	    		            if (idx == 11)
	    		                return false;
	    		            return true;
	    		        }
	    			},
	        		customize: function ( doc ) {
	        		     $(doc.document.body).find('h1').css('font-size', '18pt');
	        		     $(doc.document.body).find('h1').css('text-align', 'center'); 
	        		     $(doc.document.body).css( 'font-size', '10pt' )
	        		 
	        		
	        		},
	        		orientation: 'landscape'
	            }
	        ]
	    });

	 $('#btn_close_msg').click(function ()
	            {
	                $.ajax({
	                    type: "post",
	                    url: "/vendita/removeMessage",
	                    success: function(msg){      
	                            console.log(msg);
	                            $('#cardMsg').hide(); 
	                    }
	                });
	            });
	 	
	 $('#btnRicercaAvanzata').click(function (){
		 if(ricercaAvanzataHidden){
	 		$("#rowRicercaAvanzata").attr("hidden", false);
	 		ricercaAvanzataHidden = false;
		 }
		 else{
			 $("#rowRicercaAvanzata").attr("hidden", true);
			 ricercaAvanzataHidden = true;
		}
	  });
} );
</script>

<jsp:include page="../general/footer.jsp"></jsp:include>