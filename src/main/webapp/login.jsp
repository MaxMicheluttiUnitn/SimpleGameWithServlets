<%--
  Created by IntelliJ IDEA.
  User: Omen
  Date: 07/10/2022
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h3>Authentication</h3>
    <hr>
    <div style="color: crimson" >
        <jsp:useBean class="it.unitn.massimomichelutti.webarch.asn2.beans.ErrorMsg" id="errorMessage" scope="request" />
        <%=errorMessage%>
    </div>
    <form action="LoginServlet" method="post">
        Username: <input type="text" name="username">
        Password: <input type="password" name="password">
        <input type="submit" value="Submit Query">
    </form>
    <br><br>
    <a href="register.jsp">Click here to register</a>
</body>
</html>
