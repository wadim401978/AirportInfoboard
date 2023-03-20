<%@taglib prefix="ui" tagdir="/WEB-INF/tags"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="ru" var="loc"/><fmt:setLocale value="${lang.langTag}"/><fmt:setBundle basename="viewer" var="viewer_bundle"/>
<c:set var="title" scope="page"><fmt:message key="title.arrivals" bundle="${viewer_bundle}" />.<fmt:message key="title.departures" bundle="${viewer_bundle}" /></c:set>
<c:set value="false" var="hasNavbar" />
<ui:html locale="${lang.langTag}" title="${title}" hasNavbar="${hasNavbar}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="p-4" id="div1">
	    <h2>${title}</h2>
	    ${text} ${lang}
	    <input type="button" onclick="test()" value="Hello, world!">
	    <ui:timeOutForm/>
    </div>
</ui:html>
