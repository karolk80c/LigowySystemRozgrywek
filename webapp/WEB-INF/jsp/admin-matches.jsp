<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>Mecze </b>
</h1>
<br>

<c:if test="${param.success eq true }">
	<div class="alert alert-success">Zatwierdzono wynik meczu</div>
</c:if>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Zawodnik</th>
			<th>Wynik</th>
			<th>Zawodnik</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${matches}" var="match">
			<tr>
				<td style="vertical-align: middle">${match.firstName}</td>
				<td style="vertical-align: middle"><b>${match.firstPoints}:${match.secondPoints}</b></td>
				<td style="vertical-align: middle">${match.secondName}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

