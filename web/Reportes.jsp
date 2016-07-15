<%-- 
    Document   : Reportes
    Created on : 16-06-2016, 21:24:40
    Author     : Doterzer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MODULO DE REPORTES</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h4 class="text-center">Administración sistema Pañol</h4>
        <h6 class="text-center">Escuela de comunicaciones - Duoc UC</h6>
        <div class="col-md-0"></div>
        <H3 class="text-center">SELECCION DE REPORTES</H3>
        <br>
        <div class="text-center"><p>En esta sección podra generar los reportes mas relevantes dentro del sistema</p></div>
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
        </ul>
        <table class="text-center">
            <tr>
                <td>&boxh;</td>
                <td>&boxh;</td>
                <td>&nbsp;</td>
                <td class="text-left">Estado de Prestamos Existentes</td>
                <td>&nbsp;</td>
                <td>&Rrightarrow;</td>
                <td>&nbsp;</td>
                <td class="text-right" ><form action="ReportesActivosInactivosServlet" method="post"><input type="submit" class="btn btn-primary btn-xs" value="Generar"></form>
                    <br>
                    <c:choose>
                        <c:when test="${usuarioSesion.idPerfil == 100}">

                    </tr>
                    <tr>
                    <br>
                    <td>&boxh;</td>
                    <td>&boxh;</td>
                    <td>&nbsp;</td>
                    <td class="text-left">Reporte General de Stock</td>
                    <td>&nbsp;</td>
                    <td>&Rrightarrow;</td>
                    <td>&nbsp;</td>
                    <td class="text-right"><form action="ReportesGeneralStockServlet" method="post"><input type="submit" class="btn btn-primary btn-xs" value="Generar"></form>
                        <br>
                        </tr>
                    <tr>
                    <br>
                    <td>&boxh;</td>
                    <td>&boxh;</td>
                    <td>&nbsp;</td>
                    <td class="text-left">Reporte Elementos mas y menos Solicitados</td>
                    <td>&nbsp;</td>
                    <td>&Rrightarrow;</td>
                    <td>&nbsp;</td>
                    <td class="text-right"><form action="ProductoMasyMenosSolicitadoReporteServlet" method="post"><input type="submit" class="btn btn-primary btn-xs" value="Generar"></form>
                        <br>
                        </tr>
                    <tr>
                    <br>
                    <td>&boxh;</td>
                    <td>&boxh;</td>
                    <td>&nbsp;</td>
                    <td class="text-left">Reporte Devoluciones Atrasadas con Detalle</td>
                    <td>&nbsp;</td>
                    <td>&Rrightarrow;</td>
                    <td>&nbsp;</td>
                    <td class="text-right"><form action="ReporteDevolucionServlet" method="post"><input type="submit" class="btn btn-primary btn-xs" value="Generar"></form>
                        <br>
                        </tr>
                    <tr>
                    <br>
                    <td>&boxh;</td>
                    <td>&boxh;</td>
                    <td>&nbsp;</td>
                    <td class="text-left">Productos con mas Perdidas o de Baja</td>
                    <td>&nbsp;</td>
                    <td>&Rrightarrow;</td>
                    <td>&nbsp;</td>
                    <td class="text-right"><form action="ReportesProductosPerdidasServlet" method="post"><input type="submit" class="btn btn-primary btn-xs" value="Generar"></form>
                        <br>
                        </tr>
                    </c:when>
                    <c:when test="${usuarioSesion.idPerfil == 110}">
                        </tr>
                    <tr>
                    <br>
                    <td>&boxh;</td>
                    <td>&boxh;</td>
                    <td>&nbsp;</td>
                    <td class="text-left">Reporte General de Stock</td>
                    <td>&nbsp;</td>
                    <td>&Rrightarrow;</td>
                    <td>&nbsp;</td>
                    <td class="text-right"><form action="ReportesGeneralStockServlet" method="post"><input type="submit" class="btn btn-primary btn-xs" value="Generar"></form>
                        <br>
                        </tr>
                    <tr>
                    <br>
                    <td>&boxh;</td>
                    <td>&boxh;</td>
                    <td>&nbsp;</td>
                    <td class="text-left">Reporte Elementos mas y menos Solicitados</td>
                    <td>&nbsp;</td>
                    <td>&Rrightarrow;</td>
                    <td>&nbsp;</td>
                    <td class="text-right"><form action="ProductoMasyMenosSolicitadoReporteServlet" method="post"><input type="submit" class="btn btn-primary btn-xs" value="Generar"></form>
                        <br>
                        </tr>
                    <tr>
                    <br>
                    <td>&boxh;</td>
                    <td>&boxh;</td>
                    <td>&nbsp;</td>
                    <td class="text-left">Reporte Devoluciones Atrasadas con Detalle</td>
                    <td>&nbsp;</td>
                    <td>&Rrightarrow;</td>
                    <td>&nbsp;</td>
                    <td class="text-right"><form action="ReporteDevolucionServlet" method="post"><input type="submit" class="btn btn-primary btn-xs" value="Generar"></form>
                        <br>
                        </tr>
                    <tr>
                    <br>
                    <td>&boxh;</td>
                    <td>&boxh;</td>
                    <td>&nbsp;</td>
                    <td class="text-left">Productos con mas Perdidas o de Baja</td>
                    <td>&nbsp;</td>
                    <td>&Rrightarrow;</td>
                    <td>&nbsp;</td>
                    <td class="text-right"><form action="ReportesProductosPerdidasServlet" method="post"><input type="submit" class="btn btn-primary btn-xs" value="Generar"></form>
                        <br>
                        </tr>
                    </c:when>
                    <c:when test="${usuarioSesion.idPerfil == 120}">

                    </c:when>
                </c:choose>
        </TABLE>
    </body>
</html>
