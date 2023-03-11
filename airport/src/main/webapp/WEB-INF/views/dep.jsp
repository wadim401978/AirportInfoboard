<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="viewer" var="viewer" />
<html>
    <head>
        <title><fmt:message key="title.departures" bundle="${viewer}" /></title>
        <meta charset="utf-8">
        <link type="text/css" href="css/viewer.css" rel="stylesheet">
    </head>
<body>
    <h2><fmt:message key="title.departures" bundle="${viewer}" /></h2>
    ${departureToString}<br>
    ${departure}<br>
    
</body>
</html>
