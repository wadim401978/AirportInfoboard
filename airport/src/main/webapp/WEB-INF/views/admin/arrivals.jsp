<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<fmt:setBundle basename="viewer" var="viewer_bundle" />
<c:set value="ru" var="loc"/>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <a href="../admin.html"><fmt:message key="admin.back.to.board" bundle="${op}"/></a>
    <form method="post" action="${pageContext.request.contextPath}/admin/arrival/darrivals.html" id="deleteCheckers">
	<table class="admin dashed">
		<thead>
			<tr>
				<td style="width: 210px;"><fmt:message key="admin.date.scheduled" bundle="${op}"/></td>
				<td>
					<fmt:message key="admin.flight" bundle="${op}"/>
				</td>
				<td><fmt:message key="admin.status" bundle="${op}"/></td>
				<td><input type="checkbox" name="delete0" onchange="switchAllCheckers(this);"></td>
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
			    					<fmt:formatDate pattern="HH:mm" value="${item.scheduledDate}" var="statusTime"/>
			    					<fmt:param value="${statusTime}"/>	
			    				</fmt:message>
			    			</a>
		    			</td>
					
					<td><input type="checkbox" name="${item.id}"></td>
				</tr>
			</c:forEach>
			<tr style="border: none;">
				<td colspan="3">
					
					<input type="submit" value="delete">
				</td>
				<td>
					<input type="button" name="add" value="+" onclick="location.href='arrival/add.html'">
				</td>
			</tr>
		</tbody>
	</table>
	</form>
    </div>
</ui:html>