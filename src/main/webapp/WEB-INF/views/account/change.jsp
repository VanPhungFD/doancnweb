<%@ page pageEncoding="utf-8"%>

<form action="/account/change" method="post">
	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="text-danger pull-right">${message}</div>
			<h4 class="panel-title">Thay đổi mật khẩu</h4>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<label>Tài khoản</label>
				<input name="username" value="${param.username}" class="form-control">
			</div>
			<div class="form-group">
				<label>Mật khẩu hiện tại</label>
				<input type="password" name="password" value="${param.password}" class="form-control">
			</div>
			<div class="form-group">
				<label>Mật khẩu mới</label>
				<input type="password" name="newPassword" value="${param.newPassword}" class="form-control">
			</div>
			<div class="form-group">
				<label>Xác nhận lại mật khẩu</label>
				<input type="password" name="confirm" value="${param.confirm}" class="form-control">
			</div>
		</div>
		<div class="panel-footer">
			<button class="btn btn-warning">
				<span class="glyphicon glyphicon-user"></span> Thay đổi
			</button>	
		</div>
	</div>
</form>