<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <form:form action="${pageContext.request.contextPath}/admin/announcment/save.html" modelAttribute="announcment" >
    	<table class="admin">
    		<tr>
    			<td style="width: 180px;" class="bold"><fmt:message key="admin.id" bundle="${op}"/>:</td>
    			<td>${announcment.id}<form:hidden path="id"/></td>
    		</tr>
    		<tr>
    			<td style="width: 180px;" class="bold"><fmt:message key="admin.active" bundle="${op}"/>:</td>
				<td>
					<form:checkbox path="active"/> 
				</td>
			</tr>
    		<tr>
    			<td style="width: 180px;"  class="bold" colspan="2">
	    			<fmt:message key="admin.content" bundle="${op}"/>:
	    			<span style="color:red;">*</span><form:errors path="html" cssStyle="color:red;"/>
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    				
    				<form:textarea path="html"/>
				</td>
    		</tr>
    		<ui:itemButtons onCancelHref="${pageContext.request.contextPath}/admin/announcments.html"/>	
    	</table>
    	<form:errors element="name"  cssClass="errors"/>
    </form:form>
</div>
<script src="${pageContext.request.contextPath}/script/tinymce/tinymce.min.js"></script>

<script>
tinymce.init({ selector:'textarea' });	
</script>
</ui:html>