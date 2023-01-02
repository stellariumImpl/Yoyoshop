<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>商品详情</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/flexslider.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.flexslider.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="layer/layer.js"></script>
	<script type="text/javascript" src="js/cart.js"></script>
	<script>
		$(function() {
		  $('.flexslider').flexslider({
			animation: "slide",
			controlNav: "thumbnails"
		  });
		});
	</script>
</head>
<body>
	 
	<jsp:include page="header.jsp"/>
	
	<!--//single-page-->
	<div class="single">
		<div class="container">
			<div class="single-grids">				
				<div class="col-md-4 single-grid">		
					<div class="flexslider">
						<ul class="slides">
							<li data-thumb="../${good.cover}">
								<div class="thumb-image"> <img src="../${good.cover}" data-imagezoom="true" class="img-responsive"> </div>
							</li>
							<li data-thumb="../${good.image1}">
								 <div class="thumb-image"> <img src="../${good.image1}" data-imagezoom="true" class="img-responsive"> </div>
							</li>
							<li data-thumb="../${good.image2}">
							   <div class="thumb-image"> <img src="../${good.image2}" data-imagezoom="true" class="img-responsive"> </div>
							</li> 
						</ul>
					</div>
				</div>	
				<div class="col-md-4 single-grid simpleCart_shelfItem">		
					<h3>${good.name}</h3>
					库存: ${good.stock}
					<div class="tag">
						<p>分类 : <a href="goods?typeid=${good.type.id}&page=1&size=16">${good.type.name}</a></p>
					</div>
					<p>介绍: ${good.intro}</p>
					<div class="galry">
						<div class="prices">
							<h5 class="item_price">¥ ${good.price}   </h5>

						</div>
						<div class="clearfix"></div>
					</div>
					<div class="btn_form">
						<a href="javascript:;" class="add-cart item_add" onclick="buy(${good.id})">加入购物车</a>

					</div>
				</div>
				<div class="col-md-4 single-grid1">
					<ul>
						<c:forEach var="type" items="${typeList}">
							<li><a href="goods?typeid=${type.id}&page=1&size=16">${type.name}</a></li>
						</c:forEach>
					</ul>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
	
	<jsp:include page="footer.jsp"/>

</body>
</html>