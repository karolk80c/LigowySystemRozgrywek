<%@ include file="../layout/taglib.jsp"%>


<tilesx:useAttribute name="current" />

<!DOCTYPE html>
<html>
<head>

<link rel="shortcut icon" href="assets/image/favicon.ico"
	type="image/x-icon">
<link rel="icon" href="assets/image/favicon.ico" type="image/x-icon">
<style>
.center {
	text-align: center;
}

#apply-form {
	float: right;
	margin: 1%;
}

.footer {
	text-align: center;
	margin-bottom: 2%;
}

.ts-row-fixed {
	position: fixed;
	/* top: 0; */
	visibility: hidden;
	z-index: 1020; /* 10 less than .navbar-fixed to prevent any overlap */
	border-bottom: 1px solid #dddddd;
	background-color: #ffffff;
}

.score-header {
	width: 8%;
	vertical-align: middle;
	text-align: center;
}

.date-header {
	vertical-align: middle;
	text-align: center;
}

.principal {
	background-color: #ff4d4d;
}
</style>

<!-- Jquery -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="assets/js/table-section.js"></script>
<script
	src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"></script>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- DataTables -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/t/bs/jq-2.2.0,dt-1.10.11/datatables.min.css" />
<script type="text/javascript"
	src="https://cdn.datatables.net/t/bs/dt-1.10.11/datatables.min.js"></script>


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
							<li class="${current == 'news' ? 'active' : ' ' }"><a
								href='<spring:url value="/news.html" />'><spring:message
										code="nav.news" /></a></li>
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<li class="${current == 'users' ? 'active' : ' ' }"><a
									href='<spring:url value="/users.html" />'><spring:message
											code="nav.users" /></a></li>
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
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<li class="${current == 'admin-matches' ? 'active' : ' ' }"><a
									href='<spring:url value="/admin-matches.html" />'><spring:message
											code="nav.admin-matches" /></a></li>
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
							<li class="${current == 'photo' ? 'active' : ' ' }"><a
								href='<spring:url value="/photo.html" />'><spring:message
										code="nav.photo" /></a></li>
							<security:authorize access="! isAuthenticated()">
								<li class="${current == 'login' ? 'active' : ' ' }"><a
									href='<spring:url value="/login.html" />'><spring:message
											code="nav.login" /></a></li>
								<li class="${current == 'user-register' ? 'active' : ' ' }"><a
									href='<spring:url value="/register.html" />'><spring:message
											code="nav.register" /></a></li>
							</security:authorize>
							<li class="${current == 'rules' ? 'active' : ' ' }"><a
								href='<spring:url value="/rules.html" />'><spring:message
										code="nav.rules" /></a></li>
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

