<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<h1>
	<b>Terminarz spotkan</b>
</h1>

<br>

<script type="text/javascript">
	$(document).ready(function() {
		$('.nav-tabs a:first').tab('show');
	});
</script>


<!-- Nav tabs -->
<ul class="nav nav-tabs">
	<c:forEach items="${round}" var="round">
		<li><a href="#round${round.id}" data-toggle="tab">Kolejka
				${round.id }</a></li>
	</c:forEach>
</ul>

<!-- Tab panes -->
<div class="tab-content">
	<c:forEach items="${round}" var="round">
		<div class="tab-pane" id="round${round.id}">
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>Zawodnik</th>
						<th>Wynik</th>
						<th>Zawodnik</th>
						<th>Miejsce</th>
						<th>Data</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${round.matches}" var="match">
						<tr>						
							<td>${match.firstName}</td>
							<c:choose>
							<c:when test="${ match.completed == true}">
								<td><b>${match.firstPoints}:${match.secondPoints}</b>&nbsp;<c:forEach
									var="set" items="${match.sets}">
							(${set.firstPlayerScore}:${set.secondPlayerScore})
							</c:forEach></td>
							</c:when>
							<c:otherwise>
							<td></td>
							</c:otherwise>
							</c:choose>
							<td>${match.secondName}</td>
							<td>${match.matchPlace}</td>
							<td><fmt:formatDate value="${match.matchDate}" pattern="dd-MM-yyyy HH:mm" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:forEach>
</div>






