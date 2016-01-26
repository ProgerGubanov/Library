<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="card.">
    <c:choose>
        <c:when test="${not empty card}">
            <c:set var="author" value="${card.author}"/>
            <c:set var="titlebook" value="${card.title}"/>
            <c:set var="isbn" value="${card.isbn}"/>
            <c:set var="yearPublication" value="${card.yearPublication}"/>
            <c:set var="title"><fmt:message key="h2"/> ${card.author} «${card.title}»</c:set>
        </c:when>
        <c:otherwise>
            <c:set var="title"><fmt:message key="newBook"/></c:set>
        </c:otherwise>
    </c:choose>

    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="h1_1"/> - ${title}</title>
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
        <h2>${title}</h2>
        <c:if test="${not empty message}"><h3>${message}</h3></c:if>
        <c:url value="/search/card/save.html" var="cardSaveUrl"/>
        <form action="${cardSaveUrl}" method="post">
            <c:if test="${not empty card}">
                <input type="hidden" name="identity" value="${card.identity}">
            </c:if>
            <label for="author"><fmt:message key="author"/><span class="required"> *</span></label>
            <input type="text" id="author" name="author" value="${author}" required>
            <label for="titlebook"><fmt:message key="titleCard"/><span class="required"> *</span></label>
            <input type="text" id="titlebook" name="titlebook" value="${titlebook}" required>
            <label for="isbn"><fmt:message key="isbn"/><span class="required"> *</span></label>
            <input type="text" id="isbn" name="isbn" value="${isbn}" required>
            <label for="yearPublication"><fmt:message key="yearPublication"/></label>
            <input type="text" id="yearPublication" name="yearPublication" value="${yearPublication}">

            <button type="submit"><fmt:message key="save"/></button>
            <button type="reset"><fmt:message key="reset"/></button>
        </form>
    </div>
    </body>
    </html>
</fmt:bundle>