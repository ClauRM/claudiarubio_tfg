<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html class="h-100">
<head>
<meta charset="ISO-8859-1">
<title>CronoMed</title>
<!-- estilos bootstrap -->
<link rel="stylesheet" type="text/css" href="style/bootstrap.css">
<!-- javascript -->
<script src="script/bootstrap-5.1.3-dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="h-100">
	<!-- MENU DE NAVEGACION -->
	<nav class="navbar navbar-expand-lg navbar-light bg-primary">
		<div class="container-fluid">
			<img src="" alt="" width="30" height="24">
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
				<!-- al pulsar sobre cada parte del menu el Controlador se encarga de redirigir y el target se encarga de mostrar la vista en el iframe -->
					<li class="nav-item"><a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="Controlador?menu=home" target="iframe">CronoMed</a></li>
					<li class="nav-item"><a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="Controlador?menu=medicamentos" target="iframe">Medicamentos</a></li>
					<li class="nav-item"><a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="Controlador?menu=enCurso" target="iframe">Gesti�n de tratamientos</a></li>
					<li class="nav-item"><a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="Controlador?menu=finalizados" target="iframe">Tratamientos finalizados</a></li>
				</ul>
			</div>
			<!-- DESPLEGABLE DE USUARIO -->
			<div style="margin-rigth: 20px" class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">${usuario.getNombre()}</button>
				<ul class="dropdown-menu dropdown-menu-end text-center" aria-labelledby="dropdownMenuButton1">
					<li><a class="dropdown-item" href="#"><img alt="60" width="60" src="img/user.png"></a></li>
    				<li><a class="dropdown-item" href="#">${usuario.getEmail()}</a></li>
    				<li><hr class="dropdown-divider"></li>
    				<!-- FORMULARIO DE SALIDA -->
    				<form action="ValidarAcceso" method="post"> <!-- al pulsar Login se redirecciona al Servlet ValidarAcceso.java -->
    					<input type="submit" class="dropdown-item" name="accion" value="Salir" />
    				</form>
  				</ul>
			</div>
		</div>
	</nav>
	<!-- VENTANA DE ACCIONES -->
	<div class="mt-4" style="height: 80%;">
		<iframe name="iframe" style="height: 100%; width: 100%"></iframe>	
	</div>
</body>
</html>