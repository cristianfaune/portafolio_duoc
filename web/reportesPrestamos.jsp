<%-- 
    Document   : reportesPrestamos
    Created on : 19-06-2016, 16:23:12
    Author     : Doterzer
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reportes Prestamos</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h4 class="text-center">Administración sistema Pañol</h4>         
        <h6 class="text-center">Escuela de comunicaciones - Duoc UC</h6>
        <div class="center-block"><h4><a href="Reportes.jsp">Volver</a></h4></div>
        <div class="col-md-0"></div>
        <div class="text-center">
        
            
            <h2>REPORTES DE PRESTAMOS ACTIVOS E INACTIVOS</h2>
           
        </div>
        <div class="row">
        </div>
        <table class="table">
            <tbody>

                <tr><td colspan="2"> <h3 class="text-center">Prestamos Activos:</h3></td></tr>
            <br>
            <tr>
                <th class="text-center">Ticket Prestamo</th>
                <th class="text-center">Estado</th>
                <th class="text-center">Fecha de Retiro</th>
                <th class="text-center">Fecha Devolucion</th>
                <th class="text-center">Ticket de Solicitud Asociada</th>
                <th class="text-center">Prestamo Especial</th>
            </tr>
            <c:forEach var="activo" items="${Activos}">
                <tr>
                    <td class="text-center"><c:out value="${activo.idPrestamo}"/></td>
                    <c:choose>
                        <c:when test="${activo.activa == 1}">
                            <td class="text-center"><c:out value="Activo"/></td>        
                        </c:when>
                        <c:otherwise>
                            <td class="text-center"><c:out value="Inactivo"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td class="text-center"><c:out value="${activo.fechaRetiro}"/></td>
                    <td class="text-center"><c:out value="${activo.fechaEstimadaEntrega}"/></td>
                    <td class="text-center"><c:out value="${activo.idSolicitud}"/></td>
                    <c:choose>
                        <c:when test="${inactivo.prestamoEspecial == 1}">
                            <td class="text-center"><c:out value="Sí"/></td>        
                        </c:when>
                        <c:otherwise>
                            <td class="text-center"><c:out value="No"/></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </tbody>
        <br>
        <br>
        <tbody>

            <tr><td colspan="2">   <h3 class="text-center">Prestamos Inactivos:</h3> </td></tr>
            <tr>
                <th class="text-center">Ticket Prestamo</th>
                <th class="text-center">Estado</th>
                <th class="text-center">Fecha de Retiro</th>
                <th class="text-center">Fecha Devolucion</th>
                <th class="text-center"> Ticket de Solicitud Asociada</th>
                <th class="text-center">Prestamo Especial</th>
            </tr>
            <c:forEach var="inactivo" items="${Inactivos}">
                <tr>
                    <td class="text-center"><c:out value="${inactivo.idPrestamo}"/></td>
                    <c:choose>
                        <c:when test="${activo.activa == 1}">
                            <td class="text-center"><c:out value="Activo"/></td>        
                        </c:when>
                        <c:otherwise>
                            <td class="text-center"><c:out value="Inactivo"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td class="text-center"><c:out value="${inactivo.fechaRetiro}"/></td>
                    <td class="text-center"><c:out value="${inactivo.fechaEstimadaEntrega}"/></td>
                    <td class="text-center"><c:out value="${inactivo.idSolicitud}"/></td>
                    <c:choose>
                        <c:when test="${inactivo.prestamoEspecial == 1}">
                            <td class="text-center"><c:out value="Sí"/></td>        
                        </c:when>
                        <c:otherwise>
                            <td class="text-center"><c:out value="No"/></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </tbody>
    </table>   
</body>
</html>
