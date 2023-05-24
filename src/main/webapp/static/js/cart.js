$(function() {
	
	// thêm giỏ hàng
	$(".btn-add-to-cart").click(function() {
		// lay id sp
		var id = $(this).parents("[data-id]").attr("data-id");
		$.ajax({
			url : "/product/add-to-cart/" + id,
			success : function(res) {
				// nhận chuỗi vừa trả về từ sv (count + amount)-> biến thành đối
				// tượng json
				var info = JSON.parse(res);
				// bỏ giữa vào 2 thẻ
				updateCartInfo(info);
			}
		});
		effectCart(id);
	});
	
	
	// xóa từng item
	$(".btn-cart-remove").click(function() {
		// lay id sp
		var id = $(this).parents("[data-id]").attr("data-id");
		$.ajax({
			url : "/cart/remove/" + id,
			success : function(res) {
				// nhận chuỗi vừa trả về từ sv (count + amount)-> biến thành đối
				// tượng json
				var info = JSON.parse(res);
				// bỏ giữa vào 2 thẻ
				updateCartInfo(info);
			}
		});
	// hiệu ứng ẩn dòng sản phẩm khi xóa
		$(this).parents("[data-id]").hide(100);
	});
	
	// chức năng xóa tất cả giỏ hàng
	$(".btn-cart-clear").click(function(){
		$.ajax({
			url: "/cart/clear",
			success : function(res)
			{
				var info = JSON.parse(res);
				updateCartInfo(info);
			}	
		});
/*
 * ẩn tất cả <tr> bằng cách ẩn <tbody> $(".hide-cart").hide(100);
 */
		
		// hiệu ứng ẩn duyệt qua từng thẻ <tr>
		$(".hide-cart").each(function(index,tr){
			$(tr).hide(500 * (1 + index));
		})	
	});
	
	// chức năng thay đổi số lượng sản phẩm
	$(".txt-cart-quantity").on("input",function(){
		var id = $(this).parents("[data-id]").attr("data-id");
		var qty = $(this).val();
		$.ajax({
			url: `/cart/update/${id}/${qty}`,
			success : function(res)
			{
				// phản hồi từ sv cập nhật lại tt giỏ hàng
				var info = JSON.parse(res);
				updateCartInfo(info);
			}
		});
		
		var price = $(this).parents("[data-price]").attr("data-price");
		var disc = $(this).parents("[data-discount]").attr("data-discount");
	  var amount =Math.round(price*qty * (1- disc)*100)/100; // làm tròn 
	 $(this).parents("tr").find("td:eq(5)").html(`$${amount}`);
		
	})

})
function updateCartInfo(info) {
	$("#cart-cnt").html(info.count);
	$("#cart-amt").html(info.amount);
}
function effectCart(id) {
	var img = $(this).parents("[data-id]").find(".panel-body img");
	
	  $("style#cart-effect").html(`.cart-effect{ background-image:
	  url("${img.attr("src")}"); background-size: 100% 100%; }`);
	

/*
 * cart-img -> id đặt trong thẻ img giỏ hàng cart-fly -> định nghĩa css
 */
	var options = {
		to : "#cart-img",
		className : "cart-fly"
	}
	img.effect("transfer", options, 1000)
}
