<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%> 
<%@ page import="com.chengxusheji.po.CarProduct" %>
<%@ page import="com.chengxusheji.po.ChillCar" %>
<%@ page import="com.chengxusheji.po.Product" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    List<CarProduct> carProductList = (List<CarProduct>)request.getAttribute("carProductList");
    //获取所有的carObj信息
    List<ChillCar> chillCarList = (List<ChillCar>)request.getAttribute("chillCarList");
    //获取所有的productObj信息
    List<Product> productList = (List<Product>)request.getAttribute("productList");
    int currentPage =  (Integer)request.getAttribute("currentPage"); //当前页
    int totalPage =   (Integer)request.getAttribute("totalPage");  //一共多少页
    int recordNumber =   (Integer)request.getAttribute("recordNumber");  //一共多少记录
    Product productObj = (Product)request.getAttribute("productObj");
    ChillCar carObj = (ChillCar)request.getAttribute("carObj");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
<title>冷藏车商品查询</title>
<link href="<%=basePath %>plugins/bootstrap.css" rel="stylesheet">
<link href="<%=basePath %>plugins/bootstrap-dashen.css" rel="stylesheet">
<link href="<%=basePath %>plugins/font-awesome.css" rel="stylesheet">
<link href="<%=basePath %>plugins/animate.css" rel="stylesheet">
<link href="<%=basePath %>plugins/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
</head>
<body style="margin-top:70px;">
<div class="container">
<jsp:include page="../header.jsp"></jsp:include>
	<div class="row"> 
		<div class="col-md-9 wow fadeInDown" data-wow-duration="0.5s">
			<div>
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
			    	<li><a href="<%=basePath %>index.jsp">首页</a></li>
			    	<li role="presentation" class="active"><a href="#carProductListPanel" aria-controls="carProductListPanel" role="tab" data-toggle="tab">冷藏车商品列表</a></li>
			    	<li role="presentation" ><a href="<%=basePath %>CarProduct/carProduct_frontAdd.jsp" style="display:none;">添加冷藏车商品</a></li>
				</ul>
			  	<!-- Tab panes -->
			  	<div class="tab-content">
				    <div role="tabpanel" class="tab-pane active" id="carProductListPanel">
				    		<div class="row">
				    			<div class="col-md-12 top5">
				    				<div class="table-responsive">
				    				<table class="table table-condensed table-hover">
				    					<tr class="success bold"><td>序号</td><td>产品名称</td><td>所在冷藏车</td><td>产品数量</td><td>最低温度</td><td>最高温度</td><td>当前温度<td>操作</td></tr>
				    					<% 
				    						/*计算起始序号*/
				    	            		int startIndex = (currentPage -1) * 5;
				    	            		/*遍历记录*/
				    	            		for(int i=0;i<carProductList.size();i++) {
					    	            		int currentIndex = startIndex + i + 1; //当前记录的序号
					    	            		CarProduct carProduct = carProductList.get(i); //获取到冷藏车商品对象
 										%>
 										<tr>
 											<td><%=currentIndex %></td>
 											<td><%=carProduct.getProductObj().getProductName() %></td>
 											<td><%=carProduct.getCarObj().getCarNo() %></td>
 											<td><%=carProduct.getProductNum() %></td>
 											<td><%=carProduct.getProductObj().getZdwd() %>°C</td>
 											<td><%=carProduct.getProductObj().getZgwd() %>°C</td>
 											<td>
 												<% if(carProduct.getCarObj().getDqwd() < carProduct.getProductObj().getZdwd() || carProduct.getCarObj().getDqwd() > carProduct.getProductObj().getZgwd()) { %>
 												<font color=red><%=carProduct.getCarObj().getDqwd() %>°C</font>
 												<% } else { %>
 												<%=carProduct.getCarObj().getDqwd() %>°C
 												<% } %>
 											</td>
 											<td>
 												<a href="<%=basePath  %>CarProduct/<%=carProduct.getId() %>/frontshow"><i class="fa fa-info"></i>&nbsp;查看</a>&nbsp;
 												<a href="#" onclick="carProductEdit('<%=carProduct.getId() %>');" style="display:none;"><i class="fa fa-pencil fa-fw"></i>编辑</a>&nbsp;
 												<a href="#" onclick="carProductDelete('<%=carProduct.getId() %>');" style="display:none;"><i class="fa fa-trash-o fa-fw"></i>删除</a>
 											</td> 
 										</tr>
 										<%}%>
				    				</table>
				    				</div>
				    			</div>
				    		</div>

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
			</div>
		</div>
	<div class="col-md-3 wow fadeInRight">
		<div class="page-header">
    		<h1>冷藏车商品查询</h1>
		</div>
		<form name="carProductQueryForm" id="carProductQueryForm" action="<%=basePath %>CarProduct/frontlist" class="mar_t15" method="post">
            <div class="form-group">
            	<label for="productObj_productId">产品名称：</label>
                <select id="productObj_productId" name="productObj.productId" class="form-control">
                	<option value="0">不限制</option>
	 				<%
	 				for(Product productTemp:productList) {
	 					String selected = "";
 					if(productObj!=null && productObj.getProductId()!=null && productObj.getProductId().intValue()==productTemp.getProductId().intValue())
 						selected = "selected";
	 				%>
 				 <option value="<%=productTemp.getProductId() %>" <%=selected %>><%=productTemp.getProductName() %></option>
	 				<%
	 				}
	 				%>
 			</select>
            </div>
            <div class="form-group">
            	<label for="carObj_carNo">所在冷藏车：</label>
                <select id="carObj_carNo" name="carObj.carNo" class="form-control">
                	<option value="">不限制</option>
	 				<%
	 				for(ChillCar chillCarTemp:chillCarList) {
	 					String selected = "";
 					if(carObj!=null && carObj.getCarNo()!=null && carObj.getCarNo().equals(chillCarTemp.getCarNo()))
 						selected = "selected";
	 				%>
 				 <option value="<%=chillCarTemp.getCarNo() %>" <%=selected %>><%=chillCarTemp.getCarNo() %></option>
	 				<%
	 				}
	 				%>
 			</select>
            </div>
            <input type=hidden name=currentPage value="<%=currentPage %>" />
            <button type="submit" class="btn btn-primary">查询</button>
        </form>
	</div>

		</div>
	</div> 
