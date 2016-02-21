<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>Tabela Sezonu 2016</b>
	<br>
</h1>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Poz<</th>
			<th><a
				href='<spring:url value="/table.html?properties=firstName&order=${order}" />'>Zawodnik</a></th>
			<th>Mecze</th>
			<th><a
				href='<spring:url value="/table.html?properties=wonMatches&order=${order}" />'>W</a></th>
			<th><a
				href='<spring:url value="/table.html?properties=firstName&order=${order}" />'>P</a></th>
			<th><a
				href='<spring:url value="/table.html?properties=firstName&order=${order}" />'>B+-</a></th>
			<th>Sety</th>
			<th><a
				href='<spring:url value="/table.html?properties=firstName&order=${order}" />'>W</a></th>
			<th><a
				href='<spring:url value="/table.html?properties=firstName&order=${order}" />'>P</a></th>
			<th><a
				href='<spring:url value="/table.html?properties=firstName&order=${order}" />'>B+-</a></th>
			<th>Punkty</th>
			<th><a
				href='<spring:url value="/table.html?properties=firstName&order=${order}" />'>W</a></th>
			<th><a
				href='<spring:url value="/table.html?properties=firstName&order=${order}" />'>P</a></th>
			<th><a
				href='<spring:url value="/table.html?properties=firstName&order=${order}" />'>B+-</a></th>

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
						<td>${user.firstName } ${ user.lastName }</td>
						<td>${user.wonMatches + user.lostMatches }</td>
						<td>${user.wonMatches}</td>
						<td>${user.lostMatches }</td>
						<td>${user.wonMatches - user.lostMatches }</td>
						<td>${user.wonSets + user.lostSets }</td>
						<td>${user.wonSets}</td>
						<td>${user.lostSets }</td>
						<td>${user.wonSets - user.lostSets }</td>
						<td>${user.wonSmallPoints + user.lostSmallPoints }</td>
						<td>${user.wonSmallPoints}</td>
						<td>${user.lostSmallPoints }</td>
						<td>${user.wonSmallPoints - user.lostSmallPoints }</td>
					</tr>
				</c:if>
			</c:forEach>
		</c:forEach>
	</tbody>
</table>