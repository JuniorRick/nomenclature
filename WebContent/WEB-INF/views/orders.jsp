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
				style="cursor: pointer;">Order Filter</button>


			<div class="collapse show" id="collapseInput">
				<div class="card-body">
					<form action="${pageContext.request.contextPath}/order/filter"
						method="GET">

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">Section</label>
							<div class="col-sm-6">
								<select id="section_id" name="section_id" class="form-control">
									<option value="NONE">--Select section--</option>
									<c:forEach items="${sections}" var="section">
										<option value="${section.id}">${section.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class=" form-group row">
							<label class="col-sm-2 col-form-label" for="">Contract</label>
							<div class="col-sm-6">
								<select id="contract_id" name="contract_id" class="form-control">
									<option value="NONE">--Select contract--</option>
									<c:forEach items="${contracts}" var="contract">
										<option value="${contract.id}">${contract.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>


						<div class="clearfix">
							<hr>
							<input type="submit" class="btn btn-primary float-right"
								value="Filter"> <a
								href="${pageContext.request.contextPath}/order/list"
								class="btn float-right mr-2 btn-info">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>

		<div class="mt-2"></div>

		<div class="card">
			<div class="card-header" data-toggle="collapse">
				Create order: <span
					style="font-size: 1.2em; font-style: italic; color: #03f">
					${section_name != null ? section_name : " "} | ${section_name != null ? purchases[0].contract.name : "All orders"}
				</span>

			</div>
			<div class="card-body">

				<div class="row">
					<table class="table ">
						<thead class=" col-6">
							<tr>
								<th scope="col">#</th>
								<th scope="col">Good</th>
								<th scope="col">Remaining Quantity</th>
								<th scope="col">Request Quantity</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="purchase" items="${purchases}" varStatus="loop">


								<c:url var="delete" value="/purchase/delete">
									<c:param name="Id" value="${purchase.id}" />
								</c:url>

								<tr>
									<th class="" scope="row">${loop.index + 1}</th>
									<td>${purchase.good}</td>
									<td>${purchase.remainder}(${purchase.unit})</td>
									<td><input type="text" ${section_id != null ? '' : 'disabled' } name="${purchase.id}" />
										(${purchase.unit})</td>

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
												Delete order from<span class="text-danger font-weight-bold">${purchase.contract.name}</span>
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
				<div class="clearfix">
					<hr>
					<button type="submit" class="btn btn-primary float-right"
						${section_id != null ? '' : 'disabled' } id="send-order"> Send Order</button>

					<a href="${pageContext.request.contextPath}/order/list"
						class="btn float-right mr-2 btn-info">Cancel</a>
				</div>
			</div>

		</div>
	</div>
	
	
	<div class="loading">
		Sending data...
	</div>

	<!-- Page footer -->
	<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />


	<script>
		$('#send-order')
			.click(
				function() {
					$('.loading').show();
					let request_quantities = [];
					let purchase_ids = [];
					$('input[type="text"]').each(function(index) {
						request_quantities[index] = $(this).val();
						purchase_ids[index] = $(this).attr('name');
	
					});
					
					var wrapper = {
						ids : purchase_ids,
						quantities: request_quantities
					}
					
					$.ajax({
						type: 'POST',
						url: '${pageContext.request.contextPath}/api/orders/send/${section_id}', 
						data: JSON.stringify(wrapper),
				        contentType: "application/json; charset=utf-8",
				        dataType: "json",
				        success: function(data){$('.loading').hide();},
				        failure: function(errMsg) {
				            $('.loading').hide();
				            alert(errMsg);
				        }
					})

				});
	</script>


</body>
</html>