<div id="carProductEditDialog" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><i class="fa fa-edit"></i>&nbsp;冷藏车商品信息编辑</h4>
      </div>
      <div class="modal-body" style="height:450px; overflow: scroll;">
      	<form class="form-horizontal" name="carProductEditForm" id="carProductEditForm" enctype="multipart/form-data" method="post"  class="mar_t15">
		  <div class="form-group">
			 <label for="carProduct_id_edit" class="col-md-3 text-right">记录id:</label>
			 <div class="col-md-9"> 
			 	<input type="text" id="carProduct_id_edit" name="carProduct.id" class="form-control" placeholder="请输入记录id" readOnly>
			 </div>
		  </div> 
		  <div class="form-group">
		  	 <label for="carProduct_productObj_productId_edit" class="col-md-3 text-right">产品名称:</label>
		  	 <div class="col-md-9">
			    <select id="carProduct_productObj_productId_edit" name="carProduct.productObj.productId" class="form-control">
			    </select>
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="carProduct_carObj_carNo_edit" class="col-md-3 text-right">所在冷藏车:</label>
		  	 <div class="col-md-9">
			    <select id="carProduct_carObj_carNo_edit" name="carProduct.carObj.carNo" class="form-control">
			    </select>
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="carProduct_productNum_edit" class="col-md-3 text-right">产品数量:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="carProduct_productNum_edit" name="carProduct.productNum" class="form-control" placeholder="请输入产品数量">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="carProduct_productMemo_edit" class="col-md-3 text-right">备注信息:</label>
		  	 <div class="col-md-9">
			    <textarea id="carProduct_productMemo_edit" name="carProduct.productMemo" rows="8" class="form-control" placeholder="请输入备注信息"></textarea>
			 </div>
		  </div>
		</form> 
	    <style>#carProductEditForm .form-group {margin-bottom:5px;}  </style>
      </div>
      <div class="modal-footer"> 
      	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      	<button type="button" class="btn btn-primary" onclick="ajaxCarProductModify();">提交</button>
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
<script>
var basePath = "<%=basePath%>";
/*跳转到查询结果的某页*/
function GoToPage(currentPage,totalPage) {
    if(currentPage==0) return;
    if(currentPage>totalPage) return;
    document.carProductQueryForm.currentPage.value = currentPage;
    document.carProductQueryForm.submit();
}

