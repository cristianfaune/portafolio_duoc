<%-- 
    Document   : AdminSolicitudesEspeciales
    Created on : 01-jul-2016, 14:23:01
    Author     : cristian
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="datatables" uri="http://github.com/tduchateau/DataTables-taglib" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:choose>
    <c:when test="${usuarioSesion.idPerfil == 120}">
        <c:redirect url="HomePanolero.jsp"></c:redirect>
    </c:when>
    <c:when test="${usuarioSesion.idPerfil == 110}">
        <c:redirect url="HomeCoordinador.jsp"></c:redirect>
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
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <link href="css/jquery.dataTables.TableTools.css" rel="stylesheet">
        <script src="js/jquery.dataTables.TableTools.js"/></script>
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.js"></script>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Palanquin' rel='stylesheet' type='text/css'>
    <title>Administrar Solicitudes Especiales</title>
</head>
<body>
    <script>
        $(document).ready(function () {
            $('#tabla-solicitudes').dataTable({
                "dom": 'T<"clear">lfrtip',
                "language": {
                    "url": "//cdn.datatables.net/plug-ins/1.10.11/i18n/Spanish.json"
                },
                "tableTools": {
                    "sSwfPath": "/swf/copy_csv_xls_pdf.swf"
                },
            });

            $('.modal').on('show.bs.modal', function (e) {
                var $invoker = $(e.relatedTarget);

                var idItem = $invoker.val();

                $('#tituloModal').html('<p>Solicitud Especial Nro: <strong>' + idItem + '</strong><p>');
                $('#inputAceptar').prop('value', idItem);
                $('#inputNegar').prop('value', idItem);

                $.post('DetalleSolicitudModalServlet', {
                    idSolicitud: idItem

                }, function (responseText) {
                    $('#informacion').html(responseText);

                });
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
                    </ul>
                </div>
                <div class="col-md-3"></div>
            </div> 
        </div>
        <br>
        <div class="row">
            <div class="col-md-0"></div>
            <div class="col-md-12">
                <c:choose>
                <c:when test="${not empty mensajeA}">
                    <div class="alert alert-success" role="alert">
                        <p id="palanquin-font" class="text-center text-success"><c:out value="${mensajeA['mensajeAutorizado']}"/></p>
                    </div>
                </c:when>
                <c:when test="${not empty mensajeN}">
                    <div class="alert alert-danger" role="alert">
                        <p id="palanquin-font" class="text-center text-danger"><c:out value="${mensajeN['mensajeNegado']}"/></p>
                    </div>
                </c:when>
                </c:choose>
                <h3 class="text-center" id="palanquin-font">Administración de Solicitudes Especiales</h3>
                <hr>
                <br>
                <datatables:table cssClass="table" data="${lstSolEspeciales}" htmlTableId="tabla-solicitudes" dataObjectId="row">
                    <datatables:column title="ID">
                        <strong><c:out value="${row.solicitud.idSolicitud}"></c:out></strong>
                    </datatables:column>
                    <datatables:column title="Fecha Solicitud" sortable="true">
                        <fmt:formatDate value="${row.solicitud.fechaSolicitud}" type="date"/>
                    </datatables:column>
                    <datatables:column title="Rut" sortable="true"> 
                        <c:out value="${row.usuario.rut}"></c:out>
                    </datatables:column>
                    <datatables:column title="Nombre" sortable="true">
                        <c:out value="${row.usuario.nombres} ${row.usuario.apellidos}"></c:out>
                    </datatables:column>
                    <datatables:column title="Días Solicitados" sortable="true">
                        <c:out value="${row.solicitud.diasPrestamo}"></c:out>
                    </datatables:column>
                    <datatables:column title="Acciones">
                        <form action="<c:url value="/RegistroItemServlet"/>" method="get">
                            <button type="button" 
                                    class="btn btn-primary btn-xs" data-toggle="modal" 
                                    data-target=".modal" value="${row.solicitud.idSolicitud}"
                                    id="botonModal">Revisar
                            </button>
                        </form>
                    </datatables:column>
                </datatables:table>

                <!-- inicio modal item-->

                <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="tituloModal"></h4>
                            </div>

                            <div class="modal-body">
                                <div id="informacion" class="row">
                                    <p></p>
                                </div>                                              
                                <div class="modal-footer">
                                    <div class="col-xs-10">
                                        <form action="<c:url value="AceptarSolEspecialServlet"/>" method="post" onsubmit="return confirm('¿Está seguro de autorizar esta solicitud?');">
                                            <button type="submit" class="btn btn-success">Aceptar</button>
                                            <input id="inputAceptar" type="hidden" name="nroSolicitud" value="">
                                        </form>
                                    </div>
                                    <div>
                                        <form action="<c:url value="NegarSolEspecialServlet"/>" method="post" onsubmit="return confirm('¿Está seguro de negar esta solicitud?');">
                                            <button type="submit" class="btn btn-danger">Negar</button>
                                            <input id="inputNegar" type="hidden" name="nroSolicitud" value="">
                                        </form>
                                    </div>
                                </div>
                            </div><!-- /.modal-content -->

                        </div><!-- /.modal-dialog -->
                    </div><!-- /.modal -->
                    <!--fin modal item-->

                </div>
                <div class="col-md-0">
                </div>
            </div>
        </div>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
</body>
</html>
