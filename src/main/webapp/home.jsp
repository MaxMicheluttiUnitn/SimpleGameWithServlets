<%--
  Created by IntelliJ IDEA.
  User: Omen
  Date: 07/10/2022
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
  <%@include file="bruce_banner.jsp" %>
  <p>
    Score: <%=user.getScore()%><br>
    <form action="PlayServlet" method="post">
      <input type="submit" value="Play"/>
    </form>
  </p>
</body>
</html>
