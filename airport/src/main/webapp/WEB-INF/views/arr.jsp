<%@taglib prefix="ui" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="ru" var="loc"/><fmt:setLocale value="${lang.tag}"/><fmt:setBundle basename="viewer" var="viewer_bundle"/>
<c:set var="title" scope="page"><fmt:message key="title.arrivals" bundle="${viewer_bundle}" /></c:set>
<c:set value="true" var="hasNavbar" />
<ui:html locale="${lang.tag}" title="${title}" hasNavbar="${hasNavbar}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
    <ui:timeOutForm/>
		<div class="modal" id="testId" tabindex="-1"
			aria-labelledby="testIdLabel" aria-hidden="true">
			<div class="modal-fullscreen ">
				<div class="modal-content">
					<div class="modal-body" id="modalBody">${htmlField}</div>
				</div>${activeAnnotationsNum}
				<input type="hidden" id="activeAnnotationsNum" value="0">
			</div>
		</div>
    <ui:table locale="${lang.tag}" flights="${arrivals}"/>
</ui:html>
