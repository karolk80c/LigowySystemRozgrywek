<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>

<div class="alert alert-info">
	<spring:message code="login.info" />
</div>

<c:if test="${param.error eq true }">
	<div class="alert alert-danger">Niepoprawny login, hasło lub
		użytkownik został zablokowany.</div>
</c:if>

<form class="form-horizontal form-signin login-form" role="form"
	action="<c:url value='j_spring_security_check' />" method="POST">
	<div class="form-group">
		<h2 style="text-align: center" class="form-signin-heading">
			<spring:message code="placeholder.pleaseSignIn" />
			<br>
		</h2>
	</div>
	<div class="form-group">
		<label for="username" class="sr-only"><spring:message
				code="placeholder.pleaseSignIn" /></label> <input type="text"
			name="j_username" class="form-control"
			placeholder=<spring:message
			code="placeholder.login" /> required
			autofocus>
	</div>
	<div class="form-group">
		<label for="inputPassword" class="sr-only"><spring:message
				code="placeholder.password" /></label> <input type="password"
			name="j_password" class="form-control"
			placeholder=<spring:message
			code="placeholder.password" />
			required>
	</div>
	<div class="form-group">
		<button class="btn btn-lg btn-primary btn-block" type="submit">
			<spring:message code="placeholder.signIn" />
		</button>
	</div>
</form>

<script type="text/javascript">
	$(document).ready(
			function() {
				$(".login-form").validate(
						{
							rules : {
								j_username : {
									required : true
								},
								j_password : {
									required : true
								}
							},
							messages : {
								j_username : {
									required : "To pole jest wymagane"
								},
								j_password : {
									required : "To pole jest wymagane"
								}
							},
							highlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-success').addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-error').addClass('has-success');
							}
						});
			});
</script>

