<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>Szczegoly meczu</b> ${match.firstName} vs ${match.secondName} <br>
	<br>
</h1>

<h3>Podaj date w formacie MM/dd/YYYY Miesiac/Dzien/Rok lub wybierz z wysuwanego kalendarza.</h3>
<br>
<form:form class="form-horizontal" method="POST"
	action="/ping-pong/matches/${id}/dataAndPlace.html">
	<div class="form-group">
		<label class="control-label col-sm-2">Data spotkania:</label>
		<div class="col-sm-10">
			<input type="text"  class="datepicker"  value="${match.date}" name="matchDate" id="matchDate">
		</div>
		<script type="text/javascript">
			$('.datepicker').datepicker();
			
			
		</script>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2">Godzina (HH:MM):</label>
		<div class="col-sm-10">
			<input type="text" value="${match.hours}" name="matchHour" id="matchHour"  >
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2">Miejsce spotkania:</label>
		<div class="col-sm-10">
			<select name="matchPlace">
			<option selected="selected">---Wybierz---</option>
				<c:forEach items="${places}" var="place">
					<option value="${place.name}">${place.name}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<input class="btn btn-primary btn-lg" type="submit" value="Zatwierdź">
</form:form>
<br>
<a class="btn btn-info btn-lg"
	href='<spring:url value="/matches.html" />'>Wroc</a>



