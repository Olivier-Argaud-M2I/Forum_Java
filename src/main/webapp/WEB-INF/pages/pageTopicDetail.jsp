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
        <title>Topic</title>
    </head>
    <body>

        <jsp:include page="../fragments/_header.jsp"/>

        <h1>Detail du topic ${topic.title}</h1>
        <p>text : ${topic.text}</p>
        <p>auteur : ${topic.author.firstName} ${topic.author.lastName}</p>
        <p>date : ${topic.dateToString()}</p>


        <c:if test="${userIsLogged!=null && (userIsLogged.hasPrivilege('writeResponse'))}">
            <jsp:include page="../fragments/_formResponseTopic.jsp"/>
        </c:if>


        <c:forEach items="${topic.responses}" var="reponse">

            <p>text : ${reponse.text}</p>
            <p>auteur : ${reponse.author.firstName} ${reponse.author.lastName}</p>
            <p>date : ${reponse.dateToString()}</p>

            <c:if test="${userIsLogged!=null && userIsLogged.hasPrivilege('deleteResponse')}">
                <form  action="<c:url value="/topic?topic=deleteResponse&id=${reponse.id}"/>" method="post">
                    <input type="submit" value="Delete">
                </form>
            </c:if>

        </c:forEach>



    </body>
</html>
