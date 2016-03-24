<%@ include file="/jsp-tiles/taglibs.jsp"%>

<script type="text/javascript">
$( document ).ready(function() {
  CollapsibleLists.apply();
});
</script>

<aside class="left-side sidebar-offcanvas">
	<section class="sidebar">
		<ul class="sidebar-menu" style="font-weight: normal;">
		  <li>
		    <a href="<c:url value='/home.htm'/>" style="font-weight: normal; color: #3c8dbc">
		      <i class="fa fa-home"></i>Accueil
			</a>
		  </li>
		  <li>
		    <a href="<c:url value='/documentaire.htm'/>" style="font-weight: normal; color: #3c8dbc">
		      <i class="fa fa-film"></i>Documentaires
			</a>
		  </li>
		  <li>
		    <a href="<c:url value='/home.htm'/>" style="font-weight: normal; color: #3c8dbc">
		      <i class="fa fa-bell"></i>S&eacute;ries
			</a>
		  </li>
		</ul>
		
		<ul class="sidebar-menu" style="font-weight: normal;">
		  <li style="background-color: #85144b; color: white; padding-left: 15px; font-weight: normal;">
		    <i class="fa fa-laptop"></i>
			<fmt:message key="menu.admin.title" />
		  </li>
		  <li>
		    <a href="<c:url value='/dashboard.htm'/>" style="color: #85144b"> 
			  <i class="fa fa-dashboard"></i>
			  <fmt:message key="menu.admin.dashboard" />
		    </a>
		  </li>
		  <li>
		    <a href="<c:url value='/userAdministration.htm'/>" style="color: #85144b"">
		      <i class="fa fa-users"></i>
		      <fmt:message key="menu.admin.users" />
			</a>
		  </li>
	      </ul>
	      
	      <ul class="sidebar-menu" style="font-weight: normal;">
		  <li style="background-color: #607D8B; color: white; padding-left: 15px; font-weight: normal;">
		    <i class="fa fa-film"></i>
			&nbsp;Documentaires
		  </li>
		  
          <!-- Display documentaire categories -->
           <ul class="sidebar-menu collapsibleList">
          	<c:forEach var="entry" items="${menubean}">
         
            <li class="treeview">
            	<a href="#" style="font-weight: normal; color: #607D8B">
            		<img height="16" width="16" src="data:image/jpg;base64,${entry.key.icone}" />
            		<span>&nbsp;${entry.key.name}</span>
            		<i class="fa fa-angle-down pull-right"></i>
                </a>
                    <ul class="treeview-menu">
          				<c:forEach var="submenu" items="${entry.value}">
          					<li><a style="font-weight: normal;color:#555555" 
          						   href="/documentairesByGenre.htm?genre=${submenu.subgenre.id}">
                                   &nbsp;<img height="16" width="16" src="data:image/jpg;base64,${submenu.subgenre.icone}" />
                                   ${submenu.subgenre.name}
                            </a></li>
		  				</c:forEach>
		  			</ul>
		  	</li>
		  </c:forEach>
		  </ul>   
         
         </ul>
	</section>
	<!-- /.sidebar -->
	
	

</aside>
