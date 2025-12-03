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
<h1>상세 수정 페이지</h1>
	<img alt="" src="/_fileUpload/${b.imagefile }">
	<form action="/brd/update" method="post" enctype="multipart/form-data">
		<input type="hidden" name="bno" value="${b.bno }">
		<input type="hidden" name="imagefile" value="${b.imagefile }">
		<table>
			<tr>
				<th>no.</th>
				<td>${b.bno }</td>
			</tr>
			<tr>
				<th>title</th>
				<td><input type="text" name="title" value="${b.title }"></td>
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
				<td><textarea rows="10" cols="30" name="content">${b.content }</textarea></td>
			</tr>
			<tr>
				<th>imageFile</th>
				<td><input type="file" name="newFile"></td>
			</tr>
		</table>
		<button type="submit">수정</button>
		<button type="reset">취소</button>
	</form>
</body>
</html>