<%@ page pageEncoding="utf-8"%>
<form action="/account/forgot" method="post">
	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="text-danger pull-right">${message}</div>
			<h4 class="panel-title">Quên mật khẩu</h4>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<label>Tài khoản</label>
				<input name="username" value="" class="form-control" required="required">
			</div>
			<div class="form-group">
				<label>Địa chỉ email</label>
				<input name="email" value="" class="form-control" required="required">
			</div>
		</div>
		<div class="panel-footer">
			<button class="btn btn-warning">
				<span class="glyphicon glyphicon-user"></span> Retrieve
			</button>	
		</div>
	</div>
</form>