<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>今日头条——管理员中心</title>
<link rel="stylesheet" type="text/css"
	href="/resource/bootstrap.min.css" />
<link rel="stylesheet"  type="text/css" href="/resource/index.css" >
<script type="text/javascript" src="/resource/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/popper.min.js"></script>
<script type="text/javascript" src="/resource/bootstrap.min.js"></script>
<script type="text/javascript">
function goPage(page){
	var channelId='${article.channelId}';//栏目ID
	var categoryId='${article.categoryId}';//分类ID
	var url="/?channelId="+channelId+"&page="+page;
	if(categoryId!=null){//如果分类 不为空，则传递分类的查询条件
		url=url+"&categoryId="+categoryId;
	}
	location.href=url;
}

//注册
function reg(){
	$("#title").append("用户注册");
	$("#passport").load("/passport/reg");
}
//登录
function login(){
	$("#title").append("用户登录");
	$("#passport").load("/passport/login");
}


</script>

</head>
<body>
	<div class="container-fluid">
		<!-- 头 -->
		<div class="row" >
			<div class="col-md-12" style="background-color: #222222;height: 34" >
				&nbsp;&nbsp;<font color="white" size="3px">下载APP</font>
				
				<!-- 注册 -->
				<div style="float: right" >
					<!-- 从session获取当前是否登陆，若登陆则不显示注册和登陆按钮 -->
					<c:if test="${sessionScope.user==null }">
						<button type="button" onclick="reg()" class="btn btn-link" data-toggle="modal" data-target="#exampleModal">
						  <font color="white" size="3px">注册</font>
						</button>
						<button type="button" onclick="login()" class="btn btn-link" data-toggle="modal" data-target="#exampleModal">
						  <font color="white" size="3px">登录</font>
						</button>
					</c:if>
					<c:if test="${sessionScope.user!=null }">
						
							<div class="btn-group dropleft">
							  <button type="button" class="btn btn-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							    <font color="white" size="3px">登录信息</font>
							  </button>
							  <div class="dropdown-menu">
							    <a class="dropdown-item" href="#">${sessionScope.user.username }</a>
    							<a class="dropdown-item" href="/my">个人中心</a>
    							<a class="dropdown-item" href="/passport/logout">注销</a>
							  </div>
							</div>
						
						
					</c:if>
				</div>
				
			</div>
			
			
		</div>
		<!-- 左 -->
		<div class="row">
			<div class="col-md-2" style="padding-top: 10px">
				<ul>
					<li style="margin-bottom: 10px" ><a href="/"> <img alt="" src="/resource/images/logo-index.png"
							style="width: 108px; height: 27px">

					</a></li>
					<!-- 热点文章 -->
					<li><a href="/?hot=1" class="channel-item  ${article.channelId==null?"active":"" }" >热点</a></li>
					<!-- 遍历所有栏目 -->
					<c:forEach items="${channels}" var="channel" varStatus="i">
						<li><a href="/?channelId=${channel.id }" class="channel-item ${article.channelId==channel.id?"active":"" }">${channel.name }</a></li>
					</c:forEach>
				</ul>
			</div>
			<!-- 中 -->
			<div class="col-md-7">
				<!-- 如果栏目id为null  -->
				<c:if test="${article.channelId==null }">
					<div style="margin: 5px 5px 5px 5px" >
						<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
						  <ol class="carousel-indicators">
						  	<c:forEach items="${slides }" var="slide" varStatus="i" >
						  		<li data-target="#carouselExampleCaptions" data-slide-to="${i.index }" class="active"></li>
						  	</c:forEach>
						  	
						  </ol>
						  <div class="carousel-inner">
						  	
						  	 <c:forEach items="${slides}" var="slide" varStatus="i">
								<div class="carousel-item ${i.index==0?"active":"" }">
									<img src="/pic/${slide.url }" class="d-block w-100 rounded" alt="..."
										style="width: 350px; height: 320px">
									<div class="carousel-caption d-none d-md-block">
										<h5>${slide.title }</h5>
									</div>
								</div>
								</c:forEach>
						  	
						  </div>
						  <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
						    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
						    <span class="sr-only">Previous</span>
						  </a>
						  <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
						    <span class="carousel-control-next-icon" aria-hidden="true"></span>
						    <span class="sr-only">Next</span>
						  </a>
						</div>
					</div>
					<hr>
				</c:if>
			
			
				<c:if test="${article.channelId!=null }">
				<!-- 显示栏目下的子分类 -->
				<div class="subchannel" >
					<ul>
						<li class="sub-item ${article.categoryId==null?"sub-selected":"" }" ><a href="/?channelId=${article.channelId }" >全部</a></li>
						<c:forEach items="${categorys }" var="category">
							<li class="sub-item ${article.categoryId==category.id?"sub-selected":"" }" " ><a href="/?channelId=${article.channelId }&categoryId=${category.id}" >${category.name }</a></li>
						</c:forEach>
					</ul>
				</div>
				</c:if>
				<!-- 分类下的文章 -->
				<div>
					<c:forEach items="${info.list }" var="article">
						<div class="media">
						  <img style="height: 124px;width: 190px" src="/pic/${article.picture }" class="mr-3 rounded" alt="...">
						  <div class="media-body">
						    <h5 class="mt-0"><a href="/articleDetail?id=${article.id }" target="_blank" >${article.title }</a></h5>
						    <p>${article.user.username } · ${article.hits }  浏览 · <fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd HH:mm:SS"/></p>
						  </div>
						</div>
						<hr>
					</c:forEach>
					<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
					
				</div>
				
				
			</div>
			<!-- 右 -->
			<div class="col-md-3">
			
			<div class="card" style="width: 18rem;margin-top: 10px">
				<div class="card-header" >最新文章</div>
				<div class="card-body">
			
				<c:forEach items="${lastArticles.list }" var="lastArticle" >
					<div class="media">
					  <img src="/pic/${lastArticle.picture }" class="mr-3 rounded" alt="..." style="width: 60px;height: 60px">
					  <div class="media-body">
					    <p><a href="/articleDetail?id=${lastArticle.id }" target="_blank">${lastArticle.title }</a></p>
					    
					  </div>
					</div>
					<hr>
				</c:forEach>
				
				
			</div>
		</div>
		</div>
		</div>
	</div>
	
	<!-- 注册/登录 -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"><span id="title"></span></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body"  id="passport">
           
      </div>
      
    </div>
  </div>
</div>
	
	
</body>
</html>