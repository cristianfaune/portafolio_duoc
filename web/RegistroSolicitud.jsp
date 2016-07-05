<%-- 
    Document   : RegistroSolicitud
    Created on : 24-may-2016, 13:31:22
    Author     : cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:choose>
    <c:when test="${empty usuarioSesion}">
        <c:redirect url="index.jsp"></c:redirect>
    </c:when>
</c:choose>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo=" crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css"/>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Palanquin' rel='stylesheet' type='text/css'>
        <title>Administración de solicitudes</title>
        <title>Registro de solicitud</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <h3 class="text-center" id="titulo-pagina">Administración sistema Pañol</h3>
            <h5 class="text-center" id="titulo-pagina">Escuela de comunicaciones - Duoc UC</h5>
            <br>
            <div class="centered-pills">
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
                    <li role="presentation"><a href="<c:url value="#"/>">Administrar solicitudes</a></li>
                </ul>
            </div>
            <c:if test="${not empty mensajeError['errorCantidad']}">
                <div id="danger-box" class="bg-danger">
                    <h5 class="text-center text-danger" id="palanquin-font"><c:out value="${mensajeError['errorCantidad']}"/></h5>
                </div>
            </c:if>
                <h3 class="text-center" id="palanquin-font">Administración de solicitudes</h3>
                <hr>
                <br>
            <div class="row">
                <div class="col-md-9">
                    <table class="table">
                        <tbody>
                            <tr class="bg-primary">
                                <th class="text-center">Imagen</th>
                                <th class="text-center">Id</th>
                                <th class="text-center">producto</th>
                                <th class="text-center">Modelo</th>
                                <th class="text-center">Marca</th>
                                <th class="text-center">Descripción</th>
                                <th class="text-center">Stock</th>
                                <th></th>
                                <th class="text-center">Agregar</th>
                            </tr>
                            <c:forEach var="dato" items="${lstProductos}">
                                <c:choose>
                                    <c:when test="${dato.producto.stock == 0}">
                                        <tr>
                                            <td><img src="${dato.producto.rutaImagen}" width="70" height="70"></td>
                                            <td class="text-center"><c:out value="${dato.producto.idProducto}"/></td>
                                            <td class="text-center"><c:out value="${dato.producto.nombre}"/></td>
                                            <td class="text-center"><c:out value="${dato.producto.modelo}"/></td>
                                            <td class="text-center"><c:out value="${dato.marca.descripcion}"/></td>
                                            <td class="text-center"><c:out value="${dato.producto.descripcion}"/></td>
                                            <td class="text-center" id="info-form">sin stock</td>
                                            <td>
                                                <input class="form-control" type="number" name="cantidad" disabled="true">
                                            </td>
                                            <td>
                                                <input class="btn btn-primary btn-xs" type="submit" value="Agregar" disabled="true"/>
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td><img src="${dato.producto.rutaImagen}" width="100" height="100"></td>
                                            <td class="text-center"><c:out value="${dato.producto.idProducto}"/></td>
                                            <td class="text-center"><c:out value="${dato.producto.nombre}"/></td>
                                            <td class="text-center"><c:out value="${dato.producto.modelo}"/></td>
                                            <td class="text-center"><c:out value="${dato.marca.descripcion}"/></td>
                                            <td class="text-center"><c:out value="${dato.producto.descripcion}"/></td>
                                            <td class="text-center"><c:out value="${dato.producto.stock}"/></td>
                                            <td style="display:none">
                                                <form action="<c:url value="/RegistrarSolicitudServlet"/>" method="post">
                                                    <td>
                                                        <input class="form-control" type="number" name="cantidad" value="1">
                                                    </td>
                                                    <input type="hidden" name="idProducto" value="${dato.producto.idProducto}"/>
                                                    <td>
                                                        <input class="btn btn-primary btn-xs" type="submit" value="Agregar"/>
                                                    </td>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-3">
                    <h5 id="palanquin-font">Mis productos: 
                        <c:if test="${not empty listaDetalle}">
                            <p id="palanquin-font">Solicitud Nº </p>
                            <c:forEach var="ds" end="0" items="${listaDetalle}">
                                <strong><c:out value="${ds.idSolicitud}"></c:out></strong>
                            </c:forEach>
                        </c:if>
                    </h5>
                    <table class="table table-bordered">
                        <tr>
                            <th id="info-tabla">Nombre</th>
                            <th id="info-tabla">Marca</th>
                            <th id="info-tabla">Modelo</th>
                            <th id="info-tabla">Cantidad</th>
                            <th id="info-tabla">Acciones</th>
                        </tr>
                        <c:forEach var="lp" items="${listaProductos}">
                            <c:forEach var="d" items="${listaDetalle}">
                                <c:if test="${lp.producto.idProducto == d.idProducto}">
                                    <tr>
                                        <td id="info-form"><c:out value="${lp.producto.nombre}"></c:out></td>
                                        <td id="info-form"><c:out value="${lp.marca.descripcion}"></c:out></td>
                                        <td id="info-form"><c:out value="${lp.producto.modelo}"></c:out></td>

                                            <td><c:out value="${d.cantidad}"></c:out></td>

                                            <td><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></td>
                                        </tr> 
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </table>
                    <c:choose>
                        <c:when test="${not empty listaDetalle}">
                            <div class="row">
                                <div class="col-md-3 col-md-offset-6">
                                    <form action="<c:url value="ConfirmacionSolicitudServlet"/>" method="get" onsubmit="return confirm('¿Está seguro que esto es todo lo que necesita?');">
                                        <input class="btn btn-default btn-success btn-xs" type="submit" name="registrarSolicitud" value="Finalizar"> 
                                    </form>
                                </div>
                                <div class="col-md-3 col-md-offset-0">
                                    <form action="<c:url value="CancelarSolicitudServlet"/>" method="post" onsubmit="return confirm('¿Al cancelar eliminará todos los productos de su solicitud?');">
                                        <input class="btn btn-default btn-danger btn-xs" type="submit" name="cancelarSolicitud" value="Cancelar"> 
                                    </form>
                                </div> 

                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="row">
                                <div class="col-md-3 col-md-offset-6">
                                    <form action="<c:url value="ConfirmacionSolicitudServlet"/>" method="get">
                                        <input class="btn btn-default btn-success btn-xs" type="submit" name="registrarSolicitud" value="Finalizar" disabled="true"> 
                                    </form>
                                </div>
                                <div class="col-md-3 col-md-offset-0">
                                    <form action="<c:url value="CancelarSolicitudServlet"/>" method="post">
                                        <input class="btn btn-default btn-danger btn-xs" type="submit" name="cancelarSolicitud" value="Cancelar" disabled="true"> 
                                    </form>
                                </div> 

                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </body>
</html>
