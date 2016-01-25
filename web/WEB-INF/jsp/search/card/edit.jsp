<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="card.">
    <c:choose>
        <c:when test="${not empty card}">
            <c:set var="author" value="${card.author}"/>
            <c:set var="titlebook" value="${card.title}"/>
            <c:set var="isbn" value="${card.isbn}"/>
            <c:set var="yearPublication" value="${card.yearPublication}"/>
            <c:set var="title"><fmt:message key="h2"/> ${card.author} «${card.title}»</c:set>
        </c:when>
        <c:otherwise>
            <c:set var="title"><fmt:message key="newBook"/></c:set>
        </c:otherwise>
    </c:choose>

    <HTML>
    <HEAD>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE><fmt:message key="h1_1"/> - ${title}</TITLE>
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
        <H2>${title}</H2>
        <c:if test="${not empty message}"><H3>${message}</H3></c:if>
        <c:url value="/search/card/save.html" var="cardSaveUrl"/>
        <FORM action="${cardSaveUrl}" method="post">
            <c:if test="${not empty card}">
                <INPUT type="hidden" name="identity" value="${card.identity}">
            </c:if>
            <LABEL for="author"><fmt:message key="author"/><span class="required"> *</span></LABEL>
            <INPUT type="text" id="author" name="author" value="${author}" required>
            <LABEL for="titlebook"><fmt:message key="titleCard"/><span class="required"> *</span></LABEL>
            <INPUT type="text" id="titlebook" name="titlebook" value="${titlebook}" required>
            <LABEL for="isbn"><fmt:message key="isbn"/><span class="required"> *</span></LABEL>
            <INPUT type="text" id="isbn" name="isbn" value="${isbn}" required>
            <LABEL for="yearPublication"><fmt:message key="yearPublication"/></LABEL>
            <INPUT type="text" id="yearPublication" name="yearPublication" value="${yearPublication}">

            <BUTTON type="submit"><fmt:message key="save"/></BUTTON>
            <BUTTON type="reset"><fmt:message key="reset"/></BUTTON>
        </FORM>
    </DIV>
    </BODY>
    </HTML>
</fmt:bundle>