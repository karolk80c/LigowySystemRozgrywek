<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<div class="alert alert-info">
	<spring:message code="table.info" />
</div>
<h1>
	<b><spring:message code="table.header" /> 2016</b> <br>
</h1>
<table class="table table-bordered table-hover table-striped"
	id="example">
	<thead>
		<tr>
			<th style="vertical-align: middle" rowspan="2"><a
				href='<spring:url value="/table.html?properties=rankingPosition&order=${order}" />'>#</a></th>
			<th style="vertical-align: middle" rowspan="2"><a
				href='<spring:url value="/table.html?properties=firstName&order=${order}" />'><spring:message
						code="table.user" /></a></th>
			<th colspan="3"><spring:message code="table.match" /></th>
			<th colspan="3"><spring:message code="table.sets" /></th>
			<th colspan="3"><spring:message code="table.points" /></th>
		</tr>

		<tr>
			<th><a
				href='<spring:url value="/table.html?properties=wonMatches&order=${order}" />'><spring:message
						code="table.win" /></a></th>
			<th><a
				href='<spring:url value="/table.html?properties=lostMatches&order=${order}" />'><spring:message
						code="table.lost" /></a></th>
			<th><a
				href='<spring:url value="/table.html?properties=balanceMatches&order=${order}" />'><spring:message
						code="table.balance" /></a></th>
			<th><a
				href='<spring:url value="/table.html?properties=wonSets&order=${order}" />'>
					<spring:message code="table.win" />
			</a></th>
			<th><a
				href='<spring:url value="/table.html?properties=lostSets&order=${order}" />'><spring:message
						code="table.lost" /></a></th>
			<th><a
				href='<spring:url value="/table.html?properties=balanceSets&order=${order}" />'><spring:message
						code="table.balance" /></a></th>
			<th><a
				href='<spring:url value="/table.html?properties=wonSmallPoints&order=${order}" />'><spring:message
						code="table.win" /></a></th>
			<th><a
				href='<spring:url value="/table.html?properties=lostSmallPoints&order=${order}" />'><spring:message
						code="table.lost" /></a></th>
			<th><a
				href='<spring:url value="/table.html?properties=balanceSmallPoints&order=${order}" />'><spring:message
						code="table.balance" /></a></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<c:forEach items="${user.roles}" var="role">
				<c:if
					test="${role.name == 'ROLE_USER' or role.name == 'ROLE_DISQUALIFIED' }">
					<tr>
						<td>${user.rankingPosition }</td>
						<c:choose>
							<c:when test="${role.name == 'ROLE_DISQUALIFIED'}">
								<td>${user.firstName}&nbsp;${ user.lastName }<br>(Nieaktywny)
								</td>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${principalName eq user.fullName }">
										<td style="color: red; font-size: 105%;"><b>${user.fullName}</b></td>
									</c:when>
									<c:otherwise>
										<td><a
											href='<spring:url value="/users/${user.login}.html" />'>
												${user.fullName}</a></td>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
						<td class="success">${user.wonMatches}</td>
						<td class="danger">${user.lostMatches }</td>
						<td class="info"><b>${user.balanceMatches}</b></td>
						<td class="success">${user.wonSets }</td>
						<td class="danger">${user.lostSets }</td>
						<td class="info"><b>${user.balanceSets}</b></td>
						<td class="success">${user.wonSmallPoints}</td>
						<td class="danger">${user.lostSmallPoints }</td>
						<td class="info"><b>${user.balanceSmallPoints }</b></td>
					</tr>
				</c:if>
			</c:forEach>
		</c:forEach>
	</tbody>
</table>

<script>
	$(document)
			.ready(
					function() {
						$('#example')
								.dataTable(
										{
											"scrollY" : "290px",
											"scrollCollapse" : true,
											"paging" : false,
											"language" : {
												processing : "Przetwarzanie...",
												search : "Szukaj:",
												lengthMenu : "Pokaż _MENU_ pozycji",
												info : "",
												infoEmpty : "Pozycji 0 z 0 dostępnych",
												infoFiltered : "(filtrowanie spośród _MAX_ dostępnych pozycji)",
												infoPostFix : "",
												loadingRecords : "Wczytywanie...",
												zeroRecords : "Nie znaleziono pasujących pozycji",
												emptyTable : "Brak danych",
												paginate : {
													first : "Pierwsza",
													previous : "Poprzednia",
													next : "Następna",
													last : "Ostatnia"
												},
												aria : {
													sortAscending : ": aktywuj, by posortować kolumne rosnąco",
													sortDescending : ": aktywuj, by posortować kolumne malejąco"
												}
											}
										});
					});
</script>
