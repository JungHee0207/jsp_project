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
<h1>회원 정보 수정 페이지</h1>
<form action="/user/update" method="post">
	id: <input type="text" name="id" value="${ses.id }" readonly="readonly" placeholder="id..."><br>
	pwd: <input type="password" name="pwd" value="${ses.pwd }" placeholder="password..."><br>
	email: <input type="text" name="email" value="${ses.email }" placeholder="email..."><br>
	phone: <input type="text" name="phone" value="${ses.phone }" placeholder="phone..."><br>
	regdate: <input type="text" name="regdate" value="${ses.regdate }" readonly="readonly"><br>
	lastlogin: <input type="text" name="lastlogin" value="${ses.lastlogin }" readonly="readonly"><br>
	<button type="submit">수정</button><br>
</form>
<a href="/user/remove"><button>회원탈퇴</button></a>

<script type="text/javascript">

	const update_msg = `<c:out value="${update_msg}" />`;
	if(update_msg == 'Fail'){
		alert('회원정보 수정 실패 > 다시시도');
	}
	const delete_msg = `<c:out value="${delete_msg}" />`;
	if(delete_msg == 'Fail'){
		alert('회원탈퇴 실패 > 다시시도');
	}
	
</script>
</body>
</html>