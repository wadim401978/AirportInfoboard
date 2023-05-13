<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="viewer" var="viewer_bundle"/>
<c:set var="title" scope="page"><fmt:message key="airport.name" bundle="${viewer_bundle}" /></c:set>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
		<table class="admin" style="text-align: center;">
			<tr>
				<td>
					<a href="arr.html"><fmt:message key="title.arrivals" bundle="${viewer_bundle}" /></a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="dep.html"><fmt:message key="title.departures" bundle="${viewer_bundle}" /></a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="admin.html"><fmt:message key="operator.bar" bundle="${viewer_bundle}" /></a>
				</td>
			</tr>
		</table>
	</div>
</ui:html>