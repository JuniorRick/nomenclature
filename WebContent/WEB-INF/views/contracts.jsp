<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/layouts/header.jsp" />
<title>contracts</title>
</head>
<body>

	<!-- Navigation menu -->
	<jsp:include page="/WEB-INF/views/layouts/nav.jsp" />

	<div class="generic-container">
		<div class="card">
			<button class="card-header text-left" type="button"
				data-toggle="collapse" data-target="#collapseInput" role="button"
				aria-expanded="false" aria-controls="collapseInput"
				style="cursor: pointer;">
				<c:choose>
					<c:when test="contract.id != null ">
						<spring:message code="contract.update" />
					</c:when>
					<c:otherwise>
						<spring:message code="contract.add.new" />
					</c:otherwise>
				</c:choose>

			</button>


			<div class="collapse ${contract.id != null ? 'show' : ''}"
				id="collapseInput">
				<div class="card-body">
					<form:form action="store" modelAttribute="contract" method="POST">


						<form:hidden path="id" />

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">Provider</label>
							<div class="col-sm-6">
								<select id="provider_id" name="provider_id" class="form-control">
									<option value="NONE">--Select provider--</option>
									<c:forEach items="${providers}" var="provider">
										<option value="${provider.id}"
											${contract.provider.id == provider.id ? 'selected' : '' }>
											${provider.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>


						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for=""><spring:message code="number" /></label>
							<div class="col-sm-6">
								<form:input path="number" class="form-control"
									placeholder="Number" required="required" />
							</div>
						</div>

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">Name</label>
							<div class="col-sm-6">
								<form:input path="name" class="form-control"
									placeholder="Contract name" required="required" />
							</div>
						</div>

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">Abbr</label>
							<div class="col-sm-6">
								<form:input path="abbr" class="form-control"
									placeholder="Abbreviation" required="required" />
							</div>
						</div>

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">Start Date</label>
							<div class="col-sm-6">
								<input type="date" id="startDate" class="form-control"
									required="required" name="startDate" placeholder="Start Date"
									value="${contract.startDate}" />
							</div>
						</div>

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">End Date</label>
							<div class="col-sm-6">
								<input type="date" id="expiryDate" class="form-control"
									required="required" name="expiryDate" placeholder="End Date"
									value="${contract.expiryDate}" />
							</div>
						</div>
						<div class="clearfix">
							<hr>
							<input type="submit" class="btn btn-primary float-right"
								value="${contract.id != null ? 'Update' : 'Save' }">
							${contract.id != null ? '<a href="list" class="btn float-right mr-2 btn-info">Cancel</a>' : '' }
						</div>
					</form:form>

				</div>
			</div>
		</div>

		<div class="mt-2"></div>

		<div class="card">
			<div class="card-header" data-toggle="collapse">List of
				Contracts</div>
			<div class="card-body">

				<div class="row">
					<table class="table ">
						<thead class=" col-6">
							<tr>
								<th scope="col">#</th>
								<th scope="col">Provider</th>
								<th scope="col">Contract name</th>
								<th scope="col">Abbr</th>
								<th scope="col">Number</th>
								<th scope="col">Start Date</th>
								<th scope="col">Expiry Date</th>

								<th class="" scope="col">Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="contract" items="${contracts}" varStatus="loop">

								<c:url var="update" value="/contract/update">
									<c:param name="Id" value="${contract.id}" />
								</c:url>

								<c:url var="delete" value="/contract/delete">
									<c:param name="Id" value="${contract.id}" />
								</c:url>

								<tr>
									<th class="" scope="row">${loop.index + 1}</th>

									<td>${contract.provider.name}</td>
									<td>${contract.name}</td>
									<td>${contract.abbr }</td>
									<td>${contract.number}</td>
									<td>${contract.startDate}</td>
									<td>${contract.expiryDate}</td>

									<td class=""><a href="${update}"
										class="btn btn-warning btn-sm">Edit</a>
										<button class="btn btn-danger btn-sm" data-toggle="modal"
											data-target="#confirmModal${contract.id}">Delete</button></td>
								</tr>


								<div class="modal fade" id="confirmModal${contract.id}">
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
												Delete <span class="text-danger font-weight-bold">${contract.name}</span>
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
