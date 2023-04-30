<%@ tag language="java" pageEncoding="UTF-8"%><%@attribute name="locale" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="flights" required="true" rtexprvalue="true" type="java.util.List"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${lang.tag}"/>
<fmt:setBundle basename="viewer" var="viewer_bundle" />
		<div class="" id="ru_table" >
	    	<table class="schedule Archangelsk">
	    		<thead>
	    			<tr>
		    			<td class="flight"><fmt:message key="table.number" bundle="${viewer_bundle}" /></td>
		    			<td class="airport"><fmt:message key="table.airport" bundle="${viewer_bundle}" /></td>
		    			<td class="time"><fmt:message key="table.time" bundle="${viewer_bundle}" /></td>
		    			<td class="airline"><fmt:message key="table.airline" bundle="${viewer_bundle}" /></td>
		    			<td class="status"><fmt:message key="table.status" bundle="${viewer_bundle}" /></td>
	    			</tr>
	    		</thead>
	 			<tbody><!-- 14 rows -->
	 			<c:forEach items="${flights}" var="flight_sch">
	    			<tr><fmt:formatDate pattern="HH:mm" value="${flight_sch.scheduledDate}" var="statusTime"/>
		    			<td class="flight">
		    					<span class="yellow">${flight_sch.flight.iataNumber}</span>
		    					<span class="white"> (${flight_sch.flight.icaoNumber})</span>
		    			</td>
		    			<td class="airport">
		    					<span class="yellow">
								<c:choose>
								    <c:when test="${flight_sch.flight.airport.names[lang]==null}">
								        ${flight_sch.flight.airport.name}
								    </c:when>
								    <c:otherwise>
								        ${flight_sch.flight.airport.names[lang]}
								    </c:otherwise>
								</c:choose>
		    					</span>
		    					<span class="white">(${flight_sch.flight.airport.iataCode})</span>
		    			</td>
		    			<td class="time">
		    				<span class="white"><fmt:formatDate pattern="dd.MM.YYYY" value="${flight_sch.scheduledDate}"/></span>
		    				<span class="yellow"><fmt:formatDate pattern="HH:mm" value="${flight_sch.scheduledDate}"/></span>
		    			</td>
		    			<td>
		    				<span class="yellow"> 
		    				<c:choose>
								<c:when test="${flight_sch.flight.airline.names[lang]==null}">
								        ${flight_sch.flight.airline.name}
								</c:when>
								<c:otherwise>
								        ${flight_sch.flight.airline.names[lang]}
								</c:otherwise>
							</c:choose> 
							</span>
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

			<c:choose>
				<c:when test="${emptyRows==null}">
					<c:set value="0" var="emptyRows"/>
				</c:when>
				<c:otherwise>
					<c:set value="${emptyRows}" var="emptyRows"/>
				</c:otherwise>
			</c:choose>

			<c:if test="${emptyRows > 0}">
			<c:forEach begin="1" end="${emptyRows}" varStatus="loop">
	    			<tr>
		    			<td class="flight">
		    				<span class="yellow">-</span>
		    			</td>
		    			<td class="airport">
		    				<span class="white">-</span>
		    			</td>
		    			<td class="time">
		    				<span class="white">-</span>
		    			</td>
		    			<td>
		    				<span class="yellow">-</span>
		    			</td>
		    			<td>
			    			<span class="cyan">-</span>
		    			</td>
	    			</tr>
			</c:forEach>
			</c:if>

		</tbody>   	
	    	</table>

    </div>
