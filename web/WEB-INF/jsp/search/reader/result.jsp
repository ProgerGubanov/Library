<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="user.">
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
        <c:if test="${not empty message}"><h3>${message}</h3></c:if>
        <c:url value="/librarian/usages.html" var="userOpenUrl"/>
        <table>
            <tr>
                <th><fmt:message key="surname"/></th>
                <th><fmt:message key="name"/></th>
                <th><fmt:message key="patronymic"/></th>
                <th><fmt:message key="subscription"/></th>
                <th><fmt:message key="address"/></th>
                <th><fmt:message key="phoneHome"/></th>
                <th><fmt:message key="phoneMobile"/></th>
                <th><fmt:message key="email"/></th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.surname}</td>
                    <td>${user.name}</td>
                    <td>${user.patronymic}</td>
                    <td>${user.subscription}</td>
                    <td>${user.address}</td>
                    <td>${user.phoneHome}</td>
                    <td>${user.phoneMobile}</td>
                    <td>${user.email}</td>
                    <td>
                        <form action="${userOpenUrl}" method="post">
                            <input type="hidden" name="readerIdentity" value="${user.identity}">
                            <button type="submit"><fmt:message key="open"/></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <c:url value="/index.html" var="mainUrl"/>
        <a href="${mainUrl}"><fmt:message key="home"/></a>
    </div>
    </body>
    </html>
</fmt:bundle>