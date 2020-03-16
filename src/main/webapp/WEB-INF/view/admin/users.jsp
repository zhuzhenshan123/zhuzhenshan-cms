<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章审核</title>
</head>
<body>
<div class="form-group form-inline"  >
用户名:
	<input type="text" name="username" class="form-control form-control-sm" value="${user.username }" >&nbsp;&nbsp;
	<!-- 0：待审  1：审核通过  -1：审核未通过 -->
	用户状态:
	<select name="locked" class="form-control form-control-sm col-md-1" >
		<option value="0" ${user.locked==0?"selected":"" } >正常</option>
		<option value="1" ${user.locked==1?"selected":"" } >禁用</option>
		
	</select>
	&nbsp;&nbsp;
	<button type="button" onclick="query()" class="btn btn-warning btn-sm" >查询</button>
</div>
<table class="table table-bordered table-hover table-sm" style="text-align: center" >
	<tr>
		<td>序号</td>
		<td>用户名</td>
		<td>昵称</td>
		<td>性别</td>
		<td>生日</td>
		<td>注册时间</td>
		<td>用户状态</td>
	</tr>
	<c:forEach items="${info.list }" var="user" varStatus="i" >
		<tr>
			<td>${i.count }</td>
			<td>${user.username }</td>
			<td>${user.nickname }</td>
			<td>${user.gender==0?"女":"男" }</td>
			<td>${user.birthday }</td>
			<td><fmt:formatDate value="${user.created }" pattern="yyyy-MM-ss HH:mm:dd"/></td>
			
			<td>
				<c:if test="${user.locked==0 }">
					<button type="button" class="btn btn-info btn-sm" onclick="update(${user.id},this)">正常</button>
				</c:if>
				<c:if test="${user.locked==1 }">
					<button type="button" class="btn btn-info btn-sm" onclick="update(${user.id},this)">禁用</button>
				</c:if>
			</td>
			
		</tr>
	</c:forEach>
</table>



<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>

<script type="text/javascript">
function goPage(page){
	$("#center").load("/admin/articles?page="+page);
}

function query(){
	var locked=$("[name=locked]").val();
	var username=$("[name=username]").val();
	$("#center").load("/admin/users?locked="+locked+"&username="+username);
	
}
//改变热门文章
function update(id,obj){
	//0表示否  1 表示是
	var locked =$(obj).text()=="正常"?1:0;
	$.post("/admin/updateUser",{id:id,locked:locked},function(flag){
		if(flag){
			$(obj).text($(obj).text()=="正常"?"禁用":"正常");//改变内容
			$(obj).attr("class",locked==0?"btn btn-info btn-sm":"btn btn-danger btn-sm");
		}
	})
	
}

</script>
</body>

</html>