<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer</title>
</head>
<body>
<div class="container">
    <form:form modelAttribute="order.customer" class="form-horizontal">
        <fieldset>
            <legend>고객 세부 사항</legend>
            <div class="form-group-row">
                <label class="col-sm2-control-label">고객 ID</label>
                <div class="col-sm-3">
                    <form:input path="customerId" class="form-control"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 control-label">성명</label>
                <div class="col-sm-3">
                    <form:input path="name" class="form-control"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 control-label">전화번호</label>
                <div class="col-sm-3">
                    <form:input path="phone" class="form-control"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 control-label">국가명</label>
                <div class="col-sm-3">
                    <form:input path="address.country" class="form-control"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 control-label">우편번호</label>
                <div class="col-sm-3">
                    <form:input path="addresss.zipCode" class="form-control"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 control-label">주소</label>
                <div class="col-sm-3">
                    <form:input path="address.addressName" class="form-control"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 control-label">세부주소</label>
                <div class="col-sm-3">
                    <form:input path="address.detailName" class="form-control"/>
                </div>
            </div>
            <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
            <div class="form-group-row">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" class="btn btn-primary" value="등록" name="_eventId_customerInfo"/>
                    <button class="btn btn-default" name="_eventId_cancel">취소</button>
                </div>
            </div>
        </fieldset>
    </form:form>
</div>
</body>
</html>