<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Login</title></head>
<body>
<h2>Đăng nhập</h2>
<form action="login" method="post">
  Username: <input type="text" name="username"><br>
  Password: <input type="password" name="password"><br>
  <button type="submit">Login</button>
</form>
<c:if test="${param.error == 'true'}">
  <p style="color:red;">Sai tài khoản hoặc mật khẩu!</p>
</c:if>
</body>
</html>
