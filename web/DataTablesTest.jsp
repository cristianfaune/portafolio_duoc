<%-- 
    Document   : DataTablesTest
    Created on : 01-jun-2016, 10:51:59
    Author     : cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="datatables" uri="http://github.com/tduchateau/DataTables-taglib" %>
<!DOCTYPE html>
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
    <title>JSP Page</title>
</head>
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
</script>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <h3 class="text-center">Administración de Inventario</h3>
        <datatables:table cssClass="table" data="${lstProductos}" htmlTableId="tabla-productos" dataObjectId="row">
            <datatables:column title="Imagen">
                <img src="${row.producto.rutaImagen}" width="100" height="100">
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
                    <c:when test="${row.producto.stock > 1}">
                        <c:out value="${row.producto.stock}"></c:out>
                    </c:when>
                    <c:otherwise> 
                        <c:out value="Sin stock"></c:out>
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
    <div class="col-md-1"></div>
</div>
</body>
</html>
