<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>Dane kontaktowe</b>
</h1>
<h3>
	<b>Adres email:</b> ${user.emailAdress}<br> <b>Numer
		kontaktowy:</b> ${user.contactNumber}<br>
</h3>


<br>
<h1><b>Statystyka</b></h1>
<br>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Punkty wg kryterium pilkarskiego</th>
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
