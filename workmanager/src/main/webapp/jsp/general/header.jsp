<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<body>
<meta charset="ISO-8859-1">
<%-- <title th:text="${titlePage}"></title> --%>
<title>${titlePage}</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="https://cdn.datatables.net/1.11.4/css/jquery.dataTables.min.css" rel="stylesheet"  crossorigin="anonymous">
<link href="https://cdn.datatables.net/buttons/2.2.2/css/buttons.dataTables.min.css" rel="stylesheet"  crossorigin="anonymous">

</head>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand btn" href="/cliente/all"><i class="fa-duotone fa-grapes"></i></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/cliente/all">Gestione clienti</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Gestione fornitori</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/prodotto/all">Gestione prodotti</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Gestione trasportatori</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Acquisti</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Vendite</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Report</a>
        </li>
      </ul>
    </div>
  </div>
</nav>