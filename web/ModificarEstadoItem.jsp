<%-- 
    Document   : ModificarEstadoItem
    Created on : 25-abr-2016, 23:56:49
    Author     : cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<c:choose>
    <c:when test="${empty usuarioSesion}">
        <c:redirect url="index.jsp"></c:redirect>
    </c:when>
</c:choose>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css"/>
        <script src="js/jquery.js" type="text/javascript"></script>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Palanquin' rel='stylesheet' type='text/css'>

        <title>Administración de Inventario</title>
    </head>
    <body>
        <%@include file="header.jsp" %> 
        <div class="container">
            <h3 class="text-center" id="titulo-pagina">Administración sistema Pañol</h3>
            <h5 class="text-center" id="titulo-pagina">Escuela de comunicaciones - Duoc UC</h5>
            <br>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
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
                            <li role="presentation"><a href="<c:url value="/AdminProductosServlet"/>">Administrar inventario</a></li>
                        </ul>
                    </div>
                        <h3 class="text-center" id="palanquin-font">Administrar Recursos Pañol</h3>
                        <hr>
                    <div class="table-responsive">
                        <table class="table">
                            <tbody>
                                <tr class="bg-primary">
                                    <th></th>
                                    <th class="text-center">Id Producto</th>
                                    <th class="text-center">Producto</th>
                                    <th class="text-center">Marca</th>
                                    <th class="text-center">Modelo</th>
                                    <th class="text-center">Descripción</th>
                                    <th class="text-center">Stock Disponible</th>
                                </tr>
                                <c:forEach var="dato" items="${lstProductos}">
                                    <c:set var="idProducto" value="${dato.producto.idProducto}"/>
                                    <tr>
                                        <td><img src="${dato.producto.rutaImagen}" width="100" height="100"></td>
                                        <td class="text-center"><c:out value="${dato.producto.idProducto}"/></td>
                                        <td class="text-center"><c:out value="${dato.producto.nombre}"/></td>
                                        <td class="text-center"><c:out value="${dato.marca.descripcion}"/></td>
                                        <td class="text-center"><c:out value="${dato.producto.modelo}"/></td>
                                        <td class="text-center"><c:out value="${dato.producto.descripcion}"/></td>
                                        <td class="text-center"><c:out value="${dato.producto.stock}"/></td>
                                    </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <br>
                    <h4 class="text-center bg-primary" id="palanquin-font">Detalle recursos por producto</h4>
                    <div class="table-responsive">
                        <table class="table">
                            <tbody>
                                <tr>
                                    <th class="text-center">Nro Serie</th>
                                    <th class="text-center">Estado</th>
                                    <th class="text-center">Activar/Desactivar</th>
                                </tr>
                                <c:forEach var="dato" items="${lstItem}">
                                    <tr>
                                        <td class="text-center"><c:out value="${dato.nroSerie}"/></td>
                                        <c:choose>
                                            <c:when test="${dato.activo == 1}">
                                                <td class="text-center text-success bg-success"><c:out value="Activo"/></td>  
                                            </c:when>
                                            <c:otherwise>
                                                <td class="text-center text-danger bg-danger"><c:out value="Inactivo"/></td> 
                                            </c:otherwise>
                                        </c:choose>
                                        <td class="text-center">
                                            <form action="<c:url value="/ModificarEstadoItemServlet"/>" method="post" onsubmit="return confirm('¿Está seguro de modificar el estado?');">
                                                <input type="hidden" name="nroSerie" value="${dato.nroSerie}"/>
                                                <input type="hidden" name="idProducto" value="${idProducto}"/>
                                                <c:choose>
                                                    <c:when test="${dato.activo == 1}">
                                                        <input class="btn btn-danger btn-xs" name="activo" type="submit" value="desactivar"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input class="btn btn-success btn-xs" name="activo" type="submit" value="activar"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </form>
                                        </td>
                                    </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-md-2"></div>
            </div>
        </div>
    </body>
</html>
