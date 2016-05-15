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

<div style="float: left; width: 49%; margin-right: 1%;">
	<h1>
		<b> Potwierdzony przez jedną</b>
	</h1>
	<br>
	<table class="table table-bordered table-hover table-striped display">
		<thead>
			<tr>
				<th style="width: 20%;">Zawodnik</th>
				<th style="width: 10%;">Wynik</th>
				<th style="width: 20%;">Zawodnik</th>
				<th style="width: 50%;">Data i miejsce</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${oneAccepted}" var="match">
				<tr>
					<td><a
						href='<spring:url value="/users/find/${match.firstName}.html" />'>${match.firstName}</a></td>
					<td><b>${match.firstPoints}:${match.secondPoints}</b>&nbsp;<a
						class="btn btn-xs btn-success triggerRemove"
						href='<spring:url value="/admin-matches/accept/${match.id}/${match.firstName}.html" />'>Akceptuj</a></td>
					<td><a
						href='<spring:url value="/users/find/${match.secondName}.html" />'>${match.secondName}</a></td>
					<td style="vertical-align: middle; baseline; text-align: center;"><b><fmt:formatDate
								value="${match.matchDate}" pattern="dd.MM.yyyy HH:mm" /></b>
						${match.matchPlace}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div style="float: right; width: 49%;">
	<h1>
		<b> Potwierdzony przez żadną</b>
	</h1>
	<br>
	<table class="table table-bordered table-hover table-striped display">
		<thead>
			<tr>
				<th>Zawodnik</th>
				<th class="score-header">Wynik</th>
				<th>Zawodnik</th>
				<th>Data i miejsce</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${noAccepted}" var="match">
				<tr>
					<td><a
						href='<spring:url value="/users/find/${match.firstName}.html" />'>${match.firstName}</a><a
						href='<spring:url value="/admin-matches/disqualifie/${match.id}/${match.firstName}.html" />'
						class="btn btn-xs btn-danger triggerRemove">Dyskwalifikuj</a></td>
					<td><b>${match.firstPoints}:${match.secondPoints}</b>&nbsp;</td>

					<td><a
						href='<spring:url value="/users/find/${match.secondName}.html" />'>${match.secondName}</a><a
						href='<spring:url value="/admin-matches/disqualifie/${match.id}/${match.secondName}.html" />'
						class="btn btn-xs btn-danger triggerRemove">Dyskwalifikuj</a></td>

					<td style="vertical-align: middle; baseline; text-align: center;"><b><fmt:formatDate
								value="${match.matchDate}" pattern="dd.MM.yyyy HH:mm" /></b>
						${match.matchPlace}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<p style="clear: both;"></p>


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

<script>
	$(document)
			.ready(
					function() {
						$(".triggerRemove").click(
								function(e) {
									e.preventDefault();
									$("#modalRemove .removeBtn").attr("href",
											$(this).attr("href"));
									$("#modalRemove").modal();
								});
						$('table.display')
								.dataTable(
										{
											"scrollY" : "290px",
											"scrollCollapse" : true,
											"paging" : false,
											"language" : {
												processing : "Przetwarzanie...",
												search : "Szukaj:",
												lengthMenu : "Pokaż _MENU_ pozycji",
												info : "",
												infoEmpty : "Pozycji 0 z 0 dostępnych",
												infoFiltered : "(filtrowanie spośród _MAX_ dostępnych pozycji)",
												infoPostFix : "",
												loadingRecords : "Wczytywanie...",
												zeroRecords : "Nie znaleziono pasujących pozycji",
												emptyTable : "Brak danych",
											}
										});
					});
</script>

