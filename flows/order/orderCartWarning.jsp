<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thanks you</title>
</head>
<body>

<div class="container">
    <h2 class="alert alert-danger">주문을 취소하였습니다.</h2>
</div>
<div class="container">
    <p><a href="<c:url value="/books"/>" class="btn btn-primary">&laquo; 도서목록</a></p>
</div>
</body>
</html>
