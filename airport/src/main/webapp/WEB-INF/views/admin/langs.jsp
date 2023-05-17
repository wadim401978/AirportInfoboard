<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <form:form method="post" action="${pageContext.request.contextPath}/admin/lang/dlangs.html" id="deleteCheckers">
	<table class="admin dashed">
		<thead>
			<ui:listButtons itemHref="lang" colspan="3"/>
			<tr>
				<th style="width: 150px;"><fmt:message key="admin.tag" bundle="${op}"/></th>
				<th><fmt:message key="admin.name" bundle="${op}"/></th>
				<th><fmt:message key="admin.active" bundle="${op}"/></th>
				<th style="text-align: center;"><input type="checkbox" name="delete0" onchange="switchAllCheckers(this);"></th>
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
					
					<td align="center"><input type="checkbox" name="${item.id}"></td>
				</tr>
			</c:forEach>
		</tbody>
		
	</table>
	<div style="color:red;">
	<c:out value="${error}" />
	</div>
	</form:form><c:set var="textConfirm"><fmt:message key="admin.r.u.sure2delete" bundle="${op}"/></c:set>
	<script type="text/javascript">
		confirmDelete('deleteCheckers', '${textConfirm}');
	</script>
	
</div>
</ui:html>