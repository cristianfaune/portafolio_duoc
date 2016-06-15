<%-- 
    Document   : IngresoItem
    Created on : 21-abr-2016, 9:44:20
    Author     : cristian
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:choose>
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
        <title>Registro de Item</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <h3 class="text-center" id="titulo-pagina">Administración sistema Pañol</h3>
            <h5 class="text-center" id="titulo-pagina">Escuela de comunicaciones - Duoc UC</h5>
            <br>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
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
                    <c:if test="${not empty mapMensaje['mensaje']}">
                        <div id="danger-box" class="bg-success">
                            <h5 class="text-center text-success" id="palanquin-font"><c:out value="${mapMensaje['mensaje']}"/></h5>
                        </div>
                    </c:if>
                    <h4 class="text-center" id="palanquin-font">Registro Productos Inventario</h4>
                    <hr>
                    <br>
                    <form action="<c:url value="/RegistroItemServlet"/>" method="post" onsubmit="return confirm('¿Está seguro de enviar los datos?');">
                        <div class="row col-md-offset-2">
                            <div class="col-md-6">
                                <input class="form-control input-lg" type="text" 
                                       style="text-transform: uppercase" name="nroserie" 
                                       placeholder="Ej.   XD23455A6789"/>
                            </div>
                            <div class="col-md-6">
                                <input class="btn-default btn-group btn-lg btn-success" id="btnRegistrar" 
                                       type="submit" value="Registrar"/>
                            </div>
                        </div>
                        <p id="info-form" class="col-md-offset-3">--Ingrese el nro de serie--</p>
                        <br>
                        <table class="table">
                            <tbody>
                                <tr>
                                    <th>Imagen</th>
                                    <th>Id</th>
                                    <th>producto</th>
                                    <th>Modelo</th>
                                    <th>Marca</th>
                                    <th>Descripción</th>
                                    <th>Stock</th>
                                </tr>
                                <c:forEach var="dato" items="${lstProductos}">
                                    <tr>
                                        <td><img src="${dato.producto.rutaImagen}" 
                                                 width="100" height="100"></td>
                                        <td><c:out value="${dato.producto.idProducto}"/></td>
                                <input type="hidden" name="idProducto" value="${dato.producto.idProducto}"/>
                                <td><c:out value="${dato.producto.nombre}"/></td>
                                <td><c:out value="${dato.producto.modelo}"/></td>
                                <td><c:out value="${dato.marca.descripcion}"/></td>
                                <td><c:out value="${dato.producto.descripcion}"/></td>
                                <td><c:out value="${dato.producto.stock}"/></td>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>  
                </div>
                <div class="col-md-3"></div>
            </div>
            <h5 class="text-center text-success">
        </div>
    </body>
</html>
