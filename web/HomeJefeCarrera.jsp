<%-- 
    Document   : HomeJefeCarrera
    Created on : 26-abr-2016, 1:41:57
    Author     : cristian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<c:choose>
    <c:when test="${empty usuarioSesion}">
        <c:redirect url="index.jsp"></c:redirect>
    </c:when>
    <c:when test="${usuarioSesion.idPerfil == 110 or usuarioSesion.idPerfil == 120}">
        <c:redirect url="index.jsp"></c:redirect>
    </c:when>
</c:choose>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css"/>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Palanquin' rel='stylesheet' type='text/css'>
        <title>Escritorio - Jefe de Carrera</title>
    </head>
    <body id="page-top" class="index">

        <%@include file="header.jsp" %>
        <div class="container"> 
            <h3 class="text-center" id="titulo-pagina">Administración sistema Pañol</h3>
            <h5 class="text-center" id="titulo-pagina">Escuela de comunicaciones - Duoc UC</h5>
            <br>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8 centered-pills">
                    <ul class="nav nav-pills" id="palanquin-font">
                        <li role="presentation"><a href="<c:url value="/MostrarUsuarioServlet"/>">Administrar Usuarios</a></li>
                        <li role="presentation"><a href="<c:url value="/AdminProductosServlet"/>">Administrar Inventario</a></li>
                        <li role="presentation"><a href="<c:url value="AdminSolicitudes.jsp"/>">Administrar Solicitudes</a></li>
                        <li role="presentation"><a href="<c:url value="Reportes.jsp"/>">Reportes</a></li>
                    </ul>
                    <br>
                    <h3 class="text-center" id="palanquin-font">Bienvenido al escritorio de Jefe de Carrera</h3>
                    <h5 id="palanquin-font" class="text-center"><c:out value="${usuarioSesion.nombres} ${usuarioSesion.apellidos}"></c:out></h5>
                    <hr>
                    <br><br>
                    <p id="palanquin-font">
                        En este sitio podrás realizar todas las funcionalidades
                        necesarias para la administración de los recursos del 
                        pañol de la escuela.
                    </p>
                </div>
                <div class="col-md-2"></div>
            </div> 
        </div>
        <section>
            <div id="borde-footer"></div>
            <div>
                <footer id="footer">
                    <p class="text-center" id="info-form">--Sistema pañol Duoc UC--</p>
                    <p class="text-center" id="info-form">sistemapanol@gmail.com</p>
                </footer>
            </div>
        </section>
    </body>
</html>
