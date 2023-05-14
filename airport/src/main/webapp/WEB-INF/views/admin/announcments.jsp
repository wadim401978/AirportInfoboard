<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <form method="post" action="${pageContext.request.contextPath}/admin/announcment/dannouncments.html" id="deleteCheckers">
	<table class="admin dashed" style="">
		<thead>
			<ui:listButtons itemHref="announcment" colspan="2"/>
			<tr>
				<th><fmt:message key="admin.active" bundle="${op}"/></th>
				<th><fmt:message key="admin.content" bundle="${op}"/></th>
				<th><input type="checkbox" name="delete0" onchange="switchAllCheckers(this);"></th>
			</tr>
		</thead>
		<tbody>
			<!-- 14 rows -->
			<c:forEach items="${items}" var="item">
				<tr >
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
					<td>
						<div style="max-height:150px; max-width:700px; overflow: hidden; font-size:medium;">
							<a href="announcment/${item.id}.html"> <span class="htmlInside" style="">${item.html}</span></a>
						</div>
					</td>
					<td align="center"><input type="checkbox" name="${item.id}"></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</form>
    </div>
</ui:html>