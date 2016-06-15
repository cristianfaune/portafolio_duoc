<%-- 
    Document   : index
    Created on : 13-abr-2016, 11:43:46
    Author     : cristian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="datatables" uri="http://github.com/tduchateau/DataTables-taglib" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${empty usuarioSesion}">
    <c:redirect url="index.jsp"></c:redirect>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css"/>
        <link href="css/jquery.dataTables.css" rel="stylesheet">
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <link href="css/jquery.dataTables.TableTools.css" rel="stylesheet">
        <script src="js/jquery.dataTables.TableTools.js"/></script>
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.js"></script>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Palanquin' rel='stylesheet' type='text/css'>
    <title>Administración de Inventario</title>
<body>
    <script>
        $(document).ready(function () {
            $('#tabla-productos').dataTable({
                "dom": 'T<"clear">lfrtip',
                "language": {
                    "url": "//cdn.datatables.net/plug-ins/1.10.11/i18n/Spanish.json"
                },
                "tableTools": {
                    "sSwfPath": "/swf/copy_csv_xls_pdf.swf"
                },
            });
            
        });
    </script>

    <%@include file="header.jsp" %>
    <div class="container">
        <h3 class="text-center" id="titulo-pagina">Administración sistema Pañol</h3>
        <h5 class="text-center" id="titulo-pagina">Escuela de comunicaciones - Duoc UC</h5>
        <div>
            <br>
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10 centered-pills" id="palanquin-font">
                    <ul class="nav nav-pills">
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
                        <li role="presentation"><a href="<c:url value="/AdminProductosServlet"/>">Administrar Inventario</a></li>
                        <li role="presentation"><a href="<c:url value="/RegistroProductoServlet"/>">Registro productos</a></li>
                    </ul>
                </div>
                <div class="col-md-3"></div>
            </div> 
        </div>
        <br>
        <div class="row">
            <div class="col-md-0"></div>
            <div class="col-md-12">
                <h3 class="text-center" id="palanquin-font">Administración de Inventario</h3>
                <hr>
                <br>
                <datatables:table cssClass="table" data="${lstProductos}" htmlTableId="tabla-productos" dataObjectId="row">
                    <datatables:column title="Imagen">
                        <img src="${row.producto.rutaImagen}" width="70" height="70">
                    </datatables:column>
                    <datatables:column title="Id" sortable="true">
                        <c:out value="${row.producto.idProducto}"></c:out>
                    </datatables:column>
                    <datatables:column title="Nombre" sortable="true"> 
                        <c:out value="${row.producto.nombre}"></c:out>
                    </datatables:column>
                    <datatables:column title="Marca" sortable="true">
                        <c:out value="${row.marca.descripcion}"></c:out>
                    </datatables:column>
                    <datatables:column title="Modelo" sortable="true">
                        <c:out value="${row.producto.modelo}"></c:out>
                    </datatables:column>
                    <datatables:column title="Stock" sortable="true">
                        <c:choose>
                            <c:when test="${row.producto.stock > 0}">
                                <p class="text-center"><c:out value="${row.producto.stock}"></c:out></p>
                            </c:when>
                            <c:otherwise> 
                                <p class="text-danger bg-danger text-center" id="info-form"><c:out value="Sin stock"></c:out></p>
                            </c:otherwise>
                        </c:choose>
                    </datatables:column>
                    <datatables:column title="Agregar">
                        <form action="<c:url value="/RegistroItemServlet"/>" method="get">
                            <input type="hidden" name="idProducto" value="${row.producto.idProducto}"/>
                            <input class="btn btn-primary btn-xs" type="submit" value="Agregar Item"/>
                        </form>
                    </datatables:column>
                    <datatables:column title="Estado">
                        <form action="<c:url value="/ModificarEstadoItemServlet"/>" method="get">
                            <input type="hidden" name="idProducto" value="${row.producto.idProducto}"/>
                            <input class="btn btn-primary btn-xs" type="submit" value="Activar/Desactivar item"/>
                        </form>
                    </datatables:column>
                    <datatables:column title="Modificar">
                        <form action="<c:url value="/ModificarProductoServlet"/>" method="get">
                            <input type="hidden" name="idProducto" value="${row.producto.idProducto}"/>
                            <input class="btn btn-primary btn-xs" type="submit" value="Modificar"/>
                        </form>
                    </datatables:column>
                </datatables:table>
            </div>
            <div class="col-md-0"></div>
        </div>
    </div>
</body>
</html>
