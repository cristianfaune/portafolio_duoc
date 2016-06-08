<%-- 
    Document   : index
    Created on : 11-nov-2015, 8:31:58
    Author     : CristianFaune
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css">
        <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
        <title>Login Sistema Pañol - Escuela de comunicaciones - Duoc Viña del Mar</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-4"></div>
                <h3 id="opensans-font" align="center">Ingreso a sistema de administración de pañol</h3>
                <h5 id="opensans-font" align="center">Escuela de comunicaciones - Sede Viña del Mar</h5>
                <div class="col-md-4 col-md-offset-4">
                    <form class="form-horizontal" action="<c:url value="/ValidarIngreso"/>" method="post">
                        <c:if test="${empty mapMensajeRut}">
                            <div class="form-group form-control-static col-lg-12">
                                <label class="control-label" for="inputSuccess1">Rut:</label>
                                <input type="text" class="form-control" id="inputSuccess1" 
                                       name="rut" aria-describedby="helpBlock2" 
                                       placeholder="Ingrese su Rut -- sin puntos ni guion" autofocus="true" maxlength="9">
                                <span id="helpBlock2" class="help-block"></span>
                            </div>
                        </c:if>
                        <c:if test="${not empty mapMensajeRut}">
                            <div class="form-group has-error col-lg-12">
                                <label class="control-label" for="inputError1"><c:out value="${mapMensajeRut['errorRut']}"/></label>
                                <input type="text" class="form-control" 
                                       id="inputError1" name="rut" 
                                       placeholder="Ingrese su Rut -- sin puntos ni guion" autofocus="true"  maxlength="9">
                            </div>
                        </c:if>
                        <c:if test="${empty mapMensajePass}">
                            <div class="form-group form-control-static col-lg-12">
                                <label class="control-label" for="inputSuccess2">Password:</label>
                                <input type="password" class="form-control" id="inputSuccess1" 
                                       name="password" aria-describedby="helpBlock2" placeholder="Ingrese su password">
                                <span id="helpBlock2" class="help-block"></span>
                            </div>
                        </c:if>
                        <c:if test="${not empty mapMensajePass}">
                            <div class="form-group has-error col-lg-12">
                                <label class="control-label" for="inputError2"><c:out value="${mapMensajePass['errorPass']}"/></label>
                                <input type="password" class="form-control" 
                                       id="inputError1" name="password" placeholder="Ingrese su password">
                            </div>
                        </c:if>
                        <br><br>
                        <div class="form-group">
                            <div class="col-sm-offset-0 col-sm-11">
                                <input type="submit" class="btn btn-default btn-primary btn-block" value="Iniciar Sesión"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-4">
                </div>
            </div>
        </div>
    </div>
</body>
</html>
