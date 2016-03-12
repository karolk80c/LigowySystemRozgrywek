<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>


<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Nazwa_Uzytkownika</th>
			<th>Zaakceptuj</th>
			<th>Usuń</th>
			<th>Dyskwalifikuj</th>
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
								<c:when test="${role.name == 'ROLE_AWAIT' }">
									<a
										href=" <spring:url value="/users/update/${user.login}.html" />"
										class="btn btn-success"> Zaakceptuj </a>
								</c:when>
								<c:when test="${role.name == 'ROLE_DISQUALIFIED' }">
								Nieaktywny
							</c:when>
								<c:otherwise>
								Aktywny
							</c:otherwise>
							</c:choose></td>
						<td><a
							href=" <spring:url value="/users/remove/${user.login}.html" />"
							class="btn btn-danger">Usun</a></td>
						<c:choose>
							<c:when test="${role.name == 'ROLE_DISQUALIFIED' }">
							<td>Zdyskfalifikowany</td>
							</c:when>
							<c:otherwise>
								<td><a
									href=" <spring:url value="/users/disqualifie/${user.login}.html" />"
									class="btn btn-danger"> Zdyskwalifikuj </a></td>
							</c:otherwise>
						</c:choose>

						<td><a class="btn btn-info"
							href='<spring:url value="email/${user.login }.html" />'>Wyslij
								wiadomosc</a></td>
					</tr>
				</c:if>
			</c:forEach>
		</c:forEach>
	</tbody>
</table>
