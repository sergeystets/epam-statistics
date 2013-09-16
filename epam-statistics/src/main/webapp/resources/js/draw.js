$(document).ready(function() {

	chart = new google.visualization.GeoChart(document.getElementById('map'));
	google.visualization.events.addListener(chart, 'ready', readyHandler);
});

function drawTest() {
	var data = google.visualization.arrayToDataTable([
			[ 'City', 'Population' ], [ 'Rome', 2761477 ],
			[ 'Milan', 1324110 ], [ 'Naples', 959574 ], [ 'Turin', 907563 ],
			[ 'Palermo', 655875 ], [ 'Genoa', 607906 ], [ 'Bologna', 380181 ],
			[ 'Florence', 371282 ], [ 'Fiumicino', 67370 ], [ 'Anzio', 52192 ],
			[ 'Ciampino', 38262 ] ]);

	var options = {
		region : 'IT',
		displayMode : 'markers',
		colorAxis : {
			colors : [ 'green', 'blue' ]
		}
	};

	var chart = new google.visualization.GeoChart(document
			.getElementById('map'));
	
	chart.draw(data, options);
};

function readyHandler() {
	$("circle").attr("r", 4);
}