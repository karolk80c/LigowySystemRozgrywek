<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<div class="alert alert-info">
	<spring:message code="table.info" />
</div>
<h1>
	<b><spring:message code="table.header" /> 2016</b> <br>
</h1>
<br>
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th><a
				href='<spring:url value="/table.html?properties=rankingPosition&order=${order}" />'><spring:message
						code="table.poz" /></a></th>
			<th><a
				href='<spring:url value="/table.html?properties=firstName&order=${order}" />'><spring:message
						code="table.user" /></a></th>
			<th><spring:message code="table.match" /></th>
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
				href='<spring:url value="/table.html?properties=wonSets&order=${order}" />'><spring:message
						code="table.sets" /> <spring:message code="table.win" /></a></th>
			<th><a
				href='<spring:url value="/table.html?properties=lostSets&order=${order}" />'><spring:message
						code="table.lost" /></a></th>
			<th><a
				href='<spring:url value="/table.html?properties=balanceSets&order=${order}" />'><spring:message
						code="table.balance" /></a></th>
			<th><a
				href='<spring:url value="/table.html?properties=wonSmallPoints&order=${order}" />'><spring:message
						code="table.points" /> <spring:message code="table.win" /></a></th>
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
										<td style="font-size: 105%;"><b>${user.fullName}</b></td>
									</c:when>
									<c:otherwise>
										<td><a
											href='<spring:url value="/users/${user.login}.html" />'>
												${user.fullName}</a></td>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>

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
					</tr>
				</c:if>
			</c:forEach>
		</c:forEach>
	</tbody>
</table>