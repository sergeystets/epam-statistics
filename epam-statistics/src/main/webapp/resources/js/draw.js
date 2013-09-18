$(document).ready(
		function() {

			geoChart = new google.visualization.GeoChart(document
					.getElementById("geochart"));
			google.visualization.events.addListener(geoChart, "ready",
					onReadyHandler);

		});

function onReadyHandler() {
	$("circle").attr("r", 4);
}

function hadleGeochart() {
	var year = 2012;
	$.ajax({
		type : "POST",
		url : "getCitiesByYear",
		data : {
			year : year
		},
		dataType : "json",
		success : function(data) {
			data = transformCityListToDataTable(data['cities'], year);
			var options = {
				displayMode : 'markers',
				legend: 'none',
				colorAxis : {
					colors : [ 'green', 'blue' ]
				}
			};
			geoChart.draw(data, options);
		},
		error : function(xhr) {
			alert("something terrible happened" + xhr);
		}

	});

}

function transformCityListToDataTable(cityList, year) {
	var data = new google.visualization.DataTable();
	data.addColumn('number', 'Lat');
	data.addColumn('number', 'Long');
	data.addColumn('string', 'tooltip');
	data.addColumn('number', 'Foundation date');

	var numOfCities = cityList.length;
	for ( var i = 0; i < numOfCities; i++) {
		var cityName = cityList[i].name;
		var longitude = cityList[i].location['longitude'];
		var latitude = cityList[i].location['latitude'];
		console.log(cityName);
		console.log(longitude);
		console.log(latitude);
		data.addRows([ [ latitude, longitude, cityName, year ] ]);
	}
	console.log(data);
	return data;
}
