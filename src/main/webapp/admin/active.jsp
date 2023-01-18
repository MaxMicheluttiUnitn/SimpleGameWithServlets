<%--
  Created by IntelliJ IDEA.
  User: Omen
  Date: 08/10/2022
  Time: 09:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Active Users</title>
</head>
<body>
<%@include file="../bruce_banner.jsp" %>
<jsp:useBean class="it.unitn.massimomichelutti.webarch.asn2.beans.UserList" id="ActiveUsers" scope="application" />
<%=ActiveUsers.toString()%>
</body>
</html>
