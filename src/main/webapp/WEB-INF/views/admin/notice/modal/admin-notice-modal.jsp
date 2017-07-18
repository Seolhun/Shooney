<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Binding Modification Modal -->
<div class="modal fade" id="insertNoticeModal" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h3 class="modal-title">Insert New Notice</h3>
			</div>
			<div class="modal-body">
				<div class="row margin-bottom-10">
					<div class="col-sm-12">
						<div class="margin-bottom-10">URI.
							<input type="text" class="form-control" id="noticeUri">
						</div>
						<div class="margin-bottom-10">Content.
							<textarea type="text" class="form-control noticeContent" id="noticeContent"></textarea>
						</div>
						<div class="margin-bottom-10">State.
							<input type="text" class="form-control" id="noticeState">
						</div>
					</div>
				</div>
			</div>
			
			<div class="modal-footer text-center">
				<button type="button" class="btn-u btn-u-dark-blue width-30" onclick="NoticeService.noticeUpdate()">Update</button>
				<button type="button" class="btn-u btn-u-default width-30" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="updateNoticeModal" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h3 class="modal-title">Update Notice</h3>
            </div>

            <div class="modal-body">
                <div class="row margin-bottom-10">
                    <div class="col-sm-12">
						<div class="margin-bottom-10">No.
							<input type="text" class="form-control" id="noticeIdUpdate" readonly="readonly">
						</div>
						<div class="margin-bottom-10">URI.
							<input type="text" class="form-control" id="noticeUriUpdate">
						</div>
						<div class="margin-bottom-10">Content.
							<textarea type="text" class="form-control noticeContent" id="noticeContentUpdate"></textarea>
						</div>
						<div class="margin-bottom-10">State.
							<input type="text" class="form-control" id="noticeStateUpdate">
						</div>
                    </div>
                </div>
            </div>

            <div class="modal-footer text-center">
                <button type="button" class="btn-u btn-u-dark-blue width-30" onclick="NoticeService.noticeInsert()">Save</button>
                <button type="button" class="btn-u btn-u-default width-30" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
