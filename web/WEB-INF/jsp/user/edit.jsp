<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

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

    <u:html title="${title}">
        <h2>${title}</h2>
        <c:if test="${not empty message}"><h3>${message}</h3></c:if>
        <c:url value="/user/save.html" var="userSaveUrl"/>
        <form action="${userSaveUrl}" method="post">
            <c:if test="${not empty user}">
                <input type="hidden" name="identity" value="${user.identity}">
            </c:if>
            <label for="surname"><fmt:message key="surname"/><span class="required"> *</span></label>
            <input type="text" maxlength="30" pattern="[а-яА-ЯёЁa-zA-Z-]+"
                   id="surname" name="surname" value="${surname}" required>
            <label for="name"><fmt:message key="name"/><span class="required"> *</span></label>
            <input type="text" maxlength="30" pattern="[а-яА-ЯёЁa-zA-Z]+"
                   id="name" name="name" value="${name}" required>
            <label for="patronymic"><fmt:message key="patronymic"/><span class="required"> *</span></label>
            <input type="text" maxlength="30" pattern="[а-яА-ЯёЁa-zA-Z]+"
                   id="patronymic" name="patronymic" value="${patronymic}" required>
            <label for="subscription"><fmt:message key="subscription"/></label>
            <input type="text" maxlength="20" id="subscription" name="subscription" value="${subscription}">
            <label for="address"><fmt:message key="address"/></label>
            <input type="text" maxlength="100" id="address" name="address" value="${address}">
            <label for="phoneHome"><fmt:message key="phoneHome"/></label>
            <input type="text" maxlength="20" id="phoneHome" name="phoneHome" value="${phoneHome}">
            <label for="phoneMobile"><fmt:message key="phoneMobile"/></label>
            <input type="text" maxlength="20" id="phoneMobile" name="phoneMobile" value="${phoneMobile}">
            <label for="email"><fmt:message key="email"/></label>
            <input type="text" maxlength="50" id="email" name="email" value="${email}">
            <label for="login"><fmt:message key="login"/><span class="required"> *</span></label>
            <input type="text" maxlength="50" id="login" name="login" value="${login}" required>

            <c:choose>
                <c:when test="${not empty user}">
                    <label for="password"><fmt:message key="password"/></label>
                    <input type="text" id="password" name="password" value="${password}">
                </c:when>
                <c:otherwise>
                    <label for="password"><fmt:message key="password"/><span class="required"> *</span></label>
                    <input type="text" maxlength="20" id="password" name="password" value="${password}" required>
                </c:otherwise>
            </c:choose>

            <label for="role"><fmt:message key="roleName"/><span class="required"> *</span></label>
            <select id="role" name="role">
                <c:forEach items="${roles}" var="role">
                    <c:remove var="selected"/>
                    <c:if test="${not empty roleIdentity and roleIdentity eq role.identity}">
                        <c:set var="selected" value="selected"/>
                    </c:if>
                    <c:choose>
                        <c:when test="${empty roleIdentity and role.identity == 2}">
                            <option value="${role.identity}" selected>${role.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${role.identity}" ${selected}>${role.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <button type="submit"><fmt:message key="save"/></button>
            <button type="reset"><fmt:message key="clear"/></button>
        </form>
        <c:if test="${not empty user}">
            <c:url value="/user/delete.html" var="userDeleteUrl"/>
            <form action="${userDeleteUrl}" method="post">
                <input type="hidden" name="identity" value="${user.identity}">
                <button type="submit" ${isUserUsages ? 'disabled' : ''}><fmt:message key="delete"/></button>
            </form>
        </c:if>
    </u:html>
</fmt:bundle>