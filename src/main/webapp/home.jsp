<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <title>Posts</title>
    </head>
    <body>
        <div class="container">
            <br />
            <a href="Logout" style="float: right;">Logout</a>
            <form class="form-signin" method="post" action="Posts">
                <div class="page-header">
                    <h1>New post</h1>
                </div>
                <label for="message" class="sr-only">Message</label>
                <textarea name="message" style="width: 100%" rows="5" class="form-control" required autofocus></textarea>
                <br />
                <button class="btn btn-lg btn-primary btn-block" type="submit" style="width: 200px;">Post</button>
            </form>
            <div class="page-header">
                <h1>Previous posts</h1>
            </div>
            <core:forEach var="post" items="${posts}">
                <core:choose>
                    <core:when test="${post.mine}">
                        <div class="panel panel-warning">
                    </core:when>
                    <core:otherwise>
                        <div class="panel panel-default">
                    </core:otherwise>
                </core:choose>
                <div class="panel-heading">
                    <h3 class="panel-title">${post.username} - <fmt:formatDate type="both" value="${post.date}" /></h3>
                </div>
                <div class="panel-body">
                    ${post.message}
                </div>
            </div>
            </core:forEach>
            </div>
    </body>
</html>
