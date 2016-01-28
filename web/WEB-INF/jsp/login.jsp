<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="login.">
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="title"/></title>
        <c:url value="/main.css" var="cssUrl"/>
        <link rel="stylesheet" type="text/css" href="${cssUrl}">
    </head>
    <body>
    <div id="header">
        <h1><fmt:message key="h1_1"/><BR><fmt:message key="h1_2"/></h1>
    </div>
    <div class="mod-languages">
        <ul class="lang-inline">
            <li class="lang-active" dir="ltr">
                <a href="/chooseLanguage.html?language=ru">
                    <img title="RU" alt="RU" src="img/ru.png">
                </a>
            </li>
            <li class="" dir="ltr">
                <a href="/chooseLanguage.html?language=en">
                    <img title="EN" alt="EN" src="img/gb.png">
                </a>
            </li>
        </ul>
    </div>

    <div id="page">
        <h2><fmt:message key="h2"/></h2>
        <c:if test="${not empty message}">
            <h3 style="color: #733;">${message}</h3>
        </c:if>
        <c:url value="/login.html" var="loginUrl"/>
        <form action="${loginUrl}" method="post">
            <label for="login"><fmt:message key="username"/>:</label>
            <input type="text" id="login" name="login" value="${param.login}">
            <label for="password"><fmt:message key="password"/>:</label>
            <input type="password" id="password" name="password">
            <button type="submit"><fmt:message key="button"/></button>
        </form>
    </div>
    </body>
    </html>
</fmt:bundle>