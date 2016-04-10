<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>Terminarz spotkan</b>
</h1>

<br>

<script type="text/javascript">
	$(document).ready(function() {
		$('.nav-tabs a:first').tab('show');
		$(".triggerRemove").click(function(e) {
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
		});
	});
</script>

<security:authorize access="hasRole('ROLE_ADMIN')">
	<a class="btn btn-lg btn-success"
		href='<spring:url value="/timetable/addRound/${newNumber}.html" />'>Dodaj
		kolejkę</a>
	<br>
	<br>
</security:authorize>

<!-- Nav tabs -->
<ul class="nav nav-tabs">
	<c:forEach items="${round}" var="round">
		<li><a href="#round${round.id}" data-toggle="tab">Kolejka
				${round.number }</a></li>
	</c:forEach>
</ul>

<!-- Tab panes -->
<div class="tab-content">
	<c:forEach items="${round}" var="round">
		<div class="tab-pane" id="round${round.id}">
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<br>
				<a class="btn btn-danger triggerRemove"
					href='<spring:url value="/timetable/removeRound/${round.id}.html" />'>Usuń
					kolejkę</a>
				<a class="btn btn-success"
					href='<spring:url value="/timetable/${round.id}/addMatch.html" />'>Dodaj
					mecz</a>
				<br>
				<br>
			</security:authorize>
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>Zawodnik</th>
						<th>Wynik</th>
						<th>Zawodnik</th>
						<th>Miejsce</th>
						<th>Data</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${round.matches}" var="match">
						<tr>
							<td>${match.firstName}</td>
							<c:choose>
								<c:when test="${ match.completed == true}">
									<td><b>${match.firstPoints}:${match.secondPoints}</b>&nbsp;<c:forEach
											var="set" items="${match.sets}">
							(${set.firstPlayerScore}:${set.secondPlayerScore})
							</c:forEach></td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<td>${match.secondName}</td>
							<td>${match.matchPlace}</td>
							<td><fmt:formatDate value="${match.matchDate}"
									pattern="dd-MM-yyyy HH:mm" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:forEach>
</div>



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
				akcję ?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button>
				<a href="" class="btn btn-danger removeBtn">Wykonaj</a>
			</div>
		</div>
	</div>
</div>

