<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<c:if test="${param.success eq true }">
	<div class="alert alert-success">Poprawnie wyslano wiadomosc</div>
</c:if>

<h1 class="center">Wyslij wiadomosc</h1>
<br>

<form:form commandName="email" cssClass="form-horizontal email-form" >
	<div class="form-group">
		<label for="recipient" class="col-sm-2 control-label">Do</label>
		<div class="col-sm-10">
			<form:select cssClass="form-control" path="recipient" multiple="false" size="1">
				<ul>
					<li><form:options items="${usersList}" itemValue="emailAdress"
							itemLabel="fullName" /></li>
				</ul>
			</form:select>
		</div>
	</div>
	<div class="form-group">
		<label for="topic" class="col-sm-2 control-label">Temat</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="topic" name="topic"
				placeholder="Tutaj wpisz temat wiadomosci" value="">
		</div>
	</div>
	<div class="form-group">
		<label for="content" class="col-sm-2 control-label">Wiadomosc</label>
		<div class="col-sm-10">
			<textarea class="form-control" rows="4" name="content" id="content" placeholder="Tutaj wpisz tresc wiadomosci"></textarea>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-10 col-sm-offset-2">
			<input id="submit" name="submit" type="submit" value="Wyslij"
				class="btn-lg btn btn-primary" >
		</div>
	</div>
</form:form>

<script type="text/javascript">
	$(document).ready(function() {
		$(".email-form").validate({
			rules : {
				content : {
					required : true,
				},
				topic : {
					required : true,
				}
			},
			messages : {
				content : {
					required : "To pole jest wymagane"
				},
				topic : {
					required : "To pole jest wymagane"
				}
			},
			highlight: function(element){
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight: function(element){
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			}
		});
	});
</script>