<%@ include file="../layout/taglib.jsp"%>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Nazwa_Uzytkownika</th>
			<th>Zaakceptuj</th>
			<th>Usun</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>
				<a href=" <spring:url value="/users/${user.login}.html" />"> ${user.login }</a>
				</td>
				<td><a
					href=" <spring:url value="/users/approve.html" />"
					class="btn btn-success"> Zaakceptuj </a></td>
				<td><a
					href=" <spring:url value="/users/remove/${user.login}.html" />"
					class="btn btn-danger">Usun</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
