<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>Nadchodzące mecze</b>
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
		<c:forEach items="${incomingMatches}" var="match">
			<tr>
				<td>${match.firstName}</td>
				<td><b>${match.firstPoints}:${match.secondPoints}</b></td>
				<td>${match.secondName}</td>
				<td>${match.matchPlace}</td>
				<td>${match.matchDate}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

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
				<td>${match.firstName}</td>
				<td><b>${match.firstPoints}:${match.secondPoints}</b></td>
				<td>${match.secondName}</td>
				<td>${match.matchPlace}</td>
				<td>${match.matchDate}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
