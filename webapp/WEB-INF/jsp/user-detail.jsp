<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>${user.firstName} ${user.lastName}</b>
</h1>
<br>
<h3>
	<b>Adres email:</b> ${user.emailAdress}<br> <b>Numer
		kontaktowy:</b> ${user.contactNumber}<br>
		Liczba punktów:</b> ${userStats.wonSmallPoints}<br>
</h3>