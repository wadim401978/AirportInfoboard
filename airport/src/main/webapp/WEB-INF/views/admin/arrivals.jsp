<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<fmt:setBundle basename="viewer" var="viewer_bundle" />
<c:set value="ru" var="loc"/>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <form method="post" action="${pageContext.request.contextPath}/admin/arrival/darrivals.html" id="deleteCheckers">
	<table class="admin dashed">
		<thead>
			<ui:listButtons itemHref="arrival" colspan="3"/>
			<tr>
				<th style="width: 210px;"><fmt:message key="admin.date.scheduled" bundle="${op}"/></th>
				<th>
					<fmt:message key="admin.flight" bundle="${op}"/>
				</th>
				<th><fmt:message key="admin.status" bundle="${op}"/></th>
				<th><input type="checkbox" name="delete0" onchange="switchAllCheckers(this);"></th>
			</tr>
		</thead>
		<tbody>
			<!-- 14 rows -->
			<c:forEach items="${items}" var="item">
				<tr>
					<td>
						<a href="arrival/${item.id}.html"> 
							<span><fmt:formatDate pattern="dd.MM.YYYY" value="${item.scheduledDate}"/></span>
							<span><fmt:formatDate pattern="HH:mm" value="${item.scheduledDate}"/></span>
						</a>
					</td>
					<td>
						<a href="arrival/${item.id}.html"> 
							<span>
								${item.flight.airport.name}<br>
								${item.flight.iataNumber} (${item.flight.icaoNumber})
							</span>
						</a>
					</td>
		    			<td>
		    				<a href="arrival/${item.id}.html">
			    				<fmt:message key="${item.status.property}" bundle="${viewer_bundle}">
			    					<fmt:formatDate pattern="HH:mm" value="${item.statusTime}" var="statusTime"/>
			    					<c:choose>
			    						<c:when test="${statusTime == null}"><fmt:param value=""/></c:when>
			    						<c:otherwise><fmt:param value="${statusTime}"/>	</c:otherwise>
			    					</c:choose>
			    				</fmt:message>
			    			</a>
		    			</td>
					
					<td align="center"><input type="checkbox" name="${item.id}"></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</form><c:set var="textConfirm"><fmt:message key="admin.r.u.sure2delete" bundle="${op}"/></c:set>
	<script type="text/javascript">
		confirmDelete('deleteCheckers', '${textConfirm}');
	</script>
    </div>
</ui:html>