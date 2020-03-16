<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>今日头条——管理员中心</title>
<link rel="stylesheet" type="text/css"
	href="/resource/bootstrap.min.css" />
<script type="text/javascript" src="/resource/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/popper.min.js"></script>
<script type="text/javascript" src="/resource/bootstrap.min.js"></script>
<script type="text/javascript">

</script>

</head>
<body>
	<div class="container-fluid" >
		<!-- 头 -->
		<div class="row"  >
			
			<div class="col-md-12" style="background-color:#4169E1;height: 60px" >
				<img class="rounded" alt="" src="/resource/images/1.jpg" style="height: 55px;padding-top: 2px;padding-left: 3px " >
				<span style="color: white" >今日头条——管理员中心</span>
				
				<div class="btn-group dropleft" style="float: right">
					<button type="button" class="btn btn-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<font color="white" size="3px">登录信息</font>
					</button>
					<div class="dropdown-menu">
						 <a class="dropdown-item" href="#">${sessionScope.admin.username }</a>
    					<a class="dropdown-item" href="/my">个人中心</a>
    					<a class="dropdown-item" href="/passport/logout">注销</a>
					 </div>
				</div>
				
			</div>
				
		</div>
			
			
		<div class="row" style="padding-top: 10px;height: 680px" >
			<!-- 左侧导航菜单 -->
			<div class="col-md-2 rounded" style="background-color: #ccc;text-align: center;margin-left: 13px;padding-top: 10px"  >
				
				<nav class="nav flex-column">
				  <a class="list-group-item active" href="#" data="/admin/articles" >文章审核</a>
				  <a class="list-group-item" href="#" data="/admin/users">用户管理</a>
				  <a class="list-group-item" href="#">栏目管理</a>
				  <a class="list-group-item" href="#">分类管理</a>
				  <a class="list-group-item" href="#">系统设置</a>
				</nav>
				
			</div>
			<!-- 右侧具体内容 -->
			<div class="col-md-9" id="center" >
				
			</div>
		</div>
			
	</div>
<script type="text/javascript">
//点击样式切换

$(function(){
	//默认显示文章审核
	
	$("#center").load("/admin/articles");
	
	$("a").click(function(){
		var url = $(this).attr("data");
		
	 //去除样式
	    $("a").removeClass("active");
	   //让当前点击的li 添加选中样式
		 $(this).addClass("list-group-item active")
		//在中间区域显示url的内容
		$("#center").load(url);
	})
	
})



</script>
</body>
</html>