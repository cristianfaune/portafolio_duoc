<%-- 
    Document   : index
    Created on : 11-nov-2015, 8:31:58
    Author     : CristianFaune
--%>
<%@page import="cl.dominio.Usuario"%>

<%
    session.removeAttribute("usuarioSesion");
    session.invalidate();

%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css">
    <script src="js/jquery.js" type="text/javascript"></script>
    <link rel="shortcut icon" href="">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
    <title>Login Sistema Pañol - Escuela de comunicaciones - Duoc Viña del Mar</title>
</head>
<script>
    $(document).ready(function () {
        $('#iniciar').click(function () {
            $('#boton1').hide();
            $('#boton2').show();
        });
    });
</script>


<body onload="javascript:history.go(1)" id="page-top" class="index">
    <%@include file="header.jsp" %>
    <div class="container">
        <h3 id="opensans-font" align="center">Ingreso a sistema de administración de pañol</h3>
        <h5 id="opensans-font" align="center">Escuela de comunicaciones - Sede Viña del Mar</h5>
        <div class="row">
            <div class="col-md-5"></div>
            <div class="col-md-2" id="login">
                <form class="form-horizontal" action="<c:url value="/ValidarIngreso"/>" method="post">
                    <c:if test="${empty mapMensajeRut}">
                        <div class="form-group form-control-static">
                            <label class="control-label" for="inputSuccess1">Rut:</label>
                            <input type="text" class="form-control text-center" id="ingresoRut1" 
                                   name="rut" aria-describedby="helpBlock2" 
                                   placeholder="" autofocus="true" maxlength="9">
                            <span id="helpBlock2" class="help-block"></span>
                        </div>
                    </c:if>
                    <c:if test="${not empty mapMensajeRut}">
                        <div class="form-group has-error">
                            <label class="control-label" for="inputError1"><c:out value="${mapMensajeRut['errorRut']}"/></label>
                            <input type="text" class="form-control text-center" 
                                   id="ingresoRut2" name="rut" 
                                   placeholder="" autofocus="true"  maxlength="9">
                        </div>
                    </c:if>
                    <c:if test="${empty mapMensajePass}">
                        <div class="form-group form-control-static">
                            <label class="control-label" for="inputSuccess2">Password:</label>
                            <input type="password" class="form-control text-center" id="ingresoPass1" 
                                   name="password" aria-describedby="helpBlock2" placeholder="">
                            <span id="helpBlock2" class="help-block"></span>
                        </div>
                    </c:if>
                    <c:if test="${not empty mapMensajePass}">
                        <div class="form-group has-error">
                            <label class="control-label" for="inputError2">
                                <c:out value="${mapMensajePass['errorPass']}"/></label>
                            <input type="password" class="form-control text-center" 
                                   id="ingresoPass2" name="password" placeholder="">
                        </div>
                    </c:if>
                    <br>
                    <div id="boton1">
                        <div class="form-group">
                            <div>
                                <button type="submit" id="iniciar" class="btn btn-default btn-primary btn-block" value="Iniciar Sesión">
                                    Iniciar Sesión
                                </button>
                            </div>
                        </div>
                    </div>
                    <!--boton-->
                    <div id="boton2" hidden="true">
                        <div class="form-group">
                            <div>
                                <button type="submit" id="iniciar" class="btn btn-default btn-primary btn-block" value="Iniciar Sesión" disabled="true">
                                    Validando...  <span class="glyphicon glyphicon-refresh glyphicon-spin"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-5"></div>
        </div>
    </div>
    <section>
        <div id="borde-footer">
            <div>
                <footer id="footer">
                    <p class="text-center" id="info-form">--Sistema pañol Duoc UC--</p>
                    <p class="text-center" id="info-form">sistemapanol@gmail.com</p>
                </footer>
            </div>
        </div>
    </section>
    <script src="js/bootstrap.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
</body>

</html>