<%@ tag language="java" pageEncoding="UTF-8"%><%@attribute name="title" rtexprvalue="true" type="java.lang.String"%>
	 <nav class="navbar navbar-expand-lg py-1 pt-lg-0 bd-blue-100 bg-gradient " aria-label="Vitebsk airport navbar" >
		<div class="container-fluid ">
		  <div class="navbar-collapse collapse Archangelsk pb-lg-1" >
			<ul class="navbar-nav me-auto mb-2 mb-md-0">
			  <li class="nav-item VAIR-bdi-blue-900 ">
				<span class="nav-link bd-blue-900 bd-blue-900 " id="clock" style="width:100px;">${time}</span>
			  </li> 
			  <li class="nav-item VAIR-bdi-blue-900">
				<span class="nav-link bd-blue-900 " >${date}</span>
			  </li>
			  <li class="nav-item VAIR-bdi-blue-900">
				<span class="nav-link bd-blue-900  ps-2">${title}</span>
			  </li>
			</ul> 
		  </div>
		</div>
	  </nav>
