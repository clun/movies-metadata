<%@ include file="/jsp-tiles/taglibs.jsp"%>

<!--  test clu -->
<form id="formEdit" name="formEdit" class="form-horizontal" action="<c:url value='/updateDocumentaire'/>.htm" method="POST" >

 <!-- Modal -->
<div class="modal fade" id="modalEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    
  <!-- Header -->
  <div class="modal-header table-header" style="height:50px;">   
    <button class="close" data-dismiss="modal"><span style="color:#ffffff">x</span></button>   
    <h4 id="labelCreate" style="margin-top:0px;color:white"><i class="fa fa-tag"></i>&nbsp;Edit Documentaires</h4>
  </div>
  
  <div class="modal-body">
    <div class="control-group"> 
      <label class="control-label" for="title" style="color:#3c8dbc;font-style:normal;font-weight:normal">Titre :</label>
      <div class="controls">   
        <input type="text"   name="title" id="title" style="width:550px;height:30px;font-weight:bold"/>
      </div> 
    </div>
    
  </div>
  
  <div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i>&nbsp;Cancel</button>
    <button type="submit" class="btn btn-primary btn-success"><i class="fa fa-tag"></i>&nbsp;Validate</button>
  </div>

    </div> <!-- content -->
  </div> <!-- dialog-->
</div> <!--  modal tag -->



</form>

<script type="text/javascript" >
 $(document).on("click", ".openModalEdit", function () {
   $("#modalEdit #title").val($(this).data('uid'));
 });
 
</script>

