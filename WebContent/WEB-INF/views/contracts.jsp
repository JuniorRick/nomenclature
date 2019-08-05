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
					<c:when test="${contract.id != null}">
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
							<label class="col-sm-2 col-form-label" for=""><spring:message
									code="provider" /></label>
							<div class="col-sm-6">
								<select id="provider_id" name="provider_id" class="form-control">
									<option value="NONE"><spring:message
											code="provider.select" /></option>
									<c:forEach items="${providers}" var="provider">
										<option value="${provider.id}"
											${contract.provider.id == provider.id ? 'selected' : '' }>
											${provider.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>


						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for=""> <spring:message
									code="contract.number" var="contractNumber" />
								${contractNumber}
							</label>
							<div class="col-sm-6">
								<form:input path="number" class="form-control"
									placeholder="${contractNumber}" required="required" />
							</div>
						</div>

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for=""> <spring:message
									code="contract.name" var="contractName" /> ${contractName}
							</label>
							<div class="col-sm-6">
								<form:input path="name" class="form-control"
									placeholder="${contractName}" required="required" />
							</div>
						</div>

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for=""> <spring:message
									code="contract.abbr" var="contractAbbr" /> ${contractAbbr}
							</label>
							<div class="col-sm-6">
								<form:input path="abbr" class="form-control"
									placeholder="${contractAbbr}" required="required" />
							</div>
						</div>

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for=""> <spring:message
									code="date.start" />
							</label>
							<div class="col-sm-6">
								<input type="date" id="startDate" class="form-control"
									required="required" name="startDate"
									value="${contract.startDate}" />
							</div>
						</div>

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for=""> <spring:message
									code="date.end" />
							</label>
							<div class="col-sm-6">
								<input type="date" id="expiryDate" class="form-control"
									required="required" name="expiryDate"
									value="${contract.expiryDate}" />
							</div>
						</div>
						<div class="clearfix">
							<hr>
							<spring:message code="update" var="btnUpdate" />
							<spring:message code="save" var="btnSave" />

							<input type="submit" class="btn btn-primary float-right"
								value="${contract.id != null ? btnUpdate : btnSave }">
								<a href="list" class="btn float-right mr-2 btn-info"> <spring:message
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
				<spring:message code="contract.table.header" />
			</div>
			<div class="card-body">

				<div class="row">
					<table class="table ">
						<thead class=" col-6">
							<tr>
								<th scope="col">#</th>
								<th scope="col"><spring:message code="provider"></spring:message></th>
								<th scope="col">${contractName}</th>
								<th scope="col">${contractAbbr}</th>
								<th scope="col">${contractNumber}</th>
								<th scope="col"><spring:message code="date.start" /></th>
								<th scope="col"><spring:message code="date.end" /></th>

								<th class="" scope="col"><spring:message code="actions" /></th>
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
								
								<c:url var="generate" value="/contract/xls/deposit.xlsx">
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

									<td class="btn-group">
										<a href="${update}"	class="btn btn-warning btn-sm">
											<spring:message code="edit" />
										</a>
										<button class="btn btn-danger btn-sm" data-toggle="modal"
											data-target="#confirmModal${contract.id}">
											<spring:message code="delete" />
										</button>
										<a href="${generate}"	class="btn btn-primary btn-sm">
											<spring:message code="report" /> (xlsx)
										</a>
									</td>
								</tr>


								<div class="modal fade" id="confirmModal${contract.id}">
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
												<span class="text-danger font-weight-bold">${contract.name}</span>
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
