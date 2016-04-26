<%-- 
    Document   : ModificarEstadoItem
    Created on : 25-abr-2016, 23:56:49
    Author     : cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <title>Administración de Inventario</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <h3 class="text-center" id="p">Administrar de Recursos Pañol</h3>
                    <table class="table">
                        <tbody>
                            <tr>
                                <th>Imagen</th>
                                <th>Id Producto</th>
                                <th>Producto</th>
                                <th>Modelo</th>
                                <th>Marca</th>
                                <th>Descripción</th>
                                <th>Stock Disponible</th>
                            </tr>
                            <c:forEach var="dato" items="${lstProductos}">
                                <tr>
                                    <td><img src="${dato.producto.rutaImagen}" width="100" height="100"></td>
                                    <td><c:out value="${dato.producto.idProducto}"/></td>
                                    <td><c:out value="${dato.producto.nombre}"/></td>
                                    <td><c:out value="${dato.producto.modelo}"/></td>
                                    <td><c:out value="${dato.marca.descripcion}"/></td>
                                    <td><c:out value="${dato.producto.descripcion}"/></td>
                                    <td><c:out value="${dato.producto.stock}"/></td>
                                </c:forEach>
                        </tbody>
                    </table>
                    <br>
                    <br>
                    <h4 class="text-center">Recursos por producto</h4>
                    <table class="table">
                        <tbody>
                            <tr>
                                <th>Nro Serie</th>
                                <th>Estado</th>
                                <th>Activar/Desactivar</th>
                            </tr>
                            <c:forEach var="dato" items="${lstItem}">
                                <tr>
                                    <td><c:out value="${dato.nroSerie}"/></td>
                                    <c:choose>
                                        <c:when test="${dato.activo == 1}">
                                            <td><c:out value="Activo"/></td>  
                                        </c:when>
                                        <c:otherwise>
                                            <td><c:out value="Inactivo"/></td> 
                                        </c:otherwise>
                                    </c:choose>
                                    <td>
                                        <form action="<c:url value="/ModificarEstadoItemServlet"/>" method="post">
                                            <input type="hidden" name="idProducto" value="${dato.nroSerie}"/>
                                            <c:choose>
                                                <c:when test="${dato.activo == 1}">
                                                    <input class="btn btn-danger btn-xs" type="submit" value="Desactivar"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <input class="btn btn-success btn-xs" type="submit" value="Activar"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </form>
                                    </td>
                                </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-2"></div>
            </div>
        </div>
    </body>
</html>
