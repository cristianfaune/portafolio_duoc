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
                <div class="col-md-7">
                    <ul class="nav nav-tabs">
                        <li role="presentation"><a href="<c:url value="/HomeJefeCarrera.jsp"/>">Home</a></li>
                        <li role="presentation"><a href="<c:url value="MostrarUsuarioServlet"/>">Buscar Usuario</a></li>
                        <li role="presentation"><a href="<c:url value="/RegistroUsuario.jsp"/>">Ingresar Nuevo Usuario</a></li>
                        <li role="presentation"><a href="<c:url value="ListarUsuariosServlet"/>">Listar Usuarios</a></li>
                    </ul>
                    <br>
                    <p>
                    <div class="col-md-7 col-lg-offset-3">
                        <form action="<c:url value="/MostrarUsuarioServlet"/>" method="POST">
                            <label>Ingrese Rut para búsqueda:</label>
                            <div class="col-md-7">
                                <input class="form-control" type="text" name="rut" maxlength="9"/>
                            </div>
                            <div>
                                <input class="btn btn-default" type="submit" name="buscar" value="Buscar"/>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-7">
                        <br>
                        <h4>Datos usuario:</h4>
                        <form action="<c:url value="/ModificarUsuarioServlet"/>" method="post">
                            <br>
                            <div>
                                <label>Nombres:</label>
                                <input class="form-control" type="text" name="nombre" value="${nombre}"/>
                            </div>
                            <br>
                            <div>
                                <label>Apellidos:</label>
                                <input class="form-control" type="text" name="apellidos" value="${apellido}"/>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Teléfono / celular:</label><br>
                                    <input class="form-control" type="number" name="telefo" value="${telefo}"/>
                                </div>
                            </div>
                            <br>
                            <div>
                                <label>Dirección:</label>
                                <input class="form-control" type="text" name="direccion" value="${direccion}"/>
                            </div>
                            <br>
                            <div>
                                <label>Email:</label>
                                <input class="form-control" type="email" name="email" value="${email}"/>
                            </div>
                            <br>
                            <input type="hidden" name="rut" value="${rut}"/>
                            <input class="btn btn-default" type="submit" value="Actualizar"/>
                        </form>
                    </div>
                </div>
                <h3><c:out value="${mapMensajeRut['errorRut']}"/></h3>
                <div class="col-md-3"></div>



            </div>

    </body>
</html>
