<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <a href="../admin.html"><fmt:message key="admin.back.to.board" bundle="${op}"/></a>
    <form method="post" action="${pageContext.request.contextPath}/admin/airline/dairlines.html" id="deleteCheckers">
	<table class="admin dashed">
		<thead>
			<tr>
				<td><fmt:message key="admin.name" bundle="${op}"/></td>
				<td>
					<fmt:message key="admin.IATA.code" bundle="${op}"/>
					(<fmt:message key="admin.ICAO.code" bundle="${op}"/>)
				</td>
				<td><input type="checkbox" name="delete0" onchange="switchAllCheckers(this);"></td>
			</tr>
		</thead>
		<tbody>
			<!-- 14 rows -->
			<c:forEach items="${items}" var="item">
				<tr>
					<td><a href="airline/${item.id}.html"> <span>${item.name}</span></a>
					</td>
					<td>
						<a href="airline/${item.id}.html"> 
							<span>${item.iataCode} (${item.icaoCode})</span>
						</a>
					</td>
					<td><input type="checkbox" name="${item.id}"></td>
				</tr>
			</c:forEach>
			<tr style="border: none;">
				<td colspan="2">
					
					<input type="submit" value="delete">
				</td>
				<td>
					<input type="button" name="add" value="+" onclick="location.href='airline/add.html'">
				</td>
			</tr>
		</tbody>
	</table>
	<div style="color:red;">
	<c:out value="${error}" />
	</div>
	</form>
    </div>
</ui:html>