<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${article.title }</title>
<link rel="stylesheet" type="text/css"
	href="/resource/bootstrap.min.css" />
<link rel="stylesheet"  type="text/css" href="/resource/index.css" >
<script type="text/javascript" src="/resource/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/bootstrap.min.js"></script>
</head>
<body>

<div class="container-fluid">
		<!-- 头 -->
		<div class="row" >
			<div class="col-md-12" style="background-color: #222222;height: 34" >
				&nbsp;&nbsp;<font color="white" size="3px">下载APP</font>
			</div>
		</div>
		
		<div class="row" style="margin-top: 10px">
			<div class="col-md-1" ></div>
			<div class="col-md-7" >
				<h2>${article.title }</h2>
				<p>
					${article.user.username }
					<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd HH:mm:SS"/>
				</p>
				
				<c:if test="${collect!=null}">
					<button type="button" class="btn btn-link" onclick="deleteCollect()" >★&nbsp; 取消收藏</button>
				</c:if>
				<c:if test="${collect==null}">
					<button type="button" class="btn btn-link" onclick="collect()" >☆ &nbsp;  未收藏</button>
				</c:if>
				
				<hr>
				${article.content }
				
				<hr>
				<!-- 文章评论 -->
				<c:if test="${user!=null }">
				<div>
					<h5>文章评论:</h5>
					<textarea rows="8" cols="20" style="width:753px " name="content" ></textarea>
					<button type="button" onclick="addComment()" class="btn btn-info" >提交评论</button>
				</div>
				</c:if>
				<div>
					<c:forEach items="${info.list }" var="comment">
						<h5>${comment.user.username } <fmt:formatDate value="${comment.created }" pattern="yyyy-MM-dd HH:mm:SS" /></h5>
						
						${comment.content }
					</c:forEach>
				</div>
				
			</div>
			
			<div class="col-md-4">
				
				<div class="card" style="width: 18rem;margin-top: 10px">
				<div class="card-header" >评论排行榜</div>
				<div class="card-body">
			
					<c:forEach items="${info2.list }" var="article" varStatus="i">
						${i.count } ${article.title }<br>
						
					</c:forEach>
				
					</div>
				</div>
				
				
			</div>
		
		
		</div>

</div>
<script type="text/javascript">

//取消收藏
function deleteCollect(){
	var id='${collect.id}';
	$.post("/deleteCollect",{id:id},function(flag){
		if(flag){
			alert("取消收藏成功");
			window.location.reload();
		}else{
			alert("取消收藏失败，需登录后才能取消收藏");
		}
	})
}

//收藏
function collect(){
	//文章标题
	var title = '${article.title}';
	//alert(title);
	//文章url
	var url=window.location.href;
	$.post("/collect",{text:title,url:url},function(flag){
		if(flag){
			alert("收藏成功");
			window.location.reload();
		}else{
			alert("收藏失败，需登录后才能收藏");
		}
	})
	
}

//添加评论
function addComment(){
	var articleId='${article.id}';
	var content=$("[name=content]").val();
	$.post("addComment",{articleId:articleId,content:content},function(flag){
		if(flag){
			alert("评论成功");
			window.location.reload();
		}else{
			alert("评论失败，需登录后才能评论");
		}
	})
	
}
</script>
</body>
</html>