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
            <br>
            <div class="row">
                <div class="col-md-0"></div>
                <div class="col-md-12">
                    <div>
                        <ul class="nav nav-pills  col-lg-offset-4">
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
                            <li role="presentation"><a href="<c:url value="/MostrarUsuario.jsp"/>">Buscar Usuario</a></li>
                            <li role="presentation"><a href="<c:url value="/RegistroUsuarioServlet"/>">Ingresar Nuevo Usuario</a></li>
                        </ul>
                    </div>
                    <h3 class="text-center">Administración de Usuarios</h3>
                    <table class="table">
                        <tbody>
                            <tr>
                                <th class="text-center">Nombres</th>
                                <th class="text-center">Apellidos</th>
                                <th class="text-center">Telefono</th>
                                <th class="text-center">Direccion</th>
                                <th class="text-center">Email</th>
                                <th class="text-center">Estado</th>
                                <th class="text-center">Perfil</th>
                                <th class="text-center">Carrera</th>
                                <th class="text-center"></th>
                                <th class="text-center">Acciones</th>
                            </tr>
                            <c:forEach var="dato" items="${lstUsuarios}">
                                <tr>
                                    <td class="text-center"><c:out value="${dato.usuario.nombres}"/></td>
                                    <td class="text-center"><c:out value="${dato.usuario.apellidos}"/></td>
                                    <td class="text-center"><c:out value="${dato.usuario.telefono}"/></td>
                                    <td class="text-center"><c:out value="${dato.usuario.direccion}"/></td>
                                    <td class="text-center"><c:out value="${dato.usuario.email}"/></td>
                                    <c:choose>
                                        <c:when test="${dato.usuario.activo == 1}">
                                            <td class="text-center"><c:out value="Activo"/></td>        
                                        </c:when>
                                        <c:otherwise>
                                            <td class="text-center"><c:out value="Inactivo"/></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td class="text-center"><c:out value="${dato.perfil.descripcion}"/></td>
                                    <td class="text-center"><c:out value="${dato.carrera.descripcion}"/></td>
                                    <td>
                                        <form action="<c:url value="/ModificarEstadoUsuarioServlet"/>" method="post" onsubmit="return confirm('¿Está seguro de modificar el estado del usuario?');">
                                            <input type="hidden" name="rut" value="${dato.usuario.rut}"/>
                                            <input type="hidden" name="activo" value="${dato.usuario.activo}"/>
                                            <c:choose>
                                                <c:when test="${dato.usuario.activo == 1}">
                                                    <input class="btn btn-danger btn-xs" name="activo" type="submit" value="desactivar"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <input class="btn btn-success btn-xs" name="activo" type="submit" value="activar"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="<c:url value="/MostrarUsuarioServlet"/>" method="post">
                                            <input type="hidden" name="rut" value="${dato.usuario.rut}"/>
                                            <input class="btn btn-primary btn-xs" name="modificar" type="submit" value="modificar"/>
                                        </form>
                                    </td>
                                </tr
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-0"></div>
            </div>
        </div>

    </body>
</html>
