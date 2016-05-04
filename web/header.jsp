<%-- 
    Document   : header
    Created on : 15-abr-2016, 0:42:33
    Author     : cristian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css"/>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    </head>
    <body>
        <div class="container">
        </div>
        <header id="header">
            <div id="logo">
                <img src="imagenes/logoDuoc.png" width="300" height="110" alt="logoDuoc"/>
            </div>
            <div class="container">
                <div class="text-right">
                    <!--<c:set var="now" value="<%=new java.util.Date()%>" />
                    <h6 class="text-right" id="fecha"><fmt:formatDate value="${now}" type="date"></fmt:formatDate></h6>-->
                    </div>
                <c:if test="${usuarioSesion != null}">
                    <div class="row">
                        <div class="col-lg-5 col-lg-offset-7">
                            <h4 class="text-right" id="usuario-sesion">
                                <span class="glyphicon glyphicon-user" id="usuario-sesion" aria-hidden="true"></span>
                                <c:out value="${usuarioSesion.nombres} ${usuarioSesion.apellidos}"/>
                            </h4>
                        </div>
                    </div>
                    <div class="text-right">
                        <span class="glyphicon glyphicon-log-out" id="usuario-sesion" aria-hidden="true"></span>
                        <a id="cerrar-sesion" href="<c:url value="/CerrarSesionServlet"/>">Cerrar Sesión</a>
                    </div> 
                </c:if>
            </div>
        </header>
    </div>
</div>
</body>
</html>
