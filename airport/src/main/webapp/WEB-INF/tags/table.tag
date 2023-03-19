<%@ tag language="java" pageEncoding="UTF-8"%><%@attribute name="locale" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="flights" required="true" rtexprvalue="true" type="java.util.List"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${lang.langTag}"/>
<fmt:setBundle basename="viewer" var="viewer_bundle" />
		<div class="" id="ru_table" >
	    	<table class="schedule Archangelsk">
	    		<thead>
	    			<tr>
		    			<td class="flight"><fmt:message key="table.number" bundle="${viewer_bundle}" /></td>
		    			<td><fmt:message key="table.airport" bundle="${viewer_bundle}" /></td>
		    			<td class="time"><fmt:message key="table.time" bundle="${viewer_bundle}" /></td>
		    			<td><fmt:message key="table.airline" bundle="${viewer_bundle}" /></td>
		    			<td class="status"><fmt:message key="table.status" bundle="${viewer_bundle}" /></td>
	    			</tr>
	    		</thead>
	 			<tbody><!-- 14 rows -->
	 			<c:forEach items="${flights}" var="flight_sch">
	    			<tr><fmt:formatDate pattern="HH:mm" value="${flight_sch.scheduledDate}" var="statusTime"/>
		    			<td>
		    					<span class="yellow">${flight_sch.flight.iataNumber}</span>
		    					<span class="white"> (${flight_sch.flight.icaoNumber})</span>
		    			</td>
		    			<td>
		    					<span class="yellow">
		    					${flight_sch.flight.airport.names[lang]} 
		    					</span>
		    					<span class="white">(${flight_sch.flight.airport.iataCode})</span>
		    			</td>
		    			<td>
		    				<span class="white"><fmt:formatDate pattern="dd.MM.YYYY" value="${flight_sch.scheduledDate}"/></span>
		    				<span class="yellow"><fmt:formatDate pattern="HH:mm" value="${flight_sch.scheduledDate}"/></span>
		    			</td>
		    			<td>
		    				<span class="yellow">${flight_sch.flight.airline.names[lang]}</span>
		    			</td>
		    			<td>
			    				<span class="cyan">
			    				<fmt:message key="${flight_sch.status.property}" bundle="${viewer_bundle}">
			    				<fmt:param value="<span class='white'>${statusTime}</span>"/>
			    				</fmt:message>
			    				</span>
		    			</td>
	    			</tr>
	    		</c:forEach>
	 			</tbody>   	
	    	</table>

    </div>
