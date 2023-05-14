<!doctype html>
<html class="h-100">
  <head><%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String"%>
  <%@attribute name="locale" required="true" rtexprvalue="true" type="java.lang.String"%><%@attribute name="hasNavbar" required="true" rtexprvalue="true" type="java.lang.String"%>
    <meta charset="utf-8"/><%@tag language="java" pageEncoding="UTF-8"%><%@taglib prefix="ui" tagdir="/WEB-INF/tags"%>
    <meta name="viewport" content="width=device-width, initial-scale=1"/><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setLocale value="${locale}"/><fmt:setBundle basename="viewer" var="viewer_bundle" />
        <title>${title}<fmt:message key="title.arrivals" bundle="${viewer_bundle}" /></title>
        
		<link href="images/001_logo/fav.ico" rel="shortcut icon" type="image/x-icon" />
	    <style>
	      .bd-placeholder-img {
	        font-size: 1.125rem;
	        text-anchor: middle;
	        -webkit-user-select: none;
	        -moz-user-select: none;
	        user-select: none;
	      }	    
	     </style>
	      
	    <link href="css/bootstrap.css" type="text/css" rel="stylesheet"/>
	    <link href="css/viewer.css" type="text/css" rel="stylesheet"/>
	    <link href="css/fonts.css" type="text/css" rel="stylesheet"/>
	    <script type="text/javascript" src="script/jquery-3.6.4.js"></script>
 	    <script type="text/javascript" src="script/viewer.js"></script>
 	    <script src="script/anime.min.js"></script>
 	    <c:if test="${timeOutSource!= null}" >
 	    <script type="text/javascript">
	 	   $(document).ready(function() {
	 			runRotator("${timeOutSource}", ${timeOutValue});
	 			runClock();
	 		});
		</script>
		</c:if>

    </head>
  <body class="d-flex flex-column h-100" >
	<header class="text-white bd-blue-900  pb-1">
		<div class="container-fluid d-flex flex-wrap justify-content-center" id="body">
			<div
				class=" align-items-center mb-1 mb-lg-0 me-lg-auto text-decoration-none float-start Archangelsk">
				<div class="d-block fs-3">
					<div class="text-yellow-500 d-inline"><fmt:message key="airport.header.international" bundle="${viewer_bundle}" /></div>
					<div class="text-light d-block "><fmt:message key="airport.header.name" bundle="${viewer_bundle}" /></div>
				</div>
			</div>
			<ul class="nav nav-pills pt-2 d-none d-md-flex ps-2 ps-lg-0">
				<li class="nav-item"><img
					src="images/001_logo/white_plane001.png"
					class="float w-75 h-75 w-lg-100 h-lg-100"/></li>
				<li class="nav-item"><img src="images/001_logo/arrow_05.png"
					class="float w-75 h-75 w-lg-100 h-lg-100"/></li>
				<li class="nav-item"><img src="images/001_logo/arrow_04.png"
					class="float w-75 h-75 w-lg-100 h-lg-100"/></li>
				<li class="nav-item"><img src="images/001_logo/arrow_03.png"
					class="float w-75 h-75 w-lg-100 h-lg-100"/></li>
				<li class="nav-item"><img src="images/001_logo/arrow_02.png"
					class="float w-75 h-75 w-lg-100 h-lg-100"/></li>
				<li class="nav-item"><img src="images/001_logo/arrow_01.png"
					class="float w-75 h-75 w-lg-100 h-lg-100"/></li>
			</ul>
		</div>

	<c:if test="${hasNavbar == 'true' }">
		<ui:navbar title="${title}"/>
	</c:if>
	 
	</header>

	<main class="flex-shrink-0 fs-5">
      <jsp:doBody />
	</main>

	<footer class="footer mt-auto pt-3 bd-gray-300 bg-gradient text-blue-900 d-block" id="footer">
	  <div class="w-100 bd-blue-900" style="margin-top:-17px; padding-top:4px;"></div>

	  <div class="container Archangelsk">
		<span class="fs-4 text-blue-700"><fmt:message key="airport.footer" bundle="${viewer_bundle}" /></span>
	  </div>
	</footer>
  </body>
</html>