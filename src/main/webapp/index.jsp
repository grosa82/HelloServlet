<%@page import="com.mycompany.helloservlet.model.SuccessMessage"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sign In</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <link href="css/signup.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="container">
            
            <% if (session.getAttribute("message") != null) { 
                String className = "label-danger";
                if (((SuccessMessage)session.getAttribute("message")).success){
                    className = "label-success";
                }
            %>
                <h2 style="text-align: center;"><span class="label <%= className %>"><%= ((SuccessMessage)session.getAttribute("message")).message %></span></h2>
            <% session.removeAttribute("message"); } %>

            <form class="form-signin" method="post" action="CheckUsername">
                <h2 class="form-signin-heading">Please sign in</h2>
                <label for="inputUsername" class="sr-only">Username</label>
                <input type="text" id="inputUsername" name="inputUsername" class="form-control" placeholder="Username" required autofocus>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password" required>
                <br />
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                <br />
                <a href="create.html">Register</a>
            </form>

        </div> <!-- /container -->
    </body>
</html>
