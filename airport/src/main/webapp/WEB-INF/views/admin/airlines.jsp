<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <form method="post" action="${pageContext.request.contextPath}/admin/airline/dairlines.html" id="deleteCheckers">
	<table class="admin dashed">
		<thead>
			<ui:listButtons itemHref="airline" colspan="2"/>
			<tr>
				<th><fmt:message key="admin.name" bundle="${op}"/></th>
				<th>
					<fmt:message key="admin.IATA.code" bundle="${op}"/>
					(<fmt:message key="admin.ICAO.code" bundle="${op}"/>)
				</th>
				<th><fmt:message key="admin.file.logo" bundle="${op}"/></th>
				<th><input type="checkbox" name="delete0" onchange="switchAllCheckers(this);"></th>
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
					<td align="center">
						<a href="airline/${item.id}.html"> <c:choose>
							<c:when test="${item.logo == null}">-</c:when>
							<c:otherwise><img alt="${item.name}" src="${pageContext.request.contextPath}/airlines/${item.logo}"></c:otherwise>
						</c:choose></a>
						
					</td>
					<td align="center"><input type="checkbox" name="${item.id}"></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="color:red;">
	<c:out value="${error}" />
	</div>
	</form><c:set var="textConfirm"><fmt:message key="admin.r.u.sure2delete" bundle="${op}"/></c:set>
	<script type="text/javascript">
		confirmDelete('deleteCheckers', '${textConfirm}');
	</script>
    </div>
</ui:html>