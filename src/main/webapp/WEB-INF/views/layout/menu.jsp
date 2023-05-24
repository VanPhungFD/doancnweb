<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button class="navbar-toggle" data-toggle="collapse" data-target="#menu">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/home/index"><span class="glyphicon glyphicon-home"></span>Trang chủ</a>
		</div>
		<div class="collapse navbar-collapse" id="menu">
			<ul class="nav navbar-nav">
				 <li><a href="/home/about"><span class="glyphicon glyphicon-info-sign"></span>Về chúng tôi</a></li>
				 <li><a href="/home/contact"><span class="glyphicon glyphicon-phone-alt"></span> Liên hệ</a></li>
				 <li><a href="/home/feedback"><span class="glyphicon glyphicon-envelope"></span> Phản hồi</a></li>
				 <li><a href="/home/faq"><span class="glyphicon glyphicon-question-sign"></span> Câu hỏi</a></li>
				 <li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
						<span class="glyphicon glyphicon-user"></span> My Account
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<c:choose>
							<c:when test="${empty sessionScope.user}">
								<li><a href="/account/login">Đăng nhập</a></li>
								<li><a href="/account/forgot">Quên mật khẩu</a></li>
								<li><a href="/account/register">Đăng kí</a></li>							
							</c:when>
							<c:otherwise>
								<li><a href="/account/logoff">Đăng xuất</a></li>
								<li><a href="/account/change">Thay đổi mật khẩu</a></li>
								<li><a href="/account/edit">Chỉnh sửa thông tin</a></li>
								<li class="divider"></li>
								<li><a href="/order/list">Đơn hàng</a></li>
								<li><a href="/order/items">Mặt hàng đã mua</a></li>
								<c:if test="${sessionScope.user.admin}">
									<li class="divider"></li>
									<li><a href="/admin/home/index">Administration</a></li>
								</c:if>					
							</c:otherwise>
						</c:choose>
					</ul>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right nn-lang">
				<li><a href="#"><img src="/static/images/en.png"></a></li>
				<li><a href="#"><img src="/static/images/vi.png"></a></li>
			</ul>
		</div>
	</div>
</nav>


