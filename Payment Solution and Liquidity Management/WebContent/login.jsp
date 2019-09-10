<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<form action="AuthenticateUser" method="post">
    <table>
        <tr>
            <td><label>User name</label></td>
            <td><input type="text" name="user"></td>
        </tr>
        <tr>
            <td><label>Password</label></td>
            <td><input type="text" name="pwd"></td>

        </tr>
    </table>
    <br />
    <input type="submit" value="Login">
</form>
</body>
</html>