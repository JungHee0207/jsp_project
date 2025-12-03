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
	<h1>Hello Jsp World~!</h1>
	<h3> <a href="/brd/register">게시판 글쓰기</a> </h3>
	<h3><a href="/brd/list">게시판 리스트보기</a></h3>
	
	<c:if test="${ses.id eq null }">
		<h3><a href="/user/register">회원가입</a></h3>
		<div>
			<form action="/user/login" method="post">
				id: <input type="text" name="id" placeholder="id...">
				pwd: <input type="password" name="pwd" placeholder="pwd...">
				<button type="submit">login</button>
			</form>
		</div>
	</c:if>
	
	<div>
		<c:if test="${ses.id ne null }">
			welcome!! ${ses.id }(${ses.email })<br>
			마지막접속일: ${ses.lastlogin }
			<h3><a href="/user/logout">로그아웃</a></h3>
			<h3><a href="/user/modify">회원정보수정</a></h3>
		</c:if>
		
	</div>
	
	<script type="text/javascript">
		const login_msg = `<c:out value="${param.login_msg}" />`;
		if(login_msg == 'notUser'){
			alert('로그인 정보가 일치하지 않습니다.');
			window.location.href = "/";
		}
		
		const update_msg = `<c:out value="${param.update_msg}" />`;
		if(update_msg == 'OK'){
			alert('회원정보가 수정되었습니다. 다시 로그인해주세요.');
			window.location.href = "/";
		}
		const delete_msg = `<c:out value="${param.delete_msg}" />`;
		if(delete_msg == 'OK'){
			alert('회원탈퇴되었습니다.');
			window.location.href = "/";
		}

		
	</script>
	
</body>
</html>
