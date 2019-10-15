<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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

			<c:set var="contains" value="false" />
			<c:forEach var="item" items="${privileges}">
				<c:if test="${item eq \"APPROVE_PRIVILEGE\"}">
					<c:set var="contains" value="true" />
				</c:if>
			</c:forEach>


			<div class=" mt-3 col-sm-7">
				<c:if test="${contains}">
					<a href="${pageContext.request.contextPath}/request/list"
						class="alert alert-info d-block"> <strong>${requestsCount}</strong>
						<spring:message code="index.messages.purchases.requests" />
					</a>
					<a href="${pageContext.request.contextPath}/request/approved"
						class="alert alert-success d-block"> <strong>${approvedCount}</strong>
						<spring:message code="index.messages.purchases.approved" />
					</a>
					<a href="${pageContext.request.contextPath}/request/deposit"
						class="alert alert-primary d-block"> <strong>${depositedCount}</strong>
						<spring:message code="index.messages.purchases.deposited" />
					</a>


					<c:if test="${empty settings.director || empty settings.executor || empty settings.tel}">
						<a href="${pageContext.request.contextPath}/settings/pdf"
							class="alert alert-danger d-block"> <spring:message
								code="index.messages.settings.pdf" />
						</a>
					</c:if>

				</c:if>
				
				<div>
					<div class=" mt-3 col-sm" style="border: 2px solid white; padding: 5px;">
						<div class="row">
							<div class="col-sm-2" style="font-size: 1.5em;">
								Filter: 
							</div>
							
							<div class="col-sm-10">
								<select id="year" name="year" class="form-control">
									<c:forEach items="${years}" var="y">
										<option value="${y}" ${ year.year == y ? 'selected="selected"' : '' }>${y}</option>
									</c:forEach>
									
								</select>
							</div>
						</div>
					</div>
				</div>

			</div>

			<div class=" mt-3 col-sm ">
				<div class="card" style="width: 18rem;">
					<div class="card-body">
						<h5 class="card-title">
							<spring:message code="login.header" />
						</h5>
						<h6 class="card-subtitle mb-2 text-muted">${user.getFirstName()}
							${user.getFirstName()}</h6>
						<%-- <p class="card-text">Roles:</p>
						${ user.getRoles().toString() }
						--%>
						<div class=" mt-3 col-sm ">
							<select id="locales" name="locales" class="form-control">
								<option value=""><spring:message code="locale.language" /></option>
								<option value="en">English</option>
								<option value="ro">Română</option>
							</select>
						</div>
						<div class="mt-3">
							<a href="logout" class="btn btn-primary float-right">Logout</a>
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>
	
	<!-- Page footer -->
	<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />

	<script type="text/javascript">
		$(document).ready(function() {
			$("#locales").change(function() {
				var selectedOption = $('#locales').val();
				if (selectedOption != '') {
					window.location.replace('?lang=' + selectedOption);
				}
			});
			
			$("#year").change(function() {
				var selectedOption = $('#year').val();
				if (selectedOption != '') {
					window.location.replace('?year=' + selectedOption);
				}
			});
			
		});
		
	</script>

</body>
</html>