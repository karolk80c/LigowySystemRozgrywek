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
		<b>Nadchodzące mecze</b>
	</h1>
	<a href="<spring:url value="/news/allMatches.html" />"
		style="size: 2%; padding: 2%;">Przejdź do wszystkich ustalonych
		meczy</a> <br><br>
	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th style="width: 25%;">Zawodnik</th>
				<th style="width: 25%;">Zawodnik</th>
				<th style="width: 50%;">Data i miejsce</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${incomingMatches}" var="match">
				<tr>
					<c:choose>
						<c:when test="${match.firstName eq principalName }">
							<td style="color: red; font-size: 105%;"><b>${match.firstName}</b></td>
						</c:when>
						<c:otherwise>
							<td><a
								href='<spring:url value="/users/find/${match.firstName}.html" />'>${match.firstName}</a></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${match.secondName eq principalName }">
							<td style="color: red; font-size: 105%;"><b>${match.secondName}</b></td>
						</c:when>
						<c:otherwise>
							<td><a
								href='<spring:url value="/users/find/${match.secondName}.html" />'>${match.secondName}</a></td>
						</c:otherwise>
					</c:choose>
					<td><b><fmt:formatDate value="${match.matchDate}"
								pattern="dd.MM.yyyy HH:mm" /></b> ${match.matchPlace}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div style="float: right; width: 49%;">
	<h1>
		<b>Ostatnio dodane wyniki</b>
	</h1>
	<a href="<spring:url value="/news/allCompletedMatches.html" />"
		style="size: 2%; padding: 2%;">Przejdź do wszystkich zakończonych
		meczy</a> <br><br>
	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>Zawodnik</th>
				<th class="score-header">Wynik</th>
				<th>Zawodnik</th>
				<th>Data i miejsce</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${latestMatches}" var="match">
				<tr>
					<c:choose>
						<c:when test="${match.firstName eq principalName }">
							<td style="color: red; font-size: 105%;"><b>${match.firstName}</b></td>
						</c:when>
						<c:otherwise>
							<td><a
								href='<spring:url value="/users/find/${match.firstName}.html" />'>${match.firstName}</a></td>
						</c:otherwise>
					</c:choose>
					<td><b>${match.firstPoints}:${match.secondPoints}</b>&nbsp;</td>
					<c:choose>
						<c:when test="${match.secondName eq principalName }">
							<td style="color: red; font-size: 105%;"><b>${match.secondName}</b></td>
						</c:when>
						<c:otherwise>
							<td><a
								href='<spring:url value="/users/find/${match.secondName}.html" />'>${match.secondName}</a></td>
						</c:otherwise>
					</c:choose>
					<td><b><fmt:formatDate value="${match.matchDate}"
								pattern="dd.MM.yyyy HH:mm" /></b> ${match.matchPlace}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<p style="clear: both;"></p>