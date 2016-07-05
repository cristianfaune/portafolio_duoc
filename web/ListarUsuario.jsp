<%-- 
    Document   : HomeJefeCarrera
    Created on : 26-abr-2016, 1:41:57
    Author     : cristian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="datatables" uri="http://github.com/tduchateau/DataTables-taglib" %>
<!DOCTYPE html>
<c:choose>
    <c:when test="${usuarioSesion.idPerfil == 120}">
        <c:redirect url="HomePanolero.jsp"></c:redirect>
    </c:when>
    <c:when test="${empty usuarioSesion}">
        <c:redirect url="index.jsp"></c:redirect>
    </c:when>
</c:choose>
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
    <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css"/>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Palanquin' rel='stylesheet' type='text/css'>
    <title>Home - Jefe de Carrera</title>
</head>
<body>
    <script>
        $(document).ready(function () {
            $('#tabla-usuarios').dataTable({
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
        <br>
        <div class="row">
            <div class="col-md-0"></div>
            <div class="col-md-12">
                <div class="centered-pills">
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
                        <li role="presentation"><a href="<c:url value="/MostrarUsuario.jsp"/>">Buscar usuario</a></li>
                        <li role="presentation"><a href="<c:url value="/RegistroUsuarioServlet"/>">Ingresar usuario</a></li>
                    </ul>
                </div>
                    <h3 class="text-center" id="palanquin-font">Listado de Usuarios</h3>
                    <hr>
                    <br>
                <datatables:table cssClass="table" data="${lstUsuarios}" htmlTableId="tabla-usuarios" dataObjectId="row">
                    <datatables:column title="Rut" sortable="true">
                        <c:out value="${row.usuario.rut}"></c:out>
                    </datatables:column>
                    <datatables:column title="Nombres" sortable="true">
                        <c:out value="${row.usuario.nombres}"></c:out>
                    </datatables:column>
                    <datatables:column title="Apellidos" sortable="true"> 
                        <c:out value="${row.usuario.apellidos}"></c:out>
                    </datatables:column>
                    <datatables:column title="Perfil" sortable="true">
                        <c:out value="${row.perfil.descripcion}"></c:out>
                    </datatables:column>
                    <datatables:column title="Carrera" sortable="true">
                        <c:choose>
                            <c:when test="${empty row.carrera.descripcion}">
                                <p class="text-center text-danger bg-danger" id="info-form"><c:out value="No aplica"></c:out></p>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${row.carrera.descripcion}"></c:out>
                            </c:otherwise>
                        </c:choose>
                    </datatables:column>
                    <datatables:column title="Dirección" sortable="true">
                        <c:out value="${row.usuario.direccion}"></c:out>
                    </datatables:column>
                    <datatables:column title="Teléfono" sortable="true">
                        <c:out value="${row.usuario.telefono}"></c:out>
                    </datatables:column>
                    <datatables:column title="Email" sortable="true">
                        <c:out value="${row.usuario.email}"></c:out>
                    </datatables:column>
                    <datatables:column title="Activo" sortable="true">
                        <c:choose>
                            <c:when test="${row.usuario.activo == 1}">
                                <p class="text-success text-center bg-success" id="info-form"><c:out value="Activo"></c:out></p>
                            </c:when>
                            <c:otherwise>
                                <p class="text-center text-danger bg-danger" id="info-form"><c:out value="Inactivo"></c:out></p>
                            </c:otherwise>
                        </c:choose>
                    </datatables:column>
                    <datatables:column title="Estado">
                        <form action="<c:url value="/ModificarEstadoUsuarioServlet"/>" method="post" onsubmit="return confirm('¿Está seguro de modificar el estado del usuario?');">
                            <input type="hidden" name="rut" value="${row.usuario.rut}"/>
                            <input type="hidden" name="activo" value="${row.usuario.activo}"/>
                            <c:choose>
                                <c:when test="${row.usuario.activo == 1}">
                                    <input class="btn btn-danger btn-xs" name="activo" type="submit" value="desactivar"/>
                                </c:when>
                                <c:otherwise>
                                    <input class="btn btn-success btn-xs" name="activo" type="submit" value="activar"/>
                                </c:otherwise>
                            </c:choose>
                        </form>
                    </datatables:column>
                    <datatables:column title="Modificar">
                        <form action="<c:url value="/MostrarUsuarioServlet"/>" method="post">
                            <input type="hidden" name="rut" value="${row.usuario.rut}"/>
                            <input class="btn btn-primary btn-xs" name="modificar" type="submit" value="modificar"/>
                        </form>
                    </datatables:column>
                </datatables:table>
            </div>
            <div class="col-md-0"></div>
        </div>
    </div>

</body>
</html>
