<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>Terminarz spotkan</b>
</h1>



<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Data spotkania</th>
			<th>Zawodnik</th>
			<th>Wynik</th>
			<th>Wynik</th>
			<th>Zawodnik</th>
			<th>Wynik</th>
		</tr>
	</thead>
	<tbody>

		<c:forEach items="${match}" var="match">

			<tr>
				<td>${match.matchDate }</td>
				<td>${match.firstName}</td>
				<td>3</td>
				<td>1</td>
				<td>${match.secondName}</td>
				<td><a class="btn btn-lg btn-primary"
					href='<spring:url value="/" />'>Dodaj</a></td>
			</tr>

		</c:forEach>


	</tbody>
</table>
