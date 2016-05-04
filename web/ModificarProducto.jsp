<%-- 
    Document   : ModificarProducto
    Created on : 28-abr-2016, 20:06:27
    Author     : cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="js/jquery.js"></script>
        <script src="js/jquery.validate.js"></script>
        <script src="js/messages_es.js"></script>
        <script src="js/jquery.blockUI.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <title>Administración de Inventario</title>
    </head>
    <script type="text/javascript">
        function marcas() {
            $(document).ready(function () {
                var idCategoria = $('#seleccionCategoria').val();

                $.get('DropdownCategoriaServlet', {
                    seleccionCategoria: idCategoria

                }, function (responseText) {
                    $('#opcion').html(responseText);

                });
            });
        }
    </script>
    <body>
        <%@include file="header.jsp" %>
        <div class="container"> 
            <h4 class="text-center">Administración sistema Pañol</h4>
            <h6 class="text-center">Escuela de comunicaciones - Duoc UC</h6>
            <div>
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <ul class="nav nav-pills">
                            <c:choose>
                                <c:when test="${usuarioSesion.idPerfil == 100}">
                                    <li role="presentation"><a href="<c:url value="HomeJefeCarrera.jsp"/>">Home</a></li>
                                    </c:when>
                                    <c:when test="${usuarioSesion.idPerfil == 120}">
                                    <li role="presentation"><a href="<c:url value="HomePanolero.jsp"/>">Inicio</a></li>
                                    </c:when>
                                </c:choose>
                            <li role="presentation"><a href="<c:url value="/AdminProductosServlet"/>">Administrar Inventario Existente</a></li>
                            <li role="presentation" class="active"><a href="<c:url value="/RegistroProducto"/>">Registro Nuevo Producto</a></li>
                        </ul>
                    </div>
                    <div class="col-md-3"></div>
                </div> 
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <h5 class="text-danger text-center"><c:out value="${mapMensaje['mensaje']}"/></h5>
                    <h3 class="text-center" id="p">Modificar productos</h3>
                    <br>
                    <form action="<c:url value="/RegistroProductoServlet"/>" method="post">
                        <div class="form-group col-xs-6">
                            <label>Categoría:</label>
                            <select class="form-control" id="seleccionCategoria" name="seleccionCategoria" onchange="marcas()">
                                <option value="0">--Seleccione una categoría</option>
                                <c:forEach var="dato" items="${lstCategorias}">
                                    <option value="${dato.idCategoria}">
                                        <c:out value="${dato.descripcion}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-xs-6">
                            <label>Marca:</label>
                            <div id="opcion">
                                <select class="form-control" id="seleccionMarca" name="seleccionMarca">
                                    <option value="0">--Seleccione una marca--</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-xs-6">
                            <label>Nombre artículo:</label>
                            <input type="text" class="form-control" id="nombre" 
                                   name="nombre" placeholder="cámara, notebook, proyector..."
                                   value="<c:out value="${producto.producto.nombre}"/>"/>
                        </div>
                        <div class="form-group col-xs-6">
                            <label>Modelo:</label>
                            <input type="text" class="form-control" id="modelo" name="modelo"
                                   style="text-transform: uppercase"
                                   value="<c:out value="${producto.producto.modelo}"/>"/>
                        </div>
                        <div class="form-group col-md-12">
                            <label>Descripción:</label>
                            <textarea type="text" class="form-control" id="descripcion" 
                                      name="descripcion" rows="6"
                                      placeholder="Detalla las características del producto..."/>
                            <c:out value="${producto.producto.descripcion}"/>
                            </textarea>
                        </div>
                        <div class="form-group col-xs-12">
                            <label>Imagen:</label>
                            <input type="file" id="imagen" name="imagen">
                            <p class="help-block">**No implementado aún**</p>
                        </div>
                        <div class="col-xs-6">
                            <button type="submit" class="btn btn-default">Registrar</button>
                        </div>
                    </form>
                </div>
                <div class="col-md-3"></div>
            </div> 
        </div>

        <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    </body>
</html>
