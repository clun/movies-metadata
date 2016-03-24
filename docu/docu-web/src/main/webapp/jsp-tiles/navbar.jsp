<%@page import="fr.clunven.docu.web.domain.DocuUser"%>

<%@ include file="/jsp-tiles/taglibs.jsp"%>

<header class="header">

	<a href="#" class="logo" >
	   <img src="<c:url value='img/docus.png'/>" alt="" height="60px" style="padding: 10px; margin-top: -5px" />
	    &nbsp;<b>D O C U S</b>
	</a>

	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top" role="navigation">
		
		<div class="navbar-right">
        
			<ul class="nav navbar-nav">
			
				<!-- User Account: style can be found in dropdown.less -->
				<li class="dropdown user user-menu">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
					<span>
						<img style="width:40px;height:40px;margin-top:-10px;margin-left:20px" src="<c:url value='img/login/user.png'/>" class="img-circle" alt="User Image" />
						<i class="caret"></i>
					</span>
					</a>
					
					
					<ul class="dropdown-menu">
						<!-- User image -->
						<li class="user-header" style="background-image:url('img/bg-login.png');height:155px;">
						    <img src="<c:url value='img/login/user.png'/>" class="img-circle" alt="User Image" />
							<br/><span style="color:white;font-weight:normal">
							<sec:authentication property="principal.prenom" />&nbsp;
							<sec:authentication property="principal.nom" />
							<br> <small>email</small>
							</span>
						</li>
						<!-- Menu Body -->
						<li class="user-body" >
						 <a href="#" class="btn btn-default btn-flat" style="text-align:left"><i class="fa fa-user"></i>Profile</a>
						 <br/>
						 <a href="#" class="btn btn-default btn-flat" style="text-align:left"><i class="fa fa-tags"></i>Wish List</a>
						
						</li>
						 
						<!-- Menu Footer-->
						<li class="user-footer">
							<div class="pull-left">
								<a href="?lang=en"><img src="./img/icons/flag/english.png" style="height:25px"></a>
								<a href="?lang=fr"><img src="./img/icons/flag/france.png" style="height:25px"></a>
               
							</div>
							<div class="pull-right">
								<a href="<c:url value="logout.htm" />" class="btn btn-default btn-flat">Sign out</a>
							</div>
						</li>
					</ul></li>
			</ul>
		</div>
	</nav>
</header>