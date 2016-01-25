<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="card.">
    <HTML>
    <HEAD>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE><fmt:message key="title2"/></TITLE>
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
        <H2><fmt:message key="h2_2"/></H2>
        <c:url value="/search/card/usages.html" var="cardUsagesUrl"/>
        <c:choose>
            <c:when test="${not empty cards}">
                <TABLE>
                    <TR>
                        <TH><fmt:message key="author"/></TH>
                        <TH><fmt:message key="titleCard"/></TH>
                        <TH><fmt:message key="isbn"/></TH>
                        <TH><fmt:message key="yearPublication"/></TH>
                        <TH>&nbsp;</TH>
                    </TR>
                    <c:forEach items="${cards}" var="card">
                        <TR class="${classname}">
                            <TD>${card.author}</TD>
                            <TD>${card.title}</TD>
                            <TD>${card.isbn}</TD>
                            <TD>${card.yearPublication}</TD>
                            <TD>
                                <FORM action="${cardUsagesUrl}" method="post">
                                    <INPUT type="hidden" name="identity" value="${card.identity}">
                                    <BUTTON type="submit"><fmt:message key="open"/></BUTTON>
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
        <c:choose>
            <c:when test="${authorizedUser.role.identity == 1}">
                <c:url value="/search/card/edit.html" var="cardSaveUrl"/>
                <FORM action="${cardSaveUrl}" method="post">
                    <BUTTON type="submit"><fmt:message key="add"/></BUTTON>
                </FORM>
            </c:when>
        </c:choose>
        <BR>
        <c:url value="/index.html" var="mainUrl"/>
        <A href="${mainUrl}"><fmt:message key="home"/></A>
    </DIV>
    </BODY>
    </HTML>
</fmt:bundle>