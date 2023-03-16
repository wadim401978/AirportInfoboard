<!doctype html>
<html class="h-100">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<%@page contentType="text/html" pageEncoding="UTF-8"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="ru"/><fmt:setBundle basename="viewer" var="viewer_ru" />
        <title><fmt:message key="title.arrivals" bundle="${viewer_ru}" /></title>
        
		<link href="images/001_logo/fav.ico" rel="shortcut icon" type="image/x-icon" >
	    <style>
	      .bd-placeholder-img {
	        font-size: 1.125rem;
	        text-anchor: middle;
	        -webkit-user-select: none;
	        -moz-user-select: none;
	        user-select: none;
	      }	    
	     </style>
	      
	    <link href="css/bootstrap.css" type="text/css" rel="stylesheet">
	    <link href="css/viewer.css" type="text/css" rel="stylesheet">
	    <link href="css/fonts.css" type="text/css" rel="stylesheet">
        
    </head>
  <body class="d-flex flex-column h-100  ">
    
	<header class="text-white bd-blue-900  pb-1">
		<div class="container-fluid d-flex flex-wrap justify-content-center  ">
			<div
				class=" align-items-center mb-1 mb-lg-0 me-lg-auto text-decoration-none float-start Archangelsk">
				<div class="d-block fs-3">
					<div class="text-yellow-500 d-inline"><fmt:message key="airport.header.international" bundle="${viewer_ru}" /></div>
					<div class="text-light d-block "><fmt:message key="airport.header.name" bundle="${viewer_ru}" /></div>
				</div>
			</div>
			<ul class="nav nav-pills pt-2 d-none d-md-flex ps-2 ps-lg-0">
				<li class="nav-item"><img
					src="images/001_logo/white_plane001.png"
					class="float w-75 h-75 w-lg-100 h-lg-100"></li>
				<li class="nav-item"><img src="images/001_logo/arrow_05.png"
					class="float w-75 h-75 w-lg-100 h-lg-100"></li>
				<li class="nav-item"><img src="images/001_logo/arrow_04.png"
					class="float w-75 h-75 w-lg-100 h-lg-100"></li>
				<li class="nav-item"><img src="images/001_logo/arrow_03.png"
					class="float w-75 h-75 w-lg-100 h-lg-100"></li>
				<li class="nav-item"><img src="images/001_logo/arrow_02.png"
					class="float w-75 h-75 w-lg-100 h-lg-100"></li>
				<li class="nav-item"><img src="images/001_logo/arrow_01.png"
					class="float w-75 h-75 w-lg-100 h-lg-100"></li>
			</ul>
		</div>


	 <nav class="navbar navbar-expand-lg py-1 pt-lg-0 bd-blue-100 bg-gradient " aria-label="Vitebsk airport navbar" >
		<div class="container-fluid ">
		  <div class="navbar-collapse collapse Archangelsk pb-lg-1" >
			<ul class="navbar-nav me-auto mb-2 mb-md-0">
			  <li class="nav-item VAIR-bdi-blue-900 ">
				<span class="nav-link bd-blue-900 bd-blue-900 " >14:25:39</span>
			  </li> 
			  <li class="nav-item VAIR-bdi-blue-900">
				<span class="nav-link bd-blue-900 " >27.04.2023</span>
			  </li>
			  <li class="nav-item VAIR-bdi-blue-900">
				<span class="nav-link bd-blue-900  ps-2"><fmt:message key="title.arrivals" bundle="${viewer_ru}" /></span>
			  </li>
			</ul> 
		  </div>
		</div>
	  </nav>
	</header>

	<main class="flex-shrink-0 fs-5">
		<div class="" id="ru_table" >
	    	<table class="schedule Archangelsk">
	    		<thead>
	    			<tr>
		    			<td class="flight"><fmt:message key="table.number" bundle="${viewer_ru}" /></td>
		    			<td><fmt:message key="table.airport" bundle="${viewer_ru}" /></td>
		    			<td class="time"><fmt:message key="table.time" bundle="${viewer_ru}" /></td>
		    			<td><fmt:message key="table.airline" bundle="${viewer_ru}" /></td>
		    			<td class="status"><fmt:message key="table.status" bundle="${viewer_ru}" /></td>
	    			</tr>
	    		</thead>
	 			<tbody><!-- 14 rows -->
	 			<c:forEach items="${arrivals}" var="arr">
	    			<tr><fmt:formatDate pattern="HH:mm" value="${arr.scheduledDate}" var="statusTime"/>
		    			<td>
		    					<span class="yellow">${arr.flight.iataNumber}</span>
		    					<span class="white"> (${arr.flight.icaoNumber})</span>
		    			</td>
		    			<td>
		    					<span class="yellow">${arr.flight.airport.name} </span>
		    					<span class="white">(${arr.flight.airport.iataCode})</span>
		    			</td>
		    			<td>
		    				<span class="white"><fmt:formatDate pattern="dd.MM.YYYY" value="${arr.scheduledDate}"/></span>
		    				<span class="yellow"><fmt:formatDate pattern="HH:mm" value="${arr.scheduledDate}"/></span>
		    			</td>
		    			<td>
		    				<span class="yellow">${arr.flight.airline.name}</span>
		    			</td>
		    			<td>
			    				<span class="cyan">
			    				<fmt:message key="${arr.status.property}" bundle="${viewer_ru}">
			    				<fmt:param value="<span class='white'>${statusTime}</span>"/>
			    				</fmt:message>
			    				</span>
		    			</td>
	    			</tr>
	    			</c:forEach>
	 			</tbody>   	
	    	</table>

    </div>
	  
	</main>

	<footer class="footer mt-auto pt-3 bd-gray-300 bg-gradient text-blue-900 d-lg-block">
	  <div class="w-100 bd-blue-900" style="margin-top:-17px; padding-top:4px;"></div>

	  <div class="container Archangelsk">
		<span class="fs-4 text-blue-700"><fmt:message key="airport.footer" bundle="${viewer_ru}" /></span>
	  </div>
	</footer>
  </body>
</html>