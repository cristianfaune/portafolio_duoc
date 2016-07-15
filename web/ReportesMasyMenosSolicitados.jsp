<%-- 
    Document   : ReportesMasyMenosSolicitados
    Created on : 29-06-2016, 20:33:58
    Author     : Doterzer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Producos mas y Menos Solicitados</title>
    </head>|
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
            
            <tr>
                <td colspan="2" class="text-center"><c:out value="${MensajeVacio}"></c:out></td>
            </tr>
            <tbody>

                <tr><td colspan="2"> <h3 class="text-center">Productos Menos Solicitados:</h3></td></tr>
            <br>
            <tr>
                <th class="text-center">ID Producto</th>
                <th class="text-center">Nombre</th>
                <th class="text-center">Modelo</th>
                <th class="text-center">Descripcion</th>
                <th class="text-center">Stock</th>
                <th class="text-center">Imagen</th>
            </tr>
            <c:forEach var="menosS" items="${menosSolicitado}">
                <tr>
                    <td class="text-center"><c:out value="${menosSidProducto}"/></td>
                    <td class="text-center"><c:out value="${menosS.nombre}"/></td>
                    <td class="text-center"><c:out value="${menosS.modelo}"/></td>
                    <td class="text-center"><c:out value="${menosS.descripcion}"/></td>
                    <td class="text-center"><c:out value="${menosS.stock}"/></td>
                    <td><img src="${menosS.rutaImagen}" width="100" height="100"></td>
                </tr>
            </c:forEach>
        </tbody>
        <br>
        <br>
        <tbody>

            <tr><td colspan="2">   <h3 class="text-center">Productos Mas Solicitados:</h3> </td></tr>
            <tr>
                <th class="text-center">ID Producto</th>
                <th class="text-center">Nombre</th>
                <th class="text-center">Modelo</th>
                <th class="text-center">Descripcion</th>
                <th class="text-center">Stock</th>
                <th class="text-center">Imagen</th>
            </tr>
            <c:forEach var="mmasS" items="${masSolicitado}">
                <tr>
                    <td class="text-center"><c:out value="${mmasS.idProducto}"/></td>
                    <td class="text-center"><c:out value="${mmasS.nombre}"/></td>
                    <td class="text-center"><c:out value="${mmasS.modelo}"/></td>
                    <td class="text-center"><c:out value="${mmasS.descripcion}"/></td>
                    <td class="text-center"><c:out value="${mmasS.stock}"/></td>
                    <td><img src="${mmasS.rutaImagen}" width="100" height="100"></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>   
    </body>
</html>
