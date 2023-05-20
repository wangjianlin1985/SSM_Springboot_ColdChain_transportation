<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%> 
<%@ page import="com.chengxusheji.po.ChillCar" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    List<ChillCar> chillCarList = (List<ChillCar>)request.getAttribute("chillCarList");
    int currentPage =  (Integer)request.getAttribute("currentPage"); //当前页
    int totalPage =   (Integer)request.getAttribute("totalPage");  //一共多少页
    int recordNumber =   (Integer)request.getAttribute("recordNumber");  //一共多少记录
    String carNo = (String)request.getAttribute("carNo"); //车辆编号查询关键字
    String carType = (String)request.getAttribute("carType"); //车辆种类查询关键字
    String pinpai = (String)request.getAttribute("pinpai"); //品牌查询关键字
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
<title>冷藏车查询</title>
<link href="<%=basePath %>plugins/bootstrap.css" rel="stylesheet">
<link href="<%=basePath %>plugins/bootstrap-dashen.css" rel="stylesheet">
<link href="<%=basePath %>plugins/font-awesome.css" rel="stylesheet">
<link href="<%=basePath %>plugins/animate.css" rel="stylesheet">
<link href="<%=basePath %>plugins/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
</head>
<body style="margin-top:70px;">
<div class="container">
<jsp:include page="../header.jsp"></jsp:include>
	<div class="col-md-9 wow fadeInLeft">
		<ul class="breadcrumb">
  			<li><a href="<%=basePath %>index.jsp">首页</a></li>
  			<li><a href="<%=basePath %>ChillCar/frontlist">冷藏车信息列表</a></li>
  			<li class="active">查询结果显示</li>
  			<a class="pull-right" href="<%=basePath %>ChillCar/chillCar_frontAdd.jsp" style="display:none;">添加冷藏车</a>
		</ul>
		<div class="row">
			<%
				/*计算起始序号*/
				int startIndex = (currentPage -1) * 5;
				/*遍历记录*/
				for(int i=0;i<chillCarList.size();i++) {
            		int currentIndex = startIndex + i + 1; //当前记录的序号
            		ChillCar chillCar = chillCarList.get(i); //获取到冷藏车对象
            		String clearLeft = "";
            		if(i%4 == 0) clearLeft = "style=\"clear:left;\"";
			%>
			<div class="col-md-3 bottom15" <%=clearLeft %>>
			  <a  href="<%=basePath  %>ChillCar/<%=chillCar.getCarNo() %>/frontshow"><img class="img-responsive" src="<%=basePath%><%=chillCar.getCarPhoto()%>" /></a>
			     <div class="showFields">
			     	<div class="field">
	            		车辆编号:<%=chillCar.getCarNo() %>
			     	</div>
			     	<div class="field">
	            		车辆种类:<%=chillCar.getCarType() %>
			     	</div>
			     	<div class="field">
	            		品牌:<%=chillCar.getPinpai() %>
			     	</div>
			     	<div class="field">
	            		排量:<%=chillCar.getPl() %>
			     	</div>
			     	<div class="field">
	            		当前温度:<%=chillCar.getDqwd() %>°C
			     	</div>
			        <a class="btn btn-primary top5" href="<%=basePath %>ChillCar/<%=chillCar.getCarNo() %>/frontshow">详情</a>
			        <a class="btn btn-primary top5" onclick="chillCarEdit('<%=chillCar.getCarNo() %>');" style="display:none;">修改</a>
			        <a class="btn btn-primary top5" onclick="chillCarDelete('<%=chillCar.getCarNo() %>');" style="display:none;">删除</a>
			     </div>
			</div>
			<%  } %>

			<div class="row">
				<div class="col-md-12">
					<nav class="pull-left">
						<ul class="pagination">
							<li><a href="#" onclick="GoToPage(<%=currentPage-1 %>,<%=totalPage %>);" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
							<%
								int startPage = currentPage - 5;
								int endPage = currentPage + 5;
								if(startPage < 1) startPage=1;
								if(endPage > totalPage) endPage = totalPage;
								for(int i=startPage;i<=endPage;i++) {
							%>
							<li class="<%= currentPage==i?"active":"" %>"><a href="#"  onclick="GoToPage(<%=i %>,<%=totalPage %>);"><%=i %></a></li>
							<%  } %> 
							<li><a href="#" onclick="GoToPage(<%=currentPage+1 %>,<%=totalPage %>);"><span aria-hidden="true">&raquo;</span></a></li>
						</ul>
					</nav>
					<div class="pull-right" style="line-height:75px;" >共有<%=recordNumber %>条记录，当前第 <%=currentPage %>/<%=totalPage %> 页</div>
				</div>
			</div>
		</div>
	</div>

	<div class="col-md-3 wow fadeInRight">
		<div class="page-header">
    		<h1>冷藏车查询</h1>
		</div>
		<form name="chillCarQueryForm" id="chillCarQueryForm" action="<%=basePath %>ChillCar/frontlist" class="mar_t15" method="post">
			<div class="form-group">
				<label for="carNo">车辆编号:</label>
				<input type="text" id="carNo" name="carNo" value="<%=carNo %>" class="form-control" placeholder="请输入车辆编号">
			</div>
			<div class="form-group">
				<label for="carType">车辆种类:</label>
				<input type="text" id="carType" name="carType" value="<%=carType %>" class="form-control" placeholder="请输入车辆种类">
			</div>
			<div class="form-group">
				<label for="pinpai">品牌:</label>
				<input type="text" id="pinpai" name="pinpai" value="<%=pinpai %>" class="form-control" placeholder="请输入品牌">
			</div>
            <input type=hidden name=currentPage value="<%=currentPage %>" />
            <button type="submit" class="btn btn-primary">查询</button>
        </form>
	</div>

		</div>
