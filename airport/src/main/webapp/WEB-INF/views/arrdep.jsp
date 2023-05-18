<%@taglib prefix="ui" tagdir="/WEB-INF/tags"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="ru" var="loc"/><fmt:setLocale value="${lang.tag}"/><fmt:setBundle basename="viewer" var="viewer_bundle"/>
<c:set var="title" scope="page"><fmt:message key="title.arrivals" bundle="${viewer_bundle}" />.<fmt:message key="title.departures" bundle="${viewer_bundle}" /></c:set>
<c:set value="false" var="hasNavbar" />
<ui:html locale="${lang.tag}" title="${title}" hasNavbar="${hasNavbar}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
	<ui:timeOutForm/>
		<div id="vot"
			style="background: yellow; width: 300px; height: 300px; font-size: 15pt; margin: auto; padding: 25px; border-radius: 20px;">
			${htmlField}</div>

		<ui:announcmentPopup/> 
	

</ui:html>