<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<h1>회원가입 페이지</h1>
	<form action="/user/insert" method="post">
		<div class="mb-3">
		<label for="exampleFormControlInput1" class="form-label">id</label>
		<input type="text" class="form-control" id="exampleFormControlInput1" name="id" placeholder="id">
		</div>
		<div class="mb-3">
		<label for="exampleFormControlInput1" class="form-label">pwd</label>
		<input type="password" class="form-control" id="exampleFormControlInput1" name="pwd" placeholder="pwd">
		</div>
		<div class="mb-3">
		<label for="exampleFormControlInput1" class="form-label">email</label>
		<input type="text" class="form-control" id="exampleFormControlInput1" name="email" placeholder="email">
		</div>
		<div class="mb-3">
		<label for="exampleFormControlInput1" class="form-label">phone</label>
		<input type="text" class="form-control" id="exampleFormControlInput1" name="phone" placeholder="phone">
		</div>
		<button type="submit" class="btn btn-success">회원가입</button>

	</form>
</div>

</body>
</html>