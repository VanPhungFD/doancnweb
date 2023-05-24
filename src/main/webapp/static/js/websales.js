$(function() {
	$(".btn-like").click(function() {
		var id = $(this).parents("[data-id]").attr("data-id")
		$.ajax({
			url : "/product/favorite/" + id,
			success : function(resp) {
				alert("Đã thêm vào danh sách sản phẩm yêu thích của bạn");
			}
		})
	});

	

	
	$(".btn-envelope").click(function() {
		var id = $(this).parents("[data-id]").attr("data-id");
		// bỏ id sp vừa lấy đc bỏ vào input id sp
		$("#send-dialog #id").val(id);
		$(".nn-sending").hide();

		var id = $(this).parents("[data-id]").attr("data-id")
		$("#send-dialog").modal("show");
	})
	
	 /* $(".btn-change-order").click(function() {
		var id = $(this).parents("[data-id]").attr("data-id");
		// bỏ id sp vừa lấy đc bỏ vào input id sp
		$("#send-dialog #id").val(id);
		$(".nn-sending").hide();

		var id = $(this).parents("[data-id]").attr("data-id")
		$("#send-dialog").modal("show");
	})*/
	
	
	// chức năng thẻ btn gửi thông tin sản phẩm to friend
	$(".btn-send-To-Friend").click(function() {
		/*
		 * var id = $("#send-dialog #id").val(); // lấy id ra lại từ modal var
		 * id = $("#send-dialog #from").val(); // lấy thông tin ng gửi var id =
		 * $("#send-dialog #to").val(); // lấy ng nhận var id = $("#send-dialog
		 * #subject").val(); // lấy tiêu đề var id = $("#send-dialog
		 * #body").val(); // lấy nội dung
		 */

		// data theo chuỗi json
		var data = {
			id : $("#send-dialog #id").val(),
			from : $("#send-dialog #from").val(),
			to : $("#send-dialog #to").val(),
			subject : $("#send-dialog #subject").val(),
			body : $("#send-dialog #body").val()
		}
		$("#sending").show();
		// console.log(data)
		// gửi lên server
		$.ajax({
			url : "/product/send-friend",
			data : data,
			type : "POST",
			success : function(res) {
				$("#sending").hide();
				$("#send-dialog").modal("hide");
			}
		})

	});
	
	$(".btn-send-To-Friend").click(function() {
		/*
		 * var id = $("#send-dialog #id").val(); // lấy id ra lại từ modal var
		 * id = $("#send-dialog #from").val(); // lấy thông tin ng gửi var id =
		 * $("#send-dialog #to").val(); // lấy ng nhận var id = $("#send-dialog
		 * #subject").val(); // lấy tiêu đề var id = $("#send-dialog
		 * #body").val(); // lấy nội dung
		 */

		// data theo chuỗi json
		var data = {
			id : $("#send-dialog #id").val(),
			from : $("#send-dialog #from").val(),
			to : $("#send-dialog #to").val(),
			subject : $("#send-dialog #subject").val(),
			body : $("#send-dialog #body").val()
		}
		$("#sending").show();
		// console.log(data)
		// gửi lên server
		$.ajax({
			url : "/product/send-friend",
			data : data,
			type : "POST",
			success : function(res) {
				$("#sending").hide();
				$("#send-dialog").modal("hide");
			}
		})

	});


})

