<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <form:form action="${pageContext.request.contextPath}/admin/lang/save.html" method="POST" modelAttribute="lang" >
    	<table class="admin">
    		<tr>
    			<td style="width: 245px;"><fmt:message key="admin.id" bundle="${op}"/>:
    			</td>
    			<td><input type="text" name="id" value="${language.id}" readonly="readonly"></td>
    		</tr>
    		<tr>
    			<td ><fmt:message key="admin.name" bundle="${op}"/>:
    				<span style="color:red;">*</span>
    			</td>
    			<td><input type="text" name="name" value="${language.name}"></td>
    		</tr>
    		<tr>
    			<td><fmt:message key="admin.tag" bundle="${op}"/>:
    				<span style="color:red;">*</span>
    			</td>
    			<td><input type="text" name="tag" value="${language.tag}"></td>
    		</tr>
    		<tr>
    			<td><fmt:message key="admin.active" bundle="${op}"/>:</td>
    			<td>
    				<c:choose>
						<c:when test="${language.active==true}">
							<c:set var="checked" value='checked="checked"'/>
							
						</c:when>
						<c:otherwise>
							<c:set var="checked" value=''/>
						</c:otherwise>
					</c:choose> 
					<input type="checkbox" name="activeSwitcher" id="activeSwitcher" ${checked} onchange="changeActive();">
					<input type="hidden" name="active" id="active" value="${language.active}">
					
    			</td>
    		</tr>
    		<ui:itemButtons/>	
    	</table>
    </form:form>
</div>
</ui:html>