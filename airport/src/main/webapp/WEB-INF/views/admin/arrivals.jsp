<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <a href="../admin.html"><-</a>
	<table>
		<thead>
			<tr>
				<td>fight</td>
				<td>date</td>
			</tr>
		</thead>
		<tbody>
			<!-- 14 rows -->
			<c:forEach items="${items}" var="item">
				<tr>
					<td><a href="lang/${item.id}.html"> <span>${item.flight}</span></a>
					</td>
					<td><a href="lang/${item.id}.html"> <span>${item.scheduledDate}</span></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
    </div>
</ui:html>