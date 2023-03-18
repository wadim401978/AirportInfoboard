<%@taglib prefix="ui" tagdir="/WEB-INF/tags"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="ru" var="loc"/><fmt:setLocale value="${loc}"/><fmt:setBundle basename="viewer" var="viewer_bundle"/>
<c:set value="false" var="hasNavbar" />
<ui:html locale="${loc}" title="${title}" hasNavbar="${hasNavbar}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4">
	    <h2>${title}</h2>
	    ${text}
	    <c:forEach items="${blocks}" var="block">
	    <div>${block.html}<br></div>
	    </c:forEach>
    </div>
</ui:html>
