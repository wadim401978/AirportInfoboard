<%@taglib prefix="ui" tagdir="/WEB-INF/tags"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="ru" var="loc"/><fmt:setLocale value="${loc}"/><fmt:setBundle basename="viewer" var="viewer_bundle"/>
<c:set var="title" scope="page"><fmt:message key="title.arrivals" bundle="${viewer_bundle}" /></c:set>
<c:set value="true" var="hasNavbar" />
<ui:html locale="${loc}" title="${title}" hasNavbar="${hasNavbar}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <ui:table locale="${loc}" flights="${arrivals}"/>
</ui:html>
