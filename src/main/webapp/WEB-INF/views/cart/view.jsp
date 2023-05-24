<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">THÔNG TIN GIỎ HÀNG</h4>
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
				<th></th>
			</tr>
		</thead>
		<tbody class="hide-cart">
			<c:forEach var="p" items="${cart.itemsCart}">
				<tr data-id="${p.id}" data-price="${p.unitPrice}"
					data-discount="${p.discount}">
					<td>${p.id}</td>
					<td>${p.name}</td>
					<td>$<f:formatNumber value="${p.unitPrice}" pattern="#,###.00" />
					</td>
					<td><f:formatNumber value="${p.discount}" type="percent"/></td>
					<td><input value="${p.quantity}" type="number" min="1"
						class="txt-cart-quantity" style="width: 100px"></td>
					<td class="item-amt">$<f:formatNumber
							value="${p.unitPrice*p.quantity*(1-p.discount)}"
							pattern="#,###.00" />
					</td>
					<td>
						<button class="btn btn-sm btn-danger btn-cart-remove">
							<span class="glyphicon glyphicon-trash"></span>
						</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panel-footer">
		<a href="${backurl}" class="btn btn-primary">Add More</a>
		<button class="btn btn-danger btn-cart-clear">Clear</button>
		<a href="/order/checkout" class="btn btn-success">Checkout</a>
			<div class="pull-right text-danger">${message}</div>
	</div>
</div>