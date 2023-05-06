<%@ tag language="java" pageEncoding="UTF-8"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="item_id" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="item_name" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="items" required="true" rtexprvalue="true" type="java.util.List"%>
<div class="modal fade" id="airEntityModal" tabindex="-1" aria-labelledby="airEntityModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="airEntityModalLabel">Заголовок модального окна</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
      </div>
      <div class="modal-body">
					<table>
					<c:forEach items="${items}" var="item">
						<tr>
							<td>
								<div data-bs-dismiss="modal"  onclick="setChosenId('${item_id}', '${item_name}', '${item_id}${item.id}', this)">
									<span>${item.iataNumber}-${item.airport.name}</span>
								</div>
							</td>
							<td>
								<input type="hidden" id="${item_id}${item.id}" value="${item.id}" readonly="readonly">
							</td>
						</tr>
					</c:forEach>
					</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
      </div>
    </div>
  </div>
</div>

