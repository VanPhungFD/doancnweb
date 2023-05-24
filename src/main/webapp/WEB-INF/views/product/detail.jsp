<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<f:formatDate value="${prod.productDate}" pattern="dd-MM-yyyy"
	var="date" />
<f:formatNumber value="${prod.unitPrice}" pattern="#,###.00" var="price" />
<div class="panel panel-success nn-detail" data-id="${prod.id}">
	<div class="panel-heading">
		<h4 class="panel-title">THÔNG TIN CHI TIẾT SẢN PHẨM</h4>
	</div>
	<div class="panel-body">
		<div class="col-sm-5 text-center">
			<img src="/static/images/products/${prod.image}" />
		</div>
		<ul class="col-sm-7">
			<li><strong>Name</strong>: <em>${prod.name}</em></li>
			<li><strong>Unit Price</strong>: <em>${price}</em></li>
			<li><strong>Discount</strong>: <em><f:formatNumber value="${prod.discount}" type="percent"/></em></li>
			<li><strong>Quantity</strong>: <em>${prod.quantity}</em></li>
			<li><strong>Product Date</strong>: <em>${date}</em></li>
			<li><strong>Available</strong>: <em>${prod.available}</em></li>
			<li><strong>Category</strong>: <em>${prod.category.nameVN}</em></li>
		</ul>
	</div>
	<div class="panel-footer text-justify">${prod.description}</div>
	<div class="panel-footer text-right">
		<%@include file="btn-prod.jsp"%>
	</div>
</div>
<%@include file="dialog.jsp"%>
<jsp:include page="_comment.jsp"/>

<div class="nn-detail-relatives">
	<!-- tab buttons -->
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#same">HÀNG
				CÙNG LOẠI</a></li>
		<li><a data-toggle="tab" href="#like">HÀNG YÊU THÍCH</a></li>
		<li><a data-toggle="tab" href="#daxem">HÀNG ĐÃ XEM</a></li>
	</ul>

	<div class="panel panel-default">
		<div class="panel-body">
			<!-- tab content -->
			<div class="tab-content">
				<div id="same" class="tab-pane fade in active">
					<c:forEach var="p" items="${prod.category.products}">
						<a href="/product/detail/${p.id}"><img
							src="/static/images/products/${p.image}" /></a>
					</c:forEach>
				</div>
				<div id="like" class="tab-pane fade">
					<c:forEach var="p" items="${like}">
						<a href="/product/detail/${p.id}"><img
							src="/static/images/products/${p.image}" /></a>
					</c:forEach>
				</div>
				<div id="daxem" class="tab-pane fade">
					<c:forEach var="p" items="${daXem}">
						<a href="/product/detail/${p.id}"><img
							src="/static/images/products/${p.image}" /></a>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>




