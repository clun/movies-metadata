<%@ include file="/jsp-tiles/taglibs.jsp"%>

<!--  test clu -->
<form id="formEdit" name="formEdit" class="form-horizontal" method="POST"
      enctype="multipart/form-data"
	  action="<c:url value='/documentaireUpdate.htm'/>"
	  accept-charset="UTF-8" >

 <!-- Modal -->
<div class="modal fade" id="modalEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    
    <!-- Header -->
    <div class="modal-header " style="height:50px;background-color:#222222">   
      <button class="close" data-dismiss="modal"><span style="color:#ffffff">x</span></button>   
      <h4 id="labelCreate" style="margin-top:0px;color:white"><i class="fa fa-film"></i>
      Edition <span id="title"></span> </h4>
    </div>
  
    <!-- Content -->
    <div class="modal-body" style="background-color:#F0F0F0">
  	
  	  <!-- Image -->
  	  <img id="imgcover" style="position:absolute;right:25px;top:20px;height:180px;width:130px;border:1px solid #CCCCCC;" src="#" />
  	
  	  <!-- Bloc notation -->
  	  <div class="btn-group" style="position:absolute;right:25px;top:210px;">
  	    <label class="control-label docuEditLabel" for="docuNote">Note:</label>
        <input  type="text" name="docuNote" id="docuNote" 
        	    class="docuEdit" style="width:50px;margin-left:0px;text-align:center" readonly="readonly"/>
        <button type="button"  class="btn btn-green dropdown-toggle" data-toggle="dropdown"
				style="color:white;background-color:#003040;float:right;height:20px;margin-right:10px;margin-top:5px">
         <span class="caret"></span>
         <span class="sr-only"><i class="icon-inbox icon-white" ></i></span>
        </button>
        <ul class="dropdown-menu docuEditCombo" role="menu" style="width:40px">
          <li><a href="javascript:$('#modalEdit #docuNote').val('1');">
  		  1<span class="glyphicon glyphicon-star" style="color:#DDDD00"></span>
  		  </a></li>
          <li><a href="javascript:$('#modalEdit #docuNote').val('2');">
          2<span class="glyphicon glyphicon-star" style="color:#DDDD00"></span>
          </a></li>
          <li><a href="javascript:$('#modalEdit #docuNote').val('3');">
          3<span class="glyphicon glyphicon-star" style="color:#DDDD00"></span>
          </a></li>
          <li><a href="javascript:$('#modalEdit #docuNote').val('4');">
          4<span class="glyphicon glyphicon-star" style="color:#DDDD00"></span>
          </a></li>
          <li><a href="javascript:$('#modalEdit #docuNote').val('5');">
          5<span class="glyphicon glyphicon-star" style="color:#DDDD00"></span>
          </a></li>
        </ul>
       </div>
       
       <!-- Bloc notation -->
  	  <div class="btn-group" style="position:absolute;right:25px;top:240px;">
  	    <label class="control-label docuEditLabel" for="quality">Qual:</label>
        <input  type="text" name="quality" id="quality"
        	    class="docuEdit" style="width:50px;margin-left:0px;text-align:center" readonly="readonly"/>
        <button type="button" 
        	    class="btn btn-green dropdown-toggle" 
         	     data-toggle="dropdown" 
         	     style="color:white;background-color:#003040;float:right;height:20px;margin-right:10px;margin-top:5px">
         <span class="caret"></span>
         <span class="sr-only"><i class="icon-inbox icon-white" ></i></span>
        </button>
        <ul class="dropdown-menu docuEditCombo" role="menu" style="width:40px">
          <li><a href="javascript:$('#modalEdit #quality').val('1');">
  		  1<span class="glyphicon glyphicon-star" style="color:#DDDD00"></span>
  		  </a></li>
          <li><a href="javascript:$('#modalEdit #quality').val('2');">
          2<span class="glyphicon glyphicon-star" style="color:#DDDD00"></span>
          </a></li>
          <li><a href="javascript:$('#modalEdit #quality').val('3');">
          3<span class="glyphicon glyphicon-star" style="color:#DDDD00"></span>
          </a></li>
          <li><a href="javascript:$('#modalEdit #quality').val('4');">
          4<span class="glyphicon glyphicon-star" style="color:#DDDD00"></span>
          </a></li>
          <li><a href="javascript:$('#modalEdit #quality').val('5');">
          5<span class="glyphicon glyphicon-star" style="color:#DDDD00"></span>
          </a></li>
        </ul>
       </div>
       
       <!-- Bloc vu -->
  	  <div class="btn-group" style="position:absolute;right:25px;top:270px;">
  	    <label class="control-label docuEditLabel" for="vu">Vus:</label>
        <input  type="text" name="vu" id="vu"
        	    class="docuEdit" style="width:50px;margin-left:0px;text-align:center" readonly="readonly"/>
        <button type="button" 
        	    class="btn btn-green dropdown-toggle" 
         	     data-toggle="dropdown" 
         	     style="color:white;background-color:#003040;float:right;height:20px;margin-right:10px;margin-top:5px">
         <span class="caret"></span>
         <span class="sr-only"><i class="icon-inbox icon-white" ></i></span>
        </button>
        <ul class="dropdown-menu docuEditCombo" role="menu" style="width:40px">
          <li><a href="javascript:$('#modalEdit #vu').val('OUI');">OUI</span></a></li>
          <li><a href="javascript:$('#modalEdit #vu').val('NON');">NON</span></a></li>
        </ul>
       </div>

		<input type="hidden" id="uid"   name="uid" />
		<input type="hidden" id="genre" name="genre" />
       
       <!-- Liste des champ -->	
  	   <table>
  	     <tr>
  		 <td><label class="control-label docuEditLabel" for="coverfile">Jaquette</label></td>
      	 <td><input class="docuEditLabel" type="file" id="coverfile" name="coverfile" style="padding-left:20px"></td>
  		</tr>
  		<tr>
  		 <td><label class="control-label docuEditLabel" for="titre">Titre</label></td>
      	 <td><input type="text" name="titre" id="titre" class="docuEdit" /></td>
  		</tr>
  		<tr>
  		 <td><label class="control-label docuEditLabel" for="titreOriginal">Titre Original</label></td>
      	 <td><input type="text" name="titreOriginal" id="titreOriginal" class="docuEdit" /></td>
  		</tr>
  		<tr>
	  	  <td><label class="control-label docuEditLabel" for="docuPays" >Pays:</label></td>
	      <td>
	        <div class="btn-group">
      	      <input type="text" name="docuPays" id="docuPays" class="docuEdit" style="width:250px;" readonly="readonly" required/>
            	<button type="button" class="btn btn-green dropdown-toggle" 
                    data-toggle="dropdown"  style="color:white;background-color:#003040;float:right;height:20px;margin-right:10px;margin-top:5px">
              	<span class="caret"></span>
              	<span class="sr-only"><i class="icon-inbox icon-white" ></i></span>
            	</button>
            	<ul class="dropdown-menu" role="menu" class="docuEditCombo">
            	 <li><a style="font-size:12px" href="javascript:$('#modalEdit #docuPays').val('----');">Inconnu</a></li>
            	 <c:forEach var="refPays" items="${refPays}">
              	  <li><a style="font-size:12px" href="javascript:$('#modalEdit #docuPays').val('${refPays.nom}');">
              	  <img src="data:image/jpg;base64,${refPays.drapeau}" style="height:18px;width:20px" >&nbsp;${refPays.nom}</a></li>
              	 </c:forEach>
              	</ul>
          		</div>
      		</td>
  		 </tr>
  		<tr>
  		 <td><label class="control-label docuEditLabel" for="duree">Duree</label></td>
      	 <td><input type="text" name="duree" id="duree" class="docuEdit" /></td>
  		</tr> 
  		<tr>
  		 <td><label class="control-label docuEditLabel" for="annee">Annee</label></td>
      	 <td><input type="text" name="annee" id="annee" class="docuEdit" /></td>
  		</tr>  
 		<tr>
  		  <td><label class="control-label docuEditLabel" for="description" >Description:</label></td>
      	  <td>
      	      <textarea id="description" name="description" cols="30" rows="10" 
      	  		style="font-size:12px;width:300px;margin-left:20px;margin-top:5px;"></textarea>
      	  </td>
  		</tr>
  		</table>
  		
  		<br/><table>
  		 <tr>
  		   <td colspan="2" style="font-weight:bold;font-size:12px;color:#003040"><i class="fa fa-video-camera"></i>&nbsp; Fichier :</td>
  		 </tr>
	  	 <tr>
	  	  <td><label class="control-label docuEditLabel" for="langue" >Langue:</label></td>
	      <td>
	        <div class="btn-group">
      	      <input type="text" name="docuLangue" id="docuLangue" class="docuEdit" style="width:250px;" readonly="readonly" required/>
      	      <input type="hidden" name="docuLangue2" id="docuLangue2" />
            	<button type="button" class="btn btn-green dropdown-toggle" 
                    data-toggle="dropdown"  style="color:white;background-color:#003040;float:right;height:20px;margin-right:10px;margin-top:5px">
              	<span class="caret"></span>
              	<span class="sr-only"><i class="icon-inbox icon-white" ></i></span>
            	</button>
            	<ul class="dropdown-menu" role="menu" class="docuEditCombo">
            	<c:forEach var="refLang" items="${refLangue}">
              		<li><a style="font-size:12px" href="javascript:$('#modalEdit #docuLangue').val('${refLang.nom}');$('#modalEdit #docuLangue2').val('${refLang.code}')">
              		<img src="data:image/jpg;base64,${refLang.icone}" style="height:18px;width:20px" >&nbsp;${refLang.nom}</a></li>
              	</c:forEach>
              		
            	</ul>
          		</div> <!-- btn-group --> 
          
      		</td>
  		 </tr>
  		 
  		  <tr>
	  	  <td><label class="control-label docuEditLabel" for="docuSousTitre" >Sous Titres:</label></td>
	      <td>
	        <div class="btn-group">
      	      <input type="text" name="docuSousTitre" id="docuSousTitre" class="docuEdit" style="width:250px;" readonly="readonly" required/>
      	      <input type="hidden" name="docuSousTitre2" id="docuSousTitre2" />
            	
            	<button type="button" class="btn btn-green dropdown-toggle" 
                    data-toggle="dropdown"  style="color:white;background-color:#003040;float:right;height:20px;margin-right:10px;margin-top:5px">
              	<span class="caret"></span>
              	<span class="sr-only"><i class="icon-inbox icon-white" ></i></span>
            	</button>
            	<ul class="dropdown-menu" role="menu" class="docuEditCombo">
            	 <li><a style="font-size:12px" href="javascript:$('#modalEdit #docuSousTitre').val('');$('#modalEdit #docuSousTitre2').val('')">Aucun</a></li>
            	 <c:forEach var="refLang" items="${refLangue}">
              	  <li><a style="font-size:12px" href="javascript:$('#modalEdit #docuSousTitre').val('${refLang.nom}');$('#modalEdit #docuSousTitre2').val('${refLang.code}')">
              	  <img src="data:image/jpg;base64,${refLang.icone}" style="height:18px;width:20px" >&nbsp;${refLang.nom}</a></li>
              	 </c:forEach>
              	</ul>
          		</div> <!-- btn-group --> 
          
      		</td>
  		 </tr>
  		 <tr>
	  	  <td><label class="control-label docuEditLabel" for="docuFormat" >Format:</label></td>
	      <td>
	        <div class="btn-group">
      	      <input type="text" name="docuFormat" id="docuFormat" class="docuEdit" style="width:250px;" readonly="readonly" required/>
      	      <input type="hidden" name="docuFormat2" id="docuFormat2" />
            	<button type="button" class="btn btn-green dropdown-toggle" 
                    data-toggle="dropdown"  style="color:white;background-color:#003040;float:right;height:20px;margin-right:10px;margin-top:5px">
              	<span class="caret"></span>
              	<span class="sr-only"><i class="icon-inbox icon-white" ></i></span>
            	</button>
            	<ul class="dropdown-menu" role="menu" class="docuEditCombo">
            	 <li><a style="font-size:12px" href="javascript:$('#modalEdit #docuFormat').val('----');$('#modalEdit #docuFormat2').val('')">Inconnu</a></li>
            	 <c:forEach var="refFormat" items="${refFormat}">
              	  <li><a style="font-size:12px" href="javascript:$('#modalEdit #docuFormat').val('${refFormat.nom}');$('#modalEdit #docuFormat2').val('${refFormat.extension}')">
              	  <img src="data:image/jpg;base64,${refFormat.icone}" style="height:18px;width:20px" >&nbsp;${refFormat.nom}</a></li>
              	 </c:forEach>
              	</ul>
          		</div>
      		</td>
  		 </tr>
  		 
  		  <tr>
  		  <td><label class="control-label docuEditLabel" for="taille" >Taille:</label></td>
      	  <td><input type="text" name="taille" id="taille" class="docuEdit" value=""/></td>
  		 </tr>
  		 
  		  <tr>
  		  <td><label class="control-label docuEditLabel" for="resolution" >Resolution:</label></td>
      	  <td><input type="text" name="resolution" id="resolution" class="docuEdit" value="640x480"/></td>
  		 </tr>
  		</table>
    </div> 
  
  <div class="modal-footer" style="background-color:#F0F0F0;margin-top:-20px">
    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i>&nbsp;Cancel</button>
    <button type="submit" class="btn btn-primary"><i class="fa fa-tag"></i>&nbsp;Validate</button>
  </div>

    </div> <!-- content -->
  </div> <!-- dialog-->
