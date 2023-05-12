<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <ui:popupLang items="${langs}" 
    	popupId="airlineModal" 
    	popupHeader="admin.select.language"/>
    <form:form action="${pageContext.request.contextPath}/admin/airline/save.html" modelAttribute="airline">
    	<table class="admin">
    		<tr>
    			<td style="width: 180px;" class="bold"><fmt:message key="admin.id" bundle="${op}"/>:</td>
    			<td>
    				<form:input path ="id" readonly="true" />
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 180px;" class="bold">
    				<fmt:message key="admin.ICAO.code" bundle="${op}"/>:<span style="color:red;">*</span>
    			</td>
    			<td class="bold">
    				<form:errors path="icaoCode" cssStyle="color:red;" element="div"/>
					<form:input path ="icaoCode" maxlength="3"/>
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 180px;" class="bold">
    				<fmt:message key="admin.IATA.code" bundle="${op}"/>:<span style="color:red;">*</span>
    			</td>
    			<td class="bold">
    				<form:errors path="iataCode" cssStyle="color:red;" element="div"/>
					<form:input path ="iataCode" maxlength="2"/>
    			</td>
    		</tr>
			<tr>
				<td colspan="2" class="bold">
					<ui:AirEntityName names="${airline.names}"/>
				</td>
			</tr>
			<ui:itemButtons onCancelHref="${pageContext.request.contextPath}/admin/airlines.html" addButton="true"/>
		</table>
		<form:errors element="div"  cssClass="errors"/>
    </form:form>
</div>
<script>
	document.getElementById('addLang').setAttribute("data-bs-target", "#airlineModal");
</script>

</ui:html>