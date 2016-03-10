<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>Mecze ${user.firstName} ${user.lastName} </b>
</h1>
<br>

<div class="alert alert-info">W przypadku jakichkolwiek problemow
	przejdz do zakladki kontakt i skontaktuj sie z administratorem serwisu.</div>

<c:if test="${param.success eq true }">
	<div class="alert alert-success">Zatwierdzono wynik meczu</div>
</c:if>
<c:set var="now" value="<%=new java.util.Date()%>" />
<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
	value="${now}" />
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
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
				<td>${match.firstName}</td>
				<td><b>${match.firstPoints}:${match.secondPoints}</b>&nbsp;<c:forEach
						var="set" items="${match.sets}">
							(${set.firstPlayerScore}:${set.secondPlayerScore})
							</c:forEach></td>
				<td>${match.secondName}</td>
				<c:choose>
					<c:when
						test="${(match.firstApproved eq true && match.firstName eq user.fullName && match.secondApproved eq false) || (match.secondApproved eq true && match.secondName eq user.fullName  && match.firstApproved eq false)}">
						<td><a class="btn btn-secondary btn-lg"
							href='<spring:url value="/matches/${match.id}.html" />'>Szczegoly</a></td>
						<td>Zaakceptowane, oczekuje na potwierdzenie drugiego
							zawodnika <a class="btn btn-danger"
							href='<spring:url value="/matches/${match.id}/cancel.html" />'>Anuluj
						</a>
						</td>
						<td>${match.matchDate}${match.matchPlace}</td>
					</c:when>
					<c:when
						test="${(match.firstApproved eq true && match.firstName ne user.fullName && match.secondApproved eq false) || (match.secondApproved eq true && match.secondName ne user.fullName  && match.firstApproved eq false)}">
						<td><a class="btn btn-secondary btn-lg"
							href='<spring:url value="/matches/${match.id}.html" />'>Szczegoly</a></td>
						<td><a class="btn btn-success btn-lg"
							href='<spring:url value="/matches/${match.id}/approve.html" />'>Akceptuj</a></td>
						<td>${match.matchDate}${match.matchPlace}</td>
					</c:when>
					<c:when
						test="${match.firstApproved eq true && match.secondApproved eq true }">
						<td><a class="btn btn-secondary btn-lg"
							href='<spring:url value="/matches/${match.id}.html" />'>Szczegoly</a></td>
						<td>Zawodnicy potwierdzili wynik spotkania</td>
						<td>${match.matchDate}${match.matchPlace}</td>
					</c:when>
					<c:otherwise>
						<td><a class="btn btn-primary btn-lg"
							href='<spring:url value="/matches/${match.id}.html" />'>Uaktualnij</a></td>
						<td>Zaden zawodnik nie wprowadzil jeszcze informacji o
							spotkaniu</td>
						<td><a class="btn btn-primary btn-lg"
							href='<spring:url value="/matches/${match.id}/dateAndPlace.html" />'>Ustal</a></td>
					</c:otherwise>
				</c:choose>
				<td><a class="btn btn-lg btn-info"
					href='<spring:url value="/matches/${match.id}/sendMessage.html" />'>Wyslij
						wiadomosc</a></td>
			</tr>



		</c:forEach>
	</tbody>
</table>





