<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date"/>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="order.">
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
        <c:choose>
            <c:when test="${not empty orders}">
                <table>
                    <tr>
                        <th><fmt:message key="author"/></th>
                        <th><fmt:message key="titleCard"/></th>
                        <th><fmt:message key="inventoryNumber"/></th>
                        <th><fmt:message key="issued"/></th>
                        <th><fmt:message key="returnTo"/></th>
                        <th><fmt:message key="returned"/></th>
                        <th><fmt:message key="location"/></th>
                    </tr>
                    <c:forEach items="${orders}" var="orders">
                    <tr style="background: ${(orders.dateActualReturn == null) && (orders.datePlannedReturn < date) ? '#ECC' : ''}">
                        <td>${orders.book.card.author}</td>
                        <td>${orders.book.card.title}</td>
                        <td>${orders.book.inventoryNumber}</td>
                        <td><fmt:formatDate value="${orders.dateIssue}" pattern="dd.MM.yyyy"/></td>
                        <td><fmt:formatDate value="${orders.datePlannedReturn}" pattern="dd.MM.yyyy"/></td>
                        <td><fmt:formatDate value="${orders.dateActualReturn}" pattern="dd.MM.yyyy"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${requests.isReadingRoom() == true}">
                                    <fmt:message key="readingRoom"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="subscription"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        </c:forEach>
                    </tr>
                </table>
            </c:when>
            <c:otherwise>
                <p><fmt:message key="notFound"/></p>
            </c:otherwise>
        </c:choose>
        <br>
        <c:url value="/index.html" var="mainUrl"/>
        <a href="${mainUrl}"><fmt:message key="home"/></a>
    </div>
    </body>
    </html>
</fmt:bundle>
