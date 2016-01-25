<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="request.">
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
        <c:url value="/reader/requestdelete.html" var="requestDeleteUrl"/>
        <c:choose>
            <c:when test="${not empty requests}">
                <TABLE>
                    <TR>
                        <TH><fmt:message key="author"/></TH>
                        <TH><fmt:message key="titleCard"/></TH>
                        <c:choose>
                            <c:when test="${authorizedUser.role.identity == 1}">
                                <TH><fmt:message key="reader"/></TH>
                            </c:when>
                        </c:choose>
                        <TH><fmt:message key="yearPublication"/></TH>
                        <TH><fmt:message key="dateRequest"/></TH>
                        <TH><fmt:message key="location"/></TH>
                        <TH>&nbsp;</TH>
                    </TR>
                    <c:forEach items="${requests}" var="requests">
                        <TR>
                            <TD>${requests.card.author}</TD>
                            <TD>${requests.card.title}</TD>
                            <c:choose>
                                <c:when test="${authorizedUser.role.identity == 1}">
                                    <TD>${requests.user.surname}&nbsp;${fn:substring(requests.user.name, 0, 1)}.&nbsp;${fn:substring(requests.user.patronymic, 0, 1)}.</TD>
                                </c:when>
                            </c:choose>
                            <TD>${requests.card.yearPublication}</TD>
                            <TD><fmt:formatDate value="${requests.dateRequest}" pattern="dd.MM.yyyy HH:mm:ss"/></TD>
                            <TD>
                                <c:choose>
                                    <c:when test="${requests.isReadingRoom() == true}">
                                        <fmt:message key="readingRoom"/>
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:message key="subscription"/>
                                    </c:otherwise>
                                </c:choose>
                            </TD>
                            <TD>
                                <FORM action="${requestDeleteUrl}" method="post">
                                    <INPUT type="hidden" name="identity" value="${requests.identity}">
                                    <BUTTON type="submit"><fmt:message key="delete"/></BUTTON>
                                </FORM>
                            </TD>
                        </TR>
                    </c:forEach>
                </TABLE>
            </c:when>
            <c:otherwise>
                <P><fmt:message key="notFound"/></P>
            </c:otherwise>
        </c:choose>
        <BR>
        <c:url value="/index.html" var="mainUrl"/>
        <A href="${mainUrl}"><fmt:message key="home"/></A>
    </DIV>
    </BODY>
    </HTML>
</fmt:bundle>