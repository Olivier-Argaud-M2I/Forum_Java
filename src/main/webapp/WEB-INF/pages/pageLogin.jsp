<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: olivi
  Date: 02/08/2022
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>Login</title>
  </head>
  <body>
  <jsp:include page="../fragments/_header.jsp"/>

      <fieldset>
          <legend>Login</legend>
          <form action="<c:url value="/user"/>" method="POST">
              <input type="text" name="username" placeholder="Username"/>
              <input type="text" name="password" placeholder="Password">
              <input type="submit" value="valider" >
          </form>
      </fieldset>



  </body>
</html>
