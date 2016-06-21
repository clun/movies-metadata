<%@ include file="/jsp-tiles/taglibs.jsp"%>

<script type="text/javascript">
$( document ).ready(function() {
  CollapsibleLists.apply();
});
</script>

<aside class="left-side sidebar-offcanvas">
	<section class="sidebar">
		<ul class="sidebar-menu collapsibleList" style="font-weight: normal;">
			<ul class="sidebar-menu collapsibleList">
		   <li>
		    <a href="<c:url value='/documentaire.htm'/>" style="font-weight: normal; color: #3c8dbc">
		      <i class="fa fa-list"></i>&nbsp;Liste Globale
			</a>
		  </li>
		   <li>
		    <a href="<c:url value='/documentaireByGenre.htm?genre=23'/>" style="font-weight: normal; color: #3c8dbc">
		      <i class="fa fa-film"></i>&nbsp;Documentaires
			</a>
		  </li>
		  <li>
		    <a href="<c:url value='/home.htm'/>" style="font-weight: normal; color: #3c8dbc">
		      <i class="fa fa-video-camera"></i>&nbsp;S&eacute;ries
			</a>
		  </li>
		</ul>
			</ul>
		
		<ul class="sidebar-menu" style="font-weight: normal;">
			<ul class="sidebar-menu collapsibleList">
		  <li style="background-color: #85144b; color: white; padding-left: 15px; font-weight: normal;">
		    <i class="fa fa-laptop"></i>
			Perso
		  </li>
		  <li>
		    <a href="<c:url value='/home.htm'/>" style="font-weight: normal; color: #3c8dbc">
		      <i class="fa fa-eye"></i>A regarder
			</a>
		  </li>
		   <li>
		    <a href="<c:url value='/home.htm'/>" style="font-weight: normal; color: #3c8dbc">
		      <i class="fa fa-download"></i>Wish List
			</a>
		  </li>
	      </ul>
			</ul>
	      
		
		  
		  
		
		<ul class="sidebar-menu" style="font-weight: normal;">
			<ul class="sidebar-menu collapsibleList">
		  <li style="background-color: #003040; color: #AAAAAA; padding-left: 15px; font-weight: normal;">
		    <i class="fa fa-laptop"></i>
			<fmt:message key="menu.admin.title" />
		  </li>
		  <li>
		    <a href="<c:url value='/dashboard.htm'/>" style="color: #3c8dbc">
			  <i class="fa fa-dashboard"></i>
			  Analytics
		    </a>
		  </li>
		  <li>
		    <a href="<c:url value='/userAdministration.htm'/>" style="color: #3c8dbc"">
		      <i class="fa fa-users"></i>
		      <fmt:message key="menu.admin.users" />
			</a>
		  </li>
		   <li>
		    <a href="<c:url value='/userAdministration.htm'/>" style="color: #3c8dbc"">
		      <i class="fa fa-users"></i>
		      Batch Synchro
			</a>
		  </li>
	      </ul>
			</ul>
	      
	      
         </ul>
	</section>
	<!-- /.sidebar -->
	
	

</aside>
