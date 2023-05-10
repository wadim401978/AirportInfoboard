<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <form method="post" action="${pageContext.request.contextPath}/admin/flight/dflights.html" id="deleteCheckers">
	<table class="admin dashed">
		<thead>
			<ui:listButtons itemHref="flight" colspan="3"/>
			<tr>
				<th><fmt:message key="admin.airport" bundle="${op}"/></th>
				<th>
					<fmt:message key="admin.IATA.number" bundle="${op}"/>
					(<fmt:message key="admin.ICAO.number" bundle="${op}"/>)
				</th>
				<th><fmt:message key="admin.type" bundle="${op}"/></th>
				<th  style="text-align: center;"><input type="checkbox" name="delete0" onchange="switchAllCheckers(this);"></th>
			</tr>
		</thead>
		<tbody>
			<!-- 14 rows -->
			<c:forEach items="${items}" var="item">
				<tr>
					<td><a href="flight/${item.id}.html"> <span>${item.airport.name}</span></a>
					</td>
					<td>
						<a href="flight/${item.id}.html"> 
							<span>${item.iataNumber} (${item.icaoNumber})</span>
						</a>
					</td>
					<td>
						<a href="flight/${item.id}.html">
								<c:choose>
								    <c:when test="${item.arrival==true}">
								        <fmt:message key="admin.arrival" bundle="${op}"/>
								    </c:when>
								    <c:otherwise>
								        <fmt:message key="admin.departure" bundle="${op}"/>
								    </c:otherwise>
								</c:choose>
						</a>
					</td>
					
					<td align="center"><input type="checkbox" name="${item.id}"></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="color:red;">
	<c:out value="${error}" />
	</div>
	</form>
    </div>
</ui:html>