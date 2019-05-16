<html>
<head>
<title>Login to todo Application</title>
</head>
<body>
    <p><font color="red">${errorMessage}</font></p>
    <form action="/spring-mvc-practice/todoapp/login.mvc" method="POST">
        Name : <input name="name" type="text" /> Password : <input name="password" type="password" /> <input type="submit" />
    </form>
    <p>Or <a href="/spring-mvc-practice/todoapp/register.mvc">Register</a> if new user.</p>
</body>
</html>