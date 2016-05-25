<%-- 
    Document   : AdminPrestamos
    Created on : 11-may-2016, 14:42:12
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
                <title>Administración Préstamos</title>
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
                                    <li role="presentation"><a href="<c:url value="AdminPrestamos.jsp"/>">Préstamos y Devoluciones</a></li>
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
                                <form action="<c:url value="/BuscarSolicitudServlet"/>" method="get">
                                    <label>Buscar por ID Solicitud:</label>
                                    <div class="col-md-8">
                                        <input class="form-control" type="number" name="idSolicitud" maxlength="5"/>
                                        <p id="error-form"><c:out value="${mensajeError['errorSolicitud']}"/></p>
                                    </div>
                                    <div>
                                        <input class="btn btn-default" type="submit" name="buscar" value="Buscar"/>
                                    </div>
                                </form>
                            </div>
                            <br>
                            <br>
                            <c:if test="${not empty lstSolicitud}">
                                <div class="row">
                                    <div class="col-md-12">
                                        <br>
                                        <div class="col-md-6">
                                            <h3>Datos usuario solicitante:</h3>
                                            <hr>
                                            <c:forEach end="0" var="dato" items="${lstSolicitud}">
                                                <label id="info-form">Rut: <c:out value="${dato.usuario.rut}"/></label>
                                                <br>
                                                <label id="info-form">Nombre: <c:out value="${dato.usuario.nombres} ${dato.usuario.apellidos}"/></label>
                                                <br>
                                                <label id="info-form">Carrera: ${dato.carrera.descripcion}</label>
                                            </div>
                                            <div class="col-md-6">
                                                <h3>Datos Solicitud:</h3>
                                                <hr>
                                                <c:set var="varIdSolicitud" value="${dato.solicitud.idSolicitud}"/>
                                                <h2>id: #<c:out value="${dato.solicitud.idSolicitud}"/></h2>
                                                <label id="info-form">Fecha solicitud: <fmt:formatDate value="${dato.solicitud.fechaSolicitud}" type="date"/></label>
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
                                                        <th class="text-center">Stock</th>
                                                        <th class="text-center">Cantidad Solicitada</th>
                                                    </tr>
                                                    <c:forEach var="dato" items="${lstSolicitud}">
                                                        <c:choose>
                                                            <c:when test="${dato.producto.stock < dato.detalleSolicitud.cantidad}">
                                                                <tr class="bg-danger">
                                                                    <td><img src="${dato.producto.rutaImagen}" width="100" height="100"></td>
                                                                    <td class="text-center"><c:out value="${dato.producto.idProducto}"/></td>
                                                                    <td class="text-center"><c:out value="${dato.producto.nombre}"/></td>
                                                                    <td class="text-center"><c:out value="${dato.producto.modelo}"/></td>
                                                                    <td class="text-center"><c:out value="${dato.marca.descripcion}"/></td>
                                                                    <td class="text-center"><c:out value="${dato.producto.stock}"/></td>
                                                                    <td class="text-center"><c:out value="${dato.detalleSolicitud.cantidad}"/></td>
                                                                </tr>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <tr>
                                                                    <td><img src="${dato.producto.rutaImagen}" width="100" height="100"></td>
                                                                    <td class="text-center"><c:out value="${dato.producto.idProducto}"/></td>
                                                                    <td class="text-center"><c:out value="${dato.producto.nombre}"/></td>
                                                                    <td class="text-center"><c:out value="${dato.producto.modelo}"/></td>
                                                                    <td class="text-center"><c:out value="${dato.marca.descripcion}"/></td>
                                                                    <td class="text-center"><c:out value="${dato.producto.stock}"/></td>
                                                                    <td class="text-center"><c:out value="${dato.detalleSolicitud.cantidad}"/></td>
                                                                </tr>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:if test="${dato.producto.stock < dato.detalleSolicitud.cantidad}">
                                                            <c:set var="stock" value="1"></c:set>
                                                        </c:if>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <br>
                            <c:choose>
                                <c:when test="${stock == 1}">
                                    <div class="row col-lg-offset-10">
                                        <div class="col-md-5">
                                            <form action="#" method="post">
                                                <input class="btn btn-default btn-danger" type="submit" name="editar" value="Editar Solicitud">
                                            </form>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="row col-lg-offset-10">
                                        <div class="col-md-5">
                                            <form action="BuscarSolicitudServlet" method="post" onsubmit="return confirm('¿Está seguro de registrar este préstamo?');">
                                                <input type="hidden" name="varIdSol" value="${varIdSolicitud}">
                                                <input class="btn btn-default btn-primary" type="submit" name="prestamo" id="prestamo" value="Confirmar >">
                                            </form>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
                    <div class="col-md-0"></div>
                </div>
                <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
            </body>
        </html>
    </c:otherwise>
</c:choose>