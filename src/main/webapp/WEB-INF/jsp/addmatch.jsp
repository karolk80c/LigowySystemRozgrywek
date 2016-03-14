<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>Szczegoly meczu</b> ${match.firstName} vs ${match.secondName} <br>
	<br>
</h1>


<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Set</th>
			<th>${match.firstName}</th>
			<th>${match.secondName}</th>
			<c:if
				test="${match.firstApproved eq false && match.secondApproved eq false }">
				<th>Usuniecie</th>
			</c:if>
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
				<c:choose>
					<c:when test="${set.firstPlayerScore > set.secondPlayerScore }">
						<td><b>${set.firstPlayerScore}</b></td>
						<td>${set.secondPlayerScore }</td>
					</c:when>
					<c:otherwise>
						<td>${set.firstPlayerScore }</td>
						<td><b>${set.secondPlayerScore}</b></td>
					</c:otherwise>
				</c:choose>

				<c:if
					test="${match.firstApproved eq false && match.secondApproved eq false }">
					<td><a class="btn btn-danger btn"
						href='<spring:url value="/matches/${match.id}/deleteSet/${set.id}.html" />'>Usun</a></td>
				</c:if>
			</tr>
		</c:forEach>
	</tbody>
</table>



<br>
<br>
<!-- Trigger the modal with a button -->


<c:if
	test="${match.firstApproved eq false && match.secondApproved eq false }">
	<button class="btn btn-primary btn-lg" data-toggle="modal"
		data-target="#myModal">Dodaj Set</button>
	<td><a class="btn btn-lg btn-success"
		href='<spring:url value="/matches/${match.id}/approve.html" />'>Akceptuj</a></td>
</c:if>

<a class="btn btn-info btn-lg"
	href='<spring:url value="/matches.html" />'>Wróć</a>


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
					<h4 class="modal-title" id="myModalLabel">Dodawanie wyniku
						setu</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="firstPlayerScore" class="col-sm-4 control-label">${match.firstName}
						</label>
						<div class="col-sm-2">
							<form:input path="firstPlayerScore" id="firstTextArea"
								autocomplete='off' cssClass="form-control" />
						</div>
						<div class="col-sm-2">
							<form:input path="secondPlayerScore" autocomplete='off'
								cssClass="form-control" />
						</div>
						<label for="secondPlayerScore" class="control-label">${match.secondName}
						</label>
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

<script>
	$('#myModal').on('shown.bs.modal', function() {
		$('#firstTextArea').focus();
	})
</script>