<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error page</title>

<link rel="stylesheet" href="<c:url  value="/style/style.css"/>" />


</head>
<body>
<h3>Error page</h3>
	<div id="error">
		<c:out value="${requestScope['javax.servlet.error.message']}" />
	</div>
</body>
</html>