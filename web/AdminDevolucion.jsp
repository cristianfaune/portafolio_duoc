<%-- 
    Document   : AdminDevolucion
    Created on : 07-jun-2016, 17:55:06
    Author     : cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:choose>
    <c:when test="${usuarioSesion.idPerfil == 100}">
        <c:redirect url="HomeJefeCarrera.jsp"></c:redirect>
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
    <intercept-url pattern="/favicon.ico" access="ROLE_ANONYMOUS"></intercept-url>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css"/>
    <script src="js/jquery.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Palanquin' rel='stylesheet' type='text/css'>
    <title>Administración Devolución</title>
</head>
<body>
    <script>
        $(document).ready(function () {

            $('.modal').on('show.bs.modal', function (e) {
                var $invoker = $(e.relatedTarget);

                var idItem = $invoker.val();

                $('#tituloModal').html('<p>Registro Devolución Item Nro. Serie: <strong>' + idItem + '</strong><p>');
                $('#inputOculto').prop('value', idItem);
            });
        });
    </script>
    <%@include file="header.jsp" %>
    <div class="container">
        <h3 class="text-center" id="titulo-pagina">Administración sistema Pañol</h3>
        <h5 class="text-center" id="titulo-pagina">Escuela de comunicaciones - Duoc UC</h5>
        <br>
        <div class="row col-lg-offset-0">
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
                        <li role="presentation"><a href="<c:url value="AdminPrestamos.jsp"/>">Préstamos</a></li>
                        <li role="presentation"><a href="<c:url value="AdminDevolucion.jsp"/>">Devoluciones</a></li>
                    </ul>
                </div>
                <br>
                <c:if test="${not empty mapMensajeExito}">
                    <div class="bg-success" id="danger-box">
                        <h5 class="text-center text-success"><c:out value="${mapMensajeExito['mensajeExito']}"/></h5>
                    </div>
                </c:if>
                <br>
                <div class="row">
                    <form action="<c:url value="/AdminDevolucionServlet"/>" method="get">      
                        <div class="input-group col-md-3 center_div">    
                            <label id="palanquin-font">Ingresa el ticket de préstamo:</label>
                            <input class="form-control" type="number" name="idPrestamo" maxlength="5"/>
                            <p id="info-form">--Ingrese el número préstamo--</p>
                            <span class="input-group-btn">
                                <input class="btn btn-default" type="submit" name="buscar" value="Buscar"/>
                            </span>
                        </div>
                    </form>
                </div>
                <div class="text-center">
                    <p id="error-form"><c:out value="${mensajeError['errorPrestamo']}"/></p>
                </div>
                <br>
                <br>
                <c:if test="${not empty lstDetallePrestamo or not empty lstUsuarioPrestamo}">
                    <div class="row">
                        <div class="col-md-12 col-md-offset-1">
                            <br>
                            <div class="col-md-6">
                                <h3>Datos usuario:</h3>
                                <c:forEach end="0" var="dato" items="${lstUsuarioPrestamo}">
                                    <label id="info-form">Rut: <c:out value="${dato.usuario.rut}"/></label>
                                    <br>
                                    <label id="info-form">Nombre: <c:out value="${dato.usuario.nombres} ${dato.usuario.apellidos}"/></label>
                                    <br>
                                    <label id="info-form">Carrera: ${dato.carrera.descripcion}</label>
                                </div>
                                <div class="col-md-6">
                                    <h3>Datos Préstamo:</h3>
                                    <h2>Ticket: #<c:out value="${dato.prestamo.idPrestamo}"/></h2>
                                    <c:set var="idPres" value="${dato.prestamo.idPrestamo}"></c:set>
                                    <label id="info-form">Fecha préstamo: <fmt:formatDate value="${dato.prestamo.fechaRetiro}" type="date"/></label><br>
                                    <label id="info-form">Fecha estimada entrega: <fmt:formatDate value="${dato.prestamo.fechaEstimadaEntrega}" type="date"/></label>
                                    <c:forEach end="0" var="datoPanolero" items="${lstDetallePrestamo}">
                                        <p id="info-form">Pañolero asociado: <c:out value="${datoPanolero.usuario.nombres} ${datoPanolero.usuario.apellidos}"></c:out></p>
                                    </c:forEach>
                                    <c:if test="${contadorHcd > 0 and contadorDetalle == contadorHcd}">
                                        <div class="col-md-6">
                                            <form action="<c:url value="/ConfirmarDevolucionServlet"></c:url>" method="post">
                                                    <input type="submit" class="btn btn-success btn-block" value="Finalizar">
                                                    <input type="hidden" value="${dato.prestamo.idPrestamo}" name="idPrestamoDev">
                                            </form>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <br>

                        <div class="row col-lg-offset-0">
                            <div class="col-md-12">
                                <h3>Detalle productos:</h3>
                                <br>
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <th class="text-center">Imagen</th>
                                            <th class="text-center">Id</th>
                                            <th class="text-center">producto</th>
                                            <th class="text-center">Modelo</th>
                                            <th class="text-center">Marca</th>
                                            <th class="text-center">Nro. Serie</th>
                                            <th class="text-center">Acciones</th>
                                        </tr>
                                        <c:forEach var="dato" items="${lstDetallePrestamo}">
                                            <tr>
                                                <td><img src="${dato.producto.rutaImagen}" width="70" height="70"></td>
                                                <td class="text-center"><c:out value="${dato.producto.idProducto}"/></td>
                                                <td class="text-center"><c:out value="${dato.producto.nombre}"/></td>
                                                <td class="text-center"><c:out value="${dato.producto.modelo}"/></td>
                                                <td class="text-center"><c:out value="${dato.marca.descripcion}"/></td>
                                                <td class="text-center"><c:out value="${dato.detallePrestamo.nroSerie}"/></td>
                                                <td>
                                                    <!-- Large modal -->
                                                    <button type="button" 
                                                            class="btn btn-primary center-block" data-toggle="modal" 
                                                            data-target=".modal" value="${dato.detallePrestamo.nroSerie}"
                                                            id="botonModal">Devolución
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <!-- inicio modal item-->

                                    <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                    <h4 class="modal-title" id="tituloModal"></h4>
                                                </div>

                                                <div class="modal-body">
                                                    <form action="<c:url value="AdminDevolucionServlet"/>" method="post" onsubmit="return confirm('¿Está seguro de guardar la información');">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <label>Seleccione una opción:</label>
                                                                <select class="form-control" name="opcionDevolucion">
                                                                    <option value="aceptado">Aceptado</option>
                                                                    <option value="dañado">Dañado</option>
                                                                    <option value="no devuelto">No devuelto</option>
                                                                </select>
                                                            </div>
                                                            <div class="col-md-8">
                                                                <label>Observación: (opcional)</label>
                                                                <textarea name="observacion" class="form-control" rows="5" maxlength="200"></textarea>
                                                            </div>
                                                        </div>                                              
                                                        <div class="modal-footer">
                                                            <button type="submit" class="btn btn-success">Guardar</button>
                                                            <input type="hidden" value="${idPres}" name="idPrestamoPost"> 
                                                            <input id="inputOculto" type="hidden" name="nroSerieOculto" value="">
                                                            </form>  
                                                            <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
                                                        </div>

                                                </div><!-- /.modal-content -->

                                            </div><!-- /.modal-dialog -->
                                        </div><!-- /.modal -->
                                        <!--fin modal item-->

                                        </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
        <div class="col-md-0"></div>
    </div>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
</body>
</html>
