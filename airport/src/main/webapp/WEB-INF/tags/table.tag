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
		    				<span id="iata${flight_sch.id}" class="yellow">${flight_sch.flight.iataNumber}</span>
		    				<span id="icao${flight_sch.id}" class="white"> (${flight_sch.flight.icaoNumber})</span>
		    				<script type="text/javascript">
								printWriter('#iata${flight_sch.id}', 'letter${flight_sch.id}', 10, 50, "yellow");
								printWriter('#icao${flight_sch.id}', 'letter${flight_sch.id}', 300, 70, "white");
							</script>
		    			</td>
		    			<td class="airport">
								<c:choose>
								    <c:when test="${flight_sch.flight.airport.namesSimple[lang.id]==null}">
								    	<c:set var="apname">${flight_sch.flight.airport.name}</c:set>
								    </c:when>
								    <c:otherwise>
								    	<c:set var="apname">${flight_sch.flight.airport.namesSimple[lang.id]}</c:set>
								    </c:otherwise>
								</c:choose>
								
		    					<span id="apname${flight_sch.id}" class="yellow">${apname}</span>
		    					<span id="apcode${flight_sch.id}" class="white">(${flight_sch.flight.airport.iataCode})</span>
		    					<script type="text/javascript">
									printWriter('#apname${flight_sch.id}', 'letter${flight_sch.id}', 10, 50, "yellow");
									printWriter('#apcode${flight_sch.id}', 'letter${flight_sch.id}', 300, 70, "white");
								</script>
		    			</td>
		    			<td class="time">
		    				<span id="fdate${flight_sch.id}" class="white"><fmt:formatDate pattern="dd.MM.YYYY" value="${flight_sch.scheduledDate}"/></span>
		    				<span id="ftime${flight_sch.id}" class="yellow"><fmt:formatDate pattern="HH:mm" value="${flight_sch.scheduledDate}"/></span>
		    				<script type="text/javascript">
								printWriter('#fdate${flight_sch.id}', 'letter${flight_sch.id}', 10, 50, "white");
								printWriter('#ftime${flight_sch.id}', 'letter${flight_sch.id}', 300, 70, "yellow");
							</script>
		    			</td>
		    			<td>
		    				<c:choose>
								<c:when test="${flight_sch.flight.airline.namesSimple[lang.id]==null}">
									<c:set var="alname">${flight_sch.flight.airline.name}</c:set>
								</c:when>
								<c:otherwise>
									<c:set var="alname"> ${flight_sch.flight.airline.namesSimple[lang.id]}</c:set>
								</c:otherwise>
							</c:choose> 
		    				<span id="alname${flight_sch.id}" class="yellow">${alname}</span>
		    				<script type="text/javascript">
								printWriter('#alname${flight_sch.id}', 'letter${flight_sch.id}', 10, 50, "yellow");
							</script>
		    			</td>
		    			<td><c:set var="statusParameter"><span id="statusParameter${flight_sch.id}" class='white'>${statusTime}</span></c:set>
			    			<span class="cyan" id="status${flight_sch.id}">
			    				<fmt:message key="${flight_sch.status.property}" bundle="${viewer_bundle}">
			    				<fmt:param value="${statusParameter}"/>
			    				</fmt:message>
			    			</span>
		    				<script type="text/javascript">
								printWriter('#status${flight_sch.id}', 'letter${flight_sch.id}', 10, 50, "cyan");
								printWriter('#statusParameter${flight_sch.id}', 'letter${flight_sch.id}', 300, 70, "white");
							</script>
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
