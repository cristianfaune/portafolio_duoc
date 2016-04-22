<%-- 
    Document   : IngresoItem
    Created on : 21-abr-2016, 9:44:20
    Author     : cristian
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <title>Registro de Item</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <h4 class="text-center">Registro Productos Inventario</h4>
                    <form action="AdminProductos.jsp" method="post">
                        <div class="col-xs-6">
                            <input class="form-control input-lg" type="text" name="nroserie"/>
                        </div>
                        <input class="btn-default btn-group btn-lg" type="submit" value="Registrar"/>
                    </form>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
    </body>
</html>
