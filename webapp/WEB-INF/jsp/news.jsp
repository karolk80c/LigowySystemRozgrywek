<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>Dzisiejsze mecze</b>
</h1>
<br>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Zawodnik</th>
			<th>Wynik</th>
			<th>Wynik</th>
			<th>Zawodnik</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${matches}" var="match">
			<tr>
				<td>${match.firstName}</td>
				<td>${match.firstPoints}</td>
				<td>${match.secondPoints }</td>
				<td>${match.secondName}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
