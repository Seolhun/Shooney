<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/blog" var="blo"/><spring:url value="/file" var="file"/><spring:url value="/resources" var="resources" /><spring:url value="/resources/template" var="template"/>
<tag:layout>
<div class="call-action-v1 bg-color-light margin-bottom-20">
	<div class="container">
		<div class="call-action-v1-box">
			
		</div>
	</div>
</div>
<div class="container">
	<div class="panel panel-default padding-xs">
		<div class="row">
			<div class="col-sm-3">
				No
				<div class="input-group margin-bottom-20">
					<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
					<input type="hidden" id="blogId" value="${blog.blogId }">
					<div class="form-control rounded-right">${blog.blogId }</div>
				</div>
			</div>
			<!--  
			<div class="col-sm-2">
				Portfolio Type
				<div class="input-group margin-bottom-20">
					<span class="input-group-addon rounded-left"><i class="icon-user color-green"></i></span>
					<div class="form-control rounded-right" id="blog_entityname">${blog.entityName }</div>
				</div>
			</div>
			-->
			<div class="col-sm-3">
				Portfolio Type
				<div class="input-group margin-bottom-20">
					<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
					<div class="form-control rounded-right">${blog.portfolioType }</div>
				</div>
			</div>
			
			<div class="col-sm-3">
				Hits
				<div class="input-group margin-bottom-20">
					<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
					<div class="form-control rounded-right">${blog.hits }</div>
				</div>
			</div>
			
			<div class="col-sm-3">
				Like
				<div class="input-group margin-bottom-20">
					<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
					<div class="form-control rounded-right">${blog.likes }</div>
				</div>
			</div>
		</div>
		
		<div class="row margin-bottom-20">
			<div class="col-sm-12">
				File List
				<c:forEach var="fileData" items="${blog.fileDataList}" varStatus="status">
					<div class="input-group margin-bottom-5">
						<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
						<div class="form-control rounded-right">
							<c:choose>
								<c:when test="${fn:length(blog.fileDataList)>0}">
									<div class="row">
										<input type="hidden" id="${fileData.fileDataId }" value="${fileData.fileDataId }">
										<div class="col-sm-9">
											<a href="${file }/download/${fileData.fileDataId}">${fileData.fileDataOriginName }</a> (${fileData.fileDataSize }kb)
										</div>
										
										<c:if test="${status.first}">
											<div class="col-sm-3 text-right">
												더 보기
											</div>
										</c:if>
									</div>
								</c:when>
								<c:otherwise>
										첨부된 파일이 없습니다.
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12">
				Title
				<div class="input-group margin-bottom-20">
					<span class="input-group-addon rounded-left"><i class="icon-user color-green"></i></span>
					<div class="form-control rounded-right">${blog.title }</div>
				</div>
			</div>
			
			<div class="col-sm-12">
				<div>Content<br>
					<div class="input-group margin-bottom-20">
						<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
						<div class="form-control rounded-right" id="blog-content">${blog.content }</div>
					</div>
				</div>					
			</div>
			
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
				<a href="${blo}/${kind}/list" class="btn-u btn-u-blue btn-block rounded">List</a>
			</div>
			<c:if test="${accessUser.nickname.equals(blog.createdBy)}">
				<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
					<a href="${blo}/modify/${blog.blogId}" class="btn-u btn-u-dark-blue btn-block rounded">Modify</a>
				</div>
				<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
					<a href="${blo}/delete/${blog.blogId}" id="confirm" class="btn-u btn-u-default btn-block rounded">Delete</a>
				</div>
			</c:if>
		</div>
		
		<!-- 댓글 구현 -->
		<div class="row">
			<div class="col-sm-12">
				<hr>
				<div class="text-left"><h3>Reply</h3></div>
			</div>
			
			<div class="col-sm-12 text-center margin-bottom-20">
				<textarea name="content" rows="5" cols="auto" id="commentTextarea"></textarea>
				<div class="text-right" id="commentBtn" hidden="true">
					<button class="btn-u btn-u-default rounded" onclick="CommentService.cancelHidden();">Cancel</button>
					<button class="btn-u btn-u-dark-blue rounded" onclick="CommentService.commentSubmit();">Submit</button>
				</div>
			</div>
			
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
		
		<div class="row">
			<div class="col-sm-12">
				<div class="text-center">
		   			<button class="btn-u btn-brd btn-brd-hover btn-u-dark-blue btn-u-block margin-bottom-30">More Comments</button>
		   		</div>
		   	</div>
		</div>
	</div>
 </div>
</tag:layout>
<!-- Custom & Functional JS -->
<script type="text/javascript" src="${resources}/js/blog.js"></script>
<script type="text/javascript" src="${resources}/js/comment.js"></script>