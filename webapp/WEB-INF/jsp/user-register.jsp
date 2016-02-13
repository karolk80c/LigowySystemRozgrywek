<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<form:form commandName="user" cssClass="form-horizontal">

<c:if test="${param.success eq true }">
	<div class="alert alert-success" >Registration successfull</div>
</c:if>

	<div class="form-group">
		<label class="control-label col-sm-2" for="name">Name:</label>
		<div class="col-sm-10">
			<form:input path="name" cssClass="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="lastName">Last Name:</label>
		<div class="col-sm-10">
			<form:input path="lastName" cssClass="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="password">Password:</label>
		<div class="col-sm-10">
			<form:password path="password" cssClass="form-control" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-2">
			<input type="submit" value="Save" class="btn btn-lg btn-primary " />
		</div>
	</div>
	
</form:form>