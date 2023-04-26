<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<fmt:setBundle basename="viewer" var="viewer_bundle" />
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <form:form action="${pageContext.request.contextPath}/admin/arrivals.html" >
    	<table class="admin">
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.id" bundle="${op}"/>:</td>
    			<td><input type="text" name="id" value="${arrival.id}" readonly="readonly"></td>
    		</tr>
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.flight" bundle="${op}"/>:</td>
    			<td>
    				<input type="text" name="flight" value="${arrival.flight.iataNumber}-${arrival.flight.airport.name}" disabled="disabled">
    				<input type="button" value="..." name="addFlight" alt="select fligth">
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.date.scheduled" bundle="${op}"/>:</td>
    			<td>
    				<input type="text" name="date" value="<fmt:formatDate pattern="dd.MM.YYYY" value="${arrival.scheduledDate}"/>" disabled="disabled">
    				<input type="button" value="..." name="addDate"  alt="select date">
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.time.scheduled" bundle="${op}"/>:</td>
    			<td>
    				<input type="text" name="time" value="<fmt:formatDate pattern="HH:mm" value="${arrival.scheduledDate}"/>" disabled="disabled">
    				<input type="button" value="..." name="addTime"  alt="select time">
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.status" bundle="${op}"/>:</td>
    			<td>
    				<input type="text" name="status" value="<fmt:message key="${arrival.status.property}" bundle="${viewer_bundle}"/>" disabled="disabled">
    				<input type="button" value="..." name="addStatus"  alt="select status">
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.time.status" bundle="${op}"/>:</td>
    			<td>
    				<input type="text" name="statusTime" value="<fmt:formatDate pattern="HH:mm" value="${arrival.statusTime}"/>" disabled="disabled">
    				<input type="button" value="..." name="addStatusTime"  alt="select time">
    			</td>
    		</tr>
    		<ui:itemButtons onCancelHref="${pageContext.request.contextPath}/admin/arrivals.html"/>	
    	</table>
    </form:form>
</div>
</ui:html>