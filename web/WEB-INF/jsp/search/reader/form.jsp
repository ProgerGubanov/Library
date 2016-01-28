<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<fmt:bundle basename="by/epam/library/local/messages" prefix="search.">
    <c:set var="title"><fmt:message key="title"/></c:set>
    <u:html title="${title}">
        <h2><fmt:message key="h2"/></h2>
        <c:url value="/search/reader/result.html" var="searchReaderResultUrl"/>
        <form action="${searchReaderResultUrl}" method="post">
            <label for="subscription"><fmt:message key="findBySubscription"/>:</label>
            <input type="text" id="subscription" name="subscription" class="search">
            <button type="submit" class="search"><fmt:message key="find"/></button>
        </form>
        <hr>
        <form action="${searchReaderResultUrl}" method="post">
            <label for="surname"><fmt:message key="findBySurname"/>:</label>
            <input type="text" id="surname" name="surname" class="search">
            <button type="submit" class="search"><fmt:message key="find"/></button>
        </form>
    </u:html>
</fmt:bundle>