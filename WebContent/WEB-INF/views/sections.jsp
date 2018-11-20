<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/layouts/header.jsp" />
<title>sections</title>
</head>
<body>

	<!-- Navigation menu -->
	<jsp:include page="/WEB-INF/views/layouts/nav.jsp" />

	<div class="generic-container sm-container">
		<div class="card">
			<button class="card-header text-left" type="button"
				data-toggle="collapse" data-target="#collapseInput" role="button"
				aria-expanded="false" aria-controls="collapseInput"
				style="cursor: pointer;">${section.id != null ? 'Update' : 'Add new' }
				Section</button>


			<div class="collapse ${section.id != null ? 'show' : ''}"
				id="collapseInput">
				<div class="card-body">
					<form:form action="store" modelAttribute="section" method="POST">


						<form:hidden path="id" />
						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">Section</label>
							<div class="col-sm-6">
								<form:input path="name" class="form-control"
									placeholder="Section name" />
							</div>
						</div>

						<div class="clearfix">
							<hr>
							<input type="submit" class="btn btn-primary float-right"
								value="${section.id != null ? 'Update' : 'Save' }">
							${section.id != null ? '<a href="list" class="btn btn-info">Cancel</a>' : '' }
						</div>
					</form:form>

				</div>
			</div>
		</div>

		<div class="mt-2"></div>

		<div class="card">
			<div class="card-header" data-toggle="collapse">List of
				Sections</div>
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
							<c:forEach var="section" items="${sections}" varStatus="loop">

								<c:url var="update" value="/section/update">
									<c:param name="Id" value="${section.id}" />
								</c:url>

								<c:url var="delete" value="/section/delete">
									<c:param name="Id" value="${section.id}" />
								</c:url>

								<tr>
									<th class="custom-action-width" scope="row">${loop.index + 1}</th>
									<td>${section.name}</td>

									<td class="custom-action-width"><a href="${update}"
										class="btn btn-warning btn-sm">Edit</a>
										<button class="btn btn-danger btn-sm" data-toggle="modal"
											data-target="#confirmModal${section.id}">Delete</button></td>
								</tr>


								<div class="modal fade" id="confirmModal${section.id}">
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
												Delete <span class="text-danger font-weight-bold">${section.name}</span>
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
