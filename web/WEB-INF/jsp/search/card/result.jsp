<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="card.">
    <c:set var="title"><fmt:message key="titleResults"/></c:set>
    <u:html title="${title}">
        <h2><fmt:message key="titleResults"/></h2>
        <c:url value="/search/card/usages.html" var="cardUsagesUrl"/>
        <c:choose>
            <c:when test="${not empty cards}">
                <table>
                    <tr>
                        <th><fmt:message key="author"/></th>
                        <th><fmt:message key="titleCard"/></th>
                        <th><fmt:message key="isbn"/></th>
                        <th><fmt:message key="yearPublication"/></th>
                        <th>&nbsp;</th>
                    </tr>
                    <c:forEach items="${cards}" var="card">
                        <tr class="${classname}">
                            <td>${card.author}</td>
                            <td>${card.title}</td>
                            <td>${card.isbn}</td>
                            <td>${card.yearPublication}</td>
                            <td>
                                <form action="${cardUsagesUrl}" method="post">
                                    <input type="hidden" name="identity" value="${card.identity}">
                                    <button type="submit"><fmt:message key="open"/></button>
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
        <c:choose>
            <c:when test="${authorizedUser.role.identity == 1}">
                <c:url value="/search/card/edit.html" var="cardSaveUrl"/>
                <form action="${cardSaveUrl}" method="post">
                    <button type="submit"><fmt:message key="add"/></button>
                </form>
            </c:when>
        </c:choose>
        <br>
        <c:url value="/index.html" var="mainUrl"/>
        <a href="${mainUrl}"><fmt:message key="home"/></a>
    </u:html>
</fmt:bundle>