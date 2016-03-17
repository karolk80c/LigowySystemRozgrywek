<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<form:form method="POST"
	action="/ping-pong/resetPassword.html"
	cssClass="form-horizontal reset-form">
	<div class="form-group">
		<label class="control-label col-sm-2">Login:</label>
		<div class="col-sm-10">
			<input name="login" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" >Adres
			Email:</label>
		<div class="col-sm-10">
			<input type="email" name="emailAdress" class="form-control" />
		</div>
	</div>	
	<br>
	<div class="col-sm-2">
		<input type="submit" value="Save" class="btn btn-lg btn-primary " />
	</div>

</form:form>

<script type="text/javascript">
	$(document).ready(function() {
		$(".reset-form").validate({
			rules : {
				emailAdress : {
					required : true,
					email : true
				},
				login : {
					required : true
				}
			},
			messages : {
				emailAdress : {
					required : "To pole jest wymagane",
					email : "Podaj właściwy adres email"
				},
				login : {
					required : "To pole jest wymagane"
				}
			}
		});
	});
</script>
