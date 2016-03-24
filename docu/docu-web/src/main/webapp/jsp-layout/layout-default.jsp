<%@ include file="/jsp-tiles/taglibs.jsp"%>
<html>
<head>
  <%@ include file="/jsp-tiles/header.jsp" %>
  <decorator:head />
</head>
 <body class="skin-blue">
      
	 <!-- Bandeau avec logout, alert et profile -->
	 <%@ include file="/jsp-tiles/navbar.jsp" %>
	 
	 <!-- Wrapper -->
	 <div class="wrapper row-offcanvas row-offcanvas-left">
       
	 <!-- Bandeau avec logout, alert et profile -->
	 <%@ include file="/jsp-tiles/menu.jsp" %>
	
	  <!-- Right side column. Contains the navbar and content of the page -->
      <aside class="right-side">
       <decorator:body/>
      </aside>
      
     </div><!-- ./wrapper -->
  	
     <!-- Footer -->
     <%@ include file="/jsp-tiles/footer.jsp"%>

</body>
</html>


	
