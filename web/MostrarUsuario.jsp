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
    <body>
        <script type="text/javascript">
            $(document).ready(function () {
                
                var idPerfilSesion = '${usuarioBusqueda.idPerfil}';
                
                if (idPerfilSesion !== '130') {
                        $('#seleccionCarrera').prop('disabled', true);
                    } else {
                        $('#seleccionCarrera').prop('disabled', false);
                    }
                
                $("#seleccionPerfil").change(function(){
                    var idPerfil = $("#seleccionPerfil").val();

                    if (idPerfil === '130') {
                        $('#seleccionCarrera').prop('disabled', false);
                    } else {
                        $('#seleccionCarrera').prop('disabled', true);
                    }
                });
            });
        </script>
        <%@include file="header.jsp" %>
        <div class="container">
            <h3 class="text-center" id="titulo-pagina">Administración sistema Pañol</h3>
            <h5 class="text-center" id="titulo-pagina">Escuela de comunicaciones - Duoc UC</h5>
            <br>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8 centered-pills">
                    <div>
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
                            <li role="presentation"><a href="<c:url value="MostrarUsuarioServlet"/>">Buscar usuario</a></li>
                            <li role="presentation"><a href="<c:url value="/RegistroUsuarioServlet"/>">Ingresar usuario</a></li>
                            <li role="presentation"><a href="<c:url value="ListarUsuariosServlet"/>">Listar usuarios</a></li>
                        </ul>
                    </div>
                    <br>
                    <c:if test="${not empty mapMensajeExito}">
                        <div class="bg-success" id="danger-box">
                            <h5 class="text-center text-success"><c:out value="${mapMensajeExito['mensajeExito']}"/></h5>
                        </div>
                    </c:if>
                    <br>
                    <div class="row">
                        <form action="<c:url value="/MostrarUsuarioServlet"/>" method="post">      
                            <div class="input-group col-md-4 center_div">    
                                <label id="palanquin-font">Ingrese Rut para búsqueda:</label>
                                <input class="form-control" type="text" name="rut" maxlength="9"/>
                                <p id="info-form">--Ingrese rut sin puntos ni guion--</p>
                                <span class="input-group-btn">
                                    <input class="btn btn-default" type="submit" name="buscar" value="Buscar"/>
                                </span>
                            </div>
                        </form>
                    </div>
                    <div class="text-center">
                        <p id="error-form"><c:out value="${mapMensajeRut['errorRut']}"/></p>
                    </div>
                    <br>
                    <c:if test="${not empty usuarioBusqueda}">
                        <div class="row">
                            <div class="col-lg-12">
                                <h3 id="palanquin-font">Datos usuario:</h3> 
                            </div>
                        </div>
                        <hr>
                        <form action="<c:url value="/ModificarUsuarioServlet"/>" method="post" onsubmit="return confirm('¿Está seguro de modificar los datos?');">
                            <br>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Perfil:</label>
                                    <select class="form-control" id="seleccionPerfil" name="seleccionPerfil">
                                        <option value="0">--Seleccione--</option>
                                        <c:forEach var="dat1" items="${lstPerfiles}">
                                            <option value="${dat1.idPerfil}" ${dat1.idPerfil == usuarioBusqueda.idPerfil ? 'selected="selected"' : ''}>
                                                <c:out value="${dat1.descripcion}"/>
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <p id="error-form"><c:out value="${mapMensaje['errorPerfil']}"/></p>
                                </div>
                                <div class="col-md-6">
                                    <label>Carrera:</label>
                                    <select class="form-control" id="seleccionCarrera" name="seleccionCarrera" disabled="true">
                                        <option value="0">--Seleccione--</option>
                                        <c:forEach var="dat2" items="${lstCarreras}">
                                            <option value="${dat2.idCarrera}" ${dat2.idCarrera == usuarioBusqueda.idCarrera ? 'selected="selected"' : ''}>
                                                <c:out value="${dat2.descripcion}"/>
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <p id="info-form">**Ingresar solo si aplica**</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <label>Nombres:</label>
                                    <input class="form-control text-center" type="text" 
                                           name="nombres" value="${usuarioBusqueda.nombres}"/>
                                    <p id="error-form"><c:out value="${mapMensaje['errorNombre']}"/></p>
                                </div>
                                <div class="col-md-4">
                                    <label>Apellidos:</label>
                                    <input class="form-control text-center" type="text" 
                                           name="apellidos" value="${usuarioBusqueda.apellidos}"/>
                                    <p id="error-form"><c:out value="${mapMensaje['errorApellidos']}"/></p>
                                </div>
                                <div class="col-md-4">
                                    <label>Teléfono:</label><br>
                                    <input class="form-control text-center" type="number" 
                                           name="telefono" value="${usuarioBusqueda.telefono}" maxlength="10"/>
                                    <p id="error-form"><c:out value="${mapMensaje['errorTelefono']}"/></p>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-md-12">
                                    <label>Dirección:</label>
                                    <input class="form-control text-center" type="text" 
                                           name="direccion" value="${usuarioBusqueda.direccion}"/>
                                    <p id="error-form"><c:out value="${mapMensaje['errorDireccion']}"/></p>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-md-12">
                                    <label>Email:</label>
                                    <input class="form-control text-center" type="email" 
                                           name="email" value="${usuarioBusqueda.email}"/>
                                    <p id="error-form"><c:out value="${mapMensaje['errorEmail']}"/></p>
                                </div>
                            </div>
                            <br>
                            <input type="hidden" name="rut" value="${usuarioBusqueda.rut}"/>
                            <input class="btn btn-default btn-primary col-sm-offset-10" type="submit" value="Actualizar"/>
                        </form>
                    </div>
                </c:if>
                <div class="col-md-2"></div>
            </div>
    </body>
</html>