</div>
<div id="chillCarEditDialog" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" style="width:900px;" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><i class="fa fa-edit"></i>&nbsp;冷藏车信息编辑</h4>
      </div>
      <div class="modal-body" style="height:450px; overflow: scroll;">
      	<form class="form-horizontal" name="chillCarEditForm" id="chillCarEditForm" enctype="multipart/form-data" method="post"  class="mar_t15">
		  <div class="form-group">
			 <label for="chillCar_carNo_edit" class="col-md-3 text-right">车辆编号:</label>
			 <div class="col-md-9"> 
			 	<input type="text" id="chillCar_carNo_edit" name="chillCar.carNo" class="form-control" placeholder="请输入车辆编号" readOnly>
			 </div>
		  </div> 
		  <div class="form-group">
		  	 <label for="chillCar_carType_edit" class="col-md-3 text-right">车辆种类:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="chillCar_carType_edit" name="chillCar.carType" class="form-control" placeholder="请输入车辆种类">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="chillCar_pinpai_edit" class="col-md-3 text-right">品牌:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="chillCar_pinpai_edit" name="chillCar.pinpai" class="form-control" placeholder="请输入品牌">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="chillCar_pl_edit" class="col-md-3 text-right">排量:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="chillCar_pl_edit" name="chillCar.pl" class="form-control" placeholder="请输入排量">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="chillCar_carPhoto_edit" class="col-md-3 text-right">车辆照片:</label>
		  	 <div class="col-md-9">
			    <img  class="img-responsive" id="chillCar_carPhotoImg" border="0px"/><br/>
			    <input type="hidden" id="chillCar_carPhoto" name="chillCar.carPhoto"/>
			    <input id="carPhotoFile" name="carPhotoFile" type="file" size="50" />
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="chillCar_carDesc_edit" class="col-md-3 text-right">冷藏车介绍:</label>
		  	 <div class="col-md-9">
			 	<textarea name="chillCar.carDesc" id="chillCar_carDesc_edit" style="width:100%;height:500px;"></textarea>
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="chillCar_dqwd_edit" class="col-md-3 text-right">当前温度:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="chillCar_dqwd_edit" name="chillCar.dqwd" class="form-control" placeholder="请输入当前温度">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="chillCar_carMemo_edit" class="col-md-3 text-right">备注信息:</label>
		  	 <div class="col-md-9">
			    <textarea id="chillCar_carMemo_edit" name="chillCar.carMemo" rows="8" class="form-control" placeholder="请输入备注信息"></textarea>
			 </div>
		  </div>
		</form> 
	    <style>#chillCarEditForm .form-group {margin-bottom:5px;}  </style>
      </div>
      <div class="modal-footer"> 
      	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      	<button type="button" class="btn btn-primary" onclick="ajaxChillCarModify();">提交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<jsp:include page="../footer.jsp"></jsp:include> 
