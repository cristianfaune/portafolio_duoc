<%-- 
    Document   : ConfirmacionSolicitud
    Created on : 25-may-2016, 11:26:28
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
        <title>Administración de pañol</title>
    </head>
    <body>
        <script type="text/javascript">
            $(document).ready(function () {

                var esp = $('#especialSi').val();

                $('#especialSi').click(function () {
                    $('#diasPrestamo').prop('disabled', false);
                    $('#diasPrestamo').prop('value', 2);
                });

                $('#especialNo').click(function () {
                    $('#diasPrestamo').prop('disabled', true);
                    $('#diasPrestamo').prop('value', 1);
                });
            });

        </script>
        <%@include file="header.jsp" %>
        <div class="container">
            <h4 class="text-center">Administración sistema Pañol</h4>
            <h6 class="text-center">Escuela de comunicaciones - Duoc UC</h6>
            <div class="row">
                <div class="1"></div>
                <div class="10">
                    <div>
                        <ul class="nav nav-pills col-md-offset-4">
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
                    <h2 class="text-center">Confirmación solicitud</h2>
                    <br>
                    <div class="row col-lg-offset-1">
                        <div class="col-md-12">
                            <br>
                            <div class="row">
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
                                <div class="col-md-6">
                                    <p>¿El usuario necesita los equipos por más de un día?</p>
                                    <div class="row">
                                        <div class="col-md-8">
                                            <form action="<c:url value="/ConfirmacionSolicitudServlet"/>" method="post" onsubmit="return confirm('¿Está seguro de registrar el nuevo préstamo?');">
                                                <label class="radio-inline">
                                                    <input class="radio" type="radio" name="especial" id="especialSi" value="1"> Si
                                                </label>
                                                <label class="radio-inline">
                                                    <input class="radio" type="radio" name="especial" id="especialNo" value="0" checked="true"> No
                                                </label>
                                                <br><br>
                                                <div class="row">
                                                    <div class="col-md-5">
                                                        <input class="form-control" type="number" name="diasPrestamo" id="diasPrestamo" disabled="true" min="2">
                                                    </div>
                                                    <p id="info-form">días préstamo</p>
                                                </div>
                                                <br>
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <button class="btn btn-default btn-success btn-block" type="submit" value="Enviar">Enviar</button>
                                                    </div>
                                                </div>
                                            </form>   
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div class="col-md-8">
                                            <p id="info-form">** Al seleccionar <strong> SI </strong> la solicitud quedará en espera hasta
                                                que el jefe de carrera revise su situación aceptándola o negándola.
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br><br><br>
                            <div>
                                <table class="table">
                                    <tbody>
                                        <tr class="bg-primary">
                                            <th class="text-center">Imagen</th>
                                            <th class="text-center">producto</th>
                                            <th class="text-center">Modelo</th>
                                            <th class="text-center">Marca</th>
                                            <th class="text-center">Descripción</th>
                                            <th class="text-center">Cantidad</th>
                                        </tr>
                                        <c:forEach var="dato" items="${listaProductos}">
                                            <tr>
                                                <td><img src="${dato.producto.rutaImagen}" width="100" height="100"></td>
                                                <td class="text-center"><c:out value="${dato.producto.nombre}"/></td>
                                                <td class="text-center"><c:out value="${dato.producto.modelo}"/></td>
                                                <td class="text-center"><c:out value="${dato.marca.descripcion}"/></td>
                                                <td class="text-center"><c:out value="${dato.producto.descripcion}"/></td>
                                                <c:forEach var="d" items="${listaDetalle}">
                                                    <c:if test="${d.idProducto == dato.producto.idProducto}">
                                                        <td class="text-center"><c:out value="${d.cantidad}"/></td>
                                                    </c:if>
                                                </c:forEach>
                                            </tr>
                                        </c:forEach>
                                </table>
                            </div>
                            <div class="1"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
