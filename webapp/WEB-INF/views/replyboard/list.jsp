<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/replyboard/search" method="get">
					<input type="text" id="kwd" name="kwd" value="${param.kwd}">
					<input type="hidden" id="currNo" name="currNo" value="1">
					
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					<c:forEach items="${list}" var="vo">
					<tr>
						<td>${vo.rn }</td>
						<td><a href="${pageContext.request.contextPath }/replyboard/read?flag=1&hit=${vo.hit }&no=${vo.no }&currNo=${page.currNo}&kwd=${param.kwd}">${vo.title}</a></td>
						<td>${vo.name }</td>
						<td>${vo.hit }</td>
						<td>${vo.regDate }</td>
						
						<td>
							<c:if test="${sessionScope.authUser.no==vo.userNo }">
							<a href="${pageContext.request.contextPath }/replyboard/delete?no=${vo.no }&currNo=${page.currNo}" class="del">삭제</a>
							</c:if>
						</td>
						
					</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
					
						<c:if test="${page.currNo != 1 }">
						
						<c:if test="${empty param.kwd }">
							<li><a href="${pageContext.request.contextPath }/replyboard/list?currNo=${page.currNo-1}">◀</a></li>
						</c:if>
						<c:if test="${!(empty param.kwd) }">
							<li><a href="${pageContext.request.contextPath }/replyboard/search?currNo=${page.currNo-1}&kwd=${param.kwd}">◀</a></li>
						</c:if>
						
						</c:if>
						
						<c:forEach var="i" begin="${page.firstNo }" end="${page.endNo }" step="1">
							<c:if test="${i != page.currNo }">
								
								<c:if test="${empty param.kwd }">
									<li><a href="${pageContext.request.contextPath }/replyboard/list?currNo=${i }">${i }</a></li>
								</c:if>
								<c:if test="${!(empty param.kwd) }">
									<li><a href="${pageContext.request.contextPath }/replyboard/search?currNo=${i }&kwd=${param.kwd}">${i }</a></li>
								</c:if>
								
							</c:if>
							<c:if test="${i == page.currNo }">
								<li class="selected">${i }</li>
							</c:if>
						</c:forEach>
						<c:if test="${page.currNo != page.pageEnd }">
						
							
						<c:if test="${empty param.kwd }">
							<li><a href="${pageContext.request.contextPath }/replyboard/list?currNo=${page.currNo+1}">▶</a></li>
						</c:if>
						<c:if test="${!(empty param.kwd) }">
							<li><a href="${pageContext.request.contextPath }/replyboard/search?currNo=${page.currNo+1}&kwd=${param.kwd}">▶</a></li>
						</c:if>
						
						</c:if>
					
					</ul>
				</div>				
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/replyboard/writeform?currNo=${page.currNo}" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div>
</body>
</html>