<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
	<h1>School-Pak</h1>
</div>

<div>
	<div>
		<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
		<a href="<c:url value="/login" />">Login</a> | <a href="<c:url value="/register" />">Register</a>
		</sec:authorize>
		<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
			<sec:authentication property="principal.username" /> | <a href="<c:url value="/j_spring_security_logout" />">Logout</a> 
		</sec:authorize>
	</div>
</div>