<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<!-- Spring tags -->
<%@ taglib prefix="sec" 			uri="http://www.springframework.org/security/tags" 		  %>
<%@ taglib prefix="spring"			uri="http://www.springframework.org/tags" 				  %>
<%@ taglib prefix="form" 			uri="http://www.springframework.org/tags/form" 			  %>

<!-- JSTL -->
<%@ taglib prefix="c"				uri="http://java.sun.com/jsp/jstl/core" 				  %>
<%@ taglib prefix="fmt"				uri="http://java.sun.com/jsp/jstl/fmt" 				  	  %>
<%@ taglib prefix="fn" 				uri="http://java.sun.com/jsp/jstl/functions" 			  %>

<!-- SiteMesh -->
<%@ taglib prefix="decorator"		uri="http://www.opensymphony.com/sitemesh/decorator" 	  %>
<%@ taglib prefix="page"			uri="http://www.opensymphony.com/sitemesh/page" 		  %>

<%@ taglib prefix="v"               uri="http://www.springmodules.org/tags/commons-validator"  %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="datePattern"><fmt:message key="date.format"/></c:set>