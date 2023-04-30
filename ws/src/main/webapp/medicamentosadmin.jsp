<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.util.*,java.lang.*" %>
<%@ page import="modelo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- ESTILOS -->
<link rel="stylesheet" type="text/css" href="style/bootstrap.css">
<link rel="stylesheet" type="text/css" href="style/styles.css">
<!-- javascript -->
<script src="script/bootstrap-5.1.3-dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<h3 id="titulo1" class="px-3"><img alt="capsule" src="img/capsule.svg"> Listado de Medicamentos</h3>
	<p class="px-3">ESTOY EN MEDICAMENTOS ADMINISTRADOR</p>
	<!-- CONTENEDOR DE FORMULARIO -->
	<div class="col-sm-8 px-3">
	
	
	</div>
	<!-- CONTENEDOR DE TABLA -->
	<div class="col-sm-8 px-3">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>NOMBRE Y COMPOSICION DEL MEDICAMENTO</th>
					<th>ACCIONES</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${medicamentos}" var="medicamento">
					<tr>
						<td class="text-uppercase">${medicamento.getMedicamento()}</td>
						<td>Modificar</td>
						<td>Eliminar</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>