<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/layouts/header.jsp" />
<title>purchases</title>
</head>
<body>

	<!-- Navigation menu -->
	<jsp:include page="/WEB-INF/views/layouts/nav.jsp" />

	<div class="generic-container">
		<div class="card">
			<button class="card-header text-left" type="button"
				data-toggle="collapse" data-target="#collapseInput" role="button"
				aria-expanded="false" aria-controls="collapseInput"
				style="cursor: pointer;">${purchase.id != null ? 'Update' : 'Add new' }
				Purchase</button>


			<div class="collapse show"
				id="collapseInput">
				<div class="card-body">
					<form:form action="store" modelAttribute="purchase" method="POST">

						<form:hidden path="id" />	
						<form:hidden path="old_quantity" />	
						<form:hidden path="remainder" />
						
						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">Contract</label>
							<div class="col-sm-6">
								<select id="contract_id" name="contract_id" class="form-control">
									<option value="NONE">--Select contract--</option>
									<c:forEach items="${contracts}" var="c">
									       <option value="${c.id}" 
									       ${contract.id != null && contract.id == c.id ? 'selected' : purchase.contract.id == c.id ? 'selected' : '' }>
									            ${c.name} [${c.number}]
									        </option>
									</c:forEach>
								</select>
							</div>
						</div>
						

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">Good</label>
							<div class="col-sm-6">
								<form:input path="good" class="form-control"
									placeholder="Good" required="required"/>
							</div>
						</div>
						
						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">Quantity</label>
							<div class="col-sm-6">
								<form:input path="quantity" class="form-control"
									placeholder="Quantity" required="required" pattern="\d+\.?\d*"/>
							</div>
						</div>
						
						
						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">Unit</label>
							<div class="col-sm-6">
								<form:input path="unit" class="form-control"
									placeholder="unit" required="required"/>
							</div>
						</div>
						

						<div class="clearfix">
							<hr>
							<input type="submit" class="btn btn-primary float-right"
								value="${purchase.id != null ? 'Update' : 'Save' }">
							${purchase.id != null ? '<a href="list" class="btn float-right mr-2 btn-info">Cancel</a>' : '' }
						</div>
					</form:form>

				</div>
			</div>
		</div>

		<div class="mt-2"></div>

		<div class="card">
			<div class="card-header" data-toggle="collapse">List of
				Purchases</div>
			<div class="card-body">

				<div class="row">
					<table class="table ">
						<thead class=" col-6">
							<tr>
								<th scope="col">#</th>
								<th scope="col">Contract</th>
								<th scope="col">Good</th>
								<th scope="col">Quantity</th>
								<th scope="col">Remainder</th>
								
								<th class="" scope="col">Actions</th>
							</tr>
						</thead>
						<tbody>
							
							<c:forEach var="purchase" items="${purchases}" varStatus="loop">
								
								<c:url var="update" value="/purchase/update">
									<c:param name="Id" value="${purchase.id}" />
								</c:url>

								<c:url var="delete" value="/purchase/delete">
									<c:param name="Id" value="${purchase.id}" />
								</c:url>

								<tr>
									<th class="" scope="row">${loop.index + 1}</th>
									
									<td data-toggle="tooltip" data-placement="top" title="${purchase.contract.name}">
										${purchase.contract.abbr} [ ${purchase.contract.number} ]
									</td>
									<td>${purchase.good}</td>
									<td>${purchase.quantity } (${purchase.unit})</td>
									<td>${purchase.remainder} (${purchase.unit})</td>
									
									<td class=""><a href="${update}"
										class="btn btn-warning btn-sm">Edit</a>
										<button class="btn btn-danger btn-sm" data-toggle="modal"
											data-target="#confirmModal${purchase.id}">Delete</button></td>
								</tr>


								<div class="modal fade" id="confirmModal${purchase.id}">
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
												Delete purchase from <span class="text-danger font-weight-bold">${purchase.contract.name}</span>
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
