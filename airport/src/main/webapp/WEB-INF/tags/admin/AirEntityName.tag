<%@ tag language="java" pageEncoding="UTF-8"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<%@attribute name="names" required="true" rtexprvalue="true" type="java.util.Map"%>
<table class="langTable" >
	<thead>
		<tr><c:set var="confirm_label"><fmt:message key="admin.r.u.sure2delete" bundle="${op}"/></c:set>
			<th><fmt:message key="admin.language" bundle="${op}"/></th>
			<th colspan="2"><fmt:message key="admin.name" bundle="${op}"/></th>
		</tr>
	</thead><c:set var="delRow"><fmt:message key="admin.del.row" bundle="${op}"/></c:set>
	<tbody  id="airNamesTbody"><c:forEach items="${names}" var="entry">
		<tr id="trid${entry.key.id}">
			<td><input type="hidden" name="isEmpty" value="1">
				<input type="hidden" name="langId${entry.key.id}" value="${entry.key.id}" >
				<div>[ ${entry.key.tag} ]: &nbsp&nbsp&nbsp${entry.key.name}</div>
			</td>
			<td>
				<input type="text" name="name${entry.key.id}" value="${entry.value}" class="langname"  >
			</td>
			<td>
				<input type="button" value="-" title="${delRow}" onclick="delLangRow('trid${entry.key.id}', '${confirm_label}');">
			</td>
		</tr>
	</c:forEach></tbody>
</table><c:set var="addName"><fmt:message key="admin.add.name" bundle="${op}"/></c:set>