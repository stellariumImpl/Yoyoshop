<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>商品列表</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/simpleCart.min.js"></script>
	<script type="text/javascript" src="layer/layer.js"></script>
	<script type="text/javascript" src="js/cart.js"></script>

	<link rel="stylesheet" href="layui/css/layui.css"  media="all">
</head>
<body>
	
	<jsp:include page="header.jsp"/>
	
	<!--products-->
	<div class="products">	 
		<div class="container">
			<h2>${type.name}</h2>
			<c:if test="${type==null}">
				<c:if test="${typeid==2}"><h2>热销推荐</h2></c:if>		
				<c:if test="${typeid==3}"><h2>新品推荐</h2></c:if>
				<c:if test="${typeid==4}"><h2>搜索结果: ${keyword}</h2></c:if>
			</c:if>		
			<div class="col-md-12 product-model-sec">
				<c:forEach var="goods" items="${goodList}">
					<div class="product-grid">
						<a href="detail?goodid=${goods.id}">				
							<div class="more-product"><span> </span></div>						
							<div class="product-img b-link-stripe b-animate-go  thickbox">
								<img src="../${goods.cover}" class="img-responsive" alt="${goods.name}" width="240" height="240">
								<div class="b-wrapper">
									<h4 class="b-animate b-from-left  b-delay03">							
										<button>查看详情</button>
									</h4>
								</div>
							</div>
						</a>				
						<div class="product-info simpleCart_shelfItem">
							<div class="product-info-cust prt_name">
								<h4>${goods.name}</h4>								
								<span class="item_price">¥ ${goods.price}</span>
								<input type="button" class="item_add items" value="加入购物车" onclick="buy(${goods.id})">
								<div class="clearfix"> </div>
							</div>												
						</div>
					</div>
				</c:forEach>
				<div class="clearfix"> </div>
			</div>
			<div>${pageTool}</div>
		</div>
	</div>
	<!--//products-->	
	
	<jsp:include page="footer.jsp"/>
	<script src="layui/layui.js" charset="utf-8"></script>
</body>
</html>
