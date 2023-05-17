<!doctype html>
<html class="h-100">
  <head>
    <meta charset="utf-8"/><%@taglib prefix="ui" tagdir="/WEB-INF/tags"%>
    <meta name="viewport" content="width=device-width, initial-scale=1"/><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setLocale value="${locale}"/><fmt:setBundle basename="viewer" var="viewer_bundle" />
        <title>${title}<fmt:message key="title.arrivals" bundle="${viewer_bundle}" /></title>
        
	    <style>
	      .bd-placeholder-img {
	        font-size: 1.125rem;
	        text-anchor: middle;
	        -webkit-user-select: none;
	        -moz-user-select: none;
	        user-select: none;
	      }	    
	     </style>
	      
	    <link href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" rel="stylesheet"/>
	    <link href="${pageContext.request.contextPath}/css/viewer.css" type="text/css" rel="stylesheet"/>
	    <link href="${pageContext.request.contextPath}/css/fonts.css" type="text/css" rel="stylesheet"/>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-3.6.4.js"></script>
 	    <script type="text/javascript" src="${pageContext.request.contextPath}/script/viewer.js"></script>

    </head>
  <body class="d-flex flex-column h-100" >
	<header class="text-white bd-blue-900  pb-1"></header>

	<main class="flex-shrink-0 fs-5">
	
		<div class="modal-show" id="testId" tabindex="1"
			aria-labelledby="testIdLabel" aria-hidden="true">
			<div class="modal-fullscreen ">
				<div class="modal-content">
					<div class="modal-body" >${block.html}</div>
				</div>
			</div>
		</div>
	
	    <div class="p-5 fs-1">
			<form action="${timeOutSource}" method="post" id="timeOutForm">
				<input type="hidden" name="blockid" value="${block.id}"> 
				<input type="hidden" name="block" value="${block}">
				<!-- input type="submit" value="next block"-->
			</form>
		</div>
	</main>

	<footer class="footer mt-auto pt-3 bd-gray-300 bg-gradient text-blue-900 d-block" id="footer">
	</footer>
  </body>
</html>