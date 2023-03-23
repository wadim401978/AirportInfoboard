<%@taglib prefix="ui" tagdir="/WEB-INF/tags/admin"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ui:html title="${title}" ><%@page contentType="text/html" pageEncoding="UTF-8"%>
		<ul>
			<li><div class="p-3">References/Справочники
				<ul>
					<li><a href="admin/langs.html">Languages/Языки воспроизведения</a></li>
					<li><a href="admin/airports.html">Airports/Аэропорты</a></li>
					<li><a href="admin/airlines.html">Airlines/Авиакомпании</a></li>
					<li><a href="admin/flights.html">Flights/Рейсы(номера)</a></li>
				</ul></div>
			</li>
			
			<li><div class="p-3">Tables/Таблицы
				<ul>
					<li><a href="admin/arrivals.html">Arrivals/Прилёты</a></li>
					<li><a href="admin/departures.html">Departures/Вылеты</a></li>
					<li><a href="admin/announcments.html">Announcments/Объявления</a></li>
				</ul></div>
			</li>
		</ul>
</ui:html>