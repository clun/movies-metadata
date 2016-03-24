<%@page import="com.cannys.gui.domain.CannysUser"%>
<%@ include file="/jsp-tiles/taglibs.jsp"%>
<header class="header">

	<a href="#" class="logo" >
	   <img src="<c:url value='img/cannys-logo1.png'/>" alt="" height="40px" style="margin-top:-5px"/>
	   &nbsp;<b>C A N N Y S</a>

	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top" role="navigation">
		
		<div class="navbar-right">
		
			<ul class="nav navbar-nav" style="position:absolute;right:10px;top:50px">
		      
              <li class="dropdown" style="float:right;margin-top:0px;">
               <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <c:if test="${pageContext.response.locale == 'en'}">
                 <img src="./img/icons/flag/english.png" style="height:20px"> 
                </c:if>
                <c:if test="${pageContext.response.locale == 'fr'}">
                 <img src="./img/icons/flag/france.png" style="height:20px"> 
                </c:if>
                <c:if test="${pageContext.response.locale == 'de'}">
                 <img src="./img/icons/flag/germany.png" style="height:20px"> 
                </c:if>
                <b class="caret"></b>
              </a>
              <ul class="dropdown-menu" >
               <li><a href="?lang=en"><img src="./img/icons/flag/english.png" style="height:15px"></a></li>
               <li><a href="?lang=fr"><img src="./img/icons/flag/france.png" style="height:15px"></a></li>
              </ul>
             </li>
			</ul>
			
			 <div style="float:right;margin-top:10px;margin-right:20px;z-index:6;">
        <a href="<c:url value='/signup.htm' />"  class="btn btn-primary"style="border:1px solid white">
        <i class="fa fa-edit"></i>&nbsp
        <fmt:message key="public.button.register" />&nbsp;</a>
        &nbsp;&nbsp;
         <a href="<c:url value='/login.htm'/>"  class="btn btn-primary" style="border:1px solid white"
        ><i class="fa fa-sign-in"></i>&nbsp
        <fmt:message key="public.button.signin" />&nbsp;</a>
        </div>
			
		</div>
	</nav>
</header>
