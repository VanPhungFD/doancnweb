<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form modelAttribute="user" action="/account/edit"
	enctype="multipart/form-data">
	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="text-danger pull-right">${message}</div>
			<h4 class="panel-title">CHỈNH SỬA THÔNG TIN CÁ NHÂN</h4>
		</div>
		<div class="panel-body">
			<div class="row">
				<!-- PHOTO -->
				<div class="col-sm-4 nn-photo text-center">
					<label for="photo_file"> <img
						src="/static/images/customers/${user.photo}"
						style="max-width: 99%; height: 173px">
					</label>

					<form:hidden path="photo" />
					<input id="photo_file" name="photo_file" type="file"
						class="form-control" />
				</div>
				<!-- FORM -->
				<div class="col-sm-8">
					<div class="form-group">
						<label>Tài khoản</label>
						<form:input path="id" readonly="true" class="form-control" />
						<form:errors path="id" />
					</div>
					<div class="form-group">
						<label>Họ tên</label>
						<form:input path="fullname" class="form-control" />
						<form:errors path="fullname" />
					</div>
					<div class="form-group">
						<label>Địa chỉ email</label>
						<form:input path="email" class="form-control" />
						<form:errors path="email" />
					</div>
				</div>
			</div>
		</div>
		<div class="panel-footer">
			<form:hidden path="password" />
			<form:hidden path="activated" />
			<form:hidden path="admin" />
			<button class="btn btn-warning">
				<span class="glyphicon glyphicon-user"></span> Cập nhật
			</button>
		</div>
	</div>
</form:form>
<style>
<!--
$(function (){ 
$("#photo_file").change(function() {
		if (this.files && this.files.length > 0) {
			/* doan file reader javascript */
			var reader = new FileReader();
			reader.onload = function() {
				$(".nn-photo img").attr("src",reader.result)
			};
			reader.readAsDataURL(this.files[0]);
		}
	});
)
-->
</style>
