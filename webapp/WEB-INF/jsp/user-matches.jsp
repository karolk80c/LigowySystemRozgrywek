<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>Mecze ${user.firstName} ${user.lastName} </b>
</h1>
<br>



<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Data</th>
			<th>Zawodnik</th>
			<th>Wynik</th>
			<th>Wynik</th>
			<th>Zawodnik</th>
			<th>Uaktualnij</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${user.matches}" var="match">
			<tr>
				<td>${match.matchDate }</td>
				<td>${match.firstName}</td>
				<td>${match.firstPoints}</td>
				<td>${match.secondPoints }</td>
				<td>${match.secondName}</td>
				<td><a class="btn btn-primary btn-lg"
					href='<spring:url value="/matches/${match.id}.html" />'>Uaktualnij</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>





