<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="user.">
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
        <H2><fmt:message key="h2"/></H2>
        <c:if test="${not empty message}"><H3>${message}</H3></c:if>
        <c:url value="/librarian/usages.html" var="userOpenUrl"/>
        <TABLE>
            <TR>
                <TH><fmt:message key="surname"/></TH>
                <TH><fmt:message key="name"/></TH>
                <TH><fmt:message key="patronymic"/></TH>
                <TH><fmt:message key="subscription"/></TH>
                <TH><fmt:message key="address"/></TH>
                <TH><fmt:message key="phoneHome"/></TH>
                <TH><fmt:message key="phoneMobile"/></TH>
                <TH><fmt:message key="email"/></TH>
                <TH>&nbsp;</TH>
            </TR>
            <c:forEach items="${users}" var="user">
                <TR>
                    <TD>${user.surname}</TD>
                    <TD>${user.name}</TD>
                    <TD>${user.patronymic}</TD>
                    <TD>${user.subscription}</TD>
                    <TD>${user.address}</TD>
                    <TD>${user.phoneHome}</TD>
                    <TD>${user.phoneMobile}</TD>
                    <TD>${user.email}</TD>
                    <TD>
                        <FORM action="${userOpenUrl}" method="post">
                            <INPUT type="hidden" name="readerIdentity" value="${user.identity}">
                            <BUTTON type="submit"><fmt:message key="open"/></BUTTON>
                        </FORM>
                    </TD>
                </TR>
            </c:forEach>
        </TABLE>
        <BR>
        <c:url value="/index.html" var="mainUrl"/>
        <A href="${mainUrl}"><fmt:message key="home"/></A>
    </DIV>
    </BODY>
    </HTML>
</fmt:bundle>