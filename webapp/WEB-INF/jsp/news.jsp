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
			<th>Data</th>
			<th>Miejsce</th>
			<th>Zawodnik</th>
			<th>Wynik</th>
			<th>Zawodnik</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${round.matches}" var="match">
			<tr>
				<td>${match.matchDate}</td>
				<td>${match.matchPlace}</td>
				<td>${match.firstName}</td>
				<td><b>${match.firstPoints}:${match.secondPoints}</b>&nbsp;<c:forEach
						var="set" items="${match.sets}">
							(${set.firstPlayerScore}:${set.secondPlayerScore})
							</c:forEach></td>
				<td>${match.secondName}</td>
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
			<th>Data</th>
			<th>Miejsce</th>
			<th>Zawodnik</th>
			<th>Wynik</th>
			<th>Zawodnik</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${round.matches}" var="match">
			<tr>
				<td>${match.matchDate}</td>
				<td>${match.matchPlace}</td>
				<td>${match.firstName}</td>
				<td><b>${match.firstPoints}:${match.secondPoints}</b>&nbsp;<c:forEach
						var="set" items="${match.sets}">
							(${set.firstPlayerScore}:${set.secondPlayerScore})
							</c:forEach></td>
				<td>${match.secondName}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
