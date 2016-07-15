<%-- 
    Document   : DevolucionesFueradePlazo
    Created on : 06-07-2016, 23:49:28
    Author     : Doterzer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DEVOLUCIONES FUERA DE PLAZO</title>
    </head>
    <body>
                 <%@include file="header.jsp" %>
        <h4 class="text-center">Administración sistema Pañol</h4>         
        <h6 class="text-center">Escuela de comunicaciones - Duoc UC</h6>
        <div class="center-block"><h4><a href="Reportes.jsp">Volver</a></h4></div>
        <div class="col-md-0"></div>
        <div class="text-center">


            <h2>DEVOLUCIONES FUERA DE PLAZO</h2>

        </div>
        <div class="row">
        </div>
        <table class="table">
            
            <tr>
                <td colspan="2" class="text-center"><c:out value="${MensajeVacio}"></c:out></td>
            </tr>
            <tbody>

                <tr><td colspan="2"> <h3 class="text-center">Listado Devoluciones Fuera de plazo:</h3></td></tr>
            <br>
            <tr>
                <th class="text-center">TICKET DEVOLUCION</th>
                <th class="text-center">NOMBRE</th>
                <th class="text-center">APELLIDOS</th>
                <th class="text-center">RUT</th>
                <th class="text-center">FECHA DE LA SOLICITUD</th>
                <th class="text-center">FECHA DEL PRESTAMO</th>
                <th class="text-center">FECHA DE LA DEVOLUCION</th>
            </tr>
            <c:forEach var="rdevolucion" items="${ReporteDevolucion}">
                <tr>
                    <td class="text-center"><c:out value="${rdevolucion.devolucion.idDevolucion}"/></td>
                    <td class="text-center"><c:out value="${rdevolucion.usuario.nombres}"/></td>
                    <td class="text-center"><c:out value="${rdevolucion.usuario.apellidos}"/></td>
                    <td class="text-center"><c:out value="${rdevolucion.usuario.rut}"/></td>
                    <td class="text-center"><c:out value="${rdevolucion.solicitud.fechaSolicitud}"/></td>
                    <td class="text-center"><c:out value="${rdevolucion.prestamo.fechaRetiro}"/></td>
                    <td class="text-center"><c:out value="${rdevolucion.devolucion.fechaDevolucion}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>   
    </body>
</html>
