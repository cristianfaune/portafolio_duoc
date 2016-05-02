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
                        <li role="presentation"><a href="<c:url value="#"/>">Buscar Usuario</a></li>
                        <li role="presentation"><a href="<c:url value="/RegistroUsuario.jsp"/>">Ingresar Nuevo Usuario</a></li>
                        <li role="presentation"><a href="<c:url value="ListarUsuariosServlet"/>">Listar Usuarios</a></li>
                        <li role="presentation"><a href="<c:url value="/HomeJefeCarrera.jsp"/>">Volver</a></li>

                    </ul>
                    <br>
                    <h3>Búsqueda de Usuario</h3>
                    <br>
                    <p>
                    <form action="<c:url value="/MostrarUsuarioServlet"/>" method="POST" name="form">
                        <p>Ingrese Rut de Usuario:</p> <input type="text" name="rut"/>
                        <input type="submit" value="Buscar" name="buscar"/>
                    </form>
                    <form action="<c:url value="/ModificarUsuarioServlet"/>" method="post">
                    <br>
                    Nombres:   <input type="text" name="nombre" value="${nombre}"/>
                    <br>
                    <br>
                    Apellidos: <input type="text" name="apellido" value="${apellido}"/>
                    <br>
                    <br>
                    Telefono:  <input type="text" name="telefono" value=" ${telefo}"/>
                    <br>
                    <br>
                    Celular:   <input type="text" name="celular" value="${celular}"/>
                    <br>
                    <br>
                    Dirección: <input type="text" name="direccion" value=" ${direccion}"/>
                    <br>
                    <br>
                    Email:     <input type="text" name="email" value="${email}"/>
                    <br>
                    <br>
                    <input type="hidden" name="rut" value="${rut}"/>
                    <input type="submit" value="Modificar"/>
                    </form>
                </div>
                <h3><c:out value="${mapMensajeRut['errorRut']}"/></h3>
                <div class="col-md-3"></div>



            </div>

    </body>
</html>