/*可以直接跳转到某页*/
function changepage(totalPage)
{
    var pageValue=document.carProductQueryForm.pageValue.value;
    if(pageValue>totalPage) {
        alert('你输入的页码超出了总页数!');
        return ;
    }
    document.carProductQueryForm.currentPage.value = pageValue;
    documentcarProductQueryForm.submit();
}

/*弹出修改冷藏车商品界面并初始化数据*/
function carProductEdit(id) {
	$.ajax({
		url :  basePath + "CarProduct/" + id + "/update",
		type : "get",
		dataType: "json",
		success : function (carProduct, response, status) {
			if (carProduct) {
				$("#carProduct_id_edit").val(carProduct.id);
				$.ajax({
					url: basePath + "Product/listAll",
					type: "get",
					success: function(products,response,status) { 
						$("#carProduct_productObj_productId_edit").empty();
						var html="";
		        		$(products).each(function(i,product){
		        			html += "<option value='" + product.productId + "'>" + product.productName + "</option>";
		        		});
		        		$("#carProduct_productObj_productId_edit").html(html);
		        		$("#carProduct_productObj_productId_edit").val(carProduct.productObjPri);
					}
				});
				$.ajax({
					url: basePath + "ChillCar/listAll",
					type: "get",
					success: function(chillCars,response,status) { 
						$("#carProduct_carObj_carNo_edit").empty();
						var html="";
		        		$(chillCars).each(function(i,chillCar){
		        			html += "<option value='" + chillCar.carNo + "'>" + chillCar.carNo + "</option>";
		        		});
		        		$("#carProduct_carObj_carNo_edit").html(html);
		        		$("#carProduct_carObj_carNo_edit").val(carProduct.carObjPri);
					}
				});
				$("#carProduct_productNum_edit").val(carProduct.productNum);
				$("#carProduct_productMemo_edit").val(carProduct.productMemo);
				$('#carProductEditDialog').modal('show');
			} else {
				alert("获取信息失败！");
			}
		}
	});
}

/*删除冷藏车商品信息*/
function carProductDelete(id) {
	if(confirm("确认删除这个记录")) {
		$.ajax({
			type : "POST",
			url : basePath + "CarProduct/deletes",
			data : {
				ids : id,
			},
			success : function (obj) {
				if (obj.success) {
					alert("删除成功");
					$("#carProductQueryForm").submit();
					//location.href= basePath + "CarProduct/frontlist";
				}
				else 
					alert(obj.message);
			},
		});
	}
}

/*ajax方式提交冷藏车商品信息表单给服务器端修改*/
function ajaxCarProductModify() {
	$.ajax({
		url :  basePath + "CarProduct/" + $("#carProduct_id_edit").val() + "/update",
		type : "post",
		dataType: "json",
		data: new FormData($("#carProductEditForm")[0]),
		success : function (obj, response, status) {
            if(obj.success){
                alert("信息修改成功！");
                $("#carProductQueryForm").submit();
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

