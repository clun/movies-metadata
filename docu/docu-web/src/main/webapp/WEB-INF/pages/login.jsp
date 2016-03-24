<%@ include file="/jsp-tiles/taglibs.jsp"%>

<div class="form-box" id="login-box"
	 style="-moz-box-shadow: 8px 8px 12px #aaa; -webkit-box-shadow: 8px 8px 12px #aaa; box-shadow: 8px 8px 12px #555;">

  <div class="table-header" >
    <img src="<c:url value='img/docus.png'/>" alt="" height="60px" style="padding: 10px; margin-top: -5px" />
    &nbsp;<b>Base de documentaires</a>
  </div>

  <sec:authorize access="isAnonymous()">
   <form action="<c:url value='/j_security_check.htm'/>" method="post">
    <div class="body bg-gray">
      <br/>
      <span style="color:black;font-weight:normal">
	    <i class="fa fa-key"></i> &nbsp;Saisissez vos identifiants :
	  </span>
	  <div class="form-group">
					<input type="text" id="j_username" name="j_username"
						class="form-control"
						placeholder="<fmt:message key="login.field.username" />"
						style="border:1px solid #CCCCCC;
                        	 background: url(<c:url value='/img/login/user.png'/>) no-repeat;
                        	 background-color: #FFFFFF;
                        	 width:320px;
                        	 padding-left:50px;
                        	 color:#888888" />
				</div>
				<div class="form-group">
					<input type="password" id="j_password" name="j_password"
						class="form-control"
						placeholder="<fmt:message key="login.field.password" />"
						style="border:1px solid #CCCCCC;
                        	 background: url(<c:url value='/img/login/password.png'/>) no-repeat;
                        	 background-color: #FFFFFF;
                        	 width:320px;
                        	 padding-left:50px;
                        	 color:#888888" />
				</div>

				<c:if test="${param.error != null}">
					<center>
						<span style="color: red">
						<i class="fa fa-ban"></i> Utilisateur non authoris&eacute;</span>
					</center>
				</c:if>


				<div class="text-center">
					&nbsp;
					<%@ include file="/jsp-tiles/messages.jsp"%>
				</div>
				</div>

				<div class="table-header">
					<center>
						<button type="submit" class="btn btn-primary"
							style="border:1px solid white;margin-bottom: 10px; margin-top: 10px; width: 100px">
							<i class="fa fa-sign-in"></i>&nbsp;Entrer&nbsp;
						</button>
					</center>
				</div>
		</form>
	</sec:authorize>

</div>
</div>
<!-- /.box -->


</div>
