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
						<th>Data spotkania</th>
						<th>Zawodnik</th>
						<th>Wynik</th>
						<th>Wynik</th>
						<th>Zawodnik</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${round.matches}" var="match">
						<tr>
							<td>${match.matchDate }</td>
							<td>${match.firstName}</td>
							<td>${match.firstPoints}</td>
							<td>${match.secondPoints }</td>
							<td>${match.secondName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:forEach>
</div>






