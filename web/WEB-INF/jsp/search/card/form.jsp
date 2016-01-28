<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<fmt:bundle basename="by/epam/library/local/messages" prefix="card.">
    <c:set var="title"><fmt:message key="titleSearch"/></c:set>
    <u:html title="${title}">
        <h2><fmt:message key="titleSearch"/></h2>
        <c:url value="/search/card/result.html" var="searchCardResultUrl"/>
        <form action="${searchCardResultUrl}" method="post">
            <label for="title"><fmt:message key="findByTitle"/>:</label>
            <input type="text" id="title" name="title" class="search">
            <button type="submit" class="search"><fmt:message key="find"/></button>
        </form>
        <hr>
        <form action="${searchCardResultUrl}" method="post">
            <label for="author"><fmt:message key="findByAuthor"/>:</label>
            <input type="text" id="author" name="author" class="search">
            <button type="submit" class="search"><fmt:message key="find"/></button>
        </form>
        <hr>
        <form action="${searchCardResultUrl}" method="post">
            <label for="isbn"><fmt:message key="findByIsbn"/>:</label>
            <input type="text" id="isbn" name="isbn" class="search">
            <button type="submit" class="search"><fmt:message key="find"/></button>
        </form>
    </u:html>
</fmt:bundle>