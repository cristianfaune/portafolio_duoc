<%-- 
    Document   : RegistroSolicitudExitosa
    Created on : 25-may-2016, 17:33:39
    Author     : cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css"/>
        <title>Administración de Pañol</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <h4 class="text-center">Administración sistema Pañol</h4>
            <h6 class="text-center">Escuela de comunicaciones - Duoc UC</h6>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <h3 class="text-center">¡ Su solicitud fue ingresada con éxito !</h3>
                    <h5 class="text-center">Un correo electrónico fue enviado a 
                        <strong><c:out value="${usuarioSolicitud.usuario.email}"></c:out></strong>
                    con los detalles de su pedido.</h5>
                    <h5 class="text-center">Con este número puede pasar a validar su préstamo:</h5>
                    <br>
                    <h1 class="text-center"><strong><c:out value="${ultimoIdSolicitud}"></c:out></strong></h1>
                        <div class="row">
                            <div class="col-md-4 col-lg-offset-5">
                                <label id="info-form">Detalle de la solicitud: </label><br>
                                <form action="<c:url value="PdfSolicitudServlet"/>" method="post">
                                <input type="hidden" name="idSolicitud" value="${ultimoIdSolicitud}">
                                <button type="submit" class="btn btn-default col-lg-offset-2" aria-label="Left Align" title="Descargar PDF">
                                    <span class="glyphicon glyphicon-save-file" aria-hidden="true"></span>
                                </button>
                            </form>
                        </div>
                    </div>
                    <br><br>
                    <div class="row">
                        <div class="col-md-6 col-lg-offset-3">
                            <form action="<c:url value="CancelarSolicitudServlet"/>" method="get">
                                <button type="submit" class="btn btn-primary btn-block">Finalizar</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-2"></div>      
            </div>
        </div>
    </body>
</html>
