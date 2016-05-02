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
            <div class="text-right">
                <a href="<c:url value="/CerrarSesionServlet"/>">Cerrar Sesión</a>
            </div>   
            <div class="container">
                <div>
                    <ul class="nav nav-tabs">
                        <li role="presentation"><a href="<c:url value="/MostrarUsuario.jsp"/>">Buscar Usuario</a></li>
                        <li role="presentation"><a href="<c:url value="/RegistroUsuarioServlet"/>">Ingresar Nuevo Usuario</a></li>
                        <li role="presentation"><a href="<c:url value="/HomeJefeCarrera.jsp"/>">Volver</a></li>

                    </ul>

                </div>
                <br>
                <br>
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <h3 class="text-center" id="p">Administración de Usuarios</h3>
                        <table class="table">
                            <tbody>
                                <tr>
                                    <th class="text-center">Nombres</th>
                                    <th class="text-center">Apellidos</th>
                                    <th class="text-center">Telefono</th>
                                    <th class="text-center">Celular</th>
                                    <th class="text-center">Direccion</th>
                                    <th class="text-center">Email</th>
                                    <th class="text-center">Estado</th>
                                    <th class="text-center">Perfil</th>
                                    <th class="text-center">Carrera</th>
                                    <th class="text-center">Acciones</th>
                                </tr>
                                <c:forEach var="dato" items="${lstUsuarios}">
                                        <tr>
                                                <td class="text-center"><c:out value="${dato.usuario.nombres}"/></td>
                                                <td class="text-center"><c:out value="${dato.usuario.apellidos}"/></td>
                                                <td class="text-center"><c:out value="${dato.usuario.telefono}"/></td>
                                                <td class="text-center"><c:out value="${dato.usuario.celular}"/></td>
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
                                          <form action="<c:url value="/ModificarEstadoUsuarioServlet"/>" method="post">
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
                                        </tr
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-2"></div>
                </div>
            </div>
        </div>

    </body>
</html>
