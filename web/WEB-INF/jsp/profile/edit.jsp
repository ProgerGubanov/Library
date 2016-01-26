<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:bundle basename="by/epam/library/local/messages" prefix="profile.">
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="title"/> «${authorizedUser.login}»</title>
        <c:url value="/main.css" var="cssUrl"/>
        <link rel="stylesheet" type="text/css" href="${cssUrl}">
    </head>
    <body>
    <div id="header">
        <h1><fmt:message key="h1_1"/><br><fmt:message key="h1_2"/></h1>
        <ul class="right">
            <c:forEach items="${menu}" var="item">
                <c:url value="${item.url}" var="itemUrl"/>
                <li class="item"><a href="${itemUrl}">${item.name}</a></li>
            </c:forEach>
            <c:url value="/profile/edit.html" var="profileEditUrl"/>
            <li class="item"><a href="${profileEditUrl}">${authorizedUser.login}</a></li>
            <c:url value="/logout.html" var="logoutUrl"/>
            <li class="item"><a href="${logoutUrl}"><fmt:message key="exit"/></a></li>
        </ul>
    </div>
    <div id="page">
        <h2><fmt:message key="h2"/> «${authorizedUser.login}»</h2>
        <c:if test="${not empty message}"><h3>${message}</h3></c:if>
        <c:url value="/profile/save.html" var="profileSaveUrl"/>
        <form action="${profileSaveUrl}" method="post">
            <label for="login"><fmt:message key="userName"/>:</label>
            <input type="text" id="login" value="${authorizedUser.login}" disabled>
            <label for="role"><fmt:message key="role"/>:</label>
            <select id="role" disabled>
                <option selected>${authorizedUser.role.name}</option>
            </select>
            <label for="old-password"><fmt:message key="oldPassword"/><span class="required"> *</span></label>
            <input type="password" id="old-password" name="old-password" required>
            <label for="new-password"><fmt:message key="newPassword"/><span class="required"> *</span></label>
            <input type="password" id="new-password" name="new-password" required>
            <label for="confirmPassword"><fmt:message key="confirmPassword"/><span class="required"> *</span></label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
            <button type="submit"><fmt:message key="changePassword"/></button>
            <button type="reset"><fmt:message key="reset"/></button>
        </form>
    </div>
    </body>
    </html>
</fmt:bundle>