</div> <!--  modal tag -->



</form>

<script type="text/javascript" >
function callAPIDocu(uid) {
	  $.get('/api/docu/' + uid,
		function(docu) {
            $("#modalEdit #uid").val(docu.id);
            $("#modalEdit #genre").val(docu.genreId);
		  	$("#modalEdit #title").html(docu.titre + ' ' + docu.id);
			$("#modalEdit #titre").val(docu.titre);
			$("#modalEdit #titreOriginal").val(docu.titreOriginal);
			$("#modalEdit #annee").val(docu.annee);
			$("#modalEdit #docuPays").val(docu.pays);
			$("#modalEdit #duree").val(docu.duree);
			$("#modalEdit #description").val(docu.description);
			$("#modalEdit #imgcover").attr('src', 'data:image/jpg;base64,' + docu.image);

			$("#modalEdit #docuNote").val(docu.note);
			$("#modalEdit #quality").val(docu.qualite);
			$("#modalEdit #vu").val('NON');
			if (docu.vu) {
				$("#modalEdit #vu").val('OUI');
			}

			$("#modalEdit #docuLangue").val(docu.langue);
			$("#modalEdit #docuLangue2").val(docu.langue);

			$("#modalEdit #docuSousTitre").val(docu.soustitre);
			$("#modalEdit #docuSousTitre2").val(docu.soustitre);

			$("#modalEdit #taille").val(docu.taille);
			$("#modalEdit #docuFormat").val(docu.format);
			$("#modalEdit #docuFormat2").val(docu.format);
			$("#modalEdit #resolution").val(docu.resolution);


		});
	}
	
	
 $(document).on("click", ".openModalEdit", function () {
   $("#modalEdit #title").val($(this).data('uid'));
   callAPIDocu($(this).data('uid'));
   
 });
 
</script>

