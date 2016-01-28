<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<fmt:bundle basename="by/epam/library/local/messages" prefix="error.">
    <c:set var="title"><fmt:message key="title"/></c:set>
    <u:html title="${title}">
        <c:choose>
            <c:when test="${not empty error}">
                <h2>${error}</h2>
            </c:when>
            <c:when test="${not empty pageContext.errorData.requestURI}">
                <h2><fmt:message key="h2_1"/> ${pageContext.errorData.requestURI} <fmt:message key="h2_2"/></h2>
            </c:when>
            <c:otherwise><fmt:message key="h2_3"/></c:otherwise>
        </c:choose>
        <br>
        <c:url value="/index.html" var="mainUrl"/>
        <a href="${mainUrl}"><fmt:message key="home"/></a>
    </u:html>
</fmt:bundle>
