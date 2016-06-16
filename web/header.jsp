<%-- 
    Document   : header
    Created on : 15-abr-2016, 0:42:33
    Author     : cristian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css"/>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    </head>
    <body>
        <div class="row">
            <div class="col-md-12">
                <header id="header">
                    <div class="col-md-6">
                        <div id="logo">
                            <img class="img-responsive center-block" src="imagenes/logoDuoc.png" alt="logoDuoc" width="200" height="100"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <c:if test="${not empty usuarioSesion}">
                                <div class="row">
                                    <div class="col-lg-offset-7">
                                        <h4 id="usuario-sesion">
                                            <span class="glyphicon glyphicon-user" id="iconos" aria-hidden="true"></span>
                                            <c:out value="${usuarioSesion.nombres} ${usuarioSesion.apellidos}"/>
                                        </h4>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-offset-7">
                                        <span class="glyphicon glyphicon-log-out" id="iconos"aria-hidden="true"></span>
                                        <a href="<c:url value="ValidarIngreso"/>" id="cerrar-sesion">Cerrar Sesión</a>
                                    </div> 
                                </div>
                            </c:if>
                        </div>
                    </div>
                </header>
            </div>
        </div>
    </body>
</html>
