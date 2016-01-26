<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="search.">
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="title"/></title>
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
        <h2><fmt:message key="h2"/></h2>
        <c:url value="/search/reader/result.html" var="searchReaderResultUrl"/>
        <form action="${searchReaderResultUrl}" method="post">
            <label for="subscription"><fmt:message key="findBySubscription"/>:</label>
            <input type="text" id="subscription" name="subscription" class="search">
            <button type="submit" class="search"><fmt:message key="find"/></button>
        </form>
        <hr>
        <form action="${searchReaderResultUrl}" method="post">
            <label for="surname"><fmt:message key="findBySurname"/>:</label>
            <input type="text" id="surname" name="surname" class="search">
            <button type="submit" class="search"><fmt:message key="find"/></button>
        </form>
    </div>
    </body>
    </html>
</fmt:bundle>