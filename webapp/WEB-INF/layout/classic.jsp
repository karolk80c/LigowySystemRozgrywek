<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>


<tilesx:useAttribute name="current" />

<!DOCTYPE html>
<html>
<head>
<style>
.center {
	text-align: center;
}
.footer{
text-align:center;
margin-bottom:2%;
}
</style>


<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>

<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>

<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>


</head>
<body>
	<div class="imgdiv">
		<div class="container">

			<!-- Static navbar -->
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar"
							aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="<spring:url value="/" />  ">System
							Rozgrywek Ligowych</a>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav">
							<li class="${current == 'index' ? 'active' : ' ' }"><a
								href='<spring:url value="/" />'>Strona Główna</a></li>
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<li class="${current == 'users' ? 'active' : ' ' }"><a
									href='<spring:url value="/users.html" />'>Zawodnicy</a></li>
							</security:authorize>
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<li class="${current == 'draw' ? 'active' : ' ' }"><a
									href='<spring:url value="/draw.html" />'>Losowanie</a></li>
							</security:authorize>
							<li class="${current == 'table' ? 'active' : ' ' }"><a
								href='<spring:url value="/table.html" />'>Tabela</a></li>
							<li class="${current == 'timetable' ? 'active' : ' ' }"><a
								href='<spring:url value="/timetable.html" />'>Terminarz</a></li>
							<security:authorize
								access="hasRole('ROLE_USER')">
								<li class="${current == 'detail' ? 'active' : ' ' }"><a
									href='<spring:url value="/account.html" />'>Moje konto</a></li>
							</security:authorize>
							<security:authorize
								access="hasRole('ROLE_USER')">
								<li class="${current == 'user-matches' ? 'active' : ' ' }"><a
									href='<spring:url value="/matches.html" />'>Moje mecze</a></li>
							</security:authorize>
							<security:authorize access="! isAuthenticated()">
								<li class="${current == 'login' ? 'active' : ' ' }"><a
									href='<spring:url value="/login.html" />'>Zaloguj</a></li>
								<li class="${current == 'user-register' ? 'active' : ' ' }"><a
									href='<spring:url value="/register.html" />'>Zarejestruj</a></li>
							</security:authorize>
							<security:authorize access="isAuthenticated()">
								<li><a href='<spring:url value="/logout.html" />'>Wyloguj</a></li>
							</security:authorize>
						</ul>
					</div>
					<!--/.nav-collapse -->
				</div>
				<!--/.container-fluid -->
			</nav>

			<tiles:insertAttribute name="body"></tiles:insertAttribute>

			<br> <br>

			<div class="footer">
				<tiles:insertAttribute name="footer"></tiles:insertAttribute>
			</div>

		</div>
	</div>
</body>
</html>