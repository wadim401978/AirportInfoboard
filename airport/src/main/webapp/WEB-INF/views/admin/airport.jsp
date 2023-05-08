<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <form:form action="${pageContext.request.contextPath}/admin/airport/save.html" method="POST" modelAttribute="airport">
    	<table class="admin">
    		<tr>
    			<td style="width: 180px;"><fmt:message key="admin.id" bundle="${op}"/>:</td>
    			<td><input type="text" name="id" value="${airport.id}" readonly="readonly"></td>
    		</tr>
    		<tr>
    			<td style="width: 180px;"><fmt:message key="admin.ICAO.code" bundle="${op}"/>:</td>
    			<td>
    				<form:errors path="icaoCode" cssStyle="color:red;" element="div"/>
					<form:input path ="icaoCode" />
    			</td>
    		</tr>
    		<tr>
    			<td style="width: 180px;"><fmt:message key="admin.IATA.code" bundle="${op}"/>:</td>
    			<td>
    				<form:errors path="iataCode" cssStyle="color:red;" element="div"/>
					<form:input path ="iataCode" />
    			</td>
    		</tr>
    		
			<c:forEach items="${airport.names}" var="entry">
			<tr>
				<td colspan="2">
    				<input type="hidden" name="langid${entry.key.id}" value="${entry.key.id}" >
    				<input type="text" name="lang${entry.key.id}" value="${entry.key.tag}" class="langtag" disabled="disabled">
    				<input type="button" value="..." name="addLang${entry.key.id}" alt="select lang">
    				<input type="text" name="langV${entry.key.id}" value="${entry.value}" >
    				<input type="button" value="-" name="delLang${entry.key.id}" alt="del lang">
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="2">
    				<input type="hidden" name="langid0" value="0" >
    				<input type="text" name="lang0" value="" class="langtag" disabled="disabled">
    				<input type="button" value="..." name="addLang0" alt="select lang">
    				<input type="text" name="langV0" value="" >
    				
				</td>
			</tr>
    		
    		<ui:itemButtons onCancelHref="${pageContext.request.contextPath}/admin/airports.html"/>	
    	</table>
    </form:form>
</div>
</ui:html>