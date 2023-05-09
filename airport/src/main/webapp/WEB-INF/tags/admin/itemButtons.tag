<%@ tag language="java" pageEncoding="UTF-8"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<%@attribute name="onCancelHref" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="addButton" required="false" rtexprvalue="true" type="java.lang.Boolean"%>
<tr>
	<td colspan="2">
		<c:if test="${addButton==true}"><c:set var="addName"><fmt:message key="admin.add.name" bundle="${op}"/></c:set>
		<input type="button" value="${addName}" name="addLang" title="${addName}" onclick="addRow();"></c:if>
		<c:set var="OK"><fmt:message key="admin.ok" bundle="${op}"/></c:set>
		<input type="submit" value="OK" title="${OK}">
		<c:set var="CANCEL"><fmt:message key="admin.cancel" bundle="${op}"/></c:set>
		<input type="button" value="Cancel" onclick="location.href='${onCancelHref}'" title="${CANCEL}">
	</td>
</tr>
