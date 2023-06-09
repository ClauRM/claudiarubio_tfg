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
	<h3 id="titulo1" class="px-3"><img alt="capsule" src="img/capsule.svg"> Listado de Tratamientos en curso</h3>
	<p class="px-3">A continuaci�n, tienes un listado con la frecuencia horaria de todos los tratamientos que tienes en curso. Si quieres actualizarlos pulsa en la opci�n de <strong>Registro</strong> del men� de navegaci�n.</p>
	<!-- CONTENEDOR DE LA TABLA -->
		<div class="col-12 px-3">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>MEDICAMENTO</th>
						<th>PACIENTE</th>
						<th>DOSIS</th>
						<th>OBSERVACIONES</th>
						<th>HORA</th>
					</tr>
				</thead>
				<tbody>
                	<c:forEach items="${tratamientos}" var="tratamiento">
						<tr>
							<td class="text-uppercase">${tratamiento.getMedicamento().getMedicamento()} </td>
							<td>${tratamiento.getPaciente()} </td>
							<td>${tratamiento.getDosis()} </td>
							<td>${tratamiento.getObservaciones()}</td>	
							<td>${tratamiento.getTratamiento()}</td>					
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

</body>
</html>