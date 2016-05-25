<%-- 
    Document   : IngresoItem
    Created on : 21-abr-2016, 9:44:20
    Author     : cristian
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <title>Registro de Item</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <h4 class="text-center">Administración sistema Pañol</h4>
            <h6 class="text-center">Escuela de comunicaciones - Duoc UC</h6>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div class="col-lg-offset-2">
                        <ul class="nav nav-pills">
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
                            <li role="presentation"><a href="<c:url value="/AdminProductosServlet"/>">Administrar Inventario</a></li>
                        </ul>
                    </div>
                    <c:if test="${not empty mapMensaje['mensaje']}">
                        <div id="danger-box" class="bg-success">
                            <h5 class="text-center text-success"><c:out value="${mapMensaje['mensaje']}"/></h5>
                        </div>
                    </c:if>
                    <h4 class="text-center">Registro Productos Inventario</h4>
                    <br>
                    <form action="<c:url value="/RegistroItemServlet"/>" method="post" onsubmit="return confirm('¿Está seguro de enviar los datos?');">
                        <div class="col-xs-6">
                            <input class="form-control input-lg" type="text" 
                                   style="text-transform: uppercase" name="nroserie" 
                                   placeholder="Ej.   XD23455A6789"/>
                        </div>
                        <input class="btn-default btn-group btn-lg" id="btnRegistrar" 
                               type="submit" value="Registrar"/>
                        <br>
                        <p id="info-form">--Ingrese el nro de serie sin puntos ni guiones--</p>
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
