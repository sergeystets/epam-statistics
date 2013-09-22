<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>


<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- JQuery -->
<script type='text/javascript' src='resources/js/jquery/jquery-1.9.1.js'></script>
<script type='text/javascript' src='resources/js/jquery/jquery-ui-1.10.3.js'></script>

<!-- JSapi -->
<script type='text/javascript' src='https://www.google.com/jsapi'></script>

<!-- Custom stuff -->
<script type='text/javascript' src='resources/js/draw.js'></script>
<link rel="stylesheet" href="style/style.css" />

<!-- JQPlot basic -->
<link rel="stylesheet" type="text/css"
	href="style/jqplot/jquery.jqplot.css" />
<script type="text/javascript" src="js/jqplot/jquery.jqplot.js"></script>

<!-- JQPlot plugins -->
<script type="text/javascript"
	src="js/jqplot/plugins/jqplot.logAxisRenderer.min.js"></script>
<script type="text/javascript"
	src="js/jqplot/plugins/jqplot.pointLabels.min.js"></script>
<script type="text/javascript"
	src="js/jqplot/plugins/jqplot.dateAxisRenderer.min.js"></script>
<script type="text/javascript"
	src="js/jqplot/plugins/jqplot.canvasTextRenderer.min.js"></script>
<script type="text/javascript"
	src="js/jqplot/plugins/jqplot.canvasAxisTickRenderer.min.js"></script>
<script type="text/javascript"
	src="js/jqplot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
<script type="text/javascript"
	src="js/jqplot/plugins/jqplot.barRenderer.min.js"></script>

<script type='text/javascript'>
	google.load('visualization', '1', {
		'packages' : [ 'geochart' ]
	});
	google.setOnLoadCallback(drawGeochart);
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Epam Systems statistics</title>
</head>
<body>
<h3>Epam Systems statistics</h3>
	<div id="year-selection">
		<p>Select year</p> <select id="years">
			<c:forEach var="year" items="${ years}">
				<option value="${year}">${year}</option>
			</c:forEach>
		</select>
	</div>

	<div id="geochart"></div>
	<footer>Epam Systems 2013 </footer>
</body>
</html>