<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<div>
	<h1>
		<b>Nadchodzące mecze</b>
	</h1>
	<br>
	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>Zawodnik</th>
				<th>Zawodnik</th>
				<th>Miejsce</th>
				<th>Data</th>
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
					<td>${match.matchPlace}</td>
					<td><b><fmt:formatDate value="${match.matchDate}"
								pattern="dd.MM.yyyy HH:mm" /></b></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div>
	<h1>
		<b>Ostatnio dodane wyniki</b>
	</h1>
	<br>
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
					<td><b>${match.firstPoints}:${match.secondPoints}</b>&nbsp;<c:forEach
							var="set" items="${match.sets}">
							(${set.firstPlayerScore}:${set.secondPlayerScore})
							</c:forEach></td>
					<c:choose>
						<c:when test="${match.secondName eq principalName }">
							<td style="color: red; font-size: 105%;"><b>${match.secondName}</b></td>
						</c:when>
						<c:otherwise>
							<td><a
								href='<spring:url value="/users/find/${match.secondName}.html" />'>${match.secondName}</a></td>
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