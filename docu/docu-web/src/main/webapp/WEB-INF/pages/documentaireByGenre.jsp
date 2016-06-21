<%@ include file="/jsp-tiles/taglibs.jsp"%>
<head>
  <title>Liste des documentaires</title>
  <link href="<c:url value='css/datatables/dataTables.bootstrap.css'/>" rel="stylesheet" type="text/css" />
</head>
<body>

<!--
  <script src="<c:url value='js/jquery/jquery-2.0.2.min.js'/>" type="text/javascript"></script>
  <script src="<c:url value='js/bootstrap.min.js'/>" type="text/javascript"></script>
  <script src="<c:url value='js/AdminLTE/app.js'/>" type="text/javascript"></script>
-->

 <!--****************** -->
 <!--  TOP LEVEL BAR    -->
 <!--****************** -->
 
  <section class="content">

 	<h3 style="color:#B0C4DE;margin-top:-10px;margin-left:10px">
   		Documentaire &gt; <img height="32" width="32" src="data:image/jpg;base64,${genre.icone}" />&nbsp;${genre.name}
   		<hr>
  	</h3>
     
     <!-- Premiere ligne -->
     <div class="row" style="padding:10px">
     
       <c:forEach var="docu" items="${listdocubean}" varStatus="docuLoop">
        
        <div class="col-md-2">
       	 <div class="box" style="margin-right:10px;height:260px;width:160px;border:1px solid #333333;background-color:#222222">
       	 
          <div class="box-header">
           <h5 style="color:#F5F5DC;font-size:12px;text-align:center">${docu.titre}</h5>
          </div>
         
          <div class="box-body" style="text-align:center;">
          	<a class="openModalRead"
               data-toggle="modal" data-target="#modalRead" data-uid="${docu.id}">
           		<img style="position:absolute;top:60px;left:15px;height:180px;width:130px;border:1px solid #CCCCCC;margin-top:-10px" 
           			 src="data:image/jpg;base64,${docu.image}" />
           	</a>
           <p style="position:absolute;bottom:-5px;left:70px;color:#EEEEFF;margin-top:5px"><small>(${docu.annee})</small></p>
          </div>
        
         </div>
        </div>
       
       </c:forEach>
      
     <!-- On ferme la derniere ligne --> 
     </div>        
	  
    </div>
  	</div>
  </div>

 </section>
 
<%@ include file="modal-documentaire-read.jsp"%>


<%@ include file="modal-documentaire-edit.jsp"%>

</body>