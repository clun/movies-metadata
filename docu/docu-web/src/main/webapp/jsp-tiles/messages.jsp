<%@ include file="/jsp-tiles/taglibs.jsp"%>

<c:if test="${not empty successMessages}">
	<div class="alert alert-success alert-dismissable">
		<i class="fa fa-check"></i>
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		<c:forEach var="msg" items="${successMessages}">
			<c:out value="${msg}" escapeXml="false" />
			<br />
		</c:forEach>
	</div>
</c:if>
<c:if test="${not empty errors}">
	<div class="alert alert-danger alert-dismissable">
		<i class="fa fa-ban"></i>
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		<c:forEach var="msg" items="${errors}">
			<c:out value="${msg}" escapeXml="false" />
			<br />
		</c:forEach>
	</div>
</c:if>