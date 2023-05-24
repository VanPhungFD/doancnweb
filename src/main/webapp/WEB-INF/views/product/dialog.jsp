<%@ page pageEncoding="utf-8"%>
<div id="send-dialog" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Gửi thông tin hàng hóa cho bạn bè,người
					thân</h4>
			</div>
			<div class="modal-body">
				<input id="id" type="hidden">
				<div class="form-group">
					<input id="from" class="form-control" placeholder="Người gửi">
				</div>
				<div class="form-group">
					<input id="to" class="form-control" placeholder="Người nhận">
				</div>
				<div class="form-group">
					<input id="subject" class="form-control" placeholder="Tiêu đề mail">
				</div>
				<div class="form-group">
					<textarea id="body" class="form-control" placeholder="Nội dung"
						rows="3"></textarea>
				</div>
			</div>
			<div class="modal-footer">
				<div class="pull-left" id="sending" style="display: none;">
					<img alt="" src="/static/images/small-loading.gif"> Đang gửi , Vui
					lòng đợi ...
				</div>
				<button type="button" class="btn btn-default btn-send-To-Friend">Gửi</button>
			</div>
		</div>

	</div>
</div>