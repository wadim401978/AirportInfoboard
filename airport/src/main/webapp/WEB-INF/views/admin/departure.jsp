<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<fmt:setBundle basename="viewer" var="viewer_bundle" />
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <c:set var="popupId" value="flightModal"/>
    <c:set var="popupTargetId" value="flight_id"/>
    <c:set var="popupTargetName" value="flight_name"/>
    <ui:popupFlight 
    	items="${flights}" 
    	destination_id="${popupTargetId}" 
    	destination_name="${popupTargetName}" 
    	popupHeader="admin.select.flight"
    	popupId="${popupId}"/>
    <div class="p-4">
    <form:form action="${pageContext.request.contextPath}/admin/departure/save.html" modelAttribute="departure" >
    	<table class="admin">
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.id" bundle="${op}"/>:</td>
    			<td><input type="text" name="id" value="${departure.id}" readonly="readonly"></td>
    		</tr>
    		<tr>
    			<td style="width: 300px;">
    				<fmt:message key="admin.flight" bundle="${op}"/>:<span style="color:red;">*</span>
    			</td>
    			<td><c:set var="selectFlight"><fmt:message key="admin.select.flight" bundle="${op}"/></c:set>
    				<input type="text" id="${popupTargetName}" value="${departure.flight.presentation}" title="${selectFlight}" data-bs-toggle="modal" data-bs-target="#${popupId}" readonly="readonly">
    				<input type="hidden" name="${popupTargetId}" id="${popupTargetId}" value="${departure.flight.id}">
    				<input type="button" value="..." name="addFlight" title="${selectFlight}" data-bs-toggle="modal" data-bs-target="#${popupId}">
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 300px;">
    				<fmt:message key="admin.date.scheduled" bundle="${op}"/>:<span style="color:red;">*</span>
    			</td>
    			<td><c:set var="scheduledDate"><fmt:formatDate pattern="YYYY-MM-dd" value="${departure.scheduledDate}"/></c:set>
    				<input type="date" name="depScheduledDate" value="${scheduledDate}"  required="required">
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 300px;">
    				<fmt:message key="admin.time.scheduled" bundle="${op}"/>:<span style="color:red;">*</span>
    			</td>
    			<td><c:set var="scheduledTime"><fmt:formatDate pattern="HH:mm" value="${departure.scheduledDate}"/></c:set>
    				<input type="time" name="scheduledTime" value="${scheduledTime}"  required="required">
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 300px;"><fmt:message key="admin.status" bundle="${op}"/>:</td>
    			<td>
    				<select name="depStatus" >
    				<c:forEach items="${departure.status.values()}" var="item">
    					<c:choose>
    						<c:when test="${departure.status.id == item.id}">
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
    				<input type="time" name="depStatusTime" value="<fmt:formatDate pattern="HH:mm" value="${departure.statusTime}"/>" >
    			</td>
    		</tr>
    		<ui:itemButtons onCancelHref="${pageContext.request.contextPath}/admin/departures.html"/>	
    	</table>
    	<form:errors element="name"  cssClass="errors"/>
    </form:form>
</div>
</ui:html>