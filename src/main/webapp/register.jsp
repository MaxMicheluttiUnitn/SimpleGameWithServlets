<%--
  Created by IntelliJ IDEA.
  User: Omen
  Date: 07/10/2022
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
  <h3>Registration</h3>
  <hr>
  <div style="color: crimson" >
    <jsp:useBean class="it.unitn.massimomichelutti.webarch.asn2.beans.ErrorMsg" id="errorMessage" scope="request" />
    <%=errorMessage%>
  </div>
  <form action="RegistrationServlet" method="post">
    Username: <input type="text" name="username">
    Password: <input type="password" name="password">
    Repeat Password: <input type="password" name="password_repeat">
    <input type="submit" value="Submit Query">
  </form>
  <br>
  Already have an account? <a href="login.jsp">Click here to log in</a>
</body>
</html>
