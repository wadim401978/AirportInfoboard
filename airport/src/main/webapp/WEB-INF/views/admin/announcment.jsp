<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <form:form action="${pageContext.request.contextPath}/admin/announcments.html" >
    	<table class="admin">
    		<tr>
    			<td style="width: 180px;"><fmt:message key="admin.id" bundle="${op}"/>:</td>
    			<td><input type="text" name="id" value="${announcment.id}" readonly="readonly"></td>
    		</tr>
    		<tr>
    			<td style="width: 180px;"><fmt:message key="admin.active" bundle="${op}"/>:</td>
				<td>
					<c:choose>
						<c:when test="${announcment.active==true}">
							<input type="checkbox" name="icao" value="${announcment.active}" checked="checked">
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="icao" value="${announcment.active}">
						</c:otherwise>
					</c:choose> 
					
				</td>
			</tr>
    		<tr>
    			<td style="width: 180px;"><fmt:message key="admin.content" bundle="${op}"/>:</td>
    			<td>
    			<textarea rows="10" cols="35" name="html">
    				${announcment.html}
    			</textarea>
    			</td>
    		</tr>
    		<ui:itemButtons onCancelHref="${pageContext.request.contextPath}/admin/announcments.html"/>	
    	</table>
    </form:form>
</div>
</ui:html>