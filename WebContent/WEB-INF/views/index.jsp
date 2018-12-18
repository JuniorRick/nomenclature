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

	<div class="container">
		<div class="row ">

				<div class=" mt-3 col-sm-7">
					<a href="${pageContext.request.contextPath}/request/list"
						class="alert alert-info d-block"> <strong>${requestsCount}</strong>
						order requests waiting for approval.
					</a> <a href="${pageContext.request.contextPath}/request/approved"
						class="alert alert-success d-block"> <strong>${approvedCount}</strong>
						order requests approved.
					</a>


				</div>

				<div class=" mt-3 col-sm ">
					<div class="card" style="width: 18rem;">
						<div class="card-body">
							<h5 class="card-title">Logged in as:</h5>
							<h6 class="card-subtitle mb-2 text-muted">${user.getFirstName()}
								${user.getFirstName()}</h6>
							<p class="card-text">Roles:</p>
							${ user.getRoles().toString() }
							<!-- <a href="#" class="card-link">Card link</a> -->
						</div>
					</div>

				</div>
		</div>
	</div>

	<!-- Page footer -->
	<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />
</body>
</html>