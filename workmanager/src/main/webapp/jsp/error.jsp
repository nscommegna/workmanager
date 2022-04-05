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
	   	ERRORE
   </div>
   
 
   
</div>
<jsp:include page="general/subfooter.jsp"></jsp:include>
<jsp:include page="general/footer.jsp"></jsp:include>