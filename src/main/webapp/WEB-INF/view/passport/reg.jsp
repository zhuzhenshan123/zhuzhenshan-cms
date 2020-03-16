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
			<label for="repassword" >确认密码</label>
			<input id="repassword" type="text" class="form-control" name="repassword" >
		</div>
		<div class="form-group form-inline" >
			<label for="gender" >性别</label>
			<input id="gender" type="radio" class="form-check-input" name="gender" value="1" checked >男
			<input id="gender" type="radio" class="form-check-input" name="gender" value="0" >女
		</div>
		<div class="form-group" >
			<button type="submit" class="btn btn-info" >注册</button>
			<button type="reset" class="btn btn-warning" >重置</button>
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
					rangelength:[2,10],//用户名长度2-10
					remote:{
						type:"post",
						data:{
							username:function(){
								return $("#username").val();
							}
						},
						url:"/passport/checkName",
					},//校验用户名是否已经存在
				},
				password:{
					required:true,//密码不为空
					rangelength:[6,10],//密码长度6-10
				},
				repassword:{
					
					equalTo:"#password"
				},
				
				
			},//定义消息提示
			messages:{
				username:{
					required:"用户名不为空",//用户名不为空
					rangelength:"用户名长度2-10",//用户名长度2-10
					remote:"用户已经被注册",
				},
				password:{
					required:"密码不为空",//密码不为空
					rangelength:"密码长度6-10",//密码长度6-10
				},
				repassword:{
					
					equalTo:"两次密码不一致"
				},
				
				
			},submitHandler:function(flag){
				$.post("/passport/reg",$("#form1").serialize(),function(result){
					
					if(result.code==200){//注册成功，跳转到登录页面
						alert("恭喜注册成功");
						$("#title").html("<font color='red' >恭喜注册成功  请登录</font>");
						$("#passport").load("/passport/login");
					}else{
						alert(result.msg);
					}
					
				})
			}
			
			
			
		})
		
	})
	
	
</script>

</body>
</html>