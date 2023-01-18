<%--
  Created by IntelliJ IDEA.
  User: Omen
  Date: 07/10/2022
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<div style="background-color: cornflowerblue; font-family: Papyrus; font-size: xxx-large">
  <jsp:useBean class="it.unitn.massimomichelutti.webarch.asn2.beans.User" id="user" scope="session" />
  <%= user.getUsername() %>
  <form action="LogoutServlet" method="post">
    <input type="submit" value="Log Out"/>
  </form>
</div>
