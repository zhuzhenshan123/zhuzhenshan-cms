<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的收藏</title>
</head>
<body>
<c:forEach items="${info.list }" var="collect">
	<div class="media">
	  
	  <div class="media-body">
	    <h5 class="mt-0">${collect.text }</h5>
	    <h4 class="mt-0" ><fmt:formatDate value="${collect.created }" pattern="yyyy-MM-dd HH:mm:SS" /></h4>
	    
	  </div>
	</div>
	<hr/>
</c:forEach>

<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>




</body>
<script type="text/javascript">
function goPage(page){
	$("#center").load("/my/articles?page="+page);
}


</script>

</html>