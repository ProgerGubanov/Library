<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="profile.">
    <HTML>
    <HEAD>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE><fmt:message key="title"/> «${authorizedUser.login}»</TITLE>
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
        <H2><fmt:message key="h2"/> «${authorizedUser.login}»</H2>
        <c:if test="${not empty message}"><H3>${message}</H3></c:if>
        <c:url value="/profile/save.html" var="profileSaveUrl"/>
        <FORM action="${profileSaveUrl}" method="post">
            <LABEL for="login"><fmt:message key="userName"/>:</LABEL>
            <INPUT type="text" id="login" value="${authorizedUser.login}" disabled>
            <LABEL for="role"><fmt:message key="role"/>:</LABEL>
            <SELECT id="role" disabled>
                <OPTION selected>${authorizedUser.role.name}</OPTION>
            </SELECT>
            <LABEL for="old-password"><fmt:message key="oldPassword"/><span class="required"> *</span></LABEL>
            <INPUT type="password" id="old-password" name="old-password" required>
            <LABEL for="new-password"><fmt:message key="newPassword"/><span class="required"> *</span></LABEL>
            <INPUT type="password" id="new-password" name="new-password" required>
            <BUTTON type="submit"><fmt:message key="changePassword"/></BUTTON>
            <BUTTON type="reset"><fmt:message key="reset"/></BUTTON>
        </FORM>
    </DIV>
    </BODY>
    </HTML>
</fmt:bundle>