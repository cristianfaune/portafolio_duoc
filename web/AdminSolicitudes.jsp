<%-- 
    Document   : AdminSolicitudes
    Created on : 24-may-2016, 0:17:53
    Author     : cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:if test="${empty usuarioSesion}">
    <c:redirect url="index.jsp"></c:redirect>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo=" crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css"/>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Palanquin' rel='stylesheet' type='text/css'>
        <title>Administración de solicitudes</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <h3 class="text-center" id="titulo-pagina">Administración sistema Pañol</h3>
            <h5 class="text-center" id="titulo-pagina">Escuela de comunicaciones - Duoc UC</h5>
            <br>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8 centered-pills">
                    <div>
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
                                <c:if test="${usuarioSesion.idPerfil == 100}">
                                <li role="presentation"><a href="<c:url value="AdminSolicitudesEspecialesServlet"/>">Administrar Solicitudes Especiales</a></li>
                                </c:if>
                        </ul>
                    </div>
                    <br>
                    <h3 class="text-center" id="palanquin-font">Crear nueva de solicitud</h3>
                    <hr>
                    <br>
                    <div class="row">
                        <form action="<c:url value="/SesionUsuarioSolicitudServlet"/>" method="get">  
                            <div class="input-group col-md-4 center_div">
                                <label id="palanquin-font">Ingrese rut para búsqueda:</label>
                                <input class="form-control" type="text" name="rut" maxlength="9"/>
                                <p id="info-form">--Ingrese rut sin puntos ni guion--</p>
                                <span class="input-group-btn">
                                    <input class="btn btn-default" type="submit" name="buscar" value="Buscar"/>
                                </span>
                            </div>
                        </form>
                    </div>
                    <div class="text-center">
                        <p id="error-form"><c:out value="${mapMensajeRut['errorRut']}"/></p>
                    </div>
                    <c:if test="${not empty usuarioSolicitud}">
                        <div class="row">
                            <div class="col-md-12">
                                <br>
                                <div class="col-md-6 text-left">
                                    <h4 id="palanquin-font">Datos usuario solicitante:</h4>
                                    <hr>
                                    <label id="info-usuario">Rut: <c:out value="${usuarioSolicitud.usuario.rut}"/></label>
                                    <br>
                                    <label id="info-usuario">Nombre: <c:out value="${usuarioSolicitud.usuario.nombres} ${usuarioSolicitud.usuario.apellidos}"/></label>
                                    <br>
                                    <c:choose>
                                        <c:when test="${empty usuarioSolicitud.carrera.descripcion}">
                                            <label id="info-usuario">Carrera: No aplica</label>   
                                        </c:when>
                                        <c:otherwise>
                                            <label id="info-usuario">Carrera: ${usuarioSolicitud.carrera.descripcion}</label>     
                                        </c:otherwise>
                                    </c:choose>
                                    <br>
                                    <label id="info-usuario">Perfil: <c:out value="${usuarioSolicitud.perfil.descripcion}"/></label>
                                    <br>
                                    <c:choose>
                                        <c:when test="${usuarioSolicitud.usuario.activo == 1}">
                                            <label class="text-success" id="info-usuario">Estado: Activo</label>   
                                        </c:when>
                                        <c:otherwise>
                                            <label class="text-danger" id="info-usuario">Estado: Inactivo</label>     
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col-md-2"></div>
                                <div class="col-md-4" id="btn-continuar-solicitud">
                                    <form action="RegistrarSolicitudServlet" method="get">
                                        <input class="btn btn-default btn-primary btn-lg" type="submit" value="Continuar >" name="btnContinuar">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
