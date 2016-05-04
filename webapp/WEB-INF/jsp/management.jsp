<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<style>
tr {
	text-align: center;
	vertical-align: middle;
}

tr th {
	text-align: center;
	vertical-align: middle;
}
</style>

<h1 class="center">Panel administratora</h1>

<br>
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<td>Opis akcji</td>
			<td>Przycisk</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><h4>Losuje spotkania do sezonu</h4></td>
			<td><a
				href=" <spring:url value="/management/season/draw.html" />"
				class="btn btn-primary triggerRemove"> Losuj </a></td>
		</tr>
		<tr>
			<td><h4>Usuwa wszystkie sezony, kolejki, mecze oraz sety</h4></td>
			<td><a
				href=" <spring:url value="/management/seasons/all.html" />"
				class="btn btn-danger triggerRemove"> Usuń</a></td>
		</tr>
		<tr>
			<td><h4>Wyczyść dane punktowe użytkowników</h4></td>
			<td><a
				href=" <spring:url value="/management/users/clear.html" />"
				class="btn btn-info triggerRemove"> Wyczyść </a></td>
		</tr>
		<tr>
			<td><h4>Wyczyść dane punktowe z wszystkich meczy</h4></td>
			<td><a
				href=" <spring:url value="/management/matches/clear.html" />"
				class="btn btn-info triggerRemove"> Wyczyść </a></td>
		</tr>
		<tr>
			<td><h4>Usuwa wszystkich użytkowników (Dostępne po
					usunięciu meczy)</h4></td>
			<td><a href=" <spring:url value="/management/users/all.html" />"
				class="btn btn-danger triggerRemove"> Usuń</a></td>
		</tr>
		<tr>
			<td><h4>Przechodzi do strony gdzie można losowo usunąć
					wybraną ilość kolejek wraz z meczami</h4></td>
			<td><a
				href=" <spring:url value="/management/remove/rounds.html" />"
				class="btn btn-info "> Przejdź</a></td>
		</tr>
	</tbody>
</table>






<h1 class="center">Wyślij wiadomość do wszystkich aktywnych
	zawodników</h1>
<br>
<form:form action="/ping-pong/email/users.html" commandName="email"
	cssClass="form-horizontal email-all-form">
	<div class="form-group">
		<label for="topic" class="col-sm-2 control-label">Temat</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="topic" name="topic"
				value="">
		</div>
	</div>
	<div class="form-group">
		<label for="content" class="col-sm-2 control-label">Wiadomosc</label>
		<div class="col-sm-10">
			<textarea class="form-control" rows="4" name="content"></textarea>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-10 col-sm-offset-2">
			<input id="submit" name="submit" type="submit" value="Wyślij"
				class="btn btn-lg btn-primary">
		</div>
	</div>
</form:form>

<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Wiadomość</h4>
			</div>
			<div class="modal-body">Jesteś pewny że chcesz wykonać wybraną
				akcję?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button>
				<a href="" class="btn btn-danger removeBtn">Wykonaj</a>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(
			function() {
				$(".triggerRemove").click(
						function(e) {
							e.preventDefault();
							$("#modalRemove .removeBtn").attr("href",
									$(this).attr("href"));
							$("#modalRemove").modal();
						});
				$(".email-all-form").validate(
						{
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