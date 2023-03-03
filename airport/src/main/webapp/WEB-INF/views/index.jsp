<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Load viewer configuration file -->
<fmt:setLocale value="en" />
<fmt:setBundle basename="viewer" var="viewer" />

<!-- Read the configuration value airport.name and assign it to the variable appName -->
<fmt:message key="airport.name" var="appName" bundle="${viewer}" />

<html>
    <head>
        <title>Vitebsk</title>
        <meta charset="utf-8">
        <link type="text/css" href="${pageContext.request.contextPath}/css/viewer.css" rel="stylesheet">
    </head>
<body>
	<h2>Аэропорт Витебск!</h2>
	<ul>
		<li><a href="arr.html">Таблица прилёта</a></li>
		<li><a href="dep.html">Таблица вылета</a></li>
		<li><a href="arrdep.html">Таблица прилёта-вылета
				(чередующиеся)</a></li>
		<li><a href="info.html">Информационные объявления</a></li>
		<li><a href="admin.html">Панель оператора</a></li>

	</ul>
	<fmt:message key="my.name" bundle="${viewer}" />
	- 1
	<br> ${appName} - 2

</body>
</html>
