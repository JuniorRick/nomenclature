<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/layouts/header.jsp" />
<title>providers</title>
</head>
<body>

	<!-- Navigation menu -->
	<jsp:include page="/WEB-INF/views/layouts/nav.jsp" />

	<div class="container mt-3 ">
		<a href="${pageContext.request.contextPath}/request/list" class="alert alert-info d-block">
			<strong>${requestsCount}</strong> order requests waiting for approval.
		</a>
		<a href="${pageContext.request.contextPath}/request/approved" class="alert alert-success d-block">
			<strong>${approvedCount}</strong> order requests approved.
		</a>
	</div>



	<!-- Page footer -->
	<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />
</body>
</html>