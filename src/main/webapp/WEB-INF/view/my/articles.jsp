<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的文章</title>
</head>
<body>
<c:forEach items="${info.list }" var="article">
	<div class="media">
	  <img src="/pic/${article.picture }" style="height: 124px;width: 190px" class="mr-3 rounded" alt="...">
	  <div class="media-body">
	    <h5 class="mt-0">${article.title }</h5>
	    
	    <div style="float:right;padding-top: 60px" >
	    	<!-- Button trigger modal -->
			<button type="button" onclick="articleDetail(${article.id})" class="btn btn-link" data-toggle="modal" data-target="#exampleModalLong">
			  详情
			</button>
	    </div>
	    
	  </div>
	</div>
	<hr/>
</c:forEach>

<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>


<!-- Modal -->
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle"><span id="sp" ></span></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="content">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>


</body>
<script type="text/javascript">
function goPage(page){
	$("#center").load("/my/articles?page="+page);
}

function articleDetail(id){
	
	$.get("/my/articleDetail",{id:id},function(article){
		//清空原有的详情
		$("#content").empty();
		$("#sp").append(article.title);
		$("#content").append(article.content);
		
	})
}

</script>

</html>