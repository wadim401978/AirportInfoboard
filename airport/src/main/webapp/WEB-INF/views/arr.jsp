<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="ru"/>
<fmt:setBundle basename="viewer" var="viewer_ru" />

<html>
    <head>
        <title><fmt:message key="title.arrivals" bundle="${viewer_ru}" /></title>
        <meta charset="utf-8">
        <link type="text/css" href="css/viewer.css" rel="stylesheet">
    </head>
<body>
    <div id="table_ru">
    	<h2><fmt:message key="title.arrivals" bundle="${viewer_ru}" /></h2>
    	<table class="schedule">
    		<thead>
    			<tr>
	    			<td>#</td>
	    			<td><fmt:message key="table.number" bundle="${viewer_ru}" /></td>
	    			<td><fmt:message key="table.airport" bundle="${viewer_ru}" /></td>
	    			<td><fmt:message key="table.date" bundle="${viewer_ru}" /></td>
	    			<td><fmt:message key="table.time" bundle="${viewer_ru}" /></td>
	    			<td><fmt:message key="table.airline" bundle="${viewer_ru}" /></td>
	    			<td><fmt:message key="table.status" bundle="${viewer_ru}" /></td>
    			</tr>
    		</thead>
 			<tbody>
 			<c:forEach items="${arrivals}" var="arr">
    			<tr><fmt:formatDate pattern="HH:mm" value="${arr.scheduledDate}" var="statusTime"/>
	    			<td>#</td>
	    			<td>${arr.flight.iataNumber} | ${arr.flight.icaoNumber}</td>
	    			<td>${arr.flight.airport.name} (${arr.flight.airport.iataCode})</td>
	    			<td><fmt:formatDate pattern="dd.MM.YYYY" value="${arr.scheduledDate}"/></td>
	    			<td><fmt:formatDate pattern="HH:mm" value="${arr.scheduledDate}"/></td>
	    			<td>${arr.flight.airline.name}</td>
	    			<td><fmt:message key="${arr.status.property}" bundle="${viewer_ru}">
	    				<fmt:param value="${statusTime}"/></fmt:message>
	    			</td>
    			</tr>
    			</c:forEach>
 			</tbody>   	
    	</table>
    
    </div>
 <fmt:setLocale value="en"/>
<fmt:setBundle basename="viewer" var="viewer_en" />
    
    <div id="table_en">
    	<h2><fmt:message key="title.arrivals" bundle="${viewer_en}" /></h2>
    	<table class="schedule">
    		<thead>
    			<tr>
	    			<td>#</td>
	    			<td><fmt:message key="table.number" bundle="${viewer_en}" /></td>
	    			<td><fmt:message key="table.airport" bundle="${viewer_en}" /></td>
	    			<td><fmt:message key="table.date" bundle="${viewer_en}" /></td>
	    			<td><fmt:message key="table.time" bundle="${viewer_en}" /></td>
	    			<td><fmt:message key="table.airline" bundle="${viewer_en}" /></td>
	    			<td><fmt:message key="table.status" bundle="${viewer_en}" /></td>
    			</tr>
    		</thead>
 			<tbody>
 			<c:forEach items="${arrivals}" var="arr">
    			<tr><fmt:formatDate pattern="HH:mm" value="${arr.scheduledDate}" var="statusTime"/>
	    			<td>#</td>
	    			<td>${arr.flight.iataNumber} | ${arr.flight.icaoNumber}</td>
	    			<td>${arr.flight.airport.name} (${arr.flight.airport.iataCode})</td>
	    			<td><fmt:formatDate pattern="dd.MM.YYYY" value="${arr.scheduledDate}"/></td>
	    			<td><fmt:formatDate pattern="HH:mm" value="${arr.scheduledDate}"/></td>
	    			<td>${arr.flight.airline.name}</td>
	    			<td><fmt:message key="${arr.status.property}" bundle="${viewer_en}">
	    				<fmt:param value="${statusTime}"/></fmt:message>
	    			</td>
    			</tr>
    			</c:forEach>
 			</tbody>   	
    	</table>
    
    </div>
    
</body>
</html>
