<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>

<!-- Shopping Cart -->
<div class="panel panel-success">
	<div class="panel-heading">
		<h4 class="panel-title">GIỎ HÀNG</h4>
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Mã sản phẩm</th>
				<th>Tên sản phẩm</th>
				<th>Giá sản phẩm</th>
				<th>Giảm giá</th>
				<th>Số lượng</th>
				<th>Tổng giá</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="p" items="${cart.itemsCart}">
				<tr>
					<td>${p.id}</td>
					<td>${p.name}</td>
					<td>$<f:formatNumber value="${p.unitPrice}" pattern="#,###.00" />
					</td>
					<td><f:formatNumber value="${p.discount}" type="percent" /></td>
					<td>${p.quantity}</td>
					<td>$<f:formatNumber
							value="${p.unitPrice*p.quantity*(1-p.discount)}"
							pattern="#,###.00" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panel-footer">Tổng giá : ${cart.amountCart}</div>
</div>

<!-- Purchase Form -->
<form:form modelAttribute="order" action="/order/checkout">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<div class="text-danger pull-right">${message}</div>
			<h4 class="panel-title">THÔNG TIN MUA HÀNG</h4>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Tên khách hàng</label>
					<form:input path="customer.fullname"
						placeholder="Vui lòng nhập họ tên" required="required"
						class="form-control" />
				</div>
			<%--	<div class="form-group col-sm-6">
					<label>Số điện thoại</label>
					<form:input path="phone" class="form-control"
						placeholder="Vui lòng nhập số điện thoại" required="required"
						pattern="(03|05|07|08|09)+([0-9]{8})\b"
					 />
				</div>--%>
			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Địa chỉ vận chuyển</label>
					<form:input path="address" class="form-control"
						placeholder="Vui lòng nhập địa chỉ" required="required"
						 />
				</div>
				<div class="form-group col-sm-6">
					<label>Ngày đặt hàng</label>
					<div class="form-control">
						<f:formatDate value="${order.orderDate}" pattern="dd-MM-yyyy" />
					</div>
				</div>

			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Tổng giá</label>
					<div class="form-control">
						<f:formatNumber value="${order.amount}" pattern="#,###.00" />
						USD
					</div>
				</div>
				<div class="form-group col-sm-6">
					<label>Ghi chú</label>
					<form:textarea path="description" rows="3" class="form-control" />
				</div>
			</div>
		</div>
		<div class="panel-footer">
			<button class="btn btn-primary">
				<form:hidden path="customer.id" />
				<form:hidden path="amount" />
				<form:hidden path="orderDate" />
				<span class="glyphicon glyphicon-save"></span> Mua hàng
			</button>
		</div>
	</div>
</form:form>

