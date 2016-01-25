<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="card.">
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
        <TABLE>
            <TR>
                <TH><fmt:message key="author"/></TH>
                <TD>«${card.author}»</TD>
            </TR>
            <TR>
                <TH><fmt:message key="titleCard"/></TH>
                <TD>«${card.title}»</TD>
            </TR>
            <TR>
                <TH><fmt:message key="isbn"/></TH>
                <TD>«${card.isbn}»</TD>
            </TR>
            <TR>
                <TH><fmt:message key="yearPublication"/></TH>
                <TD>«${card.yearPublication}»</TD>
            </TR>
        </TABLE>

        <c:choose>
            <c:when test="${authorizedUser.role.identity == 1}">
                <c:url value="/search/card/edit.html" var="cardSaveUrl"/>
                <FORM action="${cardSaveUrl}" method="post">
                    <INPUT type="hidden" name="identity" value="${card.identity}">
                    <BUTTON type="submit"><fmt:message key="edit"/></BUTTON>
                </FORM>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${authorizedUser.role.identity == 2}">
                <c:url value="/reader/requestsubscription.html" var="bookRequestSubscriptionUrl"/>
                <FORM style="display: inline-block" action="${bookRequestSubscriptionUrl}" method="post">
                    <INPUT type="hidden" name="identitycard" value="${card.identity}">
                    <INPUT type="hidden" name="identityuser" value="${authorizedUser.identity}">
                    <INPUT type="hidden" name="isreadingroom" value="false">
                    <BUTTON type="submit" ${countFreeBooks <= 1 ? 'disabled' : ''}><fmt:message key="orderOnSubscription"/></BUTTON>
                </FORM>
                <c:url value="/reader/requestreadingroom.html" var="bookRequestReadingRoomUrl"/>
                <FORM style="display: inline-block" action="${bookRequestReadingRoomUrl}" method="post">
                    <INPUT type="hidden" name="identitycard" value="${card.identity}">
                    <INPUT type="hidden" name="identityuser" value="${authorizedUser.identity}">
                    <INPUT type="hidden" name="isreadingroom" value="true">
                    <BUTTON type="submit" ${countFreeBooks == 0 ? 'disabled' : ''}><fmt:message key="orderOnReadingRoom"/></BUTTON>
                </FORM>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${authorizedUser.role.identity == 1}">
                <HR>
                <c:url value="/librarian/booksave.html" var="bookSaveUrl"/>
                <FORM style="display: inline-block" action="${bookSaveUrl}" method="post">
                    <LABEL for="inventoryNumber"><fmt:message key="inventoryNumber"/></LABEL>
                    <INPUT type="inventoryNumber" id="inventoryNumber" name="inventoryNumber">
                    <INPUT type="hidden" name="identityCard" value="${card.identity}">
                    <BUTTON type="submit"><fmt:message key="addInstance"/></BUTTON>
                </FORM>
            </c:when>
        </c:choose>

        <P><fmt:message key="availableInstance"/>: ${countFreeBooks}</P>
        <HR>
        <H2><fmt:message key="availableBooks"/></H2>
        <c:url value="/librarian/bookissue.html" var="issuingBookUrl"/>
        <c:choose>
            <c:when test="${not empty books}">
                <TABLE>
                    <TH><fmt:message key="inventoryNumber"/></TH>
                    <TH><fmt:message key="currentLocation"/></TH>
                    <c:choose>
                        <c:when test="${authorizedUser.role.identity == 1 && readerIdentity != null}">
                            <TH>&nbsp;</TH>
                        </c:when>
                    </c:choose>
                    </TR>
                    <c:forEach items="${books}" var="books">
                        <TR class="${classname}">
                            <TD>${books.inventoryNumber}</TD>
                            <TD>${books.bookStatus.name}</TD>
                            <c:choose>
                                <c:when test="${authorizedUser.role.identity == 1 && readerIdentity != null}">
                                    <TD>
                                        <c:choose>
                                            <c:when test="${books.bookStatus.identity == 0}">
                                                <FORM action="${issuingBookUrl}" method="post">
                                                    <INPUT type="hidden" name="bookIdentity" value="${books.identity}">
                                                    <INPUT type="hidden" name="identitycard" value="${card.identity}">
                                                    <INPUT type="hidden" name="identityuser"
                                                           value="${authorizedUser.identity}">
                                                    <BUTTON type="submit"><fmt:message key="issue"/></BUTTON>
                                                </FORM>
                                            </c:when>
                                        </c:choose>
                                    </TD>
                                </c:when>
                            </c:choose>
                        </TR>
                    </c:forEach>
                </TABLE>
            </c:when>
            <c:otherwise>
                <P><fmt:message key="notFound"/></P>
            </c:otherwise>
        </c:choose>
    </DIV>
    </BODY>
    </HTML>
</fmt:bundle>