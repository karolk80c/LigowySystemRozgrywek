<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>
<style>
.image {
	padding: 2%;
	width: 80%;
	opacity: 0.75;
}
</style>

<div class="alert alert-info">
	<spring:message code="index.beta" />
</div>


<div class="center">
	<br>
	<h1>
		<spring:message code="homePage.applicationTitle" />
	</h1>
	<br>
	<h3>
		<spring:message code="homePage.description" />
	</h3>

	<img class="image" src="assets/image/background.jpg" />

</div>