<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: olivi
  Date: 27/07/2022
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <fieldset>
        <legend>Nouveau Topic</legend>
        <form action="<c:url value="/topic?topic=addTopic"/>" method="POST">
            <input type="text" name="title">
            <textarea name="text"></textarea>
            <input type="submit" value="Poster">
        </form>
    </fieldset>
</div>