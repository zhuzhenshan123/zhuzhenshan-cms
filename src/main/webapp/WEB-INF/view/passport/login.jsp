<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
<link rel="stylesheet" type="text/css"
	href="/resource/bootstrap.min.css" />
<link rel="stylesheet"  type="text/css" href="/resource/index.css" >
<link rel="stylesheet"  type="text/css" href="/resource/jquery/screen.css" >
<script type="text/javascript" src="/resource/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/jquery.validate.js"></script>
<script type="text/javascript" src="/resource/bootstrap.min.js"></script>
</head>
<body>

<div class="container" >
	<span style="color: red" >${msg }</span>
	<form id="form1">
		<div class="form-group" >
			<label for="username" >用户名</label>
			<input id="username" type="text" class="form-control" name="username" >
		</div>
		<div class="form-group" >
			<label for="password" >密码</label>
			<input id="password" type="text" class="form-control" name="password" >
		</div>
		
		<div class="form-group" >
			<button type="submit" class="btn btn-info" >登陆</button>
			<button type="reset" class="btn btn-warning" >重置</button>
			<span id="msg" ></span>
		</div>
		
		
	</form>
</div>

<script type="text/javascript">
	$(function(){
		$("#form1").validate({
			//定义规则
			rules:{
				username:{
					required:true,//用户名不为空
					
				},password:{
					required:true,//密码不为空
					
				}
			},//定义消息提示
			messages:{
				username:{
					required:"用户名不为空",//用户名不为空
					
				},
				password:{
					required:"密码不为空",//密码不为空
					
				}
			},submitHandler:function(flag){
				$.post("/passport/login",$("#form1").serialize(),function(result){
					
					if(result.code==200){
						//$("#msg").html("<font color='red' >恭喜登录成功</font>");
						location.href="/";//返回首页
					}else{
						 $("#msg").html("<font color='red'>"+result.msg+"</font>")
						//$("#msg").html("<font color='red' >"+result.msg+"</font>");
					}
					
				})
			}
			
			
			
		})
		
	})
	
	
</script>

</body>
</html>