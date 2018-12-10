<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/layouts/header.jsp" />
<title>request</title>
</head>
<body>

	<!-- Navigation menu -->
	<jsp:include page="/WEB-INF/views/layouts/nav.jsp" />

	<div class="generic-container">


		<div class="card">
			<div class="card-header" data-toggle="collapse">Request from
				${request.contract.name} | ${request.section.name }</div>
			<div class="card-body">

				<div class="row">
					<table class="table ">
						<thead class=" col-6">
							<tr>
								<th scope="col">#</th>
								<th scope="col">Good</th>
								<th scope="col">Remaining Quantity</th>
								<th scope="col">Requested Quantity</th>

								<th class="" scope="col">Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="order" items="${request.orders}" varStatus="loop">

								<c:url var="update" value="/order/update">
									<c:param name="Id" value="${order.id}" />
								</c:url>

								<c:url var="delete" value="/order/delete">
									<c:param name="Id" value="${order.id}" />
								</c:url>

								<tr>
									<th class="" scope="row">${loop.index + 1}</th>

									<td>${order.purchase.good}</td>
									<td>${order.purchase.remainder}(${order.purchase.unit})</td>
									<td>${order.quantity} (${order.purchase.unit})</td>

									<td class="">
										Placeholder
									</td>
								</tr>

							</c:forEach>
						</tbody>
					</table>

				</div>
				<div class="card-footer text-muted clearfix">
					<button class="btn btn-primary float-right" id="approve">Approve</button>
				</div>
			</div>

		</div>
	</div>

	<div class="loading">
		Approving request...
	</div>


	<!-- Page footer -->
	<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />

</body>
</html>
