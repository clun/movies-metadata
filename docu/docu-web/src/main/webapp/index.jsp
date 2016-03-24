<%@ include file="/jsp-tiles/taglibs.jsp"%>
<head>
<title><fmt:message key="home.title" /></title>
<link href="<c:url value='css/datatables/dataTables.bootstrap.css'/>"
  rel="stylesheet" type="text/css" />
<link
  href="<c:url value='css/daterangepicker/daterangepicker-bs3.css'/>"
  rel="stylesheet" type="text/css" />
<link href="<c:url value='css/css-menus.css'/>" rel="stylesheet"
  type="text/css" />
<script type="text/javascript" src="<c:url value='js/css-menus.js'/>"></script>
</head>
<body>

  <section class="content-header">
    <h1>
      <i class="fa fa-home"></i>&nbsp;&nbsp; Home
    </h1>
  </section>


  <!-- Main content -->
  
  <section class="content">
  
    <div class="row">
      <div class="col-xs-12">
        <div class="box box-success" style="background: url('img/blur-background04.jpg')">

          <!-- Box Header -->
          <div class="box-header">
            <h3 class="box-title" style="color: #008800">
              <i class="fa fa-flask"></i> &nbsp;Sciences</span>
            </h3>
          </div>

		  <!-- Ligne 1 -->
          <div class="row" style="padding-left:10px;padding-right:10px">

            <div class="col-md-2" >
              <a href="<c:url value='/start.htm?filter=source&value=scienceetavenir'/>" />
              <div class="box box-solid"
                style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                    <img src="./img/icons/flag/france.png" style="height: 15px"> Science et Avenir
                  </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                	<img src="./img/source/scienceetavenir.png"
                      style="height: 100px; width: 150px"></div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            <!-- ./col -->

            <div class="col-md-2" >
              <a
                href="<c:url value='/start.htm?filter=source&value=scienceetvie'/>" />
              <div class="box box-solid" style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                    <img src="./img/icons/flag/france.png" style="height: 15px"> Science et Vie
                  </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                	<img src="./img/source/scienceetvie.png"
                      style="height: 100px; width: 150px"></div>
              </div>
              <!-- /.box -->
              </a>
            </div>
            <!-- ./col -->

            <div class="col-md-2">
              <a href="<c:url value='/start.htm?filter=source&value=futura'/>" />
              <div class="box box-solid"
                style="height: 150px;">
                <div class="box-header">
                  <h3 class="box-title">
                  <img src="./img/icons/flag/france.png" style="height: 15px">  Futura Sciences
                  </h3>
                  </div>
                  <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                	<img src="./img/source/futura.png"
                      style="height: 100px; width: 150px"></div>
              </div>
              
              <!-- /.box -->
              </a>
            </div>
            <!-- ./col -->


            <div class="col-md-2">
              <a
                href="<c:url value='/start.htm?filter=source&value=pourlascience'/>" />
              <div class="box box-solid"
                style="height: 150px;">
                <div class="box-header">
                  <h3 class="box-title">
                    <img
                      src="./img/icons/flag/france.png" style="height: 15px"> Pour la science
                  </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                 <img src="./img/source/pourlascience.png"
                      style="height: 100px; width: 150px"></div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            <!-- ./col -->
            
             <div class="col-md-2">
              <a
                href="<c:url value='/start.htm?filter=source&value=journaldelascience'/>" />
              <div class="box box-solid"
                style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                     <img src="./img/icons/flag/france.png"
                      style="height: 15px"> Journal de la science
                  </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body"  style="font-weight: normal;text-align:center;margin-top:-10px"> 
               <img src="./img/source/journaldelascience.png"
                      style="height: 100px; width: 150px">
               </div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            <!-- ./col -->

          </div>

          <div class="row" style="padding-left:10px;padding-right:10px">
           
            <div class="col-md-2">
              <a href="<c:url value='/start.htm?filter=source&value=science'/>" />
              <div class="box box-solid"
                style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                    <img src="./img/icons/flag/english.png" style="height: 15px"> Science
                  </h3>
                </div>
                <!-- /.box-header -->
               <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                  <img src="./img/source/science.png" style="height: 100px; width: 150px">
                </div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            <!-- ./col -->


			<div class="col-md-2">
              <a href="<c:url value='/start.htm?filter=source&value=sciencedaily'/>" />
              <div class="box box-solid"
                style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                    <img src="./img/icons/flag/english.png" style="height: 15px"> Science Daily
                  </h3>
                </div>
                <!-- /.box-header -->
               <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                  <img src="./img/source/sciencedaily.png" style="height: 100px; width: 150px">
                </div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            
            
			<div class="col-md-2">
              <a href="<c:url value='/start.htm?filter=source&value=nationalgeographic'/>" />
              <div class="box box-solid"
                style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                    <img src="./img/source/nationalgeographic.png" style="height: 15px"> National Geographics
                  </h3>
                </div>
                <!-- /.box-header -->
               <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                  <img src="./img/source/nationalgeographic.png" style="height: 100px; width: 150px">
                </div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            
            <div class="col-md-2" >
              <a
                href="<c:url value='/start.htm?filter=source&value=journalcrns'/>" />
              <div class="box box-solid"
                style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                     <img src="./img/icons/flag/france.png" style="height: 15px"> Journal du CNRS
                  </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                  <img src="./img/source/journalcrns.png" style="height: 100px; width: 150px">
                </div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            <!-- ./col -->


          </div>
          
           <!-- Ligne 1 -->
          <div class="row" style="padding-left:10px;padding-right:10px">

            
        </div>


        <div class="row">
          <div class="col-xs-12">
            <div class="box box-info"
              style="background: url('img/blur-background04.jpg')">

              <!-- Box Header -->
              <div class="box-header">
                <h3 class="box-title" style="color: #0088AA">
                  <i class="fa fa-rss"></i> &nbsp;Information generales</span>
                </h3>
              </div>

              <div class="row" style="padding-left:10px;padding-right:10px">


			<div class="col-md-2" >
              <a
                href="<c:url value='/start.htm?filter=source&value=lemonde'/>" />
              <div class="box box-solid"
                style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                     <img src="./img/icons/flag/france.png" style="height: 15px"> Le Monde
                  </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                  <img src="./img/source/lemonde.png" style="height: 100px; width: 150px">
                </div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            <!-- ./col -->
            
   			<div class="col-md-2" >
              <a
                href="<c:url value='/start.htm?filter=source&value=lefigaro'/>" />
              <div class="box box-solid"
                style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                     <img src="./img/icons/flag/france.png" style="height: 15px"> Le Figaro
                  </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                  <img src="./img/source/lefigaro.png" style="height: 100px; width: 150px">
                </div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            <!-- ./col -->
            
            <div class="col-md-2" >
              <a
                href="<c:url value='/start.htm?filter=source&value=nytimes'/>" />
              <div class="box box-solid"
                style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                     <img src="./img/icons/flag/english.png" style="height: 15px"> New York Times
                  </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                  <img src="./img/source/nytimes.png" style="height: 100px; width: 150px">
                </div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            
             <div class="col-md-2" >
              <a
                href="<c:url value='/start.htm?filter=source&value=washingtonpost'/>" />
              <div class="box box-solid"
                style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                     <img src="./img/icons/flag/english.png" style="height: 15px"> Washington Post
                  </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                  <img src="./img/source/washingtonpost.png" style="height: 100px; width: 150px">
                </div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            <!-- ./col -->
            
            
             <div class="col-md-2" >
              <a
                href="<c:url value='/start.htm?filter=source&value=theguardian'/>" />
              <div class="box box-solid"
                style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                     <img src="./img/icons/flag/english.png" style="height: 15px"> The Guardian
                  </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                  <img src="./img/source/theguardian.png" style="height: 100px; width: 150px">
                </div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            <!-- ./col -->

              </div>

            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-xs-12">
            <div class="box box-danger"
              style="background: url('img/blur-background04.jpg')">

              <!-- Box Header -->
              <div class="box-header">
                <h3 class="box-title" style="color: #AA0000">
                  <i class="fa fa-rss"></i> &nbsp;Economie</span>
                </h3>
              </div>

              <div class="row" style="padding-left:10px;padding-right:10px">

 <div class="col-md-2" >
              <a
                href="<c:url value='/start.htm?filter=source&value=agefi'/>" />
              <div class="box box-solid"
                style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                     <img src="./img/icons/flag/france.png" style="height: 15px"> L'Agefi
                  </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                  <img src="./img/source/agefi.png" style="height: 100px; width: 150px">
                </div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            <!-- ./col -->
            
            
             <div class="col-md-2" >
              <a
                href="<c:url value='/start.htm?filter=source&value=edubourse'/>" />
              <div class="box box-solid"
                style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                     <img src="./img/icons/flag/france.png" style="height: 15px"> EduBourse
                  </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                  <img src="./img/source/edubourse.png" style="height: 100px; width: 150px">
                </div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            <!-- ./col -->
             
     <div class="col-md-2" >
              <a
                href="<c:url value='/start.htm?filter=source&value=lesechos'/>" />
              <div class="box box-solid"
                style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                     <img src="./img/icons/flag/france.png" style="height: 15px">  Les Echos
                  </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                  <img src="./img/source/lesechos.png" style="height: 100px; width: 150px">
                </div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            <!-- ./col -->

             <div class="col-md-2" >
              <a
                href="<c:url value='/start.htm?filter=source&value=cnbc'/>" />
              <div class="box box-solid"
                style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                     <img src="./img/icons/flag/english.png" style="height: 15px">  CNBC
                  </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                  <img src="./img/source/cnbc.png" style="height: 100px; width: 150px">
                </div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            <!-- ./col -->
            
              <div class="col-md-2" >
              <a
                href="<c:url value='/start.htm?filter=source&value=bloomberg'/>" />
              <div class="box box-solid"
                style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                     <img src="./img/icons/flag/english.png" style="height: 15px">  Bloomberg
                  </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                  <img src="./img/source/bloomberg.png" style="height: 100px; width: 150px">
                </div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            <!-- ./col -->
            
            
             

              </div>
              
               <div class="row" style="padding-left:10px;padding-right:10px">



               <div class="col-md-2" >
              <a
                href="<c:url value='/start.htm?filter=source&value=boursorama'/>" />
              <div class="box box-solid"
                style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                     <img src="./img/icons/flag/english.png" style="height: 15px">  Boursorama
                  </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                  <img src="./img/source/boursorama.png" style="height: 100px; width: 150px">
                </div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            <!-- ./col -->
            
                <div class="col-md-2" >
              <a
                href="<c:url value='/start.htm?filter=source&value=reuters'/>" />
              <div class="box box-solid"
                style="height: 150px">
                <div class="box-header">
                  <h3 class="box-title">
                     <img src="./img/icons/flag/english.png" style="height: 15px">  Reuters
                  </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="font-weight: normal;text-align:center;margin-top:-10px"> 
                  <img src="./img/source/reuters.png" style="height: 100px; width: 150px">
                </div>
                <!-- /.box-body -->
              </div>
              <!-- /.box -->
              </a>
            </div>
            <!-- ./col -->
           
              
                
                </div>
            </div>
          </div>
        </div>
  </section>
  <!-- /.content -->

</body>