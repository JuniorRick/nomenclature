<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/layouts/header.jsp" />
<title>orders</title>
</head>
<body>

	<!-- Navigation menu -->
	<jsp:include page="/WEB-INF/views/layouts/nav.jsp" />

	<div class="generic-container">
		<div class="card">
			<button class="card-header text-left" type="button"
				data-toggle="collapse" data-target="#collapseInput" role="button"
				aria-expanded="false" aria-controls="collapseInput"
				style="cursor: pointer;">${order.id != null ? 'Update' : 'Add new' }
				Order</button>


			<div class="collapse ${order.id != null ? 'show' : ''}"
				id="collapseInput">
				<div class="card-body">
					<form:form action="store" modelAttribute="order" method="POST">


						<form:hidden path="id" />	
						
						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">Section</label>
							<div class="col-sm-6">
								<select id="section_id" name="section_id" class="form-control">
									<option value="NONE">--Select contract--</option>
									<c:forEach items="${sections}" var="section">
									       <option value="${section.id}" ${order.section.id == section.id ? 'selected' : '' }>
									            ${section.name}
									        </option>
									</c:forEach>
								</select>
							</div>
						</div>
						
						
						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">Purchase</label>
							<div class="col-sm-6">
								<select id="purchase_id" name="purchase_id" class="form-control">
									<option value="NONE">--Select purchase--</option>
									<c:forEach items="${purchases}" var="purchase">
									       <option value="${purchase.id}" ${order.purchase.id == purchase.id ? 'selected' : '' }>
									            ${purchase.good} [${purchase.remainder}]
									        </option>
									</c:forEach>
								</select>
							</div>
						</div>
						

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">Quantity</label>
							<div class="col-sm-6">
								<form:input path="quantity" class="form-control"
									placeholder="Quantity" />
							</div>
						</div>
						

						<div class="clearfix">
							<hr>
							<input type="submit" class="btn btn-primary float-right"
								value="${order.id != null ? 'Update' : 'Save' }">
							${order.id != null ? '<a href="list" class="btn float-right mr-2 btn-info">Cancel</a>' : '' }
						</div>
					</form:form>

				</div>
			</div>
		</div>

		<div class="mt-2"></div>

		<div class="card">
			<div class="card-header" data-toggle="collapse">List of
				Orders</div>
			<div class="card-body">

				<div class="row">
					<table class="table ">
						<thead class=" col-6">
							<tr>
								<th scope="col">#</th>
								<th scope="col">Contract name</th>
								<th scope="col">Section</th>
								<th scope="col">Good</th>
								<th scope="col">Remaining Quantity</th>
								<th scope="col">Request Quantity</th>
								<th scope="col">Approval</th>
								
								<th class="" scope="col">Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="order" items="${orders}" varStatus="loop">

								<c:url var="update" value="/order/update">
									<c:param name="Id" value="${order.id}" />
								</c:url>
	
								<c:url var="delete" value="/order/delete">
									<c:param name="Id" value="${order.id}" />
								</c:url>

								<c:url var="approve" value="/order/approve">
									<c:param name="Id" value="${order.id}" />
								</c:url>
								
								<tr>
									<th class="" scope="row">${loop.index + 1}</th>
									
									<td>${order.purchase.contract.name}</td>
									<td>${order.section.name }</td>
									<td>${order.purchase.good}</td>
									<td>${order.purchase.remainder} (${order.purchase.unit})</td>
									<td>${order.quantity} (${order.purchase.unit})</td>
									<td>
										<a href="${approve}" class="btn btn-warning btn-sm">Approve</a>
									</td>
									
									<td class="">
									
										<a href="${update}" class="btn btn-warning btn-sm">Edit</a>
										
										<button class="btn btn-danger btn-sm" data-toggle="modal"
											data-target="#confirmModal${order.id}">Delete</button>
									</td>
									
								</tr>


								<div class="modal fade" id="confirmModal${order.id}">
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
												Delete order from<span class="text-danger font-weight-bold">${order.purchase.contract.name}</span>
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
