<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>


<ul class="nav nav-tabs">
	<li class="active"><a data-toggle="tab" href="#tatca">Tất cả</a></li>
	<li><a data-toggle="tab" href="#xacnhan">Chờ xác nhận</a></li>
	<li><a data-toggle="tab" href="#danggiao">Đang giao</a></li>
	<li><a data-toggle="tab" href="#danhan">Đã giao</a></li>
	<li><a data-toggle="tab" href="#dahuy">Đã hủy</a></li>
</ul>

<div class="tab-content">
	<div id="tatca" class="tab-pane fade in active">
		<jsp:include page="_all-list.jsp" />
	</div>
	<div id="xacnhan" class="tab-pane fade">
		<jsp:include page="_wait-list.jsp" />
	</div>
	<div id="danggiao" class="tab-pane fade">
		<jsp:include page="_delivery-list.jsp" />
	</div>
	<div id="danhan" class="tab-pane fade">
		<jsp:include page="_deliverted-list.jsp" />
	</div>
	<div id="dahuy" class="tab-pane fade">
		<jsp:include page="_cancel-list.jsp" />
	</div>
</div>


