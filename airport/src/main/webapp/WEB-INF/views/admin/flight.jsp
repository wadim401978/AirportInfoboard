<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <form:form action="${pageContext.request.contextPath}/admin/flights.html" >
    	<table class="admin">
    		<tr>
    			<td style="width: 180px;"><fmt:message key="admin.id" bundle="${op}"/>:</td>
    			<td><input type="text" name="id" value="${flight.id}" readonly="readonly"></td>
    		</tr>
    		<tr>
    			<td style="width: 180px;"><fmt:message key="admin.airport" bundle="${op}"/>:</td>
    			<td>
    				<input type="text" name="airport" value="${flight.airport.name}" disabled="disabled">
    				<input type="button" value="..." name="addAirport" alt="select airport">
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 180px;"><fmt:message key="admin.airline" bundle="${op}"/>:</td>
    			<td>
    				<input type="text" name="airline" value="${flight.airline.name}" disabled="disabled">
    				<input type="button" value="..." name="addAirline"  alt="select airline">
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 180px;"><fmt:message key="admin.number" bundle="${op}"/>:</td>
    			<td>
    				<input type="text" name="number" value="${flight.number}">
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 180px;"><fmt:message key="admin.type" bundle="${op}"/>:</td>
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
    </form:form>
</div>
</ui:html>