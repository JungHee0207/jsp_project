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
<h1>상세 페이지 보기</h1>
	<img alt="" src="/_fileUpload/${b.imagefile }">
	<table>
		<tr>
			<th>no.</th>
			<td>${b.bno }</td>
		</tr>
		<tr>
			<th>title</th>
			<td>${b.title }</td>
		</tr>
		<tr>
			<th>writer</th>
			<td>${b.writer }</td>
		</tr>
		<tr>
			<th>regdate</th>
			<td>${b.regdate }</td>
		</tr>
		<tr>
			<th>moddate</th>
			<td>${b.moddate }</td>
		</tr>
		<tr>
			<th>content</th>
			<td>${b.content }</td>
		</tr>
	</table>
	<a href="/brd/modify?bno=${b.bno }"><button>수정</button></a>
	<a href="/brd/remove?bno=${b.bno }"><button>삭제</button></a>
	<a href="/brd/list"><button>리스트</button></a>
	<br>
	<hr>
	
	<!-- comment line -->
	<div>
		<h3>comment line</h3>
		<input type="text" id="cmtWriter" value="${ses.id }" readonly="readonly" placeholder="writer..."><br>
		<!-- <input type="text" id="cmtText" placeholder="Add Comment..."><br> -->
		<textarea rows="5" cols="30" id="cmtText" placeholder="Add Comment..."></textarea>
		<button type="button" id="cmtAddBtn">post</button>
	</div>
	<!-- 댓글 출력 라인 -->
	<div id="commentLine">
		<div>
			<div>cno, bno, writer, regdate</div>
			<div>
				<input type="text" value="content...">
				<button type="button">mod</button>
				<button type="button">del</button>
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript">
		const bnoValue = `<c:out value="${b.bno }"></c:out>`;
		const loginUser = `<c:out value="${ses.id }"></c:out>`;
	</script>
	
<script type="text/javascript" src="/resources/boardDetail.js"></script>

<script type="text/javascript">
	printCommentList(bnoValue);
</script>
</body>
</html>