<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<h4>Aktualna ilość kolejek: ${count}</h4>
<br>
<form:form method="POST"
	action="/ping-pong/management/remove/rounds.html"
	cssClass="form-horizontal removeRounds-form">
	<div class="form-group">
		<label class="control-label col-sm-2">Ile kolejek usunąć:</label>
		<div class="col-sm-2">
			<input type="text" name="countRound" class="form-control" />
		</div>
		<div class="col-sm-2">
			<input type="submit" value="Usuń" class="btn btn-danger " />
		</div>
	</div>
</form:form>

