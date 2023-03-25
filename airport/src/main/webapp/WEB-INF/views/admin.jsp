<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
		<ul class="tabs">
			<li class="up">
				<input type="radio" name="tabs" id="tab-1" checked>
				<label for="tab-1" style="border-radius: 30px 0 0 0;"><fmt:message key="admin.references.title" bundle="${op}"/></label>
				<div class="tab-content">
					<table class="admin dashed">
						<tr>
							<td>
								<a href="admin/langs.html">
									<fmt:message key="admin.languages.title" bundle="${op}"/>
								</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="admin/airports.html">
									<fmt:message key="admin.airports.title" bundle="${op}"/>
								</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="admin/airlines.html">
									<fmt:message key="admin.airlines.title" bundle="${op}"/>
								</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="admin/flights.html">
									<fmt:message key="admin.flights.title" bundle="${op}"/>
								</a>
							</td>
						</tr>
					</table>
				</div>
			</li>
			
			<li class="up">
				<input type="radio" name="tabs" id="tab-2">
				<label for="tab-2"><fmt:message key="admin.tables.title" bundle="${op}"/></label>
				<div class="tab-content">
					<table  class="admin dashed">
						<tr>
							<td>
								<a href="admin/arrivals.html">
									<fmt:message key="admin.arrivals.title" bundle="${op}"/>
								</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="admin/departures.html">
									<fmt:message key="admin.departures.title" bundle="${op}"/>
								</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="admin/announcments.html">
									<fmt:message key="admin.announcments.title" bundle="${op}"/>
								</a>
							</td>
						</tr>
					</table>
				</div>
			</li>
		</ul>
</ui:html>