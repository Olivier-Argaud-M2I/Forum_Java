<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Forum</title>
    </head>
    <body>
        <jsp:include page="./WEB-INF/fragments/_header.jsp"/>

        <c:if test="${userIsLogged!=null && (userIsLogged.hasPrivilege('writeTopic'))}">
            Nouveau Topic
            <jsp:include page="./WEB-INF/fragments/_formTopic.jsp"/>
        </c:if>

        <c:forEach items="${topics}" var="topic">
        
            <div onclick="goToTopic(${topic.id})">
                <fieldset>
                    <p>titre : ${topic.title}</p>
                    <p>text : ${topic.text}</p>
                    <p>auteur : ${topic.author.firstName} ${topic.author.lastName}</p>
                    <p>date : ${topic.dateToString()}</p>
                    <form  hidden action="<c:url value="/topicDetail?page=topicDetail&id=${topic.id}"/>" method="post">
                        <input type="submit" id="detail${topic.id}">
                    </form>

<%--                    <p>1 : ${(userIsLogged.role.name == 'Admin')}</p>--%>
<%--                    <p>2 : ${userIsLogged.role.name}</p>--%>
<%--                    <p>3 : ${userIsLogged.userName}</p>--%>
<%--                    <p>3 : ${userIsLogged.id}</p>--%>
<%--                    <p>4 : ${userIsLogged}</p>--%>
<%--                    <p>5 : ${userIsLogged!=null}</p>--%>
<%--                    <c:if test="${userIsLogged!=null}">--%>
<%--                        <p>User est pas null</p>--%>
<%--                    </c:if>--%>
<%--                    <p>6 : ${userIslogged==null}</p>--%>
<%--                    <c:if test="${userIsLogged==null}">--%>
<%--                        <p>User est  null</p>--%>
<%--                    </c:if>--%>

<%--                    <p>7 : ${sessionScope.userIsLogged}</p>--%>
<%--                    <c:if test="${sessionScope.userIsLogged==null}">--%>
<%--                        <p>User est  null 2</p>--%>
<%--                    </c:if>--%>



                    <c:if test="${(userIsLogged!=null) && (userIsLogged.hasPrivilege('deleteTopic'))}">
                        <form  action="<c:url value="/topic?topic=deleteTopic&id=${topic.id}"/>" method="post">
                            <input type="submit" value="Delete">
                        </form>
                    </c:if>
<%--                    <c:if test="${(userIsLogged!=null) && (userIsLogged.hasPrivilegeDeleteTopic())}">--%>
<%--                        <form  action="<c:url value="/topic?topic=deleteTopic&id=${topic.id}"/>" method="post">--%>
<%--                            <input type="submit" value="Delete2">--%>
<%--                        </form>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${(userIsLogged!=null) && (userIsLogged.role.name == 'Admin')}">--%>
<%--                        <form  action="<c:url value="/topic?topic=deleteTopic&id=${topic.id}"/>" method="post">--%>
<%--                            <input type="submit" value="Delete3">--%>
<%--                        </form>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${(userIsLogged!=null)}">--%>
<%--                        <form  action="<c:url value="/topic?topic=deleteTopic&id=${topic.id}"/>" method="post">--%>
<%--                            <input type="submit" value="Delete4">--%>
<%--                        </form>--%>
<%--                    </c:if>--%>
                </fieldset>
            </div>
        </c:forEach>



        <script>
            function goToTopic(id){
                console.log("detail du topic "+id);
                console.log(id);
                let btn = document.getElementById("detail"+id);
                btn.click();
            }
        </script>






    </body>
</html>