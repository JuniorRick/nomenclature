<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/layouts/header.jsp" />
<title>goods</title>
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
					<c:when test="${good.id != null}">
						<spring:message code="good.update" />
					</c:when>
					<c:otherwise>
						<spring:message code="good.add.new" />
					</c:otherwise>
				</c:choose>
			</button>


			<div class="collapse show" id="collapseInput">
				<div class="card-body">
					<form:form action="store" modelAttribute="good" method="POST">

						<form:hidden path="id" />
						<form:hidden path="old_quantity" />
						<form:hidden path="remainder" />


						<spring:message code="contract" var="contractName" />
						<spring:message code="good" var="goodName" />
						<spring:message code="quantity" var="quantity" />
						<spring:message code="unit" var="unit" />


						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">${contractName}</label>
							<div class="col-sm-6">
								<select id="contract_id" name="contract_id" class="form-control">
									<option value="NONE"><spring:message
											code="contract.select" /></option>
									<c:forEach items="${contracts}" var="c">
										<option value="${c.id}"
											${contract.id != null && contract.id == c.id ? 'selected' : good.contract.id == c.id ? 'selected' : '' }>
											${c.name} [${c.number}]</option>
									</c:forEach>
								</select>
							</div>
						</div>


						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">${goodName}</label>
							<div class="col-sm-6">
								<form:input path="good" class="form-control"
									placeholder="${goodName}" required="required" />
							</div>
						</div>

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">${quantity}</label>
							<div class="col-sm-6">
								<form:input path="quantity" class="form-control"
									placeholder="${quantity}" required="required"
									pattern="\d+\.?\d*" />
							</div>
						</div>


						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">${unit}</label>
							<div class="col-sm-6">
								<form:input path="unit" class="form-control"
									placeholder="${unit}" required="required" />
							</div>
						</div>

						<spring:message code="update" var="btnUpdate" />
						<spring:message code="save" var="btnSave" />

						<div class="clearfix">
							<hr>
							<input type="submit" class="btn btn-primary float-right"
								value="${good.id != null ? btnUpdate : btnSave }">
							<c:if test="${good.id != null}">
								<a href="list" class="btn float-right mr-2 btn-info"> <spring:message
										code="cancel" />
								</a>
							</c:if>
						</div>
					</form:form>

				</div>
			</div>
		</div>

		<div class="mt-2"></div>

		<div class="card">
			<div class="card-header" data-toggle="collapse">
				<spring:message code="good.table.header" />
			</div>
			<div class="card-body">

				<div class="row">
					<table class="table ">
						<thead class=" col-6">
							<tr>
								<th scope="col">#</th>
								<th scope="col">${contractName}</th>
								<th scope="col">${goodName}</th>
								<th scope="col">${quantity}</th>
								<th scope="col">${unit}</th>

								<th class="" scope="col"><spring:message code="actions" /></th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="good" items="${goods}" varStatus="loop">

								<c:url var="update" value="/good/update">
									<c:param name="Id" value="${good.id}" />
								</c:url>

								<c:url var="delete" value="/good/delete">
									<c:param name="Id" value="${good.id}" />
								</c:url>

								<tr>
									<th class="" scope="row">${loop.index + 1}</th>

									<td data-toggle="tooltip" data-placement="top"
										title="${good.contract.name}">${good.contract.abbr}[
										${good.contract.number} ]</td>
									<td>${good.good}</td>
									<td>${good.quantity }(${good.unit})</td>
									<td>${good.remainder}(${good.unit})</td>

									<td class=""><a href="${update}"
										class="btn btn-warning btn-sm"><spring:message code="edit" /></a>
										<button class="btn btn-danger btn-sm" data-toggle="modal"
											data-target="#confirmModal${good.id}">
											<spring:message code="delete" />
										</button></td>
								</tr>


								<div class="modal fade" id="confirmModal${good.id}">
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
												<spna class="text-danger font-weight-bold">${good.good}</spna> <spring:message code="from" />
												<span class="text-danger font-weight-bold">${good.contract.name}</span>
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
