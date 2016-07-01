<%-- 
    Document   : RegistroSolicitudExitosa
    Created on : 25-may-2016, 17:33:39
    Author     : cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css"/>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Palanquin' rel='stylesheet' type='text/css'>
        <title>Administración de Pañol</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <h3 class="text-center" id="titulo-pagina">Administración sistema Pañol</h3>
            <h5 class="text-center" id="titulo-pagina">Escuela de comunicaciones - Duoc UC</h5>
            <br>

            <br><br>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <h4 class="text-center">La devolución fue registrada exitosamente con el ID:</h4>
                    <h2 class="text-center"><c:out default="#########" value="${idDev}"/></h2>
                    <br>
                    <h4 class="text-center" id="palanquin-font">Resumen de la devolución: </h4>
                    <br>
                    <p class="text-center" id="info-form">Devuelto a nombre de 
                        <strong><c:out value="${usuarioCliente.nombres} ${usuarioCliente.apellidos}"/></strong> 
                        registrado con el rut <strong><c:out value="${usuarioCliente.rut}"/>.</strong></p>
                    <p class="text-center" id="info-form">El encargado de pañol responsable fue <c:out value="${usuarioSesion.nombres} ${usuasioSesion.apellidos}"/></p>
                    <br>
                    <h4 class="text-center" id="palanquin-font">Observaciones:</h4>
                </div>
            </div>
            <br><br>
            <div class="row">
                <div class="col-md-4 col-lg-offset-4">
                    <form action="<c:url value="CancelarDevolucionServlet"/>" method="post">
                        <button type="submit" class="btn btn-primary btn-block">Finalizar</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-2"></div>      
    </div>
</div>
</body>
</html>