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
文章标题:
	<input type="text" name="title" class="form-control form-control-sm" value="${article.title }" >&nbsp;&nbsp;
	<!-- 0：待审  1：审核通过  -1：审核未通过 -->
	审核状态:
	<select name="status" class="form-control form-control-sm col-md-1" >
		<option value="0" ${article.status==0?"selected":"" } >待审</option>
		<option value="1" ${article.status==1?"selected":"" } >已审</option>
		<option value="-1" ${article.status==-1?"selected":"" } >驳回</option>
	</select>
	&nbsp;&nbsp;
	<button type="button" onclick="query()" class="btn btn-warning btn-sm" >查询</button>
</div>
<table class="table table-bordered table-hover table-sm" style="text-align: center" >
	<tr>
		<td>序号</td>
		<td>标题</td>
		<td>作者</td>
		<td>发布时间</td>
		<td>所属栏目</td>
		<td>所属分类</td>
		<td>是否热门</td>
		<td>点击量</td>
		<td>审核状态</td>
		<td>其他</td>
	</tr>
	<c:forEach items="${info.list }" var="article" varStatus="i" >
		<tr>
			<td>${i.count }</td>
			<td>${article.title }</td>
			<td>${article.user.username }</td>
			<td><fmt:formatDate value="${article.created }" pattern="yyyy-MM-ss HH:mm:dd"/></td>
			<td>${article.channel.name }</td>
			<td>${article.category.name }</td>
			<td>
				<c:if test="${article.hot==0 }">
					<button type="button" class="btn btn-info btn-sm" onclick="update(${article.id},this)" >否</button>
				</c:if>
				<c:if test="${article.hot==1 }">
					<button type="button" class="btn btn-danger btn-sm" onclick="update(${article.id},this)" >是</button>
				</c:if>
			</td>
			<td>${article.hits }</td>
			<td>
				<!-- 0：待审  1：审核通过  -1：审核未通过 -->
				<c:if test="${article.status==0 }">
					待审
				</c:if>
				<c:if test="${article.status==1 }">
					通过
				</c:if>
				<c:if test="${article.status==-1 }">
					驳回
				</c:if>
			</td>
			<td><button type="button" onclick="articleDetail(${article.id})" class="btn btn-link" data-toggle="modal" data-target="#exampleModalLong">
			  详情
			</button>
		</tr>
	</c:forEach>
	
	
</table>

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
      	<span id="sp1" ></span>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" onclick="updateStatus(1)" >通过</button>
        <button type="button" class="btn btn-danger" onclick="updateStatus(-1)" >驳回</button>
      </div>
    </div>
  </div>
</div>




<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>

<script type="text/javascript">
function goPage(page){
	$("#center").load("/admin/articles?page="+page);
}

function query(){
	var status=$("[name=status]").val();
	var title=$("[name=title]").val();
	$("#center").load("/admin/articles?status="+status+"&title="+title);
	
}
//改变热门文章
function update(id,obj){
	//0表示否  1 表示是
	var hot =$(obj).text()=="是"?0:1;
	$.post("/admin/update",{id:id,hot:hot},function(flag){
		if(flag){
			$(obj).text($(obj).text()=="是"?"否":"是");//改变内容
			$(obj).attr("class",hot==0?"btn btn-info btn-sm":"btn btn-danger btn-sm");
		}
	})
	
}
var articleId;
//文章详情
function articleDetail(id){
	articleId=id;
	$("#sp1").empty();//清空上一次的文章审核状态
	$.get("/admin/articleDetail",{id:id},function(article){
		//清空原有的详情
		$("#content").empty();
		$("#sp").append(article.title);
		$("#content").append(article.content);
		
	})
}
//判读是否审核通过
function updateStatus(status){
	$.post("/admin/update",{id:articleId,status:status},function(flag){
		if(flag){
			$("#sp1").append("操作成功");
		}else{
			$("#sp1").append("操作失败");
		}
	})
}

</script>
</body>

</html>