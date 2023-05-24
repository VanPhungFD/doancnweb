<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>

<form:form action="/account/register" modelAttribute="user"
	enctype="multipart/form-data">

	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="text-danger pull-right"></div>
			<h4 class="panel-title">ĐĂNG KÍ</h4>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<label>Tài khoản</label>
				<form:input path="id" class="form-control" required="required"/>
				<form:errors path="id"></form:errors>
			</div>
			<div class="form-group">
				<label>Mật khẩu</label>
				<form:input path="password" class="form-control"  type="password" required="required"/>
				<form:errors path="password"></form:errors>
			</div>
			<div class="form-group">
				<label>Xác nhận mật khẩu</label> <input name="confirm"
					type="password" value="${param.confirm}" class="form-control" required="required" />
			</div>
			<div class="form-group">
				<label>Họ Tên</label>
				<form:input path="fullname" class="form-control" required="required"/>
				<form:errors path="fullname"></form:errors>
			</div>
			<div class="form-group">
				<label>Email</label>
				<form:input path="email" class="form-control" type="email" required="required"/>
				<form:errors path="email"></form:errors>
			</div>

			<div class="form-group">
				<label>Hình đại diện</label> <input type="file" name="photo_file"
					class="form-control" />
				<form:hidden path="photo" />
			</div>

		</div>
		<div class="panel-footer text-right">
			<div class="pull-left text-danger">${message}</div>
			<button class="btn btn-warning" >
				<span class="glyphicon glyphicon-user"></span> Đăng kí
			</button>
		</div>
	</div>

</form:form>


