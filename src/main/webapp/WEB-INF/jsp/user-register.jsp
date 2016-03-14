<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>
<div class="alert alert-info">Zarejestruj się jeśli chcesz
	uczestniczyć w zawodach, w przeciwnym wypadku nie musisz się
	rejestrować.</div>

<c:if test="${param.success eq true }">
	<div class="alert alert-success">Rejestracja zakończona
		pomyślnie. Oczekuj na potwierdzenie przez administratora na podany
		adres email</div>
</c:if>
<br>
<form:form commandName="user" cssClass="form-horizontal register-form">

	<div class="form-group">
		<label class="control-label col-sm-2" for="name">Imię:</label>
		<div class="col-sm-10">
			<form:input path="firstName" cssClass="form-control" />
			<form:errors path="firstName" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="lastName">Nazwisko:</label>
		<div class="col-sm-10">
			<form:input path="lastName" cssClass="form-control" />
			<form:errors path="lastName" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="emailAdress">Adres
			Email:</label>
		<div class="col-sm-10">
			<form:input type="email" path="emailAdress" cssClass="form-control" />
			<form:errors path="emailAdress" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="name">Login:</label>
		<div class="col-sm-10">
			<form:input path="login" cssClass="form-control" />
			<form:errors path="login" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="password">Hasło:</label>
		<div class="col-sm-10">
			<form:password path="password" cssClass="form-control" />
			<form:errors path="password" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="contactNumber">Numer
			kontaktowy:</label>
		<div class="col-sm-10">
			<form:input path="contactNumber" cssClass="form-control" />
		</div>
	</div>
	<br>

	<div class="col-sm-2">
		<input type="submit" value="Save" class="btn btn-lg btn-primary " />
	</div>

</form:form>

<script type="text/javascript">
	$(document).ready(function() {
		$(".register-form").validate({
			rules : {
				firstName : {
					required : true
				},
				lastName : {
					required : true
				},
				emailAdress : {
					required : true,
					email : true
				},
				login : {
					required : true
				},
				password : {
					required : true
				}
			},
			messages : {
				firstName : {
					required : "To pole jest wymagane"
				},
				lastName : {
					required : "To pole jest wymagane"
				},
				emailAdress : {
					required : "To pole jest wymagane",
					email : "Podaj właściwy adres email"
				},
				login : {
					required : "To pole jest wymagane"
				},
				password : {
					required : "To pole jest wymagane"
				}
			}
		});
	});
</script>
