<%@ tag language="java" pageEncoding="UTF-8"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setBundle basename="operator" var="op"/>
<%@attribute name="destination_id" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="destination_name" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="popupHeader" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="popupId" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="items" required="true" rtexprvalue="true" type="java.util.List"%>
<div class="modal fade" id="${popupId}" tabindex="-1" aria-labelledby="${popupId}Label" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="${popupId}Label"><fmt:message key="${popupHeader}" bundle="${op}"/></h5>
        <c:set var="close_label"><fmt:message key="admin.close" bundle="${op}"/></c:set>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="${close_label}"></button>
      </div>
      <div class="modal-body">
					<table>
					<c:forEach items="${items}" var="item">
						<tr>
							<td>
								<div data-bs-dismiss="modal"  onclick="setChosenId('${destination_id}', '${destination_name}', '${destination_id}${item.id}', this)">
									<span>${item.presentation}</span>
								</div>
							</td>
							<td>
								<input type="hidden" id="${destination_id}${item.id}" value="${item.id}" readonly="readonly">
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

