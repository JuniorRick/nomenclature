<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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
				style="cursor: pointer;">
				<spring:message code="purchase.filter" />
			</button>


			<div class="collapse show" id="collapseInput">
				<div class="card-body">
					<form action="${pageContext.request.contextPath}/purchase/filter"
						method="GET">

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for=""><spring:message
									code="section" /></label>
							<div class="col-sm-6">
								<select id="section_id" name="section_id" class="form-control">
									<option value="NONE"><spring:message
											code="section.select" /></option>
									<c:forEach items="${sections}" var="section">
										<option value="${section.id}">${section.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for=""><spring:message
									code="contract" /></label>
							<div class="col-sm-6">
								<select id="contract_id" name="contract_id" class="form-control">
									<option value="NONE"><spring:message
											code="contract.select" /></option>
									<c:forEach items="${contracts}" var="contract">
										<option value="${contract.id}">${contract.name} [${contract.number}]</option>
									</c:forEach>
								</select>
							</div>
						</div>


						<div class="clearfix">
							<hr>
							<spring:message code="filter" var="filter"></spring:message>
							<input type="submit" class="btn btn-primary float-right"
								value="${filter}"> <a
								href="${pageContext.request.contextPath}/purchase/list"
								class="btn float-right mr-2 btn-info"><spring:message
									code="cancel" /></a>
						</div>
					</form>
				</div>
			</div>
		</div>

		<div class="mt-2"></div>

		<div class="card">
			<div class="card-header" data-toggle="collapse">
				<span style="font-size: 1.2em; font-style: italic; color: #03f">
					<c:choose>
						<c:when test="${section_name != null}">
							${section_name}  |  ${goods[0].contract.name }
						</c:when>
						<c:otherwise>
							<spring:message code="purchase.table.header" />
						</c:otherwise>
					</c:choose>

				</span>

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
							<c:forEach var="good" items="${goods}" varStatus="loop">

								<tr>
									<th class="" scope="row">${loop.index + 1}</th>
									<td>${good.good}</td>
									<td>${good.remainder} (${good.unit})</td>
									<td><input type="text"
										${section_id != null ? '' : 'disabled' } name="${good.id}" />
										(${good.unit})</td>

								</tr>

							</c:forEach>
						</tbody>
					</table>

				</div>
				<div class="clearfix">
					<hr>
					<button class="btn btn-primary float-right"
						${section_id != null ? '' : 'disabled' } id="send-purchase">
						<spring:message code="purchase.button.send" />
					</button>

					<a href="${pageContext.request.contextPath}/purchase/list"
						class="btn float-right mr-2 btn-info"><spring:message
							code="cancel" /></a>
				</div>
			</div>

		</div>
	</div>


	<div class="loading">
		<spring:message code="purchase.message.send" />
	</div>

	<!-- Page footer -->
	<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />


	<script>
		$('#send-purchase')
				.click(
						function() {
							$('.loading').show();
							let request_quantities = [];
							let good_ids = [];
							$('input[type="text"]').each(
									function(index) {
										request_quantities[index] = $(this)
												.val() != "" ? $(this).val()
												: "0";
										good_ids[index] = $(this).attr('name');

									});

							var wrapper = {
								ids : good_ids,
								quantities : request_quantities
							}

							$
									.ajax({
										type : 'POST',
										url : '${pageContext.request.contextPath}/request/send/${section_id}',
										data : JSON.stringify(wrapper),
										contentType : "application/json; charset=utf-8",
										dataType : "json",
										success : function(data) {
											$('.loading').hide();
											window.location.href = '${pageContext.request.contextPath}/request/list';
										},
										failure : function(errMsg) {
											$('.loading').hide();
											alert(errMsg);
										}
									});

						});
	</script>


</body>
</html>
