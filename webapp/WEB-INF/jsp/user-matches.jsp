<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>Mecze ${user.firstName} ${user.lastName} </b>
</h1>
<br>

<c:if test="${param.success eq true }">
	<div class="alert alert-success">Zatwierdzono wynik meczu</div>
</c:if>

<table class="table table-bordered table-hover table-striped ts-table-section">
	<thead>
		<tr class="ts-row-section">
			<th>Zawodnik</th>
			<th>Wynik</th>
			<th>Zawodnik</th>
			<th>Szczegoly</th>
			<th>Akceptacja</th>
			<th>Data i miejsce</th>
			<th>Kontakt</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${user.matches}" var="match">
			<tr>
				<c:choose>
					<c:when test="${user.fullName eq match.firstName}">
						<td style="vertical-align: middle"><b>${match.firstName}</b></td>
						<td style="vertical-align: middle"><b>${match.firstPoints}:${match.secondPoints}</b></td>
						<td style="vertical-align: middle"><a
							href='<spring:url value="/users/find/${match.secondName}.html" />'>${match.secondName}</a>
						</td>
					</c:when>
					<c:otherwise>
						<td style="vertical-align: middle"><a
							href='<spring:url value="/users/find/${match.firstName}.html" />'>${match.firstName}</a></td>
						<td style="vertical-align: middle"><b>${match.firstPoints}:${match.secondPoints}</b></td>
						<td style="vertical-align: middle"><b>${match.secondName }</b></td>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${ not empty match.matchDate }">
						<c:choose>
							<c:when
								test="${(match.firstApproved eq true && match.firstName eq user.fullName && match.secondApproved eq false) || (match.secondApproved eq true && match.secondName eq user.fullName  && match.firstApproved eq false)}">
								<td style="vertical-align: middle"><a
									class="btn btn-secondary btn-lg"
									href='<spring:url value="/matches/${match.id}.html" />'>Szczegoly</a></td>
								<td style="vertical-align: middle">Zaakceptowane, oczekuje
									na potwierdzenie drugiego zawodnika <a class="btn btn-danger"
									href='<spring:url value="/matches/${match.id}/cancel.html" />'>Anuluj
								</a>
								</td>
							</c:when>
							<c:when
								test="${(match.firstApproved eq true && match.firstName ne user.fullName && match.secondApproved eq false) || (match.secondApproved eq true && match.secondName ne user.fullName  && match.firstApproved eq false)}">
								<td style="vertical-align: middle"><a
									class="btn btn-secondary btn-lg"
									href='<spring:url value="/matches/${match.id}.html" />'>Szczegoly</a></td>
								<td style="vertical-align: middle"><a
									class="btn btn-success btn-lg approve"
									href='<spring:url value="/matches/${match.id}/approve.html" />'>Akceptuj</a></td>
							</c:when>
							<c:when
								test="${match.firstApproved eq true && match.secondApproved eq true }">
								<td style="vertical-align: middle"><a
									class="btn btn-secondary btn-lg"
									href='<spring:url value="/matches/${match.id}.html" />'>Szczegoly</a></td>
								<td style="vertical-align: middle">Zawodnicy potwierdzili
									wynik spotkania</td>
							</c:when>
							<c:otherwise>
								<td style="vertical-align: middle"><a
									class="btn btn-primary btn-lg"
									href='<spring:url value="/matches/${match.id}.html" />'>Uaktualnij</a></td>
								<td style="vertical-align: middle">Zaden zawodnik nie
									wprowadzil jeszcze informacji o spotkaniu</td>
							</c:otherwise>
						</c:choose>
						<td><b><fmt:formatDate value="${match.matchDate}"
									pattern="dd-MM-yyyy HH:mm" /> </b> <br>Miejsce: <b>${match.matchPlace}</b><br>
							<c:if test="${match.matchPlace ne 'Mecz się nie odbył'}">
								<a class="btn btn-primary btn-danger"
									href='<spring:url value="/matches/${match.id}/dateAndPlace.html" />'>Zmień</a>
							</c:if></td>
					</c:when>
					<c:otherwise>
						<td style="vertical-align: middle">Najpierw uzupełnij
							informację o dacie spotkania</td>
						<td style="vertical-align: middle">Najpierw uzupełnij
							informację o dacie spotkania</td>
						<td style="vertical-align: middle"><a
							class="btn btn-primary btn-lg"
							href='<spring:url value="/matches/${match.id}/dateAndPlace.html" />'>Ustal</a></td>
					</c:otherwise>
				</c:choose>
				<td style="vertical-align: middle"><a
					class="btn btn-lg btn-info"
					href='<spring:url value="/matches/${match.id}/sendMessage.html" />'>Wyslij
						wiadomosc</a></td>
			</tr>



		</c:forEach>
	</tbody>
</table>


<script>
	$(document).ready(function() {
		$(document).tableSection();
	});
</script>
