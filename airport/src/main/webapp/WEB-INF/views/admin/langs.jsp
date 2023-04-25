<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <a href="../admin.html"><fmt:message key="admin.back.to.board" bundle="${op}"/></a>
    <form method="post" action="${pageContext.request.contextPath}/admin/lang/dlangs.html">
	<table class="admin dashed">
		<thead>
			<tr>
				<td style="width: 150px;"><fmt:message key="admin.tag" bundle="${op}"/></td>
				<td><fmt:message key="admin.name" bundle="${op}"/></td>
				<td><fmt:message key="admin.active" bundle="${op}"/></td>
				<td><input type="checkbox" name="delete0"></td>
			</tr>
		</thead>
		
		<tbody>
			<!-- 14 rows -->
			<c:forEach items="${items}" var="item">
				<tr>
					<td  style="text-align:center;"><a href="lang/${item.id}.html"> <span>${item.tag}</span></a>
					</td>
					<td><a href="lang/${item.id}.html"> <span>${item.name}</span></a>
					</td>
					<td style="text-align:center;">
						<a href="lang/${item.id}.html">
								<c:choose>
								    <c:when test="${item.active==true}">
								        <span><fmt:message key="admin.yes" bundle="${op}"/></span>
								    </c:when>
								    <c:otherwise>
								        <span><fmt:message key="admin.no" bundle="${op}"/></span>
								    </c:otherwise>
								</c:choose>
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
					<input type="button" name="add" value="+" onclick="location.href='lang/add.html'">
				</td>
			</tr>
		</tbody>
		
	</table></form>
	
</div>
</ui:html>