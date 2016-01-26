<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="card.">
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
        <c:if test="${not empty message}"><H3>${message}</H3></c:if>
        <table>
            <tr>
                <th><fmt:message key="author"/></th>
                <td>«${card.author}»</td>
            </tr>
            <tr>
                <th><fmt:message key="titleCard"/></th>
                <td>«${card.title}»</td>
            </tr>
            <tr>
                <th><fmt:message key="isbn"/></th>
                <td>«${card.isbn}»</td>
            </tr>
            <tr>
                <th><fmt:message key="yearPublication"/></th>
                <td>«${card.yearPublication}»</td>
            </tr>
        </table>

        <c:choose>
            <c:when test="${authorizedUser.role.identity == 1}">
                <c:url value="/search/card/edit.html" var="cardSaveUrl"/>
                <form action="${cardSaveUrl}" method="post">
                    <input type="hidden" name="identity" value="${card.identity}">
                    <button type="submit"><fmt:message key="edit"/></button>
                </form>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${authorizedUser.role.identity == 2}">
                <c:url value="/reader/requestsubscription.html" var="bookRequestSubscriptionUrl"/>
                <form style="display: inline-block" action="${bookRequestSubscriptionUrl}" method="post">
                    <input type="hidden" name="identitycard" value="${card.identity}">
                    <input type="hidden" name="identityuser" value="${authorizedUser.identity}">
                    <input type="hidden" name="isreadingroom" value="false">
                    <button type="submit" ${countFreeBooks <= 1 ? 'disabled' : ''}><fmt:message key="orderOnSubscription"/></button>
                </form>
                <c:url value="/reader/requestreadingroom.html" var="bookRequestReadingRoomUrl"/>
                <form style="display: inline-block" action="${bookRequestReadingRoomUrl}" method="post">
                    <input type="hidden" name="identitycard" value="${card.identity}">
                    <input type="hidden" name="identityuser" value="${authorizedUser.identity}">
                    <input type="hidden" name="isreadingroom" value="true">
                    <button type="submit" ${countFreeBooks == 0 ? 'disabled' : ''}><fmt:message key="orderOnReadingRoom"/></button>
                </form>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${authorizedUser.role.identity == 1}">
                <hr>
                <c:url value="/librarian/booksave.html" var="bookSaveUrl"/>
                <form style="display: inline-block" action="${bookSaveUrl}" method="post">
                    <label for="inventoryNumber"><fmt:message key="inventoryNumber"/></label>
                    <input type="inventoryNumber" id="inventoryNumber" name="inventoryNumber">
                    <input type="hidden" name="identityCard" value="${card.identity}">
                    <button type="submit"><fmt:message key="addInstance"/></button>
                </form>
            </c:when>
        </c:choose>

        <p><fmt:message key="availableInstance"/>: ${countFreeBooks}</p>
        <hr>
        <h2><fmt:message key="availableBooks"/></h2>
        <c:url value="/librarian/bookissue.html" var="issuingBookUrl"/>
        <c:choose>
            <c:when test="${not empty books}">
                <table>
                    <th><fmt:message key="inventoryNumber"/></th>
                    <th><fmt:message key="currentLocation"/></th>
                    <c:choose>
                        <c:when test="${authorizedUser.role.identity == 1 && readerIdentity != null}">
                            <th>&nbsp;</th>
                        </c:when>
                    </c:choose>
                    </tr>
                    <c:forEach items="${books}" var="books">
                        <tr class="${classname}">
                            <td>${books.inventoryNumber}</td>
                            <td>${books.bookStatus.name}</td>
                            <c:choose>
                                <c:when test="${authorizedUser.role.identity == 1 && readerIdentity != null}">
                                    <td>
                                        <c:choose>
                                            <c:when test="${books.bookStatus.identity == 0}">
                                                <form action="${issuingBookUrl}" method="post">
                                                    <input type="hidden" name="bookIdentity" value="${books.identity}">
                                                    <input type="hidden" name="identitycard" value="${card.identity}">
                                                    <input type="hidden" name="identityuser"
                                                           value="${authorizedUser.identity}">
                                                    <button type="submit"><fmt:message key="issue"/></button>
                                                </form>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </c:when>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p><fmt:message key="notFound"/></p>
            </c:otherwise>
        </c:choose>
    </div>
    </body>
    </html>
</fmt:bundle>