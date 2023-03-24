<!doctype html>
<html class="h-100">
	<head><%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String"%>
	    <meta charset="utf-8"/><%@tag language="java" pageEncoding="UTF-8"%><%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%>
	    <meta name="viewport" content="width=device-width, initial-scale=1"/><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	    <title>${title}</title>
	    <link href="${pageContext.request.contextPath}/images/001_logo/fav.ico" rel="shortcut icon" type="image/x-icon" />
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/css/admin.css" type="text/css" rel="stylesheet"/>
	</head>
	<body class="d-flex flex-column h-100" >
		<header class="p-2">
			<h2 class="fs-4">${title}</h2>
		</header>
		<main class="flex-shrink-0 fs-5">
			<jsp:doBody/>
		</main>
		<footer class="footer mt-auto pt-3 d-block" id="footer">
			<div class="w-100 bd-blue-900" style="margin-top:-17px; padding-top:4px;"></div>
		</footer>
		
	</body>
</html>