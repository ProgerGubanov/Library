<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="error.">
    <HTML>
    <HEAD>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE><fmt:message key="title"/></TITLE>
        <c:url value="/main.css" var="cssUrl"/>
        <LINK rel="stylesheet" type="text/css" href="${cssUrl}">
    </HEAD>
    <BODY>
    <DIV id="header">
        <H1><fmt:message key="h1_1"/><BR><fmt:message key="h1_2"/></H1>
        <c:if test="${not empty authorizedUser}">
            <UL class="right">
                <c:forEach items="${menu}" var="item">
                    <c:url value="${item.url}" var="itemUrl"/>
                    <LI class="item"><A href="${itemUrl}">${item.name}</A></LI>
                </c:forEach>
                <c:url value="/profile/edit.html" var="profileEditUrl"/>
                <LI class="item"><A href="${profileEditUrl}">${authorizedUser.login}</A></LI>
                <c:url value="/logout.html" var="logoutUrl"/>
                <LI class="item"><A href="${logoutUrl}"><fmt:message key="exit"/></A></LI>
            </UL>
        </c:if>
    </DIV>
    <DIV id="page">
        <c:choose>
            <c:when test="${not empty error}">
                <H2>${error}</H2>
            </c:when>
            <c:when test="${not empty pageContext.errorData.requestURI}">
                <H2><fmt:message key="h2_1"/> ${pageContext.errorData.requestURI} <fmt:message key="h2_2"/></H2>
            </c:when>
            <c:otherwise><fmt:message key="h2_3"/></c:otherwise>
        </c:choose>
        <BR>
        <c:url value="/index.html" var="mainUrl"/>
        <A href="${mainUrl}"><fmt:message key="home"/></A>
    </DIV>
    </BODY>
    </HTML>
</fmt:bundle>
