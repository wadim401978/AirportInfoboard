<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <a href="../admin.html">Back to admin board</a>
	<table class="admin dashed">
		<thead>
			<tr>
				<td><fmt:message key="admin.airport" bundle="${op}"/></td>
				<td>
					<fmt:message key="admin.IATA.number" bundle="${op}"/>
					(<fmt:message key="admin.ICAO.number" bundle="${op}"/>)
				</td>
				<td><fmt:message key="admin.type" bundle="${op}"/></td>
				<td><input type="checkbox" name="delete0"></td>
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
					
					<td><input type="checkbox" name="delete${item.id}"></td>
				</tr>
			</c:forEach>
			<tr style="border: none;">
				<td colspan="2">
					
					<input type="submit" value="delete">
				</td>
				<td>
					<input type="button" name="add" value="+" onclick="location.href='flight/add.html'">
				</td>
			</tr>
		</tbody>
	</table>
    </div>
</ui:html>