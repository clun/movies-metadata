<%@ include file="/jsp-tiles/taglibs.jsp"%>
<head>
  <title>
    <fmt:message key="home.title" />
  </title>
  <link href="<c:url value='css/datatables/dataTables.bootstrap.css'/>" rel="stylesheet" type="text/css" />
  <link href="<c:url value='css/daterangepicker/daterangepicker-bs3.css'/>" rel="stylesheet" type="text/css" />
</head>
<body>

<section class="content-header">
		<h1>
			<i class="fa fa-home"></i>
			Dashboard
			<small>&gt; Statistics</small>
		</h1>

		<ol class="breadcrumb" style="font-weight:normal">
			<li class="active">Version:</li> <a href="#">${versionNumber}</a>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content" style="font-weight: normal">
		<div class="row">
			<div class="col-md-12">

		<p/>
			<div class="col-lg-3 col-xs-6">
				<!-- small box -->
				<div class="small-box bg-aqua">
					<div class="inner">
						<h3>
							20
						</h3>
						<p>News Added Today</p>
					</div>
					<div class="icon">
						<i class="fa fa-rss-square"></i>
					</div>
					<a href="<c:url value='/newsDisplay.htm'/>" class="small-box-footer"> Watch Today News <i
						class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-3 col-xs-6">
				<!-- small box -->
				<div class="small-box bg-green">
					<div class="inner">
						<h3>
							10
							<sup style="font-size: 20px"></sup> <i>&nbsp;&nbsp;&nbsp;
							</i>
						</h3>
						<p>Distances not computed</p>
					</div>
					<div class="icon">
						<i class="fa fa-arrows-h"></i>
					</div>
					<div class="small-box-footer">&nbsp;</div>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-3 col-xs-6">
				<!-- small box -->
				<div class="small-box bg-yellow">
					<div class="inner">
						<h3>
							14
						</h3>
						<p>Users Registered</p>
					</div>
					<div class="icon">
						<i class="fa fa-users"></i>
					</div>
					<a href="#" class="small-box-footer"> List Users <i
						class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-3 col-xs-6">
				<!-- small box -->
				<div class="small-box bg-red">
					<div class="inner">
						<h3>
							12
						</h3>
						<p>Total News</p>
					</div>
					<div class="icon">
						<i class="fa fa-hdd-o"></i>
					</div>
					<div class="small-box-footer">&nbsp;</div>
				</div>
			</div>
			<!-- ./col -->
		</div>
		<!-- /.row -->

		</div>
		</div>
		
	</section>
	

<script src="<c:url value='js/AdminLTE/dashboard.js'/>" type="text/javascript"></script>



</body>