<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>Mecze ${user.firstName} ${user.lastName} </b>
</h1>
<br>

<c:if test="${param.success eq true }">
	<div class="alert alert-success">Zatwierdzono wynik meczu, do
		czassu az drugi zawodnik nie potwierdzi wyniku, mozesz wprowadzic
		dalsze zmiany</div>
</c:if>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Data</th>
			<th>Zawodnik</th>
			<th>Wynik</th>
			<th>Wynik</th>
			<th>Zawodnik</th>
			<th>Szczegoly</th>
			<th>Akceptacja</th>
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

				<c:choose>
					<c:when
						test="${match.firstApproved eq true && match.secondApproved eq true }">
						<td > ---- </td>
						<td >Zaakceptowany</td>
					</c:when>
					<c:otherwise>
						<td><a class="btn btn-primary btn-lg"
							href='<spring:url value="/matches/${match.id}.html" />'>Uaktualnij</a></td>
						<td><a class="btn btn-lg btn-success"
							href='<spring:url value="/matches/${match.id}/approve.html" />'>Akceptuj</a></td>
					</c:otherwise>
				</c:choose>

			</tr>
		</c:forEach>
	</tbody>
</table>





