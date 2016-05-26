<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<style>
.image {
	padding: 2%;
	width: 50%;
}
</style>

<div style="float: left; width: 49%; margin-right: 1%;">
	<h1>
		<b>Fotografie</b>
	</h1>
	<h3>Medale</h3>
	<img style="width: 80%;" src="assets/image/Tenis.jpg" />
</div>

<div style="float: right; width: 49%;">
	<h1>
		<b>Linki</b>
		<security:authorize access="hasRole('ROLE_ADMIN')">
			<button class="btn btn-success" data-toggle="modal"
				data-target="#myModal">Dodaj</button>
		</security:authorize>
	</h1>
	<ul>
		<c:forEach items="${links}" var="link">
			<li><h3>
					<a href="${link.href}">${link.name }</a>
					<security:authorize access="hasRole('ROLE_ADMIN')">
						<a class="btn btn-sm btn-danger" style="margin-left: 5%;"
							href='<spring:url value="/photo/removeLink/${link.id}.html" />'>Usuń</a>
					</security:authorize>
				</h3></li>
		</c:forEach>
	</ul>
</div>
<p style="clear: both;"></p>


<form:form commandName="link" action="/ping-pong/photo/addLink.html"
	cssClass="form-horizontal">
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
					<h4 class="modal-title" id="myModalLabel">Dodawanie linku</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Nazwa: </label>
						<div class="col-sm-10">
							<form:input path="name" id="firstTextArea" autocomplete='off'
								cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="href" class="col-sm-2 control-label">Link: </label>
						<div class="col-sm-10">
							<form:input path="href" autocomplete='off'
								cssClass="form-control" />
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

<script>
	$('#myModal').on('shown.bs.modal', function() {
		$('#firstTextArea').focus();
	})
</script>