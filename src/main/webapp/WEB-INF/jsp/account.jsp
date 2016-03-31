<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>


<c:if test="${param.change eq true }">
	<div class="alert alert-success">Poprawnie zmieniono hasło.
		Została wysłana wiadomość na Twój adres email</div>
</c:if>

<c:if test="${param.changeData eq true }">
	<div class="alert alert-success">Poprawnie zmieniono dane osobowe.</div>
</c:if>

<h1>
	<b>${user.firstName} ${user.lastName}</b>
</h1>
<h3>
	<b>Adres email:</b> ${user.emailAdress}<br> <b>Numer
		kontaktowy:</b> ${user.contactNumber}<br> <br> <a
		class="btn btn-danger"
		href='<spring:url value="/account/changePassword.html" />'>Zmień
		hasło</a> <a class="btn btn-info"
		href='<spring:url value="/account/changePersonalData.html" />'>Zmień
		dane osobowe</a>
</h3>
<br>
<h1>
	<b>Statystyka</b>
</h1>
<br>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Pozycja w rankingu</th>
			<th>Wygrane mecze</th>
			<th>Wygrane sety</th>
			<th>Zdobyte punkty</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><b>${user.rankingPosition}</b></td>
			<td><b>${user.wonMatches}</b></td>
			<td><b>${user.wonSets}</b></td>
			<td><b>${user.wonSmallPoints }</b></td>
		</tr>
	</tbody>
</table>
