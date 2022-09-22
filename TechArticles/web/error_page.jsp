<%-- 
    Document   : error_page
    Created on : 21 Jul, 2022, 11:07:21 PM
    Author     : risha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Something went wrong</title>
        <!--css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="css/mystyle.css" type="text/css"></link>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .banner-background{
                clip-path: polygon(100% 0, 100% 35%, 100% 98%, 77% 95%, 50% 100%, 23% 95%, 0 99%, 0% 35%, 0 0);

            }
        </style>
    </head>
    <body>
        <div class="container text-center">
            <img src="img/error.png" class="img-fluid">
            <h6 class="display-3">Something went Wrong!</h6>
            <%=exception%>
            <br>
            <a href="index.jsp" class="btn primary-background mt-3 text-white btn-lg">Home</a>
        </div>
    
    </body>
</html>
