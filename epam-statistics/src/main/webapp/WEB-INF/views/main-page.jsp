<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type='text/javascript' src='https://www.google.com/jsapi'></script>
<script type='text/javascript' src='resources/js/draw.js'></script>
<link rel="stylesheet" href="style/style.css" />


<script type='text/javascript'>
	google.load('visualization', '1', {
		'packages' : [ 'geochart' ]
	});
	google.setOnLoadCallback(hadleGeochart);
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Main page</title>
</head>
<body>
	<h2>This is Epam Statistics application</h2>
	<h2 id="selectedYear"></h2>
	<div id="geochart"></div>
</body>
</html>