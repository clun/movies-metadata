<%@ include file="/jsp-tiles/taglibs.jsp"%>

<div class="modal fade" id="modalRead" tabindex="-1" role="dialog" aria-hidden="true">
 <div class="modal-dialog">
  <div class="modal-content">
    
  <!-- Header -->
  <div class="modal-header " style="height:50px;background-color:#222222">   
    <button class="close" data-dismiss="modal"><span style="color:#ffffff">x</span></button>   
    <h4 id="labelCreate" style="margin-top:0px;color:white"><i class="fa fa-film"></i>
    <span id="title"></span></h4>
  </div>
  
  <!-- Content -->
  <div class="modal-body" style="background-color:#F0F0F0">
  			
  	<table style="border:0px">
  	 <tr>
  	  <td style="width:150px">
  		<img id="imgcover" style="position:absolute;top:20px;height:180px;width:130px;border:1px solid #CCCCCC;" />
  		 <div style="position:absolute;top:210px;left:40px">
  			<span class="glyphicon glyphicon-star" style="color:#DDDD00"></span>
  		 	<span class="glyphicon glyphicon-star" style="color:#DDDD00"></span>
  		 	<span class="glyphicon glyphicon-star" style="color:#DDDD00"></span>
  		 	<span class="glyphicon glyphicon-star"></span>
  		 	<span class="glyphicon glyphicon-star"></span>
  		 </div>
  	</td>
  	<td style="padding-top:0px">
  		<p style="font-weight:bold;font-size:12px"/>Titre Original : 
  			<span id="titreOriginal" style="color: #3c8dbc"></span>
  			( <img id="imgpays" src="#" style="height:10px;width:16px;" /> )
  		</p>
  		
  		<p style="font-weight:bold;font-size:12px"/>Dur&eacute;e : 
  			<span id="duree" style="color: #3c8dbc"></span>
  		</p>
  		
  		<p style="font-weight:bold;font-size:12px"/>
  			Langue : <img id="imglang" src="#" style="height:10px;width:16px;" />
  			<span id="soustitres">&nbsp;</span>
  		</p>
  		
  		<p style="font-weight:bold;font-size:12px"/>
  			Fichier : 
  			<span id="ficformat" style="color:#3c8dbc;font-weight:normal"></span>, 
  			<span id="fictaille" style="color:#3c8dbc;font-weight:normal"></span>, 
  			<span id="ficresolution" style="color:#3c8dbc;font-weight:normal"></span>
  		</p>
  		
  		<p><span id="description" style="text-align:justify;font-size:12px"></span></p>
  		
  	</td>
  	</tr>
  	
  	</table>
  	<br/>&nbsp;
  	
  	<a id="link2Edit" 
  		class="openModalEdit btn btn-inverse" 
  		style="position:absolute;top:10px;right:10px"
  		data-dismiss="modal" 
  		data-toggle="modal" 
  		data-target="#modalEdit"><i class="fa fa-pencil"></i>&nbsp;</a>
  	
  </div>
  
    </div> <!-- content -->
  </div> <!-- dialog-->
</div> <!--  modal tag -->

</form>

<script type="text/javascript" >

function callAPIDocuEdit(uid) {
  $.get('/api/docu/' + uid,
	function(docu) {
		$("#modalRead #title").html('&nbsp;' + docu.titre + '&nbsp;(' + docu.annee + ')');
		$("#modalRead #titreOriginal").html(docu.titreOriginal);
		$("#modalRead #duree").html(docu.duree + ' min');
		$("#modalRead #description").html(docu.description);
		$("#modalRead #ficformat").html(docu.format);
		$("#modalRead #fictaille").html(docu.taille + " mo");
		$("#modalRead #ficresolution").html(docu.resolution);
		$("#modalRead #imgcover").attr('src', 'data:image/jpg;base64,' + docu.image);
		$("#modalRead #imgpays").attr('src', 'data:image/jpg;base64,' + docu.paysIcone);
		$("#modalRead #imglang").attr('src', 'data:image/jpg;base64,' + docu.langueIcone);
		$("#modalRead #link2Edit").attr('data-uid', docu.id);
	});
}

 $(document).on("click", ".openModalRead", function () {
   $("#modalRead #title").val($(this).data('uid'));
   callAPIDocuEdit($(this).data('uid'));
 });

</script>

