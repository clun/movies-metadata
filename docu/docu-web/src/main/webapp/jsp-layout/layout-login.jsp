<%@ include file="/jsp-tiles/taglibs.jsp"%>
<html>
<head>
  <%@ include file="/jsp-tiles/header.jsp" %>
  <decorator:head />
</head>
 <body class="lockscreen">
     
	 <!-- Wrapper -->
	 <div class="wrapper row-offcanvas row-offcanvas-left">
	
	  <!-- Right side column. Contains the navbar and content of the page -->
      <decorator:body/>
      
     </div><!-- ./wrapper -->
  	
  	<video autoplay loop id="bgvid">
		<source src="<c:url value='img/background/video4.mp4'/>" type="video/mp4" />
	</video>
	
     <!-- Footer -->
     <%@ include file="/jsp-tiles/footer.jsp"%>

</body>
</html>


	
