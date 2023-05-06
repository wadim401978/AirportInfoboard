<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<fmt:setBundle basename="viewer" var="viewer_bundle" />
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <ui:popup items="${flights}" item_id="flight" item_name="flight_name"/>
    <div class="p-4">
    <form:form action="${pageContext.request.contextPath}/admin/arrival/save.html" method="POST"  >
    	<table class="admin">
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.id" bundle="${op}"/>:</td>
    			<td><input type="text" name="id" value="${arrival.id}" readonly="readonly"></td>
    		</tr>
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.flight" bundle="${op}"/>:</td>
    			<td>
    				<input type="text" id="flight_name" value="${arrival.flight.iataNumber}-${arrival.flight.airport.name}" readonly="readonly">
    				<input type="hidden" name="flight" id="flight" value="${arrival.flight.id}">
    				<input type="button" value="..." name="addFlight" alt="select flight" data-bs-toggle="modal" data-bs-target="#airEntityModal">
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.date.scheduled" bundle="${op}"/>:</td>
    			<td>
    				<input type="date" name="scheduledDate" value="<fmt:formatDate pattern="YYYY-MM-dd" value="${arrival.scheduledDate}"/>" required="required">
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.time.scheduled" bundle="${op}"/>:</td>
    			<td>
    				<input type="time" name="scheduledTime" value="<fmt:formatDate pattern="HH:mm" value="${arrival.scheduledDate}"/>" >
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.status" bundle="${op}"/>:</td>
    			<td>
    				<select name="status" >
    				<c:forEach items="${arrival.status.values()}" var="item">
    					<c:choose>
    						<c:when test="${arrival.status.id == item.id}">
    						<option value="${item.id}"  selected="selected"><fmt:message key="${item.property}" bundle="${viewer_bundle}"/></option>
    						</c:when>
    						<c:otherwise>
    						<option value="${item.id}" ><fmt:message key="${item.property}" bundle="${viewer_bundle}"/></option>
    						</c:otherwise>
    					</c:choose>
    				</c:forEach>
    				</select>
    				
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.time.status" bundle="${op}"/>:</td>
    			<td>
    				<input type="time" name="statusTime" value="<fmt:formatDate pattern="HH:mm" value="${arrival.statusTime}"/>" >
    			</td>
    		</tr>
    		<ui:itemButtons onCancelHref="${pageContext.request.contextPath}/admin/arrivals.html"/>	
    	</table>
    </form:form>
</div>
</ui:html>