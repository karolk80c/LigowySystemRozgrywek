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
		</tr>
	</thead>
	<tbody>
		<%
			int i = 1;
		%>
		<c:forEach items="${match.cokolwieks}" var="cokolwiek">
			<tr>
				<td class="center">
					<%
						out.print(i);
							i++;
					%>
				</td>
				<td class="center">${cokolwiek.firstPlayerScore }</td>
				<td class="center">${cokolwiek.secondPlayerScore }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>



<br>
<br>
<!-- Trigger the modal with a button -->

<button class="btn btn-info btn-lg" data-toggle="modal"
	data-target="#myModal">Dodaj Set</button>
<a class="btn btn-info btn-lg"
	href='<spring:url value="/matches.html" />'>Wroc</a>

<form:form commandName="cokolwiek" cssClass="form-horizontal">
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
						<label for="firstPlayerScore" class="col-sm-2 control-label">${match.firstName}:
						</label>
						<div class="col-sm-10">
							<form:input path="firstPlayerScore" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="secondPlayerScore" class="col-sm-2 control-label">${match.secondName}:
						</label>
						<div class="col-sm-10">
							<form:input path="secondPlayerScore" cssClass="form-control" />
						</div>
					</div>
				</div>
				<form:hidden path="id" />
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Zamknij</button>
					<input type="submit" formmethod="post" class="btn btn-primary"
						value="Zapisz" />
				</div>
			</div>
		</div>
	</div>
</form:form>
