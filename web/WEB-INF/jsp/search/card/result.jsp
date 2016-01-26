<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="card.">
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="title2"/></title>
        <c:url value="/main.css" var="cssUrl"/>
        <link rel="stylesheet" type="text/css" href="${cssUrl}">
    </head>
    <body>
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
    <div id="page">
        <h2><fmt:message key="h2_2"/></h2>
        <c:url value="/search/card/usages.html" var="cardUsagesUrl"/>
        <c:choose>
            <c:when test="${not empty cards}">
                <table>
                    <tr>
                        <th><fmt:message key="author"/></th>
                        <th><fmt:message key="titleCard"/></th>
                        <th><fmt:message key="isbn"/></th>
                        <th><fmt:message key="yearPublication"/></th>
                        <th>&nbsp;</th>
                    </tr>
                    <c:forEach items="${cards}" var="card">
                        <tr class="${classname}">
                            <td>${card.author}</td>
                            <td>${card.title}</td>
                            <td>${card.isbn}</td>
                            <td>${card.yearPublication}</td>
                            <td>
                                <form action="${cardUsagesUrl}" method="post">
                                    <input type="hidden" name="identity" value="${card.identity}">
                                    <button type="submit"><fmt:message key="open"/></button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p><fmt:message key="notFound"/></p>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${authorizedUser.role.identity == 1}">
                <c:url value="/search/card/edit.html" var="cardSaveUrl"/>
                <form action="${cardSaveUrl}" method="post">
                    <button type="submit"><fmt:message key="add"/></button>
                </form>
            </c:when>
        </c:choose>
        <br>
        <c:url value="/index.html" var="mainUrl"/>
        <a href="${mainUrl}"><fmt:message key="home"/></a>
    </div>
    </body>
    </html>
</fmt:bundle>