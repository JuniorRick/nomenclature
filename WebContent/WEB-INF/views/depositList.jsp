<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/layouts/header.jsp" />
<title>requests</title>
</head>
<body>

	<!-- Navigation menu -->
	<jsp:include page="/WEB-INF/views/layouts/nav.jsp" />

	<div class="generic-container">


		<div class="card">
			<div class="card-header" data-toggle="collapse">
				<spring:message code="requests.deposit.table.header" />
			</div>
			<div class="card-body">

				<div class="row">
					<table class="table ">
						<thead class=" col-6">
							<tr>
								<th scope="col">#</th>
								<th scope="col"><spring:message code="contract" /></th>
								<th scope="col"><spring:message code="section" /></th>
								<th scope="col"><spring:message code="date" /></th>
								<th scope="col"><spring:message code="details" /></th>

								<th class="" scope="col">Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="request" items="${requests}" varStatus="loop">

								<tr>
									<th class="" scope="row">${loop.index + 1}</th>

									<td><b>[${request.contract.provider.name} | ${request.contract.number}]</b></td>
									<td>${request.section.name }</td>
									<td>${request.date}</td>
									<td>${request.details }</td>
									<td class="">
										<a class="btn btn-primary btn-sm"
											href="${pageContext.request.contextPath}/request/deposit/view/${request.id}">
												<spring:message code="view" />
										</a>
									</td>
								</tr>


								<div class="modal fade" id="confirmModal${request.id}">
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
												<spring:message code="requests.modal.text" />
												<span class="text-danger font-weight-bold">${request.contract.name}</span>
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
</body>
</html>
