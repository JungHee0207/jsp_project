<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시판 보기 화면</h1>

<div>
	<form action="/brd/list" method="get">
		<div>
			<select name="type">
				<option ${ph.pgvo.type eq null ? 'selected' : ''} value="">Choose...</option>
				<option ${ph.pgvo.type eq 't' ? 'selected' : ''} value="t">title</option>
				<option ${ph.pgvo.type eq 'w' ? 'selected' : ''} value="w">writer</option>
				<option ${ph.pgvo.type eq 'c' ? 'selected' : ''} value="c">content</option>
			</select>
			<input type="text" name="keyword" placeholder="Search..." value="${ph.pgvo.keyword }">
			<input type="hidden" name="pageNo" value="1">
			<input type="hidden" name="qty" value="${ph.pgvo.qty }">
			<button type="submit">검색</button>
			<%-- ${ph.pgvo.type ne null && ph.pgvo.type ne ''} --%>
			<c:if test="${not empty ph.pgvo.type }">
				<span>totalCount : ${ph.totalCount }</span>			
			</c:if>
		</div>
	</form>
</div>
<br>

<table border="1">
	<tr>
		<th>no.</th>
		<th>title</th>
		<th>writer</th>
		<th>regdate</th>
	</tr>
	<c:forEach items="${list }" var="b">
		<tr>
			<td>${b.bno }</td>
			<td>
				<a href="/brd/detail?bno=${b.bno }">
					<!-- 썸네일 이미지 추가 -->
					<img alt="" src="/_fileUpload/th_${b.imagefile }">
					${b.title }
				</a>
			</td>
			<td>${b.writer }</td>
			<td>${b.regdate }</td>
		</tr>
	</c:forEach>
	
</table>
<!-- 가져온 ph 정보를 이용하여 하단 forEach를 구성 -->
<div>
	<!-- 이전 -->
	<c:if test="${ph.prev }">
		<a href="/brd/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"> < </a>
	</c:if>
	
	<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i"> 
		<a href="/brd/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i }</a>	 
	</c:forEach>
	<!-- 다음 -->
	<c:if test="${ph.next }"> 
		<a href="/brd/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"> > </a>
	</c:if>
	
</div>

<a href="/index.jsp">index로 이동</a>
</body>
</html>