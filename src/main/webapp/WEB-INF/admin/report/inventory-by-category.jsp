<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>

<div class="panel panel-danger">
	<div class="panel-heading">
		<h3 class="panel-title"><strong>INVENTORY BY CATEGORY</strong></h3>
	</div>
	<div class="panel-body">
	</div>
	<table class="table table-hover">
		<thead class="bg-success">
			<tr>
				<th class="text-center">Loại sản phẩm</th>
				<th class="text-center">Tổng số lượng</th>
				<th class="text-center">Gía trị</th>
				<th class="text-center">Min Price</th>
				<th class="text-center">Max Price</th>
				<th class="text-center">Trung bình</th>
			</tr>
		</thead>
		<tbody class="text-center">
		<c:set var="total" value="0"/>
		<c:forEach var="a" items="${data}">
			<c:set var="total" value="${total + a[2]}"/>
			<tr>
				<td class="text-left">${a[0]}</td>
				<td><f:formatNumber value="${a[1]}" pattern="#,###"/></td>
				<td>$<f:formatNumber value="${a[2]}" pattern="#,###.00"/></td>
				<td>$<f:formatNumber value="${a[3]}" pattern="#,###.00"/></td>
				<td>$<f:formatNumber value="${a[4]}" pattern="#,###.00"/></td>
				<td>$<f:formatNumber value="${a[5]}" pattern="#,###.00"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<jsp:include page="_inventory-chart.jsp"></jsp:include>
	
	
	
	<div class="panel-footer">
		<strong>TỔNG DOANH THU : $<f:formatNumber value="${total}" pattern="#,###.00"/></strong>
	</div>
</div>