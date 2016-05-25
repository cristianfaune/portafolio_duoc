<%-- 
    Document   : AdminSolicitudes
    Created on : 24-may-2016, 0:17:53
    Author     : cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo=" crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css"/>
        <title>Administración de solicitudes</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <h4 class="text-center">Administración sistema Pañol</h4>
            <h6 class="text-center">Escuela de comunicaciones - Duoc UC</h6>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <div>
                        <ul class="nav nav-pills col-md-offset-3">
                            <c:choose>
                                <c:when test="${usuarioSesion.idPerfil == 100}">
                                    <li role="presentation"><a href="<c:url value="HomeJefeCarrera.jsp"/>">Home</a></li>
                                    </c:when>
                                    <c:when test="${usuarioSesion.idPerfil == 120}">
                                    <li role="presentation"><a href="<c:url value="HomePanolero.jsp"/>">Home</a></li>
                                    </c:when>
                                    <c:when test="${usuarioSesion.idPerfil == 110}">
                                    <li role="presentation"><a href="<c:url value="HomeCoordinador.jsp"/>">Home</a></li>
                                    </c:when>
                                </c:choose>
                            <li role="presentation"><a href="<c:url value="AdminSolicitudes.jsp"/>">Administrar Solicitudes</a></li>
                            <li role="presentation"><a href="<c:url value="#"/>">Reportes</a></li>
                        </ul>
                    </div>
                    <br>
                    <h2 class="text-center">Administración de solicitudes</h2>
                    <br>
                    <div class="col-md-7 col-lg-offset-3">
                        <form action="<c:url value="/SesionUsuarioSolicitudServlet"/>" method="get">
                            <label>Ingrese Rut para búsqueda:</label>
                            <div class="col-md-7">
                                <input class="form-control" type="text" name="rut" maxlength="9"/>
                                <p id="info-form">--Ingrese rut sin puntos ni guion--</p>
                                <p id="error-form"><c:out value="${mapMensajeRut['errorRut']}"/></p>
                            </div>
                            <div>
                                <input class="btn btn-default" type="submit" name="buscar" value="Buscar"/>
                            </div>
                        </form>
                    </div>
                    <c:if test="${not empty usuarioSolicitud}">
                        <div class="row col-lg-offset-1">
                            <div class="col-md-12">
                                <br>
                                <div class="col-md-6">
                                    <h3>Datos usuario solicitante:</h3>
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
                                <div class="col-md-2">

                                </div>
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
