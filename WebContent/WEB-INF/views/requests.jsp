<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
			<div class="card-header" data-toggle="collapse">List of
				Order Requests</div>
			<div class="card-body">

				<div class="row">
					<table class="table ">
						<thead class=" col-6">
							<tr>
								<th scope="col">#</th>
								<th scope="col">Contract name</th>
								<th scope="col">Section</th>
								<th scope="col">Good</th>
								<th scope="col">Requested Quantity</th>
								
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

								<tr>
									<th class="" scope="row">${loop.index + 1}</th>
									
									<td>${order.purchase.contract.name}</td>
									<td>${order.section.name }</td>
									<td>${order.purchase.good}</td>
									<td>${order.quantity} (${order.purchase.unit})</td>
									
									<td class=""><a href="${update}"
										class="btn btn-warning btn-sm">Edit</a>
										<button class="btn btn-danger btn-sm" data-toggle="modal"
											data-target="#confirmModal${order.id}">Delete</button></td>
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
