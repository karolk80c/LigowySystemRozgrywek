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

<div class="alert alert-info">Tylko aktywni użytkownicy są brani
	pod uwagę podczas losowania spotkań do sezonu.</div>

<table
	class="table table-bordered table-hover table-striped ts-table-section" id="example">
	<thead>
		<tr class="ts-row-section">
			<th>Nazwa_Uzytkownika</th>
			<th>Stan</th>
			<th>Dyskwalifikacja</th>
			<th>Usunięcie</th>
			<th>Kontakt</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<c:forEach items="${user.roles}" var="role">
				<c:if test="${role.name != 'ROLE_ADMIN' }">
					<tr>
						<td><a
							href=" <spring:url value="/users/${user.login}.html" />">
								${user.fullName} (${user.login})</a></td>
						<td><c:choose>
								<c:when test="${user.enabled eq false }">
									<a
										href=" <spring:url value="/users/update/${user.login}.html" />"
										class="btn btn-success"> Zaakceptuj </a>
								</c:when>

								<c:otherwise>
									<a
										href=" <spring:url value="/users/deactivate/${user.login}.html" />"
										class="btn btn-warning triggerRemove"> Dezaktywuj </a>
								</c:otherwise>
							</c:choose></td>
						<c:choose>
							<c:when test="${role.name == 'ROLE_DISQUALIFIED' }">
								<td>Zdyskwalifikowany</td>
							</c:when>
							<c:when test="${role.name == 'ROLE_AWAIT' }">
								<td>-----</td>
							</c:when>
							<c:otherwise>
								<td><a
									href=" <spring:url value="/users/disqualifie/${user.login}.html" />"
									class="btn btn-danger triggerRemove"> Zdyskwalifikuj </a></td>
							</c:otherwise>
						</c:choose>
						<td><a class="btn btn-danger"
							href='<spring:url value="/users/remove/${user.login}.html" />'>Usuń</a></td>
						<td><a class="btn btn-info"
							href='<spring:url value="email/${user.login }.html" />'>Wyślij
								wiadomość</a></td>
					</tr>
				</c:if>
			</c:forEach>
		</c:forEach>
	</tbody>
</table>

<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Wiadomość</h4>
			</div>
			<div class="modal-body">Jesteś pewny że chcesz wykonać wybraną
				akcję?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button>
				<a href="" class="btn btn-danger removeBtn">Wykonaj</a>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$(".triggerRemove").click(function(e) {
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
		});
	});
</script>

<script>
	$(document)
			.ready(
					function() {
						$('#example')
								.dataTable(
										{
											"scrollY" : "300px",
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
