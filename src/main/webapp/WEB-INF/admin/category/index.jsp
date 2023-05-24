<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<h3>QUẢN LÝ LOẠI SẢN PHẨM</h3>
<ul class="nav nav-tabs">
	<li class="active"><a data-toggle="tab" href="#edit">LOẠI SẢN PHẨM</a></li>
	<li><a data-toggle="tab" href="#list">DANH SÁCH</a></li>
</ul>

<div class="tab-content">
	<div id="edit" class="tab-pane fade in active">
		<jsp:include page="_form.jsp" />
	</div>
	<div id="list" class="tab-pane fade">
		<jsp:include page="_table.jsp"></jsp:include>

	</div>
</div>


