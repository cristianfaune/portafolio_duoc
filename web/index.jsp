<%-- 
    Document   : index
    Created on : 13-abr-2016, 11:43:46
    Author     : cristian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <div class="row">
  <div class="col-md-3"></div>
  <div class="col-md-6">
      <h1>Productos</h1>
      <table class="table">
            <tbody>
                <c:forEach var="dato" items="${lstProductos}">
                <tr>
                    <td><img src="${dato.rutaImagen}" width="100" height="80"></td>
                    <td><c:out value="${dato.idProducto}"/></td>
                    <td><c:out value="${dato.nombre}"/></td>
                    <td><c:out value="${dato.stock}"/></td>
                    <td><a href="#">agregar item</td>
                    <td><a href="#">modificar</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
  </div>
  <div class="col-md-3"></div>
            </div>
        </div>
    </body>
</html>
