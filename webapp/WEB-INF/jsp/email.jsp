<%@ include file="../layout/taglib.jsp"%>
<c:if test="${param.success eq true }">
	<div class="alert alert-success">Poprawnie wyslano wiadomosc</div>
</c:if>

<h1 class="center">Wyslij wiadomosc</h1>
<br>

<form:form commandName="SingleEmail" cssClass="form-horizontal">
	<div class="form-group">
		<label for="recipient" class="col-sm-2 control-label">Do</label>
		<div class="col-sm-10">
			<input type="email" class="form-control" id="recipient"
				name="recipient" placeholder="nazwaOdbiorcy@gmail.com" value="">
		</div>
	</div>
	<div class="form-group">
		<label for="topic" class="col-sm-2 control-label">Temat</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="topic" name="topic"
				placeholder="Data spotkania Ping-Pong" value="">
		</div>
	</div>
	<div class="form-group">
		<label for="content" class="col-sm-2 control-label">Wiadomosc</label>
		<div class="col-sm-10">
			<textarea class="form-control" rows="4" name="content"></textarea>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-10 col-sm-offset-2">
			<input id="submit" name="submit" type="submit" value="Wyslij"
				class="btn btn-primary">
		</div>
	</div>
</form:form>