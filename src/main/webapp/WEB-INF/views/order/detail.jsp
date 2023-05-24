<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>

<form:form modelAttribute="order" action="/order/checkout">
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h4 class="panel-title">THÔNG TIN ĐƠN HÀNG</h4>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Khách hàng</label>
					<div class="form-control">${order.customer.fullname}</div>
				</div>
				<div class="form-group col-sm-6">
					<label>Địa chỉ giao hàng</label>
					<div class="form-control">${order.address}</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label>Ngày đặt hàng</label>
					<div class="form-control">
						<f:formatDate value="${order.orderDate}"
							pattern="hh:mm a dd-MM-yyyy" />
					</div>
				</div>
				<div class="form-group col-sm-6">
					<label>Tổng giá</label>
					<div class="form-control">
						<f:formatNumber value="${order.amount}" pattern="#,###.00" />
						USD
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-12">
					<label>Ghi chú</label>
					<form:textarea path="description" readonly="true" rows="5"
						class="form-control" />
				</div>
			</div>
		</div>
		<jsp:include page="_detail-order.jsp" />
	</div>
</form:form>
<script type="text/javascript">
//<![CDATA[
        bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
  //]]>
  </script>
