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
<h1>
	<b>Terminarz spotkań</b>
</h1>

<br>

<security:authorize access="hasRole('ROLE_ADMIN')">
	<a class="btn btn-lg btn-success"
		href='<spring:url value="/timetable/addRound/${newNumber}.html" />'>Dodaj
		kolejke</a>
	<br>
	<br>
</security:authorize>

<!-- Nav tabs -->
<ul class="nav nav-tabs">
	<c:forEach items="${round}" var="round">
		<li><a class="buttonClick" onClick="onClick()"
			href="#round${round.id}" data-toggle="tab">Kolejka ${round.number }</a></li>
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
			<table class="table table-bordered table-hover table-striped display">
				<thead>
					<tr>
						<th width="15%;">Zawodnik</th>
						<th width="25%;">Wynik</th>
						<th width="15%;">Zawodnik</th>
						<th width="15%;">Data i miejsce</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${round.matches}" var="match">
						<tr>
							<c:choose>
								<c:when test="${principalName eq match.firstName }">
									<td style="color: red; font-size: 105%;"><b>${match.firstName}</b></td>
								</c:when>
								<c:otherwise>
									<td><a
										href='<spring:url value="/users/find/${match.firstName}.html" />'>${match.firstName}</a></td>
								</c:otherwise>
							</c:choose>
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

							<c:choose>
								<c:when test="${principalName eq match.secondName }">
									<td style="color: red; font-size: 105%;"><b>${match.secondName}</b></td>
								</c:when>
								<c:otherwise>
									<td><a
										href='<spring:url value="/users/find/${match.secondName}.html" />'>${match.secondName}</a></td>
								</c:otherwise>
							</c:choose>

							<td><b><fmt:formatDate value="${match.matchDate}"
									pattern="dd-MM-yyyy HH:mm" /></b> ${match.matchPlace}</td>
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




