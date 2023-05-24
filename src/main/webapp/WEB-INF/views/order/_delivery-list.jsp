<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">ĐƠN HÀNG ĐÃ MUA</h4>
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Mã đơn hàng</th>
				<th>Ngày đặt hàng</th>
				<th>Địa chỉ</th>
				<th>Tổng giá</th>
				<th>Trạng thái</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="o" items="${ordersDelivery}">
				<tr>
					<td>#${o.id}</td>
					<td><f:formatDate value="${o.orderDate}"
							pattern="hh:mm a, dd-MM-yyyy" /></td>
					<td>${o.address}</td>
					<td>$<f:formatNumber value="${o.amount}" pattern="#,###.00" />
					</td>
					<td style="color: green;"><c:choose>
							<c:when test="${o.status == 0}">Hoàn tất</c:when>
							<c:when test="${o.status == 1}">Chờ xác nhận</c:when>
							<c:when test="${o.status == 2}">Đang giao</c:when>
							<c:when test="${o.status == 3}">Đã giao</c:when>
							<c:when test="${o.status == 4}">Đã hủy</c:when>
						</c:choose></td>
					<td><a href="/order/detail/${o.id}"
						class="btn btn-sm btn-warning"> <span
							class="glyphicon glyphicon-search"></span>
					</a></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panel-footer"></div>
</div>