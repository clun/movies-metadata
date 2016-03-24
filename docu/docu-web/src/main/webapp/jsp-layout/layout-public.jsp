<%@ include file="/jsp-tiles/taglibs.jsp"%>
<html>
<head>
  <%@ include file="/jsp-tiles/header.jsp" %>
  <decorator:head />
</head>
 <body class="skin-blue">
 
     <!-- Bandeau avec logout, alert et profile -->
     <%@ include file="/jsp-tiles/navbar-public.jsp" %>
    
	  <!-- Right side column. Contains the navbar and content of the page -->
     <aside class="right-side" style="margin-left:0px">
       <decorator:body/>
     </aside>
      
     <%@ include file="/jsp-tiles/footer.jsp"%>
    
</body>
</html>


	
