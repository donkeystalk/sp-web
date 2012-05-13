<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<tiles:insertAttribute name="imports" />
		<title><tiles:getAsString name="pageTitle"/></title>
	</head>
	<body>
		<header>
			<tiles:insertAttribute name="header"/>
			<tiles:insertAttribute name="menu" />
		</header>
		<div class="container">
			<tiles:insertAttribute name="body" />
		</div>
		<footer>
			<tiles:insertAttribute name="footer" />
		</footer>
	</body>
</html>