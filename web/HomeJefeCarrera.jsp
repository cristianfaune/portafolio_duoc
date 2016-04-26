<%-- 
    Document   : HomeJefeCarrera
    Created on : 26-abr-2016, 1:41:57
    Author     : cristian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <h1 class="text-center">Jefe de Carrera</h1>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <ul class="nav nav-tabs">
                        <li role="presentation" class="active"><a href="#">Inicio</a></li>
                        <li role="presentation"><a href="#">Administrar Usuarios</a></li>
                        <li role="presentation"><a href="<c:url value="/AdminProductosServlet"/>">Administrar Inventario</a></li>
                    </ul>

                </div>
                <div class="col-md-2"></div>
            </div> 
        </div>
    </body>
</html>