<script src="<%=basePath %>plugins/jquery.min.js"></script>
<script src="<%=basePath %>plugins/bootstrap.js"></script>
<script src="<%=basePath %>plugins/wow.min.js"></script>
<script src="<%=basePath %>plugins/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>plugins/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jsdate.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor1_4_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor1_4_3/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor1_4_3/lang/zh-cn/zh-cn.js"></script>
<script>
//实例化编辑器
var chillCar_carDesc_edit = UE.getEditor('chillCar_carDesc_edit'); //冷藏车介绍编辑器
var basePath = "<%=basePath%>";
/*跳转到查询结果的某页*/
function GoToPage(currentPage,totalPage) {
    if(currentPage==0) return;
    if(currentPage>totalPage) return;
    document.chillCarQueryForm.currentPage.value = currentPage;
    document.chillCarQueryForm.submit();
}

/*可以直接跳转到某页*/
function changepage(totalPage)
{
    var pageValue=document.chillCarQueryForm.pageValue.value;
    if(pageValue>totalPage) {
        alert('你输入的页码超出了总页数!');
        return ;
    }
    document.chillCarQueryForm.currentPage.value = pageValue;
    documentchillCarQueryForm.submit();
}

/*弹出修改冷藏车界面并初始化数据*/
function chillCarEdit(carNo) {
	$.ajax({
		url :  basePath + "ChillCar/" + carNo + "/update",
		type : "get",
		dataType: "json",
		success : function (chillCar, response, status) {
			if (chillCar) {
				$("#chillCar_carNo_edit").val(chillCar.carNo);
				$("#chillCar_carType_edit").val(chillCar.carType);
				$("#chillCar_pinpai_edit").val(chillCar.pinpai);
				$("#chillCar_pl_edit").val(chillCar.pl);
				$("#chillCar_carPhoto").val(chillCar.carPhoto);
				$("#chillCar_carPhotoImg").attr("src", basePath +　chillCar.carPhoto);
				chillCar_carDesc_edit.setContent(chillCar.carDesc, false);
				$("#chillCar_dqwd_edit").val(chillCar.dqwd);
				$("#chillCar_carMemo_edit").val(chillCar.carMemo);
				$('#chillCarEditDialog').modal('show');
			} else {
				alert("获取信息失败！");
			}
		}
	});
}

/*删除冷藏车信息*/
function chillCarDelete(carNo) {
	if(confirm("确认删除这个记录")) {
		$.ajax({
			type : "POST",
			url : basePath + "ChillCar/deletes",
			data : {
				carNos : carNo,
			},
			success : function (obj) {
				if (obj.success) {
					alert("删除成功");
					$("#chillCarQueryForm").submit();
					//location.href= basePath + "ChillCar/frontlist";
				}
				else 
					alert(obj.message);
			},
		});
	}
}

/*ajax方式提交冷藏车信息表单给服务器端修改*/
function ajaxChillCarModify() {
	$.ajax({
		url :  basePath + "ChillCar/" + $("#chillCar_carNo_edit").val() + "/update",
		type : "post",
		dataType: "json",
		data: new FormData($("#chillCarEditForm")[0]),
		success : function (obj, response, status) {
            if(obj.success){
                alert("信息修改成功！");
                $("#chillCarQueryForm").submit();
            }else{
                alert(obj.message);
            } 
		},
		processData: false,
		contentType: false,
	});
}

$(function(){
	/*小屏幕导航点击关闭菜单*/
    $('.navbar-collapse a').click(function(){
        $('.navbar-collapse').collapse('hide');
    });
    new WOW().init();

})
</script>
</body>
</html>

