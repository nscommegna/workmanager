<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%-- <title th:text="${titlePage}"></title> --%>
<title>${titlePage}</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.datatables.net/1.11.4/css/jquery.dataTables.min.css" rel="stylesheet"  crossorigin="anonymous">
<link href="https://cdn.datatables.net/buttons/2.2.2/css/buttons.dataTables.min.css" rel="stylesheet"  crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.14.0-beta2/dist/css/bootstrap-select.min.css">
<link rel="icon" href="/assets/icons/gestioneProdotti.png">
<style type="text/css">
.card-home {
  box-shadow: 0 3px 10px rgb(0 0 0 / 0.2);
  border-radius: 10%;
  cursor: pointer;
}

.img-home {
	padding : 30px;
}
</style>
<body>

</head>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
     <a class="navbar-brand" href="/">
      <img src="/assets/icons/gestioneProdotti.png" alt="" width="30" height="24">
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <!--<li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/">Home</a>
        </li>-->
        <li class="nav-item">
          <a class="nav-link" href="/cliente/all">Gestione clienti</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/fornitore/all">Gestione fornitori</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/prodotto/all">Gestione prodotti</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/trasportatore/all">Gestione trasportatori</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/acquisto/all">Acquisti</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/vendita/all">Vendite</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/pagamento/all">Pagamenti</a>
        </li>
      </ul>
    </div>
  </div>
</nav>