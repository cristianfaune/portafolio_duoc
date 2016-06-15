<%-- 
    Document   : ErrorUsuarioInactivo
    Created on : 31-may-2016, 11:13:37
    Author     : cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css"/>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Palanquin' rel='stylesheet' type='text/css'>
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
                    <h3 class="text-center">¡ Error en el ingreso al sistema !</h3><br><br>
                    <h5 class="text-center">Actualmente ud. no cumple con los requisitos para ingresar 
                        al sistema de administración de pañol de la escuela de comunicaciones de Duoc UC
                        - sede Viña del Mar.</h5><br><br>
                    <h5 class="text-center">Le aconsejamos que se comunique con la dirección de su escuela para solucionar<br>
                        lo antes posible cualquier inconveniente.</h5><br><br>
                    <h6 class="text-center">
                        Teléfono: 800 215001 - desde Celulares : 22915 3439<br>
                        Email: <a href="mailto:sistemapanol@gmail.com">sistemapanol@gmail.com</a>
                    </h6>
                    <div class="col-md-2"></div>      
                </div>
            </div>
            <br>
            <br>
            <br>
            <p class="text-center"><a href="index.jsp">Volver</a></p>
        </div>
    </body>
</html>

