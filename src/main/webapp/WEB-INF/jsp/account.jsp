<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>


<c:if test="${param.change eq true }">
	<div class="alert alert-success">Poprawnie zmieniono hasło.
		Została wysłana wiadomość na Twój adres email</div>
</c:if>

<c:if test="${param.changeData eq true }">
	<div class="alert alert-success">Poprawnie zmieniono dane
		osobowe.</div>
</c:if>

<h1>
	<b>${user.fullName}</b>
</h1>
<h3>
	<b>Adres email:</b> ${user.emailAdress}<br> <b>Numer
		kontaktowy:</b> ${user.contactNumber}<br> <br> <a
		class="btn btn-danger"
		href='<spring:url value="/account/changePassword.html" />'>Zmień
		hasło</a> <a class="btn btn-info"
		href='<spring:url value="/account/changePersonalData.html" />'>Zmień
		dane osobowe</a>
</h3>
<br>
<h1 style="text-align: left">
	<b>Statystyka</b>
</h1>
<br>
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Pozycja w rankingu</th>
			<th>Rozegrane mecze</th>
			<th>Wygrane mecze</th>
			<th>Wygrane sety</th>
			<th>Zdobyte punkty</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><b>${user.rankingPosition}</b></td>
			<td><b>${user.wonMatches+user.lostMatches}</b></td>
			<td><b>${user.wonMatches}</b></td>
			<td><b>${user.wonSets}</b></td>
			<td><b>${user.wonSmallPoints }</b></td>
		</tr>
	</tbody>
</table>



<div style="float: left;">
	<h1 style="text-align: left">
		<b>Umówione spotkania</b>
	</h1>
	<br>
	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>Przeciwnik</th>
				<th>Miejsce</th>
				<th>Data</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${incomingMatches}" var="match">
				<tr>
					<c:choose>
						<c:when test="${user.fullName eq match.firstName }">
							<td><a
								href='<spring:url value="/users/find/${match.secondName}.html" />'>${match.secondName}</a></td>
						</c:when>
						<c:otherwise>
							<td><a
								href='<spring:url value="/users/find/${match.firstName}.html" />'>${match.firstName}</a></td>
						</c:otherwise>
					</c:choose>
					<td>${match.matchPlace}</td>
					<td><b><fmt:formatDate value="${match.matchDate}"
								pattern="dd.MM.yyyy HH:mm" /></b></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div style="float: left; margin-left: 5%;">
	<h1 style="text-align: left">
		<b>Dotychczas rozegrane mecze</b>
	</h1>
	<br>
	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>Zawodnik</th>
				<th>Wynik</th>
				<th>Zawodnik</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${latestMatches}" var="match">
				<tr>
					<c:choose>
						<c:when test="${user.fullName eq match.firstName }">
							<td style="color: red; font-size: 105%;"><b>${match.firstName}</b></td>
							<td><b>${match.firstPoints}:${match.secondPoints}</b>&nbsp;<c:forEach
									var="set" items="${match.sets}">
							(${set.firstPlayerScore}:${set.secondPlayerScore})
							</c:forEach></td>
							<td><a
								href='<spring:url value="/users/find/${match.secondName}.html" />'>${match.secondName}</a></td>
						</c:when>
						<c:otherwise>
							<td><a
								href='<spring:url value="/users/find/${match.firstName}.html" />'>${match.firstName}</a></td>
							<td><b>${match.firstPoints}:${match.secondPoints}</b>&nbsp;<c:forEach
									var="set" items="${match.sets}">
							(${set.firstPlayerScore}:${set.secondPlayerScore})
							</c:forEach></td>
							<td style="color: red; font-size: 105%;"><b>${match.secondName}</b></td>
						</c:otherwise>
					</c:choose>

				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<p style="clear: both;">