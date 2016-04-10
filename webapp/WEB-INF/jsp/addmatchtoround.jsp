<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<c:if test="${param.success eq true }">
	<div class="alert alert-success">Pomyślnie dodano mecz</div>
</c:if>

<form:form method="POST"
	action="/ping-pong/timetable/${roundId}/addMatch.html"
	cssClass="form-horizontal reset-form">
	<div class="form-group">
		<label class="col-sm-2 control-label">Pierwszy zawodnik: </label>
		<div class="col-sm-10">
			<select id="firstLogin" name="firstLogin" class="form-control">
				<option value="${user.login}">--- Wybierz ---</option>
				<c:forEach items="${usersList }" var="user">
					<option value="${user.login}">${user.fullName }</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">Drugi zawodnik: </label>
		<div class="col-sm-10">
			<select id="secondLogin" name="secondLogin" class="form-control">
				<option value="${user.login}">--- Wybierz ---</option>
				<c:forEach items="${usersList }" var="user">
					<option value="${user.login}">${user.fullName }</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="modal-footer">
		<a href="<spring:url value="/timetable.html" />" class="btn btn-info">Wróć</a>
		<input type="submit" formmethod="post" class="btn btn-success"
			value="Dodaj" />
	</div>
</form:form>



<script type="text/javascript">
	$(document).ready(
			function() {
				$(".reset-form").validate(
						{
							rules : {
								firstLogin : {
									required : true
								},
								secondLogin : {
									required : true
								}
							},
							messages : {
								firstLogin : {
									required : "To pole jest wymagane"
								},
								secondLogin : {
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
