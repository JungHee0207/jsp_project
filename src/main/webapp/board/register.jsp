<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

</head>
<body>
<div class="container-sm">
	<h1>게시판 글쓰기 페이지</h1>
	<!-- enctype="multipart/form-data" : 첨부파일 보낼때 사용 -->
	<form action="/brd/insert" method="post" enctype="multipart/form-data">
		title : <input type="text" name="title" placeholder="title..."> <br>
		writer : <input type="text" name="writer" value="${ses.id }" readonly="readonly" placeholder="writer..."><br>
		content: <br>
		<textarea rows="10" cols="30" name="content" placeholder="content..."></textarea><br>
		file: <input type="file" name="imagefile">
		<button type="submit">등록</button>
	</form>
</div>
</body>
</html>