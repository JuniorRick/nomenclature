<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/layouts/header.jsp" />
<title>request</title>
</head>
<body>

	<!-- Navigation menu -->
	<jsp:include page="/WEB-INF/views/layouts/nav.jsp" />

	<div class="generic-container sm-container">


		<div class="card">
			<div class="card-header" data-toggle="collapse">
				<b>[${request.contract.provider.name} | ${request.contract.number}] </b> | ${request.section.name } 
				<a href="${pageContext.request.contextPath}/request/deposit/cancel/${request.id}"
					class="btn btn-danger btn-sm float-right pl-3 pr-3 mr-3"><spring:message
						code="request.deposited.cancel" /></a> 
				
				<br />		
				<i>${request.details }</i>
			</div>
			<div class="card-body">

				<div class="row">
					<table class="table ">
						<thead class=" col-6">
							<tr>
								<th scope="col">#</th>
								<th scope="col"><spring:message code="good" /></th>
								<th scope="col"><spring:message code="quantity.remaining" /></th>
								<th scope="col"><spring:message code="quantity.request" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="purchase" items="${request.purchases}"
								varStatus="loop">
								<c:if test="${purchase.quantity != 0.0}">
									<tr>
										<th class="" scope="row">${loop.index + 1}</th>

										<td>${purchase.good.good}</td>
										<td>${purchase.good.remainder} (${purchase.good.unit})</td>
										<td>${purchase.quantity} (${purchase.good.unit})</td>
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
