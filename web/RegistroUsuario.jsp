<%-- 
    Document   : HomeJefeCarrera
    Created on : 26-abr-2016, 1:41:57
    Author     : cristian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:choose>
    <c:when test="${usuarioSesion.idPerfil == 120}">
        <c:redirect url="HomePanolero.jsp"></c:redirect>
    </c:when>
    <c:when test="${empty usuarioSesion}">
        <c:redirect url="index.jsp"></c:redirect>
    </c:when>
</c:choose>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css">
        <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Palanquin' rel='stylesheet' type='text/css'>
        <script src="js/jquery.js" type="text/javascript"></script>
        <title>Home - Jefe de Carrera</title>
    </head>
    <script type="text/javascript">
        $(document).ready(function () {

            $("#seleccionPerfil").change(function () {
                var idPerfil = $("#seleccionPerfil").val();

                if (idPerfil === '130') {
                    $('#seleccionCarrera').prop('disabled', false);
                } else {
                    $('#seleccionCarrera').prop('disabled', true);
                }
            });
        });
    </script>

    <style>
        #linea{
            color: white;
        }

    </style>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <h3 class="text-center" id="titulo-pagina">Administración sistema Pañol</h3>
            <h5 class="text-center" id="titulo-pagina">Escuela de comunicaciones - Duoc UC</h5>
            <br>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8 centered-pills">
                    <ul class="nav nav-pills" id="palanquin-font">
                        <c:choose>
                            <c:when test="${usuarioSesion.idPerfil == 100}">
                                <li role="presentation"><a href="<c:url value="HomeJefeCarrera.jsp"/>">Inicio</a></li>
                                </c:when>
                                <c:when test="${usuarioSesion.idPerfil == 120}">
                                <li role="presentation"><a href="<c:url value="HomePanolero.jsp"/>">Inicio</a></li>
                                </c:when>
                                <c:when test="${usuarioSesion.idPerfil == 110}">
                                <li role="presentation"><a href="<c:url value="HomeCoordinador.jsp"/>">Inicio</a></li>
                                </c:when>
                            </c:choose>
                        <li role="presentation"><a href="<c:url value="/MostrarUsuarioServlet"/>">Buscar usuario</a></li>
                        <li role="presentation"><a href="<c:url value="#"/>">Ingresar usuario</a></li>
                        <li role="presentation"><a href="<c:url value="ListarUsuariosServlet"/>">Listar usuarios</a></li>

                    </ul>
                    <br>
                    <c:if test="${not empty mapMensajeExito}">
                        <div class="bg-success" id="danger-box">
                            <h5 class="text-center text-success"><c:out value="${mapMensajeExito['mensaje']}"/></h5>
                        </div>
                    </c:if>
                    <h3 class="text-center" id="palanquin-font">Registro de Usuario</h3>
                    <hr>
                    <br>
                    <p>
                    <form class="form-horizontal" action="<c:url value="/RegistroUsuarioServlet"/>" method="post" onsubmit="return confirm('¿Está seguro de registrar al nuevo usuario?');">
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-3 text-left">
                                    <label for="inputNombre" class="control-label">Rut: </label>
                                    <input type="number" class="form-control text-center" name="rut"  
                                           placeholder="Ingrese su Rut" autofocus="true" 
                                           value="<c:out value="${param.rut}"/>" maxlength="8"
                                           id="inputWarning1">
                                    <c:choose>
                                        <c:when test="${empty mapMensaje}">
                                            <p id="info-form"><c:out value="${mapMensajeGet['errorRut']}"/></p>
                                        </c:when>
                                        <c:otherwise>
                                            <p id="error-form"><c:out value="${mapMensaje['errorRut']}"/></p> 
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col-md-1">
                                    <label for="inputNombre" class="control-label" id="linea">____</label>
                                    <input type="text" class="form-control text-center" name="digito"  
                                           value="<c:out value="${param.digito}"/>" maxlength="1"
                                           id="inputWarning1">  
                                </div>
                                <div class="col-md-4 text-left">
                                    <label for="inputNombre" class="control-label">Perfil: </label>
                                    <select class="form-control" id="seleccionPerfil" name="seleccionPerfil">
                                        <option value="0">--Seleccione--</option>
                                        <c:forEach var="dat1" items="${lstPerfiles}">
                                            <option value="${dat1.idPerfil}">
                                                <c:out value="${dat1.descripcion}"/>
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <p id="error-form"><c:out value="${mapMensaje['errorPerfil']}"/></p>
                                </div>
                                <div class="col-md-4 text-left">
                                    <label for="inputNombre" class="control-label">Carrera: </label>
                                    <select class="form-control" id="seleccionCarrera" name="seleccionCarrera" disabled="true">
                                        <option value="0">--Seleccione--</option>
                                        <c:forEach var="dat2" items="${lstCarreras}">
                                            <option value="${dat2.idCarrera}">
                                                <c:out value="${dat2.descripcion}"/>
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 text-left">
                                <label for="inputNombre" class="control-label">Nombres: </label>
                                <input type="text" class="form-control text-center" id="nombre" name="nombres" 
                                       value="<c:out value="${param.nombres}"/>" placeholder="Ingrese nombres">
                                <p id="error-form"><c:out value="${mapMensaje['errorNombre']}"/></p>
                            </div>
                            <div class="col-md-6 text-left">
                                <label for="inputApellidos" class="control-label">Apellidos: </label>
                                <input type="text" class="form-control text-center" id="apellidos" name="apellidos" 
                                       value="<c:out value="${param.apellidos}"/>" placeholder="Ingrese sus apellidos">
                                <p id="error-form"><c:out value="${mapMensaje['errorApellidos']}"/></p>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-12 text-left">
                                <label for="inputDireccion" class="control-label">Dirección: </label>
                                <input type="text" class="form-control text-center" id="direccion" name="direccion" 
                                       placeholder="Ingrese su dirección">
                                <p id="error-form"><c:out value="${mapMensaje['errorDireccion']}"/></p>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-4 text-left">
                                <label for="inputTelefono" class="control-label">Teléfono: </label>
                                <input type="text" class="form-control text-center" id="telefono" name="telefono" 
                                       value="<c:out value="${param.telefono}"/>" 
                                       placeholder="Ingrese su teléfono" maxlength="10">
                                <p id="error-form"><c:out value="${mapMensaje['errorTelefono']}"/></p>
                            </div>
                            <div class="col-md-8 text-left">
                                <label for="inputEmail" class="control-label">Email: </label>
                                <input type="email" class="form-control text-center" id="direccion" name="email" 
                                       value="<c:out value="${param.email}"/>" placeholder="Ingrese un email">
                                <p id="error-form"><c:out value="${mapMensaje['errorEmail']}"/></p>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-5 text-left">
                                <label for="inputPassword" class="control-label">Password: </label>
                                <input type="password" class="form-control text-center" id="direccion" name="password" 
                                       placeholder="Ingrese contraseña" maxlength="10">
                                <p id="info-form">**Máximo 10 caractéres**</p>
                                <p id="error-form"><c:out value="${mapMensaje['errorPassword']}"/></p>
                            </div>
                            <br>
                            <br>
                            <div class="col-lg-offset-10">
                                <input type="submit" class="btn btn-default btn-primary" value="Guardar"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-md-2"></div>
        </div> 
    </body>
</html>
