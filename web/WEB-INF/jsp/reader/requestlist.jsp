<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<fmt:bundle basename="by/epam/library/local/messages" prefix="request.">
    <c:set var="title"><fmt:message key="title"/></c:set>
    <u:html title="${title}">
        <h2><fmt:message key="h2"/></h2>
        <c:if test="${not empty message}"><h3>${message}</h3></c:if>
        <c:url value="/reader/requestdelete.html" var="requestDeleteUrl"/>
        <c:choose>
            <c:when test="${not empty requests}">
                <table>
                    <tr>
                        <th><fmt:message key="author"/></th>
                        <th><fmt:message key="titleCard"/></th>
                        <c:choose>
                            <c:when test="${authorizedUser.role.identity == 1}">
                                <th><fmt:message key="reader"/></th>
                            </c:when>
                        </c:choose>
                        <th><fmt:message key="yearPublication"/></th>
                        <th><fmt:message key="dateRequest"/></th>
                        <th><fmt:message key="location"/></th>
                        <th>&nbsp;</th>
                    </tr>
                    <c:forEach items="${requests}" var="requests">
                        <tr>
                            <td>${requests.card.author}</td>
                            <td>${requests.card.title}</td>
                            <c:choose>
                                <c:when test="${authorizedUser.role.identity == 1}">
                                    <td>${requests.user.surname}&nbsp;${fn:substring(requests.user.name, 0, 1)}.&nbsp;${fn:substring(requests.user.patronymic, 0, 1)}.</td>
                                </c:when>
                            </c:choose>
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
                                <form action="${requestDeleteUrl}" method="post">
                                    <input type="hidden" name="identity" value="${requests.identity}">
                                    <button type="submit"><fmt:message key="delete"/></button>
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
        <br>
        <c:url value="/index.html" var="mainUrl"/>
        <a href="${mainUrl}"><fmt:message key="home"/></a>
    </u:html>
</fmt:bundle>