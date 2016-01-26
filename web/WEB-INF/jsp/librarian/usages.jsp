<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<jsp:useBean id="date" class="java.util.Date"/>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="usages.">
    <c:set var="title"><fmt:message key="title"/></c:set>
    <u:html title="${title}">
            <h2><fmt:message key="h2"/></h2>
            <c:url value="/search/card/form.html" var="searchCardUrl"/>
            <c:if test="${not empty message}"><h3>${message}</h3></c:if>
            <table>
                <tr>
                    <th><fmt:message key="surname"/></th>
                    <td>${user.surname}</td>
                </tr>
                <tr>
                    <th><fmt:message key="name"/></th>
                    <td>${user.name}</td>
                </tr>
                <tr>
                    <th><fmt:message key="patronymic"/></th>
                    <td>${user.patronymic}</td>
                </tr>
                <tr>
                    <th><fmt:message key="address"/></th>
                    <td>${user.address}</td>
                </tr>
                <tr>
                    <th><fmt:message key="phone"/></th>
                    <td>${user.phoneMobile}</td>
                </tr>
            </table>
            <br>

            <h2><fmt:message key="ordersList"/></h2>
            <c:url value="/search/card/usages.html" var="bookUsagesUrl"/>
            <c:choose>
                <c:when test="${not empty requests}">
                    <table>
                        <tr>
                            <th><fmt:message key="author"/></th>
                            <th><fmt:message key="titleCard"/></th>
                            <th><fmt:message key="isbn"/></th>
                            <th><fmt:message key="yearPublication"/></th>
                            <th><fmt:message key="dateRequest"/></th>
                            <th><fmt:message key="location"/></th>
                            <th>&nbsp;</th>
                        </tr>
                        <c:forEach items="${requests}" var="requests">
                            <tr>
                                <td>${requests.card.author}</td>
                                <td>${requests.card.title}</td>
                                <td>${requests.card.isbn}</td>
                                <td>${requests.card.yearPublication}</td>
                                <td><fmt:formatDate value="${requests.dateRequest}" pattern="dd.MM.yyyy HH:mm:ss"/></td>
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
                                <td>
                                    <form action="${bookUsagesUrl}" method="post">
                                        <input type="hidden" name="identity" value="${requests.card.identity}">
                                        <input type="hidden" name="readerIdentity" value="${user.identity}">
                                        <button type="submit"><fmt:message key="issue"/></button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <p><fmt:message key="ordersNotFound"/></p>
                </c:otherwise>
            </c:choose>
            <br>

            <h2><fmt:message key="bookList"/></h2>
            <c:url value="/librarian/bookreturn.html" var="bookReturnUrl"/>
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
                            <th>&nbsp;</th>
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
                                    <c:when test="${(orders.dateActualReturn == null)}">
                                        <form action="${bookReturnUrl}" method="post">
                                            <input type="hidden" name="orderIdentity" value="${orders.identity}">
                                            <input type="hidden" name="readerIdentity" value="${user.identity}">
                                            <button type="submit"><fmt:message key="accept"/></button>
                                        </form>
                                    </c:when>
                                </c:choose>
                            </td>
                            </c:forEach>
                        </tr>
                    </table>
                </c:when>
                <c:otherwise>
                    <p><fmt:message key="message"/></p>
                </c:otherwise>
            </c:choose>
            <br>
            <c:url value="/index.html" var="mainUrl"/>
            <a href="${mainUrl}"><fmt:message key="home"/></a>
    </u:html>
</fmt:bundle>