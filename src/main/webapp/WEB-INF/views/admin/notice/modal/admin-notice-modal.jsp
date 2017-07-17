<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Binding Modification Modal -->
<div class="modal fade" id="updateNoticeModal" role="dialog">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h3 class="modal-title">Update Menu</h3>
			</div>
			
			<div class="modal-body">
				<div class="row margin-bottom-10">
					<div class="col-sm-12">
						<div>No.
							<input type="text" class="form-control" id="menuIdInput" readonly="readonly">
						</div>
						<div>Name.
							<input type="text" class="form-control" id="menuNameInput">
						</div>
						<div>Type.
                            <select class="form-control" id="menuTypeInput">
                                <optgroup label="Type을 고르세요">
                                    <option value="normal">Normal</option>
                                    <option value="admin">Admin</option>
                                </optgroup>
                            </select>
						</div>
						<div>URL.
							<input type="text" class="form-control" id="menuUrlInput">
						</div>
						<div>Order.
							<input type="text" class="form-control" id="menuOrderInput">
						</div>
						<div>Depth.
							<input type="text" class="form-control" id="menuDepthInput">
						</div>
						<div>Parent Id.
							<input type="text" class="form-control" id="menuParentIdInput">
						</div>
					</div>
				</div>
			</div>
			
			<div class="modal-footer text-center">
				<button type="button" class="btn-u btn-u-dark-blue width-30" onclick="MenuService.menuUpdate()">Update</button>
				<button type="button" class="btn-u btn-u-default width-30" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="insertNoticeModal" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h3 class="modal-title">Insert New Menu</h3>
            </div>

            <div class="modal-body">
                <div class="row margin-bottom-10">
                    <div class="col-sm-12">
                        <div>Name.
                            <input type="text" class="form-control" id="menuNameInsert">
                        </div>
                        <div>Type.
							<select class="form-control" id="menuTypeInsert">
								<optgroup label="Type을 고르세요">
									<option value="normal">Normal</option>
									<option value="admin">Admin</option>
								</optgroup>
							</select>
                        </div>
                        <div>URL.
                            <input type="text" class="form-control" id="menuUrlInsert">
                        </div>
                        <div>Order.
                            <input type="text" class="form-control" id="menuOrderInsert">
                        </div>
                        <div>Depth.
                            <input type="text" class="form-control" id="menuDepthInsert">
                        </div>
                        <div>Parent Id.
                            <input type="text" class="form-control" id="menuParentIdInsert">
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal-footer text-center">
                <button type="button" class="btn-u btn-u-dark-blue width-30" onclick="MenuService.menuInsert()">Save</button>
                <button type="button" class="btn-u btn-u-default width-30" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
