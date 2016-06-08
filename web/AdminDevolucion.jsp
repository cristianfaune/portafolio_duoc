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
    <c:otherwise>
        <html>
            <head>
                <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo=" crossorigin="anonymous"></script>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
                <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css"/>
                <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
                <link href='https://fonts.googleapis.com/css?family=Palanquin' rel='stylesheet' type='text/css'>
                <title>Administración Devolución</title>
            </head>
            <body>
                <%@include file="header.jsp" %>
                <div class="container">
                    <h4 class="text-center">Administración sistema Pañol</h4>
                    <h6 class="text-center">Escuela de comunicaciones - Duoc UC</h6>
                    <div class="row col-lg-offset-0">
                        <div class="col-md-0"></div>
                        <div class="col-md-12">
                            <div>
                                <ul class="nav nav-pills col-md-offset-4">
                                    <c:choose>
                                        <c:when test="${usuarioSesion.idPerfil == 100}">
                                            <li role="presentation"><a href="<c:url value="HomeJefeCarrera.jsp"/>">Home</a></li>
                                            </c:when>
                                            <c:when test="${usuarioSesion.idPerfil == 120}">
                                            <li role="presentation"><a href="<c:url value="HomePanolero.jsp"/>">Home</a></li>
                                            </c:when>
                                            <c:when test="${usuarioSesion.idPerfil == 110}">
                                            <li role="presentation"><a href="<c:url value="HomeCoordinador.jsp"/>">Home</a></li>
                                            </c:when>
                                        </c:choose>
                                    <li role="presentation"><a href="<c:url value="AdminPrestamos.jsp"/>">Préstamos</a></li>
                                    <li role="presentation"><a href="<c:url value="AdminDevolucion.jsp"/>">Devoluciones</a></li>
                                    <li role="presentation"><a href="<c:url value="#"/>">Reportes</a></li>
                                </ul>
                            </div>
                            <br>
                            <c:if test="${not empty mapMensajeExito}">
                                <div class="bg-success" id="danger-box">
                                    <h5 class="text-center text-success"><c:out value="${mapMensajeExito['mensajeExito']}"/></h5>
                                </div>
                            </c:if>
                            <br>
                            <div class="col-md-5 col-lg-offset-4">
                                <form action="<c:url value="/AdminDevolucionServlet"/>" method="get">
                                    <label>Ingresa el ticket de préstamo:</label>
                                    <div class="col-md-8">
                                        <input class="form-control" type="number" name="idPrestamo" maxlength="5"/>
                                        <p id="error-form"><c:out value="${mensajeError['errorPrestamo']}"/></p>
                                    </div>
                                    <div>
                                        <input class="btn btn-default" type="submit" name="buscar" value="Buscar"/>
                                    </div>
                                </form>
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
                                                <c:set var="varIdSolicitud" value="${dato.prestamo.idPrestamo}"/>
                                                <h2>Ticket: #<c:out value="${dato.prestamo.idPrestamo}"/></h2>
                                                <label id="info-form">Fecha préstamo: <fmt:formatDate value="${dato.prestamo.fechaRetiro}" type="date"/></label>
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
                                                                <form action="#" method="post">
                                                                    <input class="btn btn-default btn-success btn-xs" type="submit" value="devolver">
                                                                </form>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
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
    </c:otherwise>
</c:choose>
