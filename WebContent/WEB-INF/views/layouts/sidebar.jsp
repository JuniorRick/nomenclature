<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


	<div class="sidebar text-center">
		<aside>
			<nav>
				<ul>
					<li><a href="${pageContext.request.contextPath}/settings/users"><spring:message
								code="settings.users" /></a></li>
					<li><a href="${pageContext.request.contextPath}/settings/pdf"><spring:message
								code="settings.pdf" /></a></li>
<%-- 					<li><a href="${pageContext.request.contextPath}/settings/pdf"><spring:message
								code="settings.pdf" /></a></li> --%>
				</ul>
			</nav>
		</aside>
	</div>