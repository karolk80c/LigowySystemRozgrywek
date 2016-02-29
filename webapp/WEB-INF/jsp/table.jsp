<%@ include file="../layout/taglib.jsp"%>

<div class="alert alert-info">Tabela aktualizuje sie po rozegraniu
	spotkania i zatwierdzeniu wyniku przez obu zawodnikow.</div>
<h1>
	<b>Tabela Sezonu 2016</b> <br>
</h1>
<br>
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Poz</th>
			<th><a
				href='<spring:url value="/table.html?properties=firstName&order=${order}" />'>Zawodnik</a></th>
			<th>Mecze</th>
			<th><a
				href='<spring:url value="/table.html?properties=wonMatches&order=${order}" />'>W</a></th>
			<th><a
				href='<spring:url value="/table.html?properties=lostMatches&order=${order}" />'>P</a></th>
			<th><a
				href='<spring:url value="/table.html?properties=balanceMatches&order=${order}" />'>B+-</a></th>
			<th><a
				href='<spring:url value="/table.html?properties=wonSets&order=${order}" />'>Sety
					W</a></th>
			<th><a
				href='<spring:url value="/table.html?properties=lostSets&order=${order}" />'>P</a></th>
			<th><a
				href='<spring:url value="/table.html?properties=balanceSets&order=${order}" />'>B+-</a></th>
			<th><a
				href='<spring:url value="/table.html?properties=wonSmallPoints&order=${order}" />'>Punkty
					W</a></th>
			<th><a
				href='<spring:url value="/table.html?properties=lostSmallPoints&order=${order}" />'>P</a></th>
			<th><a
				href='<spring:url value="/table.html?properties=balanceSmallPoints&order=${order}" />'>B+-</a></th>
			<th><a
				href='<spring:url value="/table.html?properties=balanceSets&order=${order}" />'>Tenisowa</a></th>
			<th><a
				href='<spring:url value="/table.html?properties=footballPoints&order=${order}" />'>Pilkarska</a></th>
			<th><a
				href='<spring:url value="/table.html?properties=volleyballPoints&order=${order}" />'>Siatkarska</a></th>
		</tr>
	</thead>
	<tbody>
		<%
			int i = 1;
		%>
		<c:forEach items="${users}" var="user">
			<c:forEach items="${user.roles}" var="role">
				<c:if test="${role.name == 'ROLE_USER' }">
					<tr>
						<td>
							<%
								out.print(i);
											i++;
							%>
						</td>
						<td><a
							href='<spring:url value="/users/${user.login}.html" />'>
								${user.firstName}&nbsp;${ user.lastName }</a></td>
						<td>${user.wonMatches + user.lostMatches}</td>
						<td>${user.wonMatches}</td>
						<td>${user.lostMatches }</td>
						<td><b>${user.balanceMatches}</b></td>
						<td>${user.wonSets }</td>
						<td>${user.lostSets }</td>
						<td><b>${user.balanceSets}</b></td>
						<td>${user.wonSmallPoints}</td>
						<td>${user.lostSmallPoints }</td>
						<td><b>${user.balanceSmallPoints }</b></td>
						<td><b>${user.balanceSets }</b></td>
						<td><b>${user.footballPoints }</b></td>
						<td><b>${user.volleyballPoints }</b></td>
					</tr>
				</c:if>
			</c:forEach>
		</c:forEach>
	</tbody>
</table>