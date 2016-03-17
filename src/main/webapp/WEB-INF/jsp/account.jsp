<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>Dane kontaktowe</b>
</h1>
<h3>
	<b>Adres email:</b> ${user.emailAdress}<br> <b>Numer
		kontaktowy:</b> ${user.contactNumber}<br>
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
