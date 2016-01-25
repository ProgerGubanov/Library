<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="user.">

    <c:choose>
        <c:when test="${not empty user}">
            <c:set var="roleIdentity" value="${user.role.identity}"/>
            <c:set var="surname" value="${user.surname}"/>
            <c:set var="name" value="${user.name}"/>
            <c:set var="patronymic" value="${user.patronymic}"/>
            <c:set var="subscription" value="${user.subscription}"/>
            <c:set var="address" value="${user.address}"/>
            <c:set var="phoneHome" value="${user.phoneHome}"/>
            <c:set var="phoneMobile" value="${user.phoneMobile}"/>
            <c:set var="email" value="${user.email}"/>
            <c:set var="login" value="${user.login}"/>
            <c:set var="title"><fmt:message key="userTitle"/> «${user.login}»</c:set>
        </c:when>
        <c:otherwise>
            <c:set var="title"><fmt:message key="userNew"/></c:set>
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
        <c:url value="/user/save.html" var="userSaveUrl"/>
        <FORM action="${userSaveUrl}" method="post">
            <c:if test="${not empty user}">
                <INPUT type="hidden" name="identity" value="${user.identity}">
            </c:if>
            <LABEL for="surname"><fmt:message key="surname"/><span class="required"> *</span></LABEL>
            <INPUT type="text" id="surname" name="surname" value="${surname}" required>
            <LABEL for="name"><fmt:message key="name"/><span class="required"> *</span></LABEL>
            <INPUT type="text" id="name" name="name" value="${name}" required>
            <LABEL for="patronymic"><fmt:message key="patronymic"/><span class="required"> *</span></LABEL>
            <INPUT type="text" id="patronymic" name="patronymic" value="${patronymic}" required>
            <LABEL for="subscription"><fmt:message key="subscription"/></LABEL>
            <INPUT type="text" id="subscription" name="subscription" value="${subscription}">
            <LABEL for="address"><fmt:message key="address"/></LABEL>
            <INPUT type="text" id="address" name="address" value="${address}">
            <LABEL for="phoneHome"><fmt:message key="phoneHome"/></LABEL>
            <INPUT type="text" id="phoneHome" name="phoneHome" value="${phoneHome}">
            <LABEL for="phoneMobile"><fmt:message key="phoneMobile"/></LABEL>
            <INPUT type="text" id="phoneMobile" name="phoneMobile" value="${phoneMobile}">
            <LABEL for="email"><fmt:message key="email"/></LABEL>
            <INPUT type="text" id="email" name="email" value="${email}">
            <LABEL for="login"><fmt:message key="login"/><span class="required"> *</span></LABEL>
            <INPUT type="text" id="login" name="login" value="${login}" required>

            <c:choose>
                <c:when test="${not empty user}">
                    <LABEL for="password"><fmt:message key="password"/></LABEL>
                    <INPUT type="text" id="password" name="password" value="${password}">
                </c:when>
                <c:otherwise>
                    <LABEL for="password"><fmt:message key="password"/><span class="required"> *</span></LABEL>
                    <INPUT type="text" id="password" name="password" value="${password}" required>
                </c:otherwise>
            </c:choose>

            <LABEL for="role"><fmt:message key="roleName"/><span class="required"> *</span></LABEL>
            <SELECT id="role" name="role">
                <c:forEach items="${roles}" var="role">
                    <c:remove var="selected"/>
                    <c:if test="${not empty roleIdentity and roleIdentity eq role.identity}">
                        <c:set var="selected" value="selected"/>
                    </c:if>
                    <OPTION value="${role.identity}" ${selected}>${role.name}</OPTION>
                </c:forEach>
            </SELECT>
            <BUTTON type="submit"><fmt:message key="save"/></BUTTON>
            <BUTTON type="reset"><fmt:message key="clear"/></BUTTON>
        </FORM>
        <c:if test="${not empty user}">
            <c:url value="/user/delete.html" var="userDeleteUrl"/>
            <FORM action="${userDeleteUrl}" method="post">
                <INPUT type="hidden" name="identity" value="${user.identity}">
                <BUTTON type="submit" ${isUserUsages ? 'disabled' : ''}><fmt:message key="delete"/></BUTTON>
            </FORM>
        </c:if>
    </DIV>
    </BODY>
    </HTML>
</fmt:bundle>