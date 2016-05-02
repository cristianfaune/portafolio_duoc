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
            <div class="row col-lg-offset-1">
                <div class="col-md-2"></div>
                <div class="col-md-7">
                    <ul class="nav nav-tabs">
                        <li role="presentation"><a href="<c:url value="/MostrarUsuario.js"/>">Buscar Usuario</a></li>
                        <li role="presentation"><a href="<c:url value="#"/>">Ingresar Nuevo Usuario</a></li>
                        <li role="presentation"><a href="<c:url value="ListarUsuariosServlet"/>">Mostrar Usuarios</a></li>
                        <li role="presentation"><a href="<c:url value="/HomeJefeCarrera.jsp"/>">Volver</a></li>

                    </ul>
                    <br>
                    <c:if test="${not empty mapMensaje['mensaje']}">
                        <div id="danger-box" class="bg-danger">
                            <p class="text-danger text-center"><c:out value="${mapMensaje['errorRut']}"/></p>
                            <p class="text-danger text-center"><c:out value="${mapMensaje['errorNombre']}"/></p>
                            <p class="text-danger text-center"><c:out value="${mapMensaje['errorApellidos']}"/></p>
                            <p class="text-danger text-center"><c:out value="${mapMensaje['errorTelefono']}"/></p>
                            <p class="text-danger text-center"><c:out value="${mapMensaje['errorCelular']}"/></p>
                            <p class="text-danger text-center"><c:out value="${mapMensaje['errorDireccion']}"/></p>
                            <p class="text-danger text-center"><c:out value="${mapMensaje['errorEmail']}"/></p>
                            <p class="text-danger text-center"><c:out value="${mapMensaje['errorPassword']}"/></p>
                        </div>
                    </c:if>
                    <h3>Registro de Usuario</h3>
                    <br>
                    <p>
                    <form class="form-horizontal" action="<c:url value="/RegistroUsuarioServlet"/>" method="post">
                        <div class="form-group">
                            **Ingrese su rut sin puntos ni guion**
                            <label for="inputRut" class="col-sm-2 control-label">Rut:</label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" id="rut" name="rut" 
                                       value="<c:out value="${param.rut}"/>" 
                                       placeholder="Ingrese su Rut" autofocus="true" maxlength="9">           
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputNombre" class="col-sm-2 control-label">Nombres: </label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" id="nombre" name="nombre" 
                                       value="<c:out value="${param.nombre}"/>" placeholder="Ingrese nombres">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputApellidos" class="col-sm-2 control-label">Apellidos: </label>
                            <div class="col-xs-6">
                                <input type="text" class="form-control" id="apellidos" name="apellidos" 
                                       value="<c:out value="${param.apellidos}"/>" placeholder="Ingrese sus apellidos">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputTelefono" class="col-sm-2 control-label">Teléfono: </label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" id="telefono" name="telefono" 
                                       value="<c:out value="${param.telefono}"/>" 
                                       placeholder="Ingrese su teléfono" maxlength="10">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputCelular" class="col-sm-2 control-label">Celular </label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" id="telefono" name="celular" 
                                       value="<c:out value="${param.telefono}"/>" placeholder="Ingrese su teléfono Móvil">

                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputDireccion" class="col-sm-2 control-label">Dirección: </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="direccion" name="direccion" 
                                       value="<c:out value="${param.direccion}"/>" placeholder="Ingrese su dirección">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputEmail" class="col-sm-2 control-label">Email: </label>
                            <div class="col-md-8">
                                <input type="email" class="form-control" id="direccion" name="email" 
                                       value="<c:out value="${param.email}"/>" placeholder="Ingrese un email">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword" class="col-sm-2 control-label">Password: </label>
                            <div class="col-md-8">
                                <input type="password" class="form-control" id="direccion" name="password" 
                                       value="<c:out value="${param.password}"/>" placeholder="Ingrese contraseña">
                            </div>

                        </div>
                        <div class="form-group">
                            <label>Perfil:</label>
                            <select class="form-control" id="seleccionCategoria" name="seleccionPerfil">
                                <option value="0">--Seleccione una categoría--</option>
                                <c:forEach var="dat1" items="${lstPerfiles}">
                                    <option value="${dat1.idPerfil}">
                                        <c:out value="${dat1.descripcion}"/>
                                    </option>
                                </c:forEach>
                            </select>
                            <div class="form-group">
                                <label>Carrera:</label>
                                <select class="form-control" id="seleccionCategoria" name="seleccionCarrera" >
                                    <option value="0">--Seleccione una categoría--</option>
                                    <c:forEach var="dat2" items="${lstCarreras}">
                                        <option value="${dat2.idCarrera}">
                                            <c:out value="${dat2.descripcion}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-8">
                                <input type="submit" class="btn btn-default" value="Grabar"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-3"></div>
            </div> 
        </div>
    </body>
</html>
