<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <form:form action="${pageContext.request.contextPath}/admin/lang/save.html" method="POST" modelAttribute="lang" >
    	<table class="admin">
    		<tr>
    			<td style="width: 180px;"><fmt:message key="admin.id" bundle="${op}"/>:</td>
    			<td><input type="text" name="id" value="${language.id}" ></td>
    		</tr>
    		<tr>
    			<td style="width: 180px;"><fmt:message key="admin.name" bundle="${op}"/>:</td>
    			<td><input type="text" name="name" value="${language.name}"></td>
    		</tr>
    		<tr>
    			<td><fmt:message key="admin.tag" bundle="${op}"/>:</td>
    			<td><input type="text" name="tag" value="${language.langTag}"></td>
    		</tr>
    		<tr>
    			<td><fmt:message key="admin.active" bundle="${op}"/>:</td>
    			<td>
    				<c:choose>
						<c:when test="${language.active==true}">
							<input type="checkbox" name="active" checked="checked">
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="active" >
						</c:otherwise>
					</c:choose> 
					
    			</td>
    		</tr>
    		<ui:itemButtons/>	
    	</table>
    </form:form>
</div>
</ui:html>