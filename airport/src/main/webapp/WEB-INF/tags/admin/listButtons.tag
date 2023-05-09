<%@ tag language="java" pageEncoding="UTF-8"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<%@attribute name="itemHref" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="colspan" required="true" rtexprvalue="true" type="java.lang.Integer"%>
			<tr style="border: none;">
				<td colspan="${colspan}"><c:set var="back_home"><fmt:message key="admin.back.to.board" bundle="${op}"/></c:set>
					<input type="button" value="<=" name="back_home" title="${back_home}" onclick="location.href='../admin.html'">
					<input type="button" name="add" value="+" title="add new" onclick="location.href='${itemHref}/add.html'">
				</td>
				<td align="center">
					<input type="submit" value="-" title="delete">
				</td>
			</tr>
