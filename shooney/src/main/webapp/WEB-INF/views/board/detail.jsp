<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<spring:url value="/board" var="bo"/>
<c:url value="/resources/" var="resources" />
<c:url value="/resources/template" var="template"/>
<tag:layout>
<div class="call-action-v1 bg-color-light margin-bottom-20">
	<div class="container">
		<div class="call-action-v1-box">
			
		</div>
	</div>
</div>
<div class="container">
	<div class="panel panel-default row">
		<div class="col-sm-12">
			<div class="margin-bottom-20"></div>
			<div class="col-sm-2">
				<div class="input-group margin-bottom-20">
					<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
					<div class="form-control rounded-right" id="board_id">${board.id }</div>
				</div>
			</div>
			
			<div class="col-sm-2">
				<div class="input-group margin-bottom-20">
					<span class="input-group-addon rounded-left"><i class="icon-user color-green"></i></span>
					<div class="form-control rounded-right" id="board_entityname">${board.entityName }</div>
				</div>
			</div>
			
			<div class="col-sm-3">
				<div class="input-group margin-bottom-20">
					<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
					<div class="form-control rounded-right">${board.pfName }</div>
				</div>
			</div>
			
			<div class="col-sm-3">
				<div class="input-group margin-bottom-20">
					<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
					<div class="form-control rounded-right">${board.hits }</div>
				</div>
			</div>
			
			<div class="col-sm-2">
				<div class="input-group margin-bottom-20">
					<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
					<div class="form-control rounded-right">${board.likes }</div>
				</div>
			</div>
			
			<div class="col-sm-12">
				<div class="input-group margin-bottom-20">
					<span class="input-group-addon rounded-left"><i class="icon-user color-green"></i></span>
					<div class="form-control rounded-right">${board.title }</div>
				</div>
			</div>
			
			<div class="col-sm-12">
				<div>Content<br>
					<div class="input-group margin-bottom-20">
						<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
						<div class="form-control rounded-right" id="board-content">${board.content }</div>
					</div>
				</div>					
			</div>
			<div class="col-sm-12 margin-bottom-20">
				<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
					<a href="${bo}/${kind}/list" class="btn-u btn-u-blue btn-block rounded">List</a>
				</div>
				<c:if test="${accessUser.nickname.equals(board.writer)}">
					<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<a href="${bo}/${kind}/m${board.id}" class="btn-u btn-u-dark-blue btn-block rounded">Modify</a>
					</div>
					<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<a href="${bo}/${kind}/d${board.id}" id="confirm" class="btn-u btn-u-default btn-block rounded">Delete</a>
					</div>
				</c:if>
			</div>
		</div>
		<!-- 댓글 구현 -->
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<hr>
					<div class="text-left"><h3>Reply</h3></div>
				</div>
				<div class="col-sm-12 text-center margin-bottom-20">
					<textarea name="content" rows="5" cols="auto" id="commentTextarea"></textarea>
					<div class="text-right" id="commentBtn" hidden="true">
						<button class="btn-u btn-u-default rounded" onclick="cancelHidden();">Cancel</button>
						<button class="btn-u btn-u-dark-blue rounded" id="commentSubmit">Submit</button>
					</div>
				</div>
				<%-- <c:if test="${!empty comments}"> --%>
					<div class="col-sm-12">
					<hr>
						<div class="col-sm-12" id="commentDiv">
							<%-- 
							<c:forEach items="${comments}" var="i">
								<div class="col-sm-4 col-xs-4">
									<div class="input-group margin-bottom-20">
										<span class="input-group-addon rounded-left"><i class="icon-user color-green"></i></span>
										<div class="form-control rounded-right" >${i.writer}</div>
									</div>
								</div>
															
								<div class="col-sm-4 col-xs-4">
									<div class="input-group margin-bottom-20">
										<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
										<div class="form-control rounded-right">${i.likes}</div>
									</div>
								</div>
								
								<div class="col-sm-4 col-xs-4">
									<div class="input-group margin-bottom-20">
										<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
										<div class="form-control rounded-right"><fmt:formatDate value="${i.latestDate}" pattern="yy-MM-dd, HH:mm"/></div>
									</div>
								</div>
		
								<div class="col-sm-12">
									<div class="input-group margin-bottom-20">
										<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
										<div class="form-control rounded-right" id="comment-content">${i.content}</div>
									</div>
									<hr>
								</div>
								
								<c:if test="${accessUser.nickname.equals(i.writer)}">
									<div class="col-sm-12 text-right">
										<button class="btn-u btn-u-default rounded" onclick="commentModify();">Modify</button>
										<button class="btn-u btn-u-dark-blue rounded" onclick="commentDelete();">Delete</button>
									</div>
								</c:if>
							</c:forEach>  
							--%>
						</div>
					</div>

					<div class="col-sm-12">
						<div class="text-center">
				   			<button class="btn-u btn-brd btn-brd-hover btn-u-dark-blue btn-u-block margin-bottom-30">More Comments</button>
				   		</div>
				   	</div>
				<%-- </c:if> --%>
			</div>
		</div>
	</div>
 </div>
</tag:layout>
<script>
	var csrfParameter = '${_csrf.parameterName}';
	var csrfToken = '${_csrf.token}';
	var csrfHeader = '${_csrf.headerName}';
	var kind="${board.entityName}";
	var board_id="${board.id }";
	var accessUser="${accessUser.nickname}";
</script>
<!-- Custom & Functional JS -->
<script type="text/javascript" src="${resources}/js/board.js"></script>