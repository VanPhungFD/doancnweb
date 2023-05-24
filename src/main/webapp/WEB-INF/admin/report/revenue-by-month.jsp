<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>

<div class="panel panel-danger">
	<div class="panel-heading">
		<h3 class="panel-title"><strong>DOANH SỐ THEO THÁNG</strong></h3>
	</div>
	<table class="table table-hover">
		<thead class="bg-success">
			<tr>
				<th class="text-center">Tháng</th>
				<th class="text-center">Số lượng</th>
				<th class="text-center">Doanh số</th>
				<th class="text-center">Giá thấp</th>
				<th class="text-center">Giá cao</th>
				<th class="text-center">Trung Bình</th>
			</tr>
		</thead>
		<tbody class="text-center">
		<c:set var="total" value="0"/>
		<c:forEach var="a" items="${rpMonth}">
			<c:set var="total" value="${total + a[2]}"/>
			<tr>
				<td>${a[0]}</td>
				<td><f:formatNumber value="${a[1]}" pattern="#,###"/></td>
				<td>$<f:formatNumber value="${a[2]}" pattern="#,###.00"/></td>
				<td>$<f:formatNumber value="${a[3]}" pattern="#,###.00"/></td>
				<td>$<f:formatNumber value="${a[4]}" pattern="#,###.00"/></td>
				<td>$<f:formatNumber value="${a[5]}" pattern="#,###.00"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<jsp:include page="_revenue-by-month.jsp"/>
	<div class="panel-footer">
		<strong>TỔNG DOANH SỐ: $<f:formatNumber value="${total}" pattern="#,###.00"/></strong>
	</div>
</div>
