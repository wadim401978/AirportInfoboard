<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
    <form action="${pageContext.request.contextPath}/admin/airlines.html" >
    	<table class="admin">
    		<tr>
    			<td style="width: 180px;"><fmt:message key="admin.id" bundle="${op}"/>:</td>
    			<td><input type="text" name="id" value="${airline.id}"  readonly="readonly"></td>
    		</tr>
    		<tr>
    			<td style="width: 180px;"><fmt:message key="admin.ICAO.code" bundle="${op}"/>:</td>
    			<td><input type="text" name="icao" value="${airline.icaoCode}" ></td>
    		</tr>
    		<tr>
    			<td style="width: 180px;"><fmt:message key="admin.IATA.code" bundle="${op}"/>:</td>
    			<td><input type="text" name="iata" value="${airline.iataCode}" ></td>
    		</tr>
			<c:forEach items="${airline.names}" var="entry">
			<tr>
				<td colspan="2">
    				<input type="hidden" name="langid" value="${entry.key.id}" >
    				<input type="text" name="lang" value="${entry.key.langTag}" class="langtag" disabled="disabled">
    				<input type="button" value="..." name="addLang${entry.key.id}" alt="select lang">
    				<input type="text" name="lang" value="${entry.value}" >
    				<input type="button" value="-" name="delLang${entry.key.id}" alt="del lang">
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="2">
    				<input type="hidden" name="langid" value="0" >
    				<input type="text" name="lang" value="" class="langtag" disabled="disabled">
    				<input type="button" value="..." name="addLang0" alt="select lang">
    				<input type="text" name="lang" value="" >
    				
				</td>
			</tr>
			
			<ui:itemButtons/>			
		</table>
    </form>
</div>
</ui:html>