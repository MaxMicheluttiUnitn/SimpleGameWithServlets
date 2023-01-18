<%--
  Created by IntelliJ IDEA.
  User: Omen
  Date: 07/10/2022
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Game</title>
    <style>
        img {
            width: 106pt;
            length: 71pt;
            border-width:thick;
            border-style:ridge;
            border-color:black;
        }
    </style>
</head>
<body>
    <%@include file="bruce_banner.jsp" %>
    Select the correct capital for each flag!
    <jsp:useBean class="it.unitn.massimomichelutti.webarch.asn2.beans.CapitalList" id="CapitalList" scope="application" />
    <%=CapitalList.toString()%>
    <jsp:useBean class="it.unitn.massimomichelutti.webarch.asn2.beans.Game" id="game" scope="session" />
    <form action="ResultsServlet" method="post">
        <%=game.toString()%>
        <input type="submit" value="Submit Query">
    </form>
</body>
</html>
