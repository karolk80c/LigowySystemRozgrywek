<%@ include file="../layout/taglib.jsp"%>

<div class="alert alert-info">W przypadku jakichkolwiek problemow
	przejdz do zakladki kontakt i skontaktuj sie z pomoca techniczna
	serwisu.</div>

<h1>
	<b>Szczegoly meczu</b> ${match.firstName} vs ${match.secondName} <br>
	<br>
</h1>

Data spotkania (dd/mm/yyyy) : (wprowadzanie daty)<br>
Miejsce spotkania : (wprowadzanie miejsca)<br><br>


<a class="btn btn-info btn-lg"
	href='<spring:url value="/matches.html" />'>Wroc</a>
