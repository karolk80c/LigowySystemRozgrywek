<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>Mecze ${user.firstName} ${user.lastName} </b>
</h1>

<c:if test="${param.success eq true }">
	<div class="alert alert-success">Zatwierdzono wynik meczu</div>
</c:if>

<table
	class="table table-bordered table-hover table-striped ts-table-section"
	id="example">
	<thead>
		<tr class="ts-row-section">
			<th style="width: 15%;">Zawodnik</th>
			<th class="score-header">Wynik</th>
			<th style="width: 15%;">Zawodnik</th>
			<th style="width: 10%; text-align: center;">Szczegoly</th>
			<th style="width: 10%; text-align: center;">Akceptacja</th>
			<th class="date-header" style="width: 20%;">Data i miejsce</th>
			<th style="width: 10%; vertical-align: middle; text-align: center">Kontakt</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${user.matches}" var="match">
			<tr>
				<c:choose>
					<c:when test="${user.fullName eq match.firstName}">
						<td style="vertical-align: middle"><b>${match.firstName}</b></td>
						<td style="vertical-align: middle; text-align: center;"><b>${match.firstPoints}:${match.secondPoints}</b></td>
						<td style="vertical-align: middle"><a
							href='<spring:url value="/users/find/${match.secondName}.html" />'>${match.secondName}</a>
						</td>
					</c:when>
					<c:otherwise>
						<td style="vertical-align: middle"><a
							href='<spring:url value="/users/find/${match.firstName}.html" />'>${match.firstName}</a></td>
						<td style="vertical-align: middle; text-align: center;"><b>${match.firstPoints}:${match.secondPoints}</b></td>
						<td style="vertical-align: middle"><b>${match.secondName }</b></td>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${ not empty match.matchDate }">
						<c:choose>
							<c:when
								test="${(match.firstApproved eq true && match.firstName eq user.fullName && match.secondApproved eq false) || (match.secondApproved eq true && match.secondName eq user.fullName  && match.firstApproved eq false)}">
								<td style="vertical-align: middle"><a
									class="btn btn-secondary "
									href='<spring:url value="/matches/${match.id}.html" />'>Szczegoly</a></td>
								<td style="text-align: center; vertical-align: middle">Zatwierdzone
									<a class="btn btn btn-xs btn-danger"
									href='<spring:url value="/matches/${match.id}/cancel.html" />'>Anuluj
								</a>
								</td>
							</c:when>
							<c:when
								test="${(match.firstApproved eq true && match.firstName ne user.fullName && match.secondApproved eq false) || (match.secondApproved eq true && match.secondName ne user.fullName  && match.firstApproved eq false)}">
								<td
									style="vertical-align: middle; baseline; text-align: center;"><a
									class="btn btn-secondary "
									href='<spring:url value="/matches/${match.id}.html" />'>Szczegoly</a></td>
								<td
									style="vertical-align: middle; baseline; text-align: center;"><a
									class="btn btn-success  approve"
									href='<spring:url value="/matches/${match.id}/approve.html" />'>Akceptuj</a></td>
							</c:when>
							<c:when
								test="${match.firstApproved eq true && match.secondApproved eq true }">
								<td
									style="vertical-align: middle; baseline; text-align: center;"><a
									class="btn btn-secondary "
									href='<spring:url value="/matches/${match.id}.html" />'>Szczegoly</a></td>
								<td
									style="vertical-align: middle; baseline; text-align: center;">Zatwierdzone</td>
							</c:when>
							<c:otherwise>
								<td
									style="vertical-align: middle; baseline; text-align: center;"><a
									class="btn btn-sm btn-primary "
									href='<spring:url value="/matches/${match.id}.html" />'>Uaktualnij</a></td>
								<td
									style="vertical-align: middle; baseline; text-align: center;">Niezatwiedzone</td>
							</c:otherwise>
						</c:choose>
						<td style="vertical-align: middle; baseline; text-align: center;"><b><fmt:formatDate
									value="${match.matchDate}" pattern="dd-MM-yyyy HH:mm" /> </b> <b>${match.matchPlace}</b>
							<c:if test="${match.matchPlace ne 'Mecz się nie odbył'}">
								<br>
								<a class="btn btn-xs btn-primary btn-danger"
									href='<spring:url value="/matches/${match.id}/dateAndPlace.html" />'>Zmień</a>
							</c:if></td>
					</c:when>
					<c:otherwise>
						<td style="vertical-align: middle; baseline; text-align: center;">----</td>
						<td style="vertical-align: middle; baseline; text-align: center;">----</td>
						<td style="vertical-align: middle baseline; text-align: center;"><a
							class="btn btn-primary "
							href='<spring:url value="/matches/${match.id}/dateAndPlace.html" />'>Ustal</a></td>
					</c:otherwise>
				</c:choose>
				<td style="vertical-align: middle; baseline; text-align: center;"><a
					class="btn  btn-info"
					href='<spring:url value="/matches/${match.id}/sendMessage.html" />'>Wyslij
						wiadomosc</a></td>
			</tr>



		</c:forEach>
	</tbody>
</table>


<script>
	$(document)
			.ready(
					function() {
						$('#example')
								.dataTable(
										{
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

