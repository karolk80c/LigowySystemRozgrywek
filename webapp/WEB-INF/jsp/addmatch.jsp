<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>Szczegoly meczu</b> ${match.firstName} vs ${match.secondName} <br>
	<br>
</h1>


<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th class="center">Set</th>
			<th class="center">${match.firstName}</th>
			<th class="center">${match.secondName}</th>
			<th class="center">Edycja</th>
		</tr>
	</thead>
	<tbody>
		<%
			int i = 1;
		%>
		<c:forEach items="${match.sets}" var="set">
			<tr>
				<td class="center">
					<%
						out.print(i);
							i++;
					%>
				</td>
				<td class="center">${set.firstPlayerScore }</td>
				<td class="center">${set.secondPlayerScore }</td>
				<td class="center"><a class="btn btn-primary btn-lg"
					href='<spring:url value="/timetable/${match.id}.html" />'>Edytuj</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>



<br>
<br>
<!-- Trigger the modal with a button -->
<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
	data-target="#myModal">Dodaj Set</button>

<button type="button" class="btn btn-info btn-lg">Zapisz</button>

<form:form commandName="set" cssClass="form-horizontal">
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Aktualizacja meczu</h4>
				</div>

				<div class="modal-body">


					<div class="form-group">
						<label for="firstPlayerScore" class="col-sm-2 control-label">Zdobyte
							Punkty:</label>
						<div class="col-sm-10">
							<form:input path="firstPlayerScore" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="secondPlayerScore" class="col-sm-2 control-label">Stracone
							Punkty:</label>
						<div class="col-sm-10">
							<form:input path="secondPlayerScore" cssClass="form-control" />
						</div>
					</div>


				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Zamknij</button>
					<input type="submit" class="btn btn-primary" value="Zapisz" />
				</div>
			</div>
		</div>
	</div>
</form:form>
