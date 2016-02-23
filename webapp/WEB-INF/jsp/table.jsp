<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>Tabela Sezonu 2016</b> <br>
</h1>
<br>
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th class="center">Poz</th>
			<th class="center"><a
				href='<spring:url value="/table.html?properties=firstName&order=${order}" />'>Zawodnik</a></th>
			<th class="center">Mecze</th>
			<th class="center"><a
				href='<spring:url value="/table.html?properties=wonMatches&order=${order}" />'>W</a></th>
			<th class="center"><a
				href='<spring:url value="/table.html?properties=lostMatches&order=${order}" />'>P</a></th>
			<th class="center"><a
				href='<spring:url value="/table.html?properties=balanceMatches&order=${order}" />'>B+-</a></th>
			<th class="center">Sety</th>
			<th class="center"><a
				href='<spring:url value="/table.html?properties=wonSets&order=${order}" />'>W</a></th>
			<th class="center"><a
				href='<spring:url value="/table.html?properties=lostSets&order=${order}" />'>P</a></th>
			<th class="center"><a
				href='<spring:url value="/table.html?properties=balanceSets&order=${order}" />'>B+-</a></th>
			<th class="center">Punkty</th>
			<th class="center"><a
				href='<spring:url value="/table.html?properties=wonSmallPoints&order=${order}" />'>W</a></th>
			<th class="center"><a
				href='<spring:url value="/table.html?properties=lostSmallPoints&order=${order}" />'>P</a></th>
			<th class="center"><a
				href='<spring:url value="/table.html?properties=balanceSmallPoints&order=${order}" />'>B+-</a></th>


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
						<td class="center">
							<%
								out.print(i);
											i++;
							%>
						</td>
						<td class="center">${user.firstName}${ user.lastName }</td>
						<td class="center">${user.wonMatches + user.lostMatches}</td>
						<td class="center">${user.wonMatches}</td>
						<td class="center">${user.lostMatches }</td>
						<td class="center"><b>${user.wonMatches - user.lostMatches}</b></td>
						<td class="center">${user.wonSets + user.lostSets }</td>
						<td class="center">${user.wonSets }</td>
						<td class="center">${user.lostSets }</td>
						<td class="center"><b>${user.wonSets - user.lostSets}</b></td>
						<td class="center"> XXX </td>
						<td class="center">${user.wonSmallPoints}</td>
						<td class="center">${user.lostSmallPoints }</td>
						<td class="center"><b>${user.balanceSmallPoints }</b></td>
					</tr>
				</c:if>
			</c:forEach>
		</c:forEach>
	</tbody>
</table>