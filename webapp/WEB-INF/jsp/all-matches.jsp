<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<style>
tr {
	text-align: center;
	vertical-align: middle;
}

tr th {
	text-align: center;
	vertical-align: middle;
}
</style>

<h1>
	<b>Wszystkie ustalone spotkania</b>
</h1>

<table class="table table-bordered table-hover table-striped"
	id="example" style="width: 60%;">
	<thead>
		<tr>
			<th>Zawodnik</th>
			<th>Zawodnik</th>
			<th class="date-header" style="width: 30%;">Data i miejsce</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${allMatches}" var="match">
			<tr>
				<c:choose>
					<c:when test="${match.firstName eq principalName }">
						<td style="color: red; font-size: 105%;"><b>${match.firstName}</b></td>
					</c:when>
					<c:otherwise>
						<td><a
							href='<spring:url value="/users/find/${match.firstName}.html" />'>${match.firstName}</a></td>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${match.secondName eq principalName }">
						<td style="color: red; font-size: 105%;"><b>${match.secondName}</b></td>
					</c:when>
					<c:otherwise>
						<td><a
							href='<spring:url value="/users/find/${match.secondName}.html" />'>${match.secondName}</a></td>
					</c:otherwise>
				</c:choose>
				<td><b><fmt:formatDate value="${match.matchDate}"
							pattern="dd.MM.yyyy HH:mm" /></b> ${match.matchPlace}</td>
			</tr>
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
											"paging" : false,
											"scrollY" : "290px",
											"scrollCollapse" : true,
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
											}
										});
					});
</script>

