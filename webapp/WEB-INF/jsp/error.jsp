<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<div class="center">
	<h1>
		<spring:message code="error.title" />
	</h1>
	<br>
	<p>
		<spring:message code="error.content" />
	</p>

	<!--
    Failed URL: ${url}
    Exception:  ${exception.message}
        <c:forEach items="${exception.stackTrace}" var="ste">    ${ste} 
    </c:forEach>
    -->

</div>