<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<div style="float: left;">
	<h1 style="text-align: left">
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
					<td>${match.firstName}</td>
					<td>${match.secondName}</td>
					<td>${match.matchPlace}</td>
					<td><b><fmt:formatDate value="${match.matchDate}"
								pattern="dd.MM.yyyy HH:mm" /></b></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div style="float: left; margin-left: 8%;">
	<h1 style="text-align: left">
		<b>Ostatnio dodane wyniki</b>
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
					<td>${match.firstName}</td>
					<td><b>${match.firstPoints}:${match.secondPoints}</b></td>
					<td>${match.secondName}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<p style="clear: both;">