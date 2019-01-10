<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/layouts/header.jsp" />
<meta charset="UTF-8">
<title>Settings</title>
</head>
<body>

	<!-- Navigation menu -->
	<jsp:include page="/WEB-INF/views/layouts/nav.jsp" />



	<jsp:include page="/WEB-INF/views/layouts/sidebar.jsp" />

	<div class="generic-container">
		<h3 class="text-center">Users Settings</h3>
		<div class="card">
			<button class="card-header text-left" type="button"
				data-toggle="collapse" data-target="#collapseInput" role="button"
				aria-expanded="false" aria-controls="collapseInput"
				style="cursor: pointer;">
				<c:choose>
					<c:when test="${user.id != null}">
						<spring:message code="settings.user.update" />
					</c:when>
					<c:otherwise>
						<spring:message code="settings.user.add.new" />
					</c:otherwise>
				</c:choose>

			</button>

			<div class="collapse ${user.id != null ? 'show' : ''}"
				id="collapseInput">
				<div class="card-body">
					<form:form action="user/store" modelAttribute="user" method="POST">


						<form:hidden path="id" />

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for=""> <spring:message
									code="settings.users.firstName" var="firstName" />
								${firstName}
							</label>
							<div class="col-sm-6">
								<form:input path="firstName" class="form-control"
									placeholder="${firstName}" required="required" />
							</div>
						</div>

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for=""> <spring:message
									code="settings.users.lastName" var="lastName" /> ${lastName}
							</label>
							<div class="col-sm-6">
								<form:input path="lastName" class="form-control"
									placeholder="${lastName}" required="required" />
							</div>
						</div>

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for=""> Email </label>
							<div class="col-sm-6">
								<form:input path="email" class="form-control"
									placeholder="Email" required="required" />
							</div>
						</div>

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for=""><spring:message
									code="role" /></label>
							<div class="col-sm-6">
								<select id="role_name" name="role_name" class="form-control">
									<option value="NONE"><spring:message
											code="role.select" /></option>
									<c:forEach items="${roles}" var="role">
										<option value="${role.name}"
											${user.roles[0].name == role.name ? 'selected' : '' }>
											${role.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for=""> <spring:message
									code="settings.users.password" var="password" /> ${password}
							</label>
							<div class="col-sm-6">
								<input type="password" id="password" name="password"
									class="form-control" placeholder="${password}"
									required="required" />
							</div>
						</div>

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for=""> <spring:message
									code="settings.users.password.confirm" />
							</label>
							<div class="col-sm-6">
								<input type="password" class="form-control"
									id="confirm_password" placeholder="${password}"
									required="required" />
							</div>
						</div>


						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for=""> <spring:message
									code="enabled" />
							</label>
							<div class="col-sm-6">
								<label class="" for="enabled"> <spring:message
										code="enabled" />
								</label> <input type="checkbox" name="enabled" id="enabled" checked>
							</div>
						</div>




						<div class="clearfix">
							<hr>
							<spring:message code="update" var="btnUpdate" />
							<spring:message code="save" var="btnSave" />

							<input type="submit" class="btn btn-primary float-right"
								value="${user.email != null ? btnUpdate : btnSave }"> <a
								href="users" class="btn float-right mr-2 btn-info"> <spring:message
									code="cancel" />
							</a>
						</div>
					</form:form>

				</div>
			</div>

		</div>

		<div class="mt-2"></div>

		<div class="card">
			<div class="card-header" data-toggle="collapse">
				<spring:message code="settings.user.table.header" />
			</div>
			<div class="card-body">

				<div class="row">
					<table class="table ">
						<thead class=" col-6">
							<tr>
								<th scope="col">#</th>
								<th scope="col">${firstName}</th>
								<th scope="col">${lastName}</th>
								<th scope="col">Email</th>
								<th scope="col"><spring:message code="role" /></th>
								<th scope="col"><spring:message code="enabled" /></th>

								<th class="" scope="col"><spring:message code="actions" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${users}" varStatus="loop">

								<c:url var="update" value="/settings/user/update">
									<c:param name="Email" value="${user.email}" />
								</c:url>

								<c:url var="delete" value="/settings/user/delete">
									<c:param name="Email" value="${user.email}" />
								</c:url>

								<tr>
									<th class="" scope="row">${loop.index + 1}</th>

									<td>${user.firstName}</td>
									<td>${user.lastName}</td>
									<td>${user.email}</td>
									<td>${user.roles}</td>
									<td>${user.enabled}</td>

									<td class=""><a href="${update}"
										class="btn btn-warning btn-sm"><spring:message code="edit" /></a>
										<button class="btn btn-danger btn-sm" data-toggle="modal"
											data-target="#confirmModal${user.id}">
											<spring:message code="delete" />
										</button></td>
								</tr>


								<div class="modal fade" id="confirmModal${user.id}">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">
													<spring:message code="delete" />
												</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">
												<spring:message code="delete" />
												<span class="text-danger font-weight-bold">${user.firstName}
													${user.lastName}</span>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-dismiss="modal">
													<spring:message code="cancel" />
												</button>
												<a href="${delete}" class="btn btn-danger"><spring:message
														code="confirm" /></a>
											</div>
										</div>
									</div>
								</div>

							</c:forEach>
						</tbody>
					</table>

				</div>
				<!-- <div class="card-footer text-muted">2 days ago</div> -->
			</div>

		</div>
	</div>




	<!-- Page footer -->
	<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />

	<script>
		var password = document.getElementById("password");
		var confirm_password = document.getElementById("confirm_password");

		$('input[type=submit]').click(function(e) {
			if(password.value !== confirm_password.value) {
				e.preventDefault();
				confirm_password.style.border = "1px solid red";
				confirm_password.style.boxShadow = '0px 0px 1px 1px #dc3545';
			}
			else {
				confirm_password.style.border = "1px solid #ced4da";
				confirm_password.style.boxShadow = 'none';
			}
		});
	</script>

</body>
</html>