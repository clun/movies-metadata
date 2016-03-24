<%@ include file="/jsp-tiles/taglibs.jsp"%>
<head>
  <title>Liste des documentaires</title>
  <link href="<c:url value='css/datatables/dataTables.bootstrap.css'/>" rel="stylesheet" type="text/css" />
</head>
<body>

 <!--****************** -->
 <!--  TOP LEVEL BAR    -->
 <!--****************** -->
 
 <section class="content-header">
  <h1>
   <i class="fa fa-film"></i>Documentaires
   <small>&gt; Liste dynamique</small>
  </h1>
  <ol class="breadcrumb" style="font-weight:normal">
   <li class="active">Version:</li> <a href="#">${versionNumber}</a>
   </ol>
 </section>
	
 
 <section class="content">
  <div class="row">
   <div class="col-xs-12">
    <div class="box box-success">

     <div class="box-header">
       <h3 class="box-title">Liste des documentaires <small>Sans les series</small></h3>
     </div>
   
     <c:if test="${not empty successMessages}">
		<div class="alert alert-success alert-dismissable" style="margin:20px">
	      <i class="fa fa-check"></i>
	      <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	      <c:forEach var="msg" items="${successMessages}">
	        <c:out value="${msg}" escapeXml="false"/><br/>
	      </c:forEach>
	    </div>
      </c:if>
	  <c:if test="${not empty errors}">
		<div class="alert alert-danger alert-dismissable" style="margin:20px">
	      <i class="fa fa-check"></i>
	      <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	      <c:forEach var="msg" items="${errors}">
	        <c:out value="${msg}" escapeXml="false"/><br/>
	      </c:forEach>
	    </div>
	  </c:if>
	     
    	
     <div class="box-body table-responsive">
      <table id="example1" class="table table-bordered table-hover">
	   <thead>
		<tr> 
		 <th width="50%px">Titre</th>
		 <th width="20px">Ann&eacute;e</th>
		 <th width="20%">Genre</th>
		 <th width="10px">VU</th>
		 <th width="100px">OPS</th>
		</tr>
	   </thead>
	   <tbody>
        <c:forEach var="docu" items="${doculist}">
		 <tr>
		  <td><a href="#">${docu.titre}</a></td>
		  <td>${docu.annee}</td>
		  <td>${docu.genre}</td>
		  <td><c:choose>
           <c:when test="${docu.vu}"><i class="fa fa-circle"></i>&nbsp;ok</c:when>
           <c:otherwise><i class="fa fa-circle-o"></i>&nbsp;ko</c:otherwise>
          </c:choose></td>
		  <td>
										
                                <button class="openModalEdit  btn btn-primary" type="button"
                                  data-toggle="modal" data-target="#modalEdit" data-uid="${docu.uid}">
                                  <i class="fa fa-pencil" ></i>
                                </button>
                                
                                 <button class="btn btn-danger" type="button" onclick="javascript:alert('Shared by Mail : not yet ready')">
                                  <i class="fa fa-trash-o"> </i>
                                </button>
                                
										</td>
									</tr>
								</c:forEach>
							</tbody>
       </table>
    </div>
  	</div>
  </div>

	</section>
	
<!-- iCheck -->
<script src="<c:url value='js/plugins/datatables/jquery.dataTables.js'/>"    type="text/javascript"></script>
<script src="<c:url value='js/plugins/datatables/dataTables.bootstrap.js'/>" type="text/javascript"></script>

<script type="text/javascript">
            $(function() {
            	
            	// DataTable
            	$('#example1').dataTable();
            	
            	// DataTable
                $('#example2').dataTable({
                    "dom": '<"top"i>rt<"bottom"flp><"clear">',
                    "bPaginate": true,
                    "bLengthChange": false,
                    "bFilter": true,
                    "bSort": false,
                    "bInfo": false,
                    "bAutoWidth": false,
                });
            
            });
            
            
 </script>    

<%@ include file="modal-documentaire-edit.jsp"%>


</body>