<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <a href="../admin.html">Back to admin board</a>
	<table class="admin">
		<thead>
			<tr>
				<td style="width: 150px;"><fmt:message key="admin.tag" bundle="${op}"/></td>
				<td><fmt:message key="admin.name" bundle="${op}"/></td>
				<td><input type="checkbox" name="delete0"></td>
			</tr>
		</thead>
		<form method="post" action="${pageContext.request.contextPath}/admin/langs.html">
		<tbody>
			<!-- 14 rows -->
			<c:forEach items="${items}" var="item">
				<tr>
					<td><a href="lang/${item.id}.html"> <span>${item.langTag}</span></a>
					</td>
					<td><a href="lang/${item.id}.html"> <span>${item.name}</span></a>
					</td>
					<td><input type="checkbox" name="delete${item.id}"></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="2">
					
					<input type="submit" value="delete">
				</td>
				<td>
					<input type="button" name="add" value="+">
				</td>
			</tr>
		</tbody>
		</form>
	</table>
	
</div>
</ui:html>