<%@tag language="java" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages">
    <div id="header">
        <h1><fmt:message key="h1_1"/><br><fmt:message key="h1_2"/></h1>
        <ul class="right">
            <c:forEach items="${menu}" var="item">
                <c:url value="${item.url}" var="itemUrl"/>
                <li class="item"><a href="${itemUrl}">${item.name}</a></li>
            </c:forEach>
            <c:url value="/profile/edit.html" var="profileEditUrl"/>
            <li class="item"><a href="${profileEditUrl}">${authorizedUser.login}</a></li>
            <c:url value="/logout.html" var="logoutUrl"/>
            <li class="item"><a href="${logoutUrl}"><fmt:message key="exit"/></a></li>
        </ul>
    </div>
</fmt:bundle>

<%--    <div id="header">
        <h1><fmt:message key="h1_1"/><br><fmt:message key="h1_2"/></h1>
        <ul class="right">
            <c:forEach items="${menu}" var="item">
                <c:url value="${item.url}" var="itemUrl"/>
                <li class="item"><a href="${itemUrl}">${item.name}</a></li>
            </c:forEach>
            <c:url value="/profile/edit.html" var="profileEditUrl"/>
            <li class="item"><a href="${profileEditUrl}">${authorizedUser.login}</a></li>
            <c:url value="/logout.html" var="logoutUrl"/>
            <li class="item"><a href="${logoutUrl}"><fmt:message key="exit"/></a></li>
        </ul>
    </div>--%>