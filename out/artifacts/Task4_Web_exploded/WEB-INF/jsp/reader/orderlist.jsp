<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date"/>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="order.">
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
        <c:choose>
            <c:when test="${not empty orders}">
                <TABLE>
                    <TR>
                        <TH><fmt:message key="author"/></TH>
                        <TH><fmt:message key="titleCard"/></TH>
                        <TH><fmt:message key="inventoryNumber"/></TH>
                        <TH><fmt:message key="issued"/></TH>
                        <TH><fmt:message key="returnTo"/></TH>
                        <TH><fmt:message key="returned"/></TH>
                        <TH><fmt:message key="location"/></TH>
                    </TR>
                    <c:forEach items="${orders}" var="orders">
                    <TR style="background: ${(orders.dateActualReturn == null) && (orders.datePlannedReturn < date) ? '#ECC' : ''}">
                        <TD>${orders.book.card.author}</TD>
                        <TD>${orders.book.card.title}</TD>
                        <TD>${orders.book.inventoryNumber}</TD>
                        <TD><fmt:formatDate value="${orders.dateIssue}" pattern="dd.MM.yyyy"/></TD>
                        <TD><fmt:formatDate value="${orders.datePlannedReturn}" pattern="dd.MM.yyyy"/></TD>
                        <TD><fmt:formatDate value="${orders.dateActualReturn}" pattern="dd.MM.yyyy"/></TD>
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
                        </c:forEach>
                    </TR>
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
