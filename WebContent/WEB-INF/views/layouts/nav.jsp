<%@page import="org.springframework.security.core.GrantedAuthority"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<link href="https://fonts.googleapis.com/css?family=Niramit"
	rel="stylesheet">

<%
	java.util.Collection<? extends GrantedAuthority> authorities = org.springframework.security.core.context.SecurityContextHolder
			.getContext().getAuthentication().getAuthorities();

	java.util.List<String> privileges = new java.util.ArrayList<>();
	java.util.List<GrantedAuthority> auths = new java.util.ArrayList<GrantedAuthority>(authorities);
	for (int i = 0; i < auths.size(); i++) {
		privileges.add(auths.get(i).toString());
	}
%>

<nav class="navbar navbar-expand-lg nav-top">
	<a class="navbar-brand" href="#"> <img
		src="<c:url value="/resources/images/IMSP_CRDM.png" />"
		style="width: 40px; height: auto;" alt="" />
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarColor03" aria-controls="navbarColor03"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarColor03">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item" id="home-link"><a class="nav-link"
				href="${pageContext.request.contextPath}/"><spring:message
						code="nav.home" /> <span class="sr-only">(current)</span></a></li>


			<%
				if (privileges.contains("WRITE_PRIVILEGE")) {
			%>
			<li class="nav-item" id="new-release-link"><a class="nav-link"
				href="${pageContext.request.contextPath}/contract/list"><spring:message
						code="nav.contracts" /></a></li>


			<li class="nav-item" id="reagents-link"><a class="nav-link"
				href="${pageContext.request.contextPath}/good/list"><spring:message
						code="nav.goods" /></a></li>

			<%
				}
			%>
			<%
				if (privileges.contains("PURCHASE_REQUEST_PRIVILEGE")) {
			%>
			<li class="nav-item" id="lab-link"><a class="nav-link"
				href="${pageContext.request.contextPath}/purchase/list"><spring:message
						code="nav.purchases" /></a></li>
			<%
				}
			%>

			<%
				if (privileges.contains("APPROVE_PRIVILEGE")) {
			%>
			<li class="nav-item" id="lab-link"><a class="nav-link"
				href="${pageContext.request.contextPath}/request/list"><spring:message
						code="nav.purchases.requests" /></a></li>
			<li class="nav-item" id="lab-link"><a class="nav-link"
				href="${pageContext.request.contextPath}/request/approved"><spring:message
						code="nav.purchases.approved" /></a></li>
			<%
				}
			%>

			<%
				if (privileges.contains("WRITE_PRIVILEGE")) {
			%>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <spring:message code="nav.more" /> ...
			</a>
				<div class="dropdown-menu" style="background-color: #669fa1;"
					aria-labelledby="navbarDropdown">


					<a class="dropdown-item"
						href="${pageContext.request.contextPath}/provider/list"><spring:message
							code="nav.providers" /></a> <a class="dropdown-item"
						href="${pageContext.request.contextPath}/section/list"><spring:message
							code="nav.sections" /></a>
					<div class="dropdown-divider"></div>



					<c:set var="contains" value="false" />
					<c:forEach var="item" items="${privileges}">
						<c:if test="${item eq \"SETTINGS_PRIVILEGE\"}">
							<c:set var="contains" value="true" />
						</c:if>
					</c:forEach>

					<%
						if (privileges.contains("SETTINGS_PRIVILEGE")) {
					%>

					<a class="dropdown-item"
						href="${pageContext.request.contextPath}/settings"><spring:message
							code="nav.settings" /></a>
					<%
						}
					%>
				</div></li>
			<%
				}
			%>
		</ul>

		<form class="form-inline ">
			<input class="form-control mr-sm-2"
				placeholder="<spring:message code="nav.search" />"
				aria-label="Search" type="search" id="search">
			<!-- 			<button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Search</button> -->
		</form>

	</div>
</nav>


<style>
.navbar {
	padding-left: 10% !important;
	padding-right: 10% !important;
}
</style>