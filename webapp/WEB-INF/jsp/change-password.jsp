<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<form:form method="POST" action="/ping-pong/account/changePassword.html"
	cssClass="form-horizontal reset-form">
	<div class="form-group">
		<label class="control-label col-sm-2">Stare hasło:</label>
		<div class="col-sm-10">
			<input type="password" name="oldPassword" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2">Nowe hasło:</label>
		<div class="col-sm-10">
			<input type="password" name="newPassword" class="form-control" />
		</div>
	</div>
	<br>
	<div class="col-sm-4">
		<a class="btn btn-info btn-lg "
			href='<spring:url value="/account.html" />'>Wróć</a> <input
			type="submit" value="Zapisz" class="btn btn-lg btn-primary " />
	</div>

</form:form>


<script type="text/javascript">
	$(document).ready(function() {
		$(".reset-form").validate({
			rules : {
				oldPassword : {
					required : true
				},
				newPassword : {
					required : true
				}
			},
			messages : {
				oldPassword : {
					required : "To pole jest wymagane"
				},
				newPassword : {
					required : "To pole jest wymagane"
				}
			}
		});
	});
</script>
