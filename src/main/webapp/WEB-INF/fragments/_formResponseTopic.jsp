<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <fieldset>
        <legend>Nouvelle Réponse</legend>
        <form action="<c:url value="/topic?topic=addResponse"/>" method="POST">
            <textarea name="text"></textarea>
            <input type="number" hidden name="id" value="${topic.id}">
            <input type="submit" value="Poster">
        </form>
    </fieldset>
</div>