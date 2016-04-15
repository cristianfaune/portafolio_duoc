<%-- 
    Document   : index
    Created on : 13-abr-2016, 11:43:46
    Author     : cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form name="upload" action="index.jsp" method="POST" enctype="multipart/form-data">
            <input type="file" name="file" value="" width="20" />
        </form>
    </body>
</html>
