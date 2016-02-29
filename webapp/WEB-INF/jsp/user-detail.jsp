<%@ include file="../layout/taglib.jsp"%>


<h1>
	<b>${user.firstName} ${user.lastName}</b>
</h1>
<a class="btn btn-lg btn-info" href='<spring:url value="../email/${user.login }.html" />'>Wyslij
	wiadomosc</a>
<br>
<h3>
	<b>Adres email:</b> ${user.emailAdress}<br> <b>Numer
		kontaktowy:</b> ${user.contactNumber}<br>
</h3>

<h1>Statystyka</h1>
<br>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Punkty Glowne</th>
			<th>Bilans rozegranych meczy</th>
			<th>Bilans rozegranych setow</th>
			<th>Bilans zdobytych malych punktow</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>${user.footballPoints}</td>
			<td><b>${user.balanceMatches}</b></td>
			<td><b>${user.balanceSets}</b></td>
			<td><b>${user.balanceSmallPoints }</b></td>
		</tr>
	</tbody>
</table>