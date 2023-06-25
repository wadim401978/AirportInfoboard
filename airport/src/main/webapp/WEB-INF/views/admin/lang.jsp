<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op" />
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <form:form action="${pageContext.request.contextPath}/admin/lang/save.html" method="POST" modelAttribute="language" >
    	<table class="admin">
    		<tr>
    			<td style="width: 245px;" class="bold"><fmt:message key="admin.id" bundle="${op}"/>:
    			</td>
    			<td><input type="text" name="id" value="${language.id}" readonly="readonly"></td>
    		</tr>
    		<tr>
    			<td  class="bold"><fmt:message key="admin.name" bundle="${op}"/>:
    				<span style="color:red;">*</span>
    			</td>
    			<td class="bold">
    				<form:errors path="name" cssStyle="color:red;" element="div"/>
    				<form:input path="name"/>
    			</td>
    		</tr>
    		<tr>
    			<td class="bold"><fmt:message key="admin.tag" bundle="${op}"/>:
    				<span style="color:red;">*</span>
    			</td>
    			<td class="bold">
    				<form:errors path="tag" cssStyle="color:red;" element="div"/>
    				<form:input path="tag"/>
    			</td>
    		</tr>
    		<tr>
    			<td class="bold"><fmt:message key="admin.active" bundle="${op}"/>:</td>
    			<td>
    				<form:checkbox path="active"/>
    			</td>
    		</tr>
    		<ui:itemButtons onCancelHref="${pageContext.request.contextPath}/admin/langs.html"/>	
    	</table>
    	<form:errors element="div"  cssClass="errors"/>
    </form:form>
    
    
</div>
</ui:html>