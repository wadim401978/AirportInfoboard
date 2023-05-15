<%@ tag language="java" pageEncoding="UTF-8"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<%@attribute name="popupHeader" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="popupId" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="items" required="true" rtexprvalue="true" type="java.util.List"%>
<div class="modal fade" id="${popupId}" tabindex="-1" aria-labelledby="${popupId}Label" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content" >
      <div class="modal-header">
        <h5 class="modal-title" id="${popupId}Label"><fmt:message key="${popupHeader}" bundle="${op}"/></h5>
        <c:set var="close_label"><fmt:message key="admin.close" bundle="${op}"/></c:set>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="${close_label}"></button>
      </div>
      <div class="modal-body"><c:set var="alert_label"><fmt:message key="admin.selected.language.already.exists" bundle="${op}"/></c:set>
					<table><c:set var="delete_label"><fmt:message key="admin.del.row" bundle="${op}"/></c:set>
					<c:forEach items="${items}" var="lang">
						<tr>
							<td >
								<div data-bs-dismiss="modal"   
									id="dataBsDismiss${lang.id}" 
									onclick="addLangRow(this, '${delete_label}','${alert_label}');">
									<input type="hidden" id="selectId${lang.id}" value="${lang.id}" readonly="readonly">
									<span>${lang.presentation}</span>
								</div>
							</td>
							<td>
								<input type="hidden" id="selectId${lang.id}" value="${lang.id}" readonly="readonly">
								<input type="hidden" id="selectTag${lang.id}" value="${lang.tag}" readonly="readonly">
								<input type="hidden" id="selectName${lang.id}" value="${lang.name}" readonly="readonly">
							</td>
						</tr>
					</c:forEach>
					</table>
      </div>
      <div class="modal-footer">
        <input type="button" value="${close_label}"  data-bs-dismiss="modal">
      </div>
    </div>
  </div>
</div>

