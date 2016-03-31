<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<form:form method="POST"
	action="/ping-pong/account/changePersonalData.html"
	cssClass="form-horizontal reset-form">
	<div class="form-group">
		<label class="control-label col-sm-2">Imię:</label>
		<div class="col-sm-10">
			<input type="text" name="firstName" value="${user.firstName }"
				class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2">Nazwisko:</label>
		<div class="col-sm-10">
			<input type="text" name="lastName" value="${user.lastName }"
				class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2">Numer Kontaktowy:</label>
		<div class="col-sm-10">
			<input type="text" name="contactNumber" value="${user.contactNumber}"
				class="form-control" />
		</div>
	</div>
	<br>
	<div class="col-sm-4">
		<a class="btn btn-info btn-lg "
			href='<spring:url value="/account.html" />'>Wróć</a> <input
			type="submit" value="Zapisz" class="btn btn-lg btn-primary " />
	</div>

</form:form>
