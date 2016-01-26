<%@ attribute name="title" %>
<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@tag language="java" pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${title}</title>
    <c:url value="/main.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}">
</head>
<body>
<u:menu/>
<div id="page">
    <jsp:doBody/>
</div>
</body>
</html>
</fmt:bundle>