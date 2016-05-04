<%-- 
    Document   : HomePanolero
    Created on : 26-abr-2016, 21:45:58
    Author     : cristian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <title>Home - Pañolero</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">  
            <h4 class="text-center">Administración sistema Pañol</h4>
            <h6 class="text-center">Escuela de comunicaciones - Duoc UC</h6>
            <div class="row col-lg-offset-1">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <ul class="nav nav-pills">
                        <li role="presentation"><a href="#">Administrar Solicitudes</a></li>
                        <li role="presentation"><a href="<c:url value="/AdminProductosServlet"/>">Administrar Inventario</a></li>
                        <li role="presentation"><a href="#">Préstamos</a></li>
                        <li role="presentation"><a href="#">Devoluciones</a></li>
                        <li role="presentation"><a href="#">Reportes</a></li>
                    </ul>
                    <br>
                    <h3>Bienvenido al home de Pañolero</h3>
                    <br>
                    <p>
                        En tu sitio podrás realizar todas las funcionalidades
                        necesarias para la administración de los recursos del 
                        pañol de la escuela.
                    </p>
                </div>
                <div class="col-md-2"></div>
            </div> 
        </div>
    </body>
</html>
