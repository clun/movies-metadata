<%@ include file="/jsp-tiles/taglibs.jsp"%>
<html>
<head>
	<%@ include file="/jsp-tiles/header.jsp"%>
</head>
<body class="skin-blue">

	<!-- Bandeau avec logout, alert et profile -->
	<%@ include file="/jsp-tiles/navbar-empty.jsp"%>

	<!-- Wrapper -->
	<div class="wrapper row-offcanvas row-offcanvas-left">

		<!-- Right side column. Contains the navbar and content of the page -->
		<aside class="right-side">
			<div class="error-page">
				<h2 class="headline text-info">404</h2>
				<div class="error-content">
					<h3>
						<i class="fa fa-warning text-yellow"></i> Oops! Page not found.
					</h3>
					<p>
						We could not find the page you were looking for. Meanwhile, you
						may <a href='../../index.html'>return to dashboard</a> or try
						using the search form.
					</p>
					
					</form>
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


