<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>
<style type="text/css">
/* Simple message styles customization */
#errors {
	border-left: 5px solid #a94442;
	padding-left: 15px;
}

#errors li {
	list-style-type: none;
}

#errors li:before {
	content: '\b7\a0';
}
</style>

<h1>
	<b>Szczegoly meczu</b> ${match.firstName} vs ${match.secondName} <br>
	<br>
</h1>
<form:form class="form-horizontal" method="POST"
	action="/ping-pong/matches/${id}/dataAndPlace.html"
	cssClass="dateAndPlaceForm">
	<div class="form-group col-xs-6">
		<label>Data spotkania:</label>
		<div class='input-group date' id='datetimepicker1'>
			<input class="form-control" name="matchDate" id='matchdateinput'
				readonly="readonly" /> <span class="input-group-addon"> <span
				class="glyphicon glyphicon-calendar"></span>
			</span>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#datetimepicker1').datetimepicker({
				locale : 'pl',
				showClear : true,
				showClose : true,
				ignoreReadonly : true,
			});
		});
	</script>
	<div class="form-group col-xs-6">
		<label>Miejsce spotkania:</label> <select name="matchPlace"
			id="matchPlace" class='form-control'>
			<option value="">Wybierz...</option>
			<c:forEach items="${places}" var="place">
				<option value="${place.name}">${place.name}</option>
			</c:forEach>
		</select>
	</div>
	<br>
	<br>
	<br>
	<br>
	<input 
		class="btn btn-primary btn-lg col-xs-2" id='apply-form' type="submit"
		value="Zatwierdź">
</form:form>
<br>
<br>
<br>
<br>
<script type="text/javascript">
	$(document).ready(function() {
		$(".dateAndPlaceForm").validate({
			rules : {
				matchDate : {
					required : true,
				},
				matchPlace : {
					required : true,
				}
			},
			messages : {
				matchDate : {
					required : "To pole jest wymagane"
				},
				matchPlace : {
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



<!-- Datetimepicker -->
<link rel="stylesheet"
	href="../../assets/css/bootstrap-datetimepicker.css">
<script type="text/javascript" src="../../assets/js/moment.js"></script>
<script type="text/javascript" src="../../assets/js/datetimepicker.js"></script>