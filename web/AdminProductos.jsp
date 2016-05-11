<%-- 
    Document   : index
    Created on : 13-abr-2016, 11:43:46
    Author     : cristian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <title>Administración de Inventario</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <h4 class="text-center">Administración sistema Pañol</h4>
            <h6 class="text-center">Escuela de comunicaciones - Duoc UC</h6>
                <div>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-10">
                            <ul class="nav nav-pills col-lg-offset-3">
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
                                <li role="presentation"><a href="<c:url value="/AdminProductosServlet"/>">Administrar Inventario Existente</a></li>
                                <li role="presentation"><a href="<c:url value="/RegistroProductoServlet"/>">Registro Nuevo Producto</a></li>
                            </ul>
                        </div>
                        <div class="col-md-3"></div>
                    </div> 
                </div>
                <br>
                <br>
                <div class="row">
                    <div class="col-md-0"></div>
                    <div class="col-md-12">
                        <h3 class="text-center" id="p">Administración de Inventario</h3>
                        <table class="table">
                            <tbody>
                                <tr>
                                    <th class="text-center">Imagen</th>
                                    <th class="text-center">Id</th>
                                    <th class="text-center">producto</th>
                                    <th class="text-center">Modelo</th>
                                    <th class="text-center">Marca</th>
                                    <th class="text-center">Descripción</th>
                                    <th class="text-center">Stock</th>
                                    <th></th>
                                    <th class="text-center">Acciones</th>
                                </tr>
                                <c:forEach var="dato" items="${lstProductos}">
                                    <c:choose>
                                        <c:when test="${dato.producto.stock == 0}">
                                            <tr class="bg-danger">
                                                <td><img src="${dato.producto.rutaImagen}" width="100" height="100"></td>
                                                <td class="text-center"><c:out value="${dato.producto.idProducto}"/></td>
                                                <td class="text-center"><c:out value="${dato.producto.nombre}"/></td>
                                                <td class="text-center"><c:out value="${dato.producto.modelo}"/></td>
                                                <td class="text-center"><c:out value="${dato.marca.descripcion}"/></td>
                                                <td class="text-center"><c:out value="${dato.producto.descripcion}"/></td>
                                                <td class="text-center"><c:out value="${dato.producto.stock}"/></td>
                                                <td>
                                                    <form action="<c:url value="/RegistroItemServlet"/>" method="get">
                                                        <input type="hidden" name="idProducto" value="${dato.producto.idProducto}"/>
                                                        <input class="btn btn-primary btn-xs" type="submit" value="Agregar Item"/>
                                                    </form>
                                                </td>
                                                <td>
                                                    <form action="<c:url value="/ModificarEstadoItemServlet"/>" method="get">
                                                        <input type="hidden" name="idProducto" value="${dato.producto.idProducto}"/>
                                                        <input class="btn btn-primary btn-xs" type="submit" value="Activar/Desactivar item"/>
                                                    </form>
                                                </td>
                                                <td>
                                                    <form action="<c:url value="/ModificarProductoServlet"/>" method="get">
                                                        <input type="hidden" name="idProducto" value="${dato.producto.idProducto}"/>
                                                        <input class="btn btn-primary btn-xs" type="submit" value="Modificar"/>
                                                    </form>
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
                                                <td>
                                                    <form action="<c:url value="/RegistroItemServlet"/>" method="get">
                                                        <input type="hidden" name="idProducto" value="${dato.producto.idProducto}"/>
                                                        <input class="btn btn-primary btn-xs" type="submit" value="Agregar Item"/>
                                                    </form>
                                                </td>
                                                <td>
                                                    <form action="<c:url value="/ModificarEstadoItemServlet"/>" method="get">
                                                        <input type="hidden" name="idProducto" value="${dato.producto.idProducto}"/>
                                                        <input class="btn btn-primary btn-xs" type="submit" value="Activar/Desactivar item"/>
                                                    </form>
                                                </td>
                                                <td>
                                                    <form action="<c:url value="/ModificarProductoServlet"/>" method="get">
                                                        <input type="hidden" name="idProducto" value="${dato.producto.idProducto}"/>
                                                        <input class="btn btn-primary btn-xs" type="submit" value="Modificar"/>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-0"></div>
                </div>
        </div>
    </body>
</html>
