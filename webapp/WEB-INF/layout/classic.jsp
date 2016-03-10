<%@ include file="../layout/taglib.jsp"%>


<tilesx:useAttribute name="current" />

<!DOCTYPE html>
<html>
<head>
<style>
.center {
	text-align: center;
}

.footer {
	text-align: center;
	margin-bottom: 2%;
}

tr {
	text-align: center;
}

tr th {
	text-align: center;
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
						<a class="navbar-brand" href="<spring:url value="/" />  "><spring:message
								code="nav.title" /></a>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav">
							<li class="${current == 'index' ? 'active' : ' ' }"><a
								href='<spring:url value="/" />'><spring:message
										code="nav.homePage" /></a></li>
							<li class="${current == 'news' ? 'active' : ' ' }"><a
								href='<spring:url value="/news.html" />'><spring:message
										code="nav.news" /></a></li>
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<li class="${current == 'users' ? 'active' : ' ' }"><a
									href='<spring:url value="/users.html" />'><spring:message
											code="nav.users" /></a></li>
							</security:authorize>
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<li class="${current == 'draw' ? 'active' : ' ' }"><a
									href='<spring:url value="/draw.html" />'><spring:message
											code="nav.draw" /></a></li>
							</security:authorize>
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<li class="${current == 'management' ? 'active' : ' ' }"><a
									href='<spring:url value="/management.html" />'><spring:message
											code="nav.management" /></a></li>
							</security:authorize>
							<li class="${current == 'table' ? 'active' : ' ' }"><a
								href='<spring:url value="/table.html" />'><spring:message
										code="nav.table" /></a></li>
							<li class="${current == 'timetable' ? 'active' : ' ' }"><a
								href='<spring:url value="/timetable.html" />'><spring:message
										code="nav.timetable" /></a></li>
							<security:authorize access="hasRole('ROLE_USER')">
								<li class="${current == 'detail' ? 'active' : ' ' }"><a
									href='<spring:url value="/account.html" />'><spring:message
											code="nav.account" /></a></li>
							</security:authorize>
							<security:authorize access="hasRole('ROLE_USER')">
								<li class="${current == 'user-matches' ? 'active' : ' ' }"><a
									href='<spring:url value="/matches.html" />'><spring:message
											code="nav.matches" /></a></li>
							</security:authorize>
							<security:authorize access="isAuthenticated()">
								<li class="${current == 'email' ? 'active' : ' ' }"><a
									href='<spring:url value="/email.html" />'><spring:message
											code="nav.contact" /></a></li>
							</security:authorize>
							<security:authorize access="! isAuthenticated()">
								<li class="${current == 'login' ? 'active' : ' ' }"><a
									href='<spring:url value="/login.html" />'><spring:message
											code="nav.login" /></a></li>
								<li class="${current == 'user-register' ? 'active' : ' ' }"><a
									href='<spring:url value="/register.html" />'><spring:message
											code="nav.register" /></a></li>
							</security:authorize>
							<security:authorize access="isAuthenticated()">
								<li><a href='<spring:url value="/logout.html" />'><spring:message
											code="nav.logout" /></a></li>
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