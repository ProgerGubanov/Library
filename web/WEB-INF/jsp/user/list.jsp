<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<fmt:bundle basename="by/epam/library/local/messages" prefix="user.">
    <c:set var="title"><fmt:message key="title"/></c:set>
    <u:html title="${title}">
        <h2><fmt:message key="h2"/></h2>
        <c:if test="${not empty message}"><h3>${message}</h3></c:if>
        <c:url value="/user/edit.html" var="userEditUrl"/>
        <table>
            <tr>
                <th><fmt:message key="surname"/></th>
                <th><fmt:message key="name"/></th>
                <th><fmt:message key="patronymic"/></th>
                <th><fmt:message key="subscription"/></th>
                <th><fmt:message key="address"/></th>
                <th><fmt:message key="phoneHome"/></th>
                <th><fmt:message key="phoneMobile"/></th>
                <th><fmt:message key="email"/></th>
                <th><fmt:message key="roleName"/></th>
                <th><fmt:message key="login"/></th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.surname}</td>
                    <td>${user.name}</td>
                    <td>${user.patronymic}</td>
                    <td>${user.subscription}</td>
                    <td>${user.address}</td>
                    <td>${user.phoneHome}</td>
                    <td>${user.phoneMobile}</td>
                    <td>${user.email}</td>
                    <td>${user.role.name}</td>
                    <td>${user.login}</td>
                    <td>
                        <form action="${userEditUrl}" method="post">
                            <input type="hidden" name="identity" value="${user.identity}">
                            <button type="submit" class="edit" title="<fmt:message key="edit"/>"></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <form action="${userEditUrl}" method="post">
            <button type="submit"><fmt:message key="add"/></button>
        </form>
    </u:html>
</fmt:bundle>