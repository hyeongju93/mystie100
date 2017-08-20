<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="navigation">
	<ul>
		<li><a href="${pageContext.request.contextPath }/main">메인화면</a></li>
		<li><a href="${pageContext.request.contextPath }/guestbook/list">방명록</a></li>				<!--여기 고칠거 2개  -->
		<c:if test="${!(empty sessionScope.authUser) }">
			<li><a href="${pageContext.request.contextPath }/board/list?currNo=1">게시판</a></li>			<!-- 여기 고칠거2개 -->
		</c:if>
		<c:if test="${!(empty sessionScope.authUser) }">
			<li><a href="${pageContext.request.contextPath }/replyboard/list?currNo=1">댓글게시판</a></li>			<!-- 여기 고칠거2개 -->
		</c:if>
	</ul>
</div>
<!-- /navigation -->