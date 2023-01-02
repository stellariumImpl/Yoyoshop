
/**
 * 加入购物车
 */
function buy(goodid){
	$.post("addToCart", {goodid:goodid}, function(data){
		if(data=="ok"){
			layer.msg("操作成功!", {time:1000}, function(){
				location.reload();
			});
		}else if(data=="login"){
            layer.msg("请登录后加入购物车!", {time:1200}, function(){
                location.href="login";
            });
		}else if(data=="empty"){
            layer.msg("库存不足!", {time:1000}, function(){
                location.reload();
            });
		}else{
            layer.msg("请求失败!", {time:1000}, function(){
                location.reload();
            });
		}
	});
}
/**
 * 购物车减去
 */
function lessen(goodid){
	$.post("lessen", {goodid:goodid}, function(data){
		if(data=="ok"){
			layer.msg("操作成功!", {time:800}, function(){
				location.reload();
			});
		}else if(data=="login"){
            layer.msg("请登录后操作!", {time:800}, function(){
                location.href="login";
            });
		}else{
            layer.msg("请求失败!", {time:800}, function(){
            });
		}
	});
}
/**
 * 购物车删除
 */
function deletes(goodid){
	$.post("delete", {goodid:goodid}, function(data){
		if(data=="ok"){
			layer.msg("删除成功!", {time:800}, function(){
				location.reload();
			});
		}else if(data=="login"){
            layer.msg("请登录后操作!", {time:800}, function(){
                location.href="login";
            });
		}else{
            layer.msg("请求失败!", {time:800}, function(){
            });
		}
	});
}