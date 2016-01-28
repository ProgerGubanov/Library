<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<fmt:bundle basename="by/epam/library/local/messages" prefix="card.">
    <c:set var="title"><fmt:message key="title"/></c:set>
    <u:html title="${title}">
        <h2><fmt:message key="title"/></h2>
        <c:if test="${not empty message}"><h3>${message}</h3></c:if>
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
            <c:when test="${authorizedUser.role.identity == 1 && readerIdentity == null}">
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
                    <button type="submit" ${countFreeBooks <= 1 ? 'disabled' : ''}><fmt:message
                            key="orderOnSubscription"/></button>
                </form>
                <c:url value="/reader/requestreadingroom.html" var="bookRequestReadingRoomUrl"/>
                <form style="display: inline-block" action="${bookRequestReadingRoomUrl}" method="post">
                    <input type="hidden" name="identitycard" value="${card.identity}">
                    <input type="hidden" name="identityuser" value="${authorizedUser.identity}">
                    <input type="hidden" name="isreadingroom" value="true">
                    <button type="submit" ${countFreeBooks == 0 ? 'disabled' : ''}><fmt:message
                            key="orderOnReadingRoom"/></button>
                </form>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${authorizedUser.role.identity == 1 && readerIdentity == null}">
                <hr>
                <h2><fmt:message key="addInstanceHeader"/></h2>
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
    </u:html>
</fmt:bundle>