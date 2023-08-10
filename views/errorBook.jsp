<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
<head>
  <title>도서 상세 정보</title>
</head>
<body>
<div class="jumbotron">
  <div class="container">
    <h2 class="alert alert-danger">
      해당 도서가 존재하지 않습니다.<br>
      도서ID : ${invalidBookId}
    </h2>
  </div>
</div>
<div class="container">
  <p>${url}</p>
  <p>${exception}</p>
</div>
<div class="container">
  <p>
    <a href="<c:url value="/books"/>" class="btn btn-secondary">
      도서목록 &raquo; </a>
  </p>
</div>
</head>
</body>
</html>
