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
				${request.contract.name} | ${request.section.name }
				<a href="${pageContext.request.contextPath}/request/cancel/${request.id}" 
					class="btn btn-info btn-sm float-right pl-3 pr-3 mr-3">Cancel Approval</a>		
				<a href="${pageContext.request.contextPath}/request/pdf/${request.id}" 
					class="btn btn-primary btn-sm float-right pl-3 pr-3 mr-3">PDF</a>
			</div>
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
								<c:if test="${order.quantity != 0.0}">
									<tr>
										<th class="" scope="row">${loop.index + 1}</th>

										<td>${order.purchase.good}</td>
										<td>${order.purchase.remainder}(${order.purchase.unit})</td>
										<td>${order.quantity}(${order.purchase.unit})</td>

										<td class="">Placeholder</td>
									</tr>
								</c:if>

							</c:forEach>
						</tbody>
					</table>

				</div>

			</div>

		</div>
	</div>


	<!-- Page footer -->
	<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />

</body>
</html>
