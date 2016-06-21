<%@ include file="/jsp-tiles/taglibs.jsp"%>

<script type="text/javascript">
$( document ).ready(function() {
  CollapsibleLists.apply();
});
</script>

<aside class="left-side sidebar-offcanvas">
	<section class="sidebar">
		<ul class="sidebar-menu" style="font-weight:normal;">
		 
          <!-- Display documentaire categories -->
           <ul class="sidebar-menu collapsibleList">
          	<c:forEach var="entry" items="${menubean}">
         
            <li class="treeview">
            	<a href="#" style="font-weight: normal; color:#F0F8FF">
            		<img height="16" width="16" src="data:image/jpg;base64,${entry.key.icone}" />
            		<span>&nbsp;${entry.key.name}</span>
            		<i class="fa fa-angle-down pull-right"></i>
                </a>
                    <ul class="treeview-menu">
          				<c:forEach var="submenu" items="${entry.value}">
          					<li style="background-color:#FFFFFF">
          					   <a style="font-weight:normal;color:black"
          					   	   onmouseover="this.style.color='#880000';"
          					   	   onmouseout="this.style.color='#000000';"
          						   href="/documentaireByGenre.htm?genre=${submenu.subgenre.id}">
                                   &nbsp;<img height="16" width="16" src="data:image/jpg;base64,${submenu.subgenre.icone}" />
                                   &nbsp;${submenu.subgenre.name}
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
