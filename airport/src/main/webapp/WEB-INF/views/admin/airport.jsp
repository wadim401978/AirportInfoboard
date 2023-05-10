<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <ui:popupLang items="${langs}" 
    	popupId="airportModal" 
    	popupHeader="admin.select.language"/>
    <form:form action="${pageContext.request.contextPath}/admin/airport/save.html" method="POST" modelAttribute="airport">
    	<table class="admin" style="">
    		<tr>
    			<td style="width: 180px;"><fmt:message key="admin.id" bundle="${op}"/>:</td>
    			<td><form:errors path="id" cssStyle="color:red;" element="div"/>
    				<form:input path ="id" readonly="true"  />
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 180px;">
    				<fmt:message key="admin.ICAO.code" bundle="${op}"/>:<span style="color:red;">*</span>
    			</td>
    			<td>
    				<form:errors path="icaoCode" cssStyle="color:red;" element="div"/>
					<form:input path ="icaoCode" maxlength="4"/>
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 180px;">
    				<fmt:message key="admin.IATA.code" bundle="${op}"/>:<span style="color:red;">*</span>
    			</td>
    			<td>
    				<form:errors path="iataCode" cssStyle="color:red;" element="div"/>
					<form:input path ="iataCode" maxlength="3"/>
    			</td>
    		</tr>
    		
			<tr>
				<td colspan="2" >
					<ui:AirEntityName names="${airport.names}"/>
				</td>
			</tr>
    		<ui:itemButtons onCancelHref="${pageContext.request.contextPath}/admin/airports.html" addButton="true"/>	
    	</table>
    	<form:errors element="div"  cssClass="errors"/>
    </form:form>
</div>
<script>
	document.getElementById('addLang').setAttribute("data-bs-target", "#airportModal");
</script>
</ui:html>