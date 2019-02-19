<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/layouts/header.jsp" />
<meta charset="UTF-8">
<title>Settings</title>
</head>
<body>

	<!-- Navigation menu -->
	<jsp:include page="/WEB-INF/views/layouts/nav.jsp" />



	<jsp:include page="/WEB-INF/views/layouts/sidebar.jsp" />

	<div class="col-sm-10 content">
		<div class="generic-container sm-container">
			<div class="card">
				<button class="card-header text-left" type="button"
					data-toggle="collapse" data-target="#collapseInput" role="button"
					aria-expanded="false" aria-controls="collapseInput"
					style="cursor: pointer;">
					PDF
				</button>


				<div class="collapse show"
					id="collapseInput">
					<div class="card-body">
						<form:form action="pdf/store" modelAttribute="settings" method="POST">


							<form:hidden path="id" />
							
							<div class=" form-group row">
								<label class="col-sm-2 col-form-label" for="">Președintele gr. de lucru</label>
								<div class="col-sm-6">
									<form:input path="director" class="form-control"
										placeholder="director general" required="required" />
								</div>
							</div>
							
							<div class=" form-group row">
								<label class="col-sm-2 col-form-label" for="">Executor</label>
								<div class="col-sm-6">
									<form:input path="executor" class="form-control"
										placeholder="executor" required="required" />
								</div>
							</div>
							
							<div class=" form-group row">
								<label class="col-sm-2 col-form-label" for="">Tel</label>
								<div class="col-sm-6">
									<form:input path="Tel" class="form-control"
										placeholder="tel" required="required" />
								</div>
							</div>
							
							
							<spring:message code="save" var="btnSave" />
							<div class="clearfix">
								<hr>
								<input type="submit" class="btn btn-primary float-right"
									value=" ${btnSave}"> <a
									href="list" class="btn float-right mr-2 btn-info"> <spring:message
										code="cancel" /></a>
							</div>
						</form:form>

					</div>
				</div>
			</div>
		</div>
	</div>



	<!-- Page footer -->
	<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />


</body>
</html>