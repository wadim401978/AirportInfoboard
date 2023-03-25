<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <a href="../admin.html">Back to admin board</a>
	<table class="admin dashed">
		<thead>
			<tr>
				<td><fmt:message key="admin.active" bundle="${op}"/></td>
				<td><fmt:message key="admin.content" bundle="${op}"/></td>
				<td><input type="checkbox" name="delete0"></td>
			</tr>
		</thead>
		<tbody>
			<!-- 14 rows -->
			<c:forEach items="${items}" var="item">
				<tr>
					<td>
						<a href="announcment/${item.id}.html"> 
							<c:choose>
								<c:when test="${item.active==true}">
									<span><fmt:message key="admin.yes" bundle="${op}" /></span>
								</c:when>
								<c:otherwise>
									<span><fmt:message key="admin.no" bundle="${op}" /></span>
								</c:otherwise>
							</c:choose>
						</a>
					</td>
					<td><a href="announcment/${item.id}.html"> <span>${item.html}</span>
					</td>
					<td><input type="checkbox" name="delete${item.id}"></td>
				</tr>
			</c:forEach>
			<tr style="border: none;">
				<td colspan="2">
					
					<input type="submit" value="delete">
				</td>
				<td>
					<input type="button" name="add" value="+" onclick="location.href='announcment/add.html'">
				</td>
			</tr>
		</tbody>
	</table>
    </div>
</ui:html>