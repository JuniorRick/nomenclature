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

	<div class="generic-container sm-container">
		<div class="card">
			<button class="card-header text-left" type="button"
				data-toggle="collapse" data-target="#collapseInput" role="button"
				aria-expanded="false" aria-controls="collapseInput"
				style="cursor:pointer;">${provider.id != null ? 'Update' : 'Add new' }	Provider</button>


			<div class="collapse ${provider.id != null ? 'show' : ''}" id="collapseInput">
				<div class="card-body">
					<form:form action="store" modelAttribute="provider" method="POST">


						<form:hidden path="id" />
						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">Provider</label>
							<div class="col-sm-6">
								<form:input path="name" class="form-control"
									placeholder="Provider name" />
							</div>
						</div>


						<!-- Modal footer -->
						<div class="modal-footer">
							<input type="submit" class="btn btn-primary" value="${provider.id != null ? 'Update' : 'Save' }">
							${provider.id != null ? '<a href="list" class="btn btn-info">Cancel</a>' : '' }
						</div>
					</form:form>

				</div>
			</div>
		</div>

		<div class="mt-2"></div>

		<div class="card">
			<div class="card-header" data-toggle="collapse">List of
				Providers</div>
			<div class="card-body">

				<div class="row">
					<table class="table ">
						<thead class=" col-6">
							<tr>
								<th scope="col">#</th>
								<th scope="col">Name</th>
								<th class="custom-action-width" scope="col">Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="provider" items="${providers}" varStatus="loop">

								<c:url var="update" value="/provider/update">
									<c:param name="Id" value="${provider.id}" />
								</c:url>

								<c:url var="delete" value="/provider/delete">
									<c:param name="Id" value="${provider.id}" />
								</c:url>

								<tr>
									<th class="custom-action-width" scope="row">${loop.index + 1}</th>
									<td>${provider.name}</td>

									<td class="custom-action-width"><a href="${update}"
										class="btn btn-warning btn-sm">Edit</a>
										<button class="btn btn-danger btn-sm" data-toggle="modal"
											data-target="#confirmModal${provider.id}">Delete</button></td>
								</tr>


								<div class="modal fade" id="confirmModal${provider.id}">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">Modal
													title</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">
												Delete <span class="text-danger font-weight-bold">${provider.name}</span>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-dismiss="modal">Cancel</button>
												<a href="${delete}" class="btn btn-danger">Confirm</a>
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
</body>
</html>
