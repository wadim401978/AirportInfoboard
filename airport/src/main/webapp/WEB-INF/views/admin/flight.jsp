<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <c:set var="popupAirportId" value="airportModal"/>
    <c:set var="popupAirlineId" value="airlineModal"/>
    <c:set var="popupTargetAirportId" value="airport_id"/>
    <c:set var="popupTargetAirlineId" value="airline_id"/>
    <c:set var="popupTargetAirportName" value="airport_name"/>
    <c:set var="popupTargetAirlineName" value="airline_name"/>
    <ui:popup 
    	items="${airports}" 
    	destination_id="${popupTargetAirportId}" 
    	destination_name="${popupTargetAirportName}" 
    	popupHeader="admin.select.airport"
    	popupId="${popupAirportId}"/>
    
    <ui:popup 
    	items="${airlines}" 
    	destination_id="${popupTargetAirlineId}" 
    	destination_name="${popupTargetAirlineName}" 
    	popupHeader="admin.select.airline"
    	popupId="${popupAirlineId}"/>
    
    <div class="p-4">
    <form:form action="${pageContext.request.contextPath}/admin/flight/save.html"  modelAttribute="flight">
    	<table class="admin">
    		<tr>
    			<td style="width: 180px;"><fmt:message key="admin.id" bundle="${op}"/>:</td>
    			<td><input type="text" name="id" value="${flight.id}" readonly="readonly"></td>
    		</tr>
    		<tr>
    			<td style="width: 180px;">
    				<fmt:message key="admin.airport" bundle="${op}"/>:
    				<span style="color:red;">*</span>
    			</td>
    			<td>
    				<input type="text" id="${popupTargetAirportName}" value="${flight.airport.presentation}" data-bs-toggle="modal" data-bs-target="#${popupAirportId}" readonly="readonly">
    				<input type="hidden" name="${popupTargetAirportId}" id="${popupTargetAirportId}" value="${flight.airport.id}" >
    				<input type="button" value="..." name="addAirport" alt="select airport"  data-bs-toggle="modal" data-bs-target="#${popupAirportId}">
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 180px;">
    				<fmt:message key="admin.airline" bundle="${op}"/>:<span style="color:red;">*</span>
    			</td>
    			<td>
    				<input type="text" id="${popupTargetAirlineName}" value="${flight.airline.presentation}" data-bs-toggle="modal" data-bs-target="#${popupAirlineId}" readonly="readonly">
    				<input type="hidden" name="${popupTargetAirlineId}" id="${popupTargetAirlineId}" value="${flight.airline.id}" >
    				<input type="button" value="..." name="addAirline"  alt="select airline" data-bs-toggle="modal" data-bs-target="#${popupAirlineId}">
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 180px;">
    				<fmt:message key="admin.number" bundle="${op}"/>:<span style="color:red;">*</span>
    			</td>
    			<td>
    				<form:errors path="number" cssStyle="color:red;" element="div"/>
    				<form:input path="number" pattern="\d+"/>
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 180px;">
    				<fmt:message key="admin.type" bundle="${op}"/>:<span style="color:red;">*</span>
    			</td>
    			<td>
	    			<select name="arrival">
					<c:choose>
						<c:when test="${flight.arrival==true}">
							<option value="true" selected="selected"><fmt:message key="admin.arrival" bundle="${op}"/></option>
							<option value="false"><fmt:message key="admin.departure" bundle="${op}"/></option>
						</c:when>
						<c:otherwise>
							<option value="true"><fmt:message key="admin.arrival" bundle="${op}"/></option>
							<option value="false" selected="selected"><fmt:message key="admin.departure" bundle="${op}"/></option>
						</c:otherwise>
					</c:choose>
	    			
					</select>
				</td>
    		</tr>
    		<ui:itemButtons onCancelHref="${pageContext.request.contextPath}/admin/flights.html"/>	
    	</table>
    	<form:errors element="name" cssStyle="color:red;"/>
    </form:form>
</div>
</ui:html>