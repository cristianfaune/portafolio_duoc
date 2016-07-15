<%-- 
    Document   : reportesProductos
    Created on : 16-06-2016, 21:19:03
    Author     : Doterzer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h4 class="text-center">Administración sistema Pañol</h4>         
        <h6 class="text-center">Escuela de comunicaciones - Duoc UC</h6>
        <div class="center-block"><h4><a href="Reportes.jsp">Volver</a></h4></div>
        <div class="col-md-0"></div>
        <div class="text-center">


            <h2>REPORTES STOCK DE PRODUCTOS</h2>

        </div>
        <div class="row">
        </div>
        <table class="table">
            <tbody>

                <tr><td colspan="2"> <h3 class="text-center">Productos con Stock:</h3></td></tr>
            <br>
            <tr>
                <th class="text-center">ID Producto</th>
                <th class="text-center">Nombre</th>
                <th class="text-center">Modelo</th>
                <th class="text-center">Descripcion</th>
                <th class="text-center">Stock</th>
                <th class="text-center">Imagen</th>
            </tr>
            <c:forEach var="conS" items="${conStock}">
                <tr>
                    <td class="text-center"><c:out value="${conS.idProducto}"/></td>
                    <td class="text-center"><c:out value="${conS.nombre}"/></td>
                    <td class="text-center"><c:out value="${conS.modelo}"/></td>
                    <td class="text-center"><c:out value="${conS.descripcion}"/></td>
                    <td class="text-center"><c:out value="${conS.stock}"/></td>
                    <td><img src="${conS.rutaImagen}" width="100" height="100"></td>
                </tr>
            </c:forEach>
        </tbody>
        <br>
        <br>
        <tbody>

            <tr><td colspan="2">   <h3 class="text-center">Productos Sin Stock:</h3> </td></tr>
            <tr>
                <th class="text-center">ID Producto</th>
                <th class="text-center">Nombre</th>
                <th class="text-center">Modelo</th>
                <th class="text-center">Descripcion</th>
                <th class="text-center">Stock</th>
                <th class="text-center">Imagen</th>
            </tr>
            <c:forEach var="sinS" items="${sinStock}">
                <tr>
                    <td class="text-center"><c:out value="${sinS.idProducto}"/></td>
                    <td class="text-center"><c:out value="${sinS.nombre}"/></td>
                    <td class="text-center"><c:out value="${sinS.modelo}"/></td>
                    <td class="text-center"><c:out value="${sinS.descripcion}"/></td>
                    <td class="text-center"><c:out value="${sinS.stock}"/></td>
                    <td><img src="${sinS.rutaImagen}" width="100" height="100"></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>   
</body>
</html>
