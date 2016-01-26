<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<jsp:useBean id="date" class="java.util.Date"/>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="order.">
    <c:set var="title"><fmt:message key="title"/></c:set>
    <u:html title="${title}">
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
    </u:html>
</fmt:bundle>
