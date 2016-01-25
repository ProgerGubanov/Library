<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="card.">
    <HTML>
    <HEAD>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE><fmt:message key="title3"/></TITLE>
        <c:url value="/main.css" var="cssUrl"/>
        <LINK rel="stylesheet" type="text/css" href="${cssUrl}">
    </HEAD>
    <BODY>
    <DIV id="header">
        <H1><fmt:message key="h1_1"/><BR><fmt:message key="h1_2"/></H1>
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
    </DIV>
    <DIV id="page">
        <H2><fmt:message key="h2_3"/></H2>
        <c:url value="/search/card/result.html" var="searchCardResultUrl"/>
        <FORM action="${searchCardResultUrl}" method="post">
            <LABEL for="title"><fmt:message key="findByTitle"/>:</LABEL>
            <INPUT type="text" id="title" name="title" class="search">
            <BUTTON type="submit" class="search"><fmt:message key="find"/></BUTTON>
        </FORM>
        <HR>
        <FORM action="${searchCardResultUrl}" method="post">
            <LABEL for="author"><fmt:message key="findByAuthor"/>:</LABEL>
            <INPUT type="text" id="author" name="author" class="search">
            <BUTTON type="submit" class="search"><fmt:message key="find"/></BUTTON>
        </FORM>
        <HR>
        <FORM action="${searchCardResultUrl}" method="post">
            <LABEL for="isbn"><fmt:message key="findByIsbn"/>:</LABEL>
            <INPUT type="text" id="isbn" name="isbn" class="search">
            <BUTTON type="submit" class="search"><fmt:message key="find"/></BUTTON>
        </FORM>
    </DIV>
    </BODY>
    </HTML>
</fmt:bundle>