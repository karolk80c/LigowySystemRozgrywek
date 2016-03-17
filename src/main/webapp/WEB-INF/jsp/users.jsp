<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<div class="alert alert-info">Tylko aktywni użytkownicy są brani
	pod uwagę podczas losowania spotkań do sezonu.</div>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Nazwa_Uzytkownika</th>
			<th>Stan</th>
			<th>Dyskwalifikacja</th>
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
								${user.login }</a></td>
						<td><c:choose>
								<c:when test="${user.enabled eq false }">
									<a
										href=" <spring:url value="/users/update/${user.login}.html" />"
										class="btn btn-success"> Zaakceptuj </a>
								</c:when>

								<c:otherwise>
									<a
										href=" <spring:url value="/users/deactivate/${user.login}.html" />"
										class="btn btn-warning"> Dezaktywuj </a>
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
									class="btn btn-danger"> Zdyskwalifikuj </a></td>
							</c:otherwise>
						</c:choose>
						<td><a class="btn btn-info"
							href='<spring:url value="email/${user.login }.html" />'>Wyślij
								wiadomość</a></td>
					</tr>
				</c:if>
			</c:forEach>
		</c:forEach>
	</tbody>
</table>
