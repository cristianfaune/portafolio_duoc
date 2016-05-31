<%-- 
    Document   : HomeJefeCarrera
    Created on : 26-abr-2016, 1:41:57
    Author     : cristian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:choose>
    <c:when test="${empty usuarioSesion}">
        <c:redirect url="index.jsp"></c:redirect>
    </c:when>
    <c:when test="${usuarioSesion.activo == 0}">
        <c:redirect url="ErrorUsuarioInactivo.jsp"></c:redirect>
    </c:when>
</c:choose>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/nuevosEstilos.css">
        <title>Home - Jefe de Carrera</title>
    </head>
    <script type="text/javascript">
        function perfil() {
            $(document).ready(function () {
                var idPerfil = $('#seleccionaPerfil').val();

            });
        }
        function carrera() {
            $(document).ready(function () {
                var idCarrera = $('#seleccionaCarrera').val();

            });
        }
    </script>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <h4 class="text-center">Administración sistema Pañol</h4>
            <h6 class="text-center">Escuela de comunicaciones - Duoc UC</h6>
            <div class="row col-lg-offset-0">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <ul class="nav nav-pills col-lg-offset-2">
                        <c:choose>
                            <c:when test="${usuarioSesion.idPerfil == 100}">
                                <li role="presentation"><a href="<c:url value="HomeJefeCarrera.jsp"/>">Home</a></li>
                                </c:when>
                                <c:when test="${usuarioSesion.idPerfil == 120}">
                                <li role="presentation"><a href="<c:url value="HomePanolero.jsp"/>">Home</a></li>
                                </c:when>
                                <c:when test="${usuarioSesion.idPerfil == 110}">
                                <li role="presentation"><a href="<c:url value="HomeCoordinador.jsp"/>">Home</a></li>
                                </c:when>
                            </c:choose>
                        <li role="presentation"><a href="<c:url value="/MostrarUsuarioServlet"/>">Buscar Usuario</a></li>
                        <li role="presentation"><a href="<c:url value="#"/>">Ingresar Nuevo Usuario</a></li>
                        <li role="presentation"><a href="<c:url value="ListarUsuariosServlet"/>">Mostrar Usuarios</a></li>

                    </ul>
                    <br>
                    <c:if test="${not empty mapMensajeExito}">
                        <div class="bg-success" id="danger-box">
                            <h5 class="text-center text-success"><c:out value="${mapMensajeExito['mensaje']}"/></h5>
                        </div>
                    </c:if>
                    <h3>Registro de Usuario</h3>
                    <br>
                    <p>
                    <form class="form-horizontal" action="<c:url value="/RegistroUsuarioServlet"/>" method="post" onsubmit="return confirm('¿Está seguro de registrar al nuevo usuario?');">
                        <div class="row">
                            <div class="col-md-4">
                                <label for="inputRut" class="control-label">Rut:</label>
                                <input type="text" class="form-control" id="rut" name="rut" 
                                       value="<c:out value="${param.rut}"/>" 
                                       placeholder="Ingrese su Rut" autofocus="true" maxlength="9">   
                                <p id="info-form">**Ingrese su rut sin puntos ni guion**</p>
                                <p id="error-form"><c:out value="${mapMensaje['errorRut']}"/></p>
                            </div>
                            <div class="col-md-4">
                                <label>Perfil:</label>
                                <select class="form-control" id="seleccionCategoria" name="seleccionPerfil">
                                    <option value="0">--Seleccione--</option>
                                    <c:forEach var="dat1" items="${lstPerfiles}">
                                        <option value="${dat1.idPerfil}">
                                            <c:out value="${dat1.descripcion}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <p id="error-form"><c:out value="${mapMensaje['errorPerfil']}"/></p>
                            </div>
                            <div class="col-md-4">
                                <label>Carrera:</label>
                                <select class="form-control" id="seleccionCategoria" name="seleccionCarrera" >
                                    <option value="0">--Seleccione--</option>
                                    <c:forEach var="dat2" items="${lstCarreras}">
                                        <option value="${dat2.idCarrera}">
                                            <c:out value="${dat2.descripcion}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <p id="info-form">**Ingresar solo si aplica**</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label for="inputNombre" class="control-label">Nombres: </label>
                                <input type="text" class="form-control" id="nombre" name="nombres" 
                                       value="<c:out value="${param.nombre}"/>" placeholder="Ingrese nombres">
                                <p id="error-form"><c:out value="${mapMensaje['errorNombre']}"/></p>
                            </div>
                            <div class="col-md-6">
                                <label for="inputApellidos" class="control-label">Apellidos: </label>
                                <input type="text" class="form-control" id="apellidos" name="apellidos" 
                                       value="<c:out value="${param.apellidos}"/>" placeholder="Ingrese sus apellidos">
                                <p id="error-form"><c:out value="${mapMensaje['errorApellidos']}"/></p>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-12">
                                <label for="inputDireccion" class="control-label">Dirección: </label>
                                <input type="text" class="form-control" id="direccion" name="direccion" 
                                       value="<c:out value="${param.direccion}"/>" placeholder="Ingrese su dirección">
                                <p id="error-form"><c:out value="${mapMensaje['errorDireccion']}"/></p>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-4">
                                <label for="inputTelefono" class="control-label">Teléfono: </label>
                                <input type="text" class="form-control" id="telefono" name="telefono" 
                                       value="<c:out value="${param.telefono}"/>" 
                                       placeholder="Ingrese su teléfono" maxlength="10">
                                <p id="error-form"><c:out value="${mapMensaje['errorTelefono']}"/></p>
                            </div>
                            <div class="col-md-8">
                                <label for="inputEmail" class="control-label">Email: </label>
                                <input type="email" class="form-control" id="direccion" name="email" 
                                       value="<c:out value="${param.email}"/>" placeholder="Ingrese un email">
                                <p id="error-form"><c:out value="${mapMensaje['errorEmail']}"/></p>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-5">
                                <label for="inputPassword" class="control-label">Password: </label>
                                <input type="password" class="form-control" id="direccion" name="password" 
                                       value="<c:out value="${param.password}"/>" placeholder="Ingrese contraseña">
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
