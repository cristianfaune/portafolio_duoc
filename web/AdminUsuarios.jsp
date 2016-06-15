<%-- 
    Document   : HomeJefeCarrera
    Created on : 26-abr-2016, 1:41:57
    Author     : cristian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:choose>
    <c:when test="${usuarioSesion.idPerfil == 120}">
        <c:redirect url="HomePanolero.jsp"></c:redirect>
    </c:when>
    <c:when test="${empty usuarioSesion}">
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
        <title>Home - Jefe de Carrera</title>
    </head>
    <body id="page-top" class="index">

        <%@include file="header.jsp" %>
        <div class="container">
            <h3 class="text-center" id="titulo-pagina">Administración sistema Pañol</h3>
            <h5 class="text-center" id="titulo-pagina">Escuela de comunicaciones - Duoc UC</h5>
            <div class="row col-lg-offset-1">
                <div class="col-md-2"></div>
                <div class="col-md-7 centered-pills">
                    <ul class="nav nav-pills" id="palanquin-font">
                        <c:choose>
                            <c:when test="${usuarioSesion.idPerfil == 100}">
                                <li role="presentation"><a href="<c:url value="HomeJefeCarrera.jsp"/>">Inicio</a></li>
                                </c:when>
                                <c:when test="${usuarioSesion.idPerfil == 120}">
                                <li role="presentation"><a href="<c:url value="HomePanolero.jsp"/>">Inicio</a></li>
                                </c:when>
                                <c:when test="${usuarioSesion.idPerfil == 110}">
                                <li role="presentation"><a href="<c:url value="HomeCoordinador.jsp"/>">Inicio</a></li>
                                </c:when>
                            </c:choose>
                        <li role="presentation"><a href="<c:url value="/MostrarUsuario.jsp"/>">Buscar Usuario</a></li>
                        <li role="presentation"><a href="<c:url value="/RegistroUsuarioServlet"/>">Ingresar Nuevo Usuario</a></li>
                        <li role="presentation"><a href="<c:url value="ListarUsuariosServlet"/>">Listar Usuarios</a></li>
                    </ul>
                    <br>
                    <br>
                    <p>

                    </p>
                </div>
                <div class="col-md-3"></div>
            </div> 
        </div>
    </body>
</html>
