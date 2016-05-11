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
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <h4 class="text-center">Administración sistema Pañol</h4>
            <h6 class="text-center">Escuela de comunicaciones - Duoc UC</h6>
            <div class="row col-lg-offset-1">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <div>
                        <ul class="nav nav-pills col-lg-offset-1">
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
                            <li role="presentation"><a href="<c:url value="MostrarUsuarioServlet"/>">Buscar Usuario</a></li>
                            <li role="presentation"><a href="<c:url value="/RegistroUsuarioServlet"/>">Ingresar Nuevo Usuario</a></li>
                            <li role="presentation"><a href="<c:url value="ListarUsuariosServlet"/>">Listar Usuarios</a></li>
                        </ul>
                    </div>
                    <br>
                    <c:if test="${not empty mapMensajeExito}">
                        <div class="bg-success" id="danger-box">
                            <h5 class="text-center text-success"><c:out value="${mapMensajeExito['mensajeExito']}"/></h5>
                        </div>
                    </c:if>
                    <br>
                    <div class="col-md-7 col-lg-offset-3">
                        <form action="<c:url value="/MostrarUsuarioServlet"/>" method="POST">
                            <label>Ingrese Rut para búsqueda:</label>
                            <div class="col-md-7">
                                <input class="form-control" type="text" name="rut" maxlength="9"/>
                                <p id="info-form">--Ingrese rut sin puntos ni guion--</p>
                                <p id="error-form"><c:out value="${mapMensajeRut['errorRut']}"/></p>
                            </div>
                            <div>
                                <input class="btn btn-default" type="submit" name="buscar" value="Buscar"/>
                            </div>
                        </form>
                    </div>
                    <br>
                    <br>
                    <div class="row">
                        <div class="col-lg-6">
                            <h4>Datos usuario:</h4> 
                        </div>
                    </div>
                    <form action="<c:url value="/ModificarUsuarioServlet"/>" method="post">
                        <br>
                        <div class="row">
                            <div class="col-md-5">
                                <label>Perfil:</label>
                                <select class="form-control" id="seleccionCategoria" name="seleccionPerfil">
                                    <option value="0">--Seleccione--</option>
                                    <c:forEach var="dat1" items="${lstPerfiles}">
                                        <option value="${dat1.idPerfil}" ${dat1.idPerfil == usuarioBusqueda.idPerfil ? 'selected="selected"' : ''}>
                                            <c:out value="${dat1.descripcion}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <p id="error-form"><c:out value="${mapMensaje['errorPerfil']}"/></p>
                            </div>
                            <div class="col-md-5">
                                <label>Carrera:</label>
                                <select class="form-control" id="seleccionCategoria" name="seleccionCarrera" >
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
                            <div class="col-md-3">
                                <label>Teléfono / celular:</label><br>
                                <input class="form-control" type="number" 
                                       name="telefono" value="${usuarioBusqueda.telefono}" maxlength="10"/>
                                <p id="error-form"><c:out value="${mapMensaje['errorTelefono']}"/></p>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-11">
                                <label>Dirección:</label>
                                <input class="form-control text-center" type="text" 
                                       name="direccion" value="${usuarioBusqueda.direccion}"/>
                                <p id="error-form"><c:out value="${mapMensaje['errorDireccion']}"/></p>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-11">
                                <label>Email:</label>
                                <input class="form-control text-center" type="email" 
                                       name="email" value="${usuarioBusqueda.email}"/>
                                <p id="error-form"><c:out value="${mapMensaje['errorEmail']}"/></p>
                            </div>
                        </div>
                        <br>
                        <input type="hidden" name="rut" value="${usuarioBusqueda.rut}"/>
                        <input class="btn btn-default btn-primary col-sm-offset-9" type="submit" value="Actualizar"/>
                    </form>
                </div>
                <div class="col-md-2"></div>
            </div>
    </body>
</html>
