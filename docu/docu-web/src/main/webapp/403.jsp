<%@ include file="/jsp-tiles/taglibs.jsp"%>
<html>
<head>
<%@ include file="/jsp-tiles/header.jsp"%>
</head>
<body class="skin-blue">

	<%@ include file="/jsp-tiles/navbar.jsp"%>

	<!-- Wrapper -->
	<div class="wrapper row-offcanvas row-offcanvas-left">

		<!-- Right side column. Contains the navbar and content of the page -->
		<aside class="right-side">
			<div class="error-page">
				<h2 class="headline text-info">403</h2>
				<div class="error-content" style="margin-top:40px">
					<h3>
						<i class="fa fa-warning text-yellow"></i> Forbidden
					</h3>
					<p>Vous n'etes pas autorisé 
					<br>a visualiser cette page.
					</p>
					
				</div>
				<!-- /.error-content -->
			</div>
			<!-- /.error-page -->
		</aside>

	</div>
	<!-- ./wrapper -->

	<!-- Footer -->
	<%@ include file="/jsp-tiles/footer.jsp"%>

</body>
</html>
