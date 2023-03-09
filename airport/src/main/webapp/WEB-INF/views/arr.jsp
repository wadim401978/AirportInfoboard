<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="viewer" var="viewer" />
<html>
    <head>
        <title>${title}</title>
        <meta charset="utf-8">
        <link type="text/css" href="css/viewer.css" rel="stylesheet">
    </head>
<body>
    <h2>${title}</h2>
    ${langTag}<br>
    ${airport}<br>
    ${airline}<br>
    ${flightb2}<br>
    ${flightbru}<br>
    <fmt:message key="my.name" bundle="${viewer}" />
</body>
</html>
