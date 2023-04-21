<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<fmt:setBundle basename="viewer" var="viewer_bundle" />
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <form action="${pageContext.request.contextPath}/admin/departures.html" >
    	<table class="admin">
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.id" bundle="${op}"/>:</td>
    			<td><input type="text" name="id" value="${departure.id}" readonly="readonly"></td>
    		</tr>
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.flight" bundle="${op}"/>:</td>
    			<td>
    				<input type="text" name="flight" value="${departure.flight.iataNumber}-${departure.flight.airport.name}" disabled="disabled">
    				<input type="button" value="..." name="addFlight" alt="select fligth">
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.date.scheduled" bundle="${op}"/>:</td>
    			<td>
    				<input type="text" name="date" value="<fmt:formatDate pattern="dd.MM.YYYY" value="${departure.scheduledDate}"/>" disabled="disabled">
    				<input type="button" value="..." name="addDate"  alt="select date">
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.time.scheduled" bundle="${op}"/>:</td>
    			<td>
    				<input type="text" name="time" value="<fmt:formatDate pattern="HH:mm" value="${departure.scheduledDate}"/>" disabled="disabled">
    				<input type="button" value="..." name="addTime"  alt="select time">
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.status" bundle="${op}"/>:</td>
    			<td>
    				<input type="text" name="status" value="<fmt:message key="${departure.status.property}" bundle="${viewer_bundle}"/>" disabled="disabled">
    				<input type="button" value="..." name="addStatus"  alt="select status">
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.time.status" bundle="${op}"/>:</td>
    			<td>
    				<input type="text" name="statusTime" value="<fmt:formatDate pattern="HH:mm" value="${departure.statusTime}"/>" disabled="disabled">
    				<input type="button" value="..." name="addStatusTime"  alt="select time">
    			</td>
    		</tr>
    		<ui:itemButtons/>	
    	</table>
    </form>
</div>
</ui:html>