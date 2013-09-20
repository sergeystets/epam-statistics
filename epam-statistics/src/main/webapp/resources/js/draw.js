year = 2012;
cityList = undefined;

$(document).ready(
		function() {

			$("#selectedYear").html("Selected year: " + year);
			geoChart = new google.visualization.GeoChart(document
					.getElementById("geochart"));
			google.visualization.events.addListener(geoChart, "ready",
					onReadyHandler);
			google.visualization.events.addListener(geoChart, 'select',
					onSelectHandler);

		});

function onReadyHandler() {
	$("circle").attr("r", 4);
}

function onSelectHandler() {
	var selection = geoChart.getSelection();
	geoChart.setSelection(undefined);
	var row = selection[0].row;
	var cityName = cityList[row].name;

	$.ajax({
		type : "GET",
		url : "getEmployeesInfo",
		dataType : "json",
		data : {
			year : year,
			cityName : cityName
		},
		success : function(data) {
			alert("employees chart");
			drawEmployeesChart(data);

		},
		error : function(xhr) {
			alert("something terrible happened" + xhr);
		}

	});

}

function drawEmployeesChart(data) {
	
	$("#chartPopup").remove();
}

function hadleGeochart() {
	var year = 2012;
	$.ajax({
		type : "GET",
		url : "getCitiesByYear",
		data : {
			year : year
		},
		dataType : "json",
		success : function(data) {
			cityList = data['cities'];
			var dataTable = transformCityListToDataTable(cityList, year);
			var options = {
				displayMode : 'markers',
				legend : 'none',
				colorAxis : {
					colors : [ 'green', 'blue' ]
				}
			};
			geoChart.draw(dataTable, options);

		},
		error : function(xhr) {
			alert("something terrible happened" + xhr);
		}

	});

}

function transformCityListToDataTable(cityList) {
	var data = new google.visualization.DataTable();
	data.addColumn('number', 'Lat');
	data.addColumn('number', 'Long');
	data.addColumn('string', 'tooltip');

	var numOfCities = cityList.length;
	for ( var i = 0; i < numOfCities; i++) {
		var cityName = cityList[i].name;
		var longitude = cityList[i].location['longitude'];
		var latitude = cityList[i].location['latitude'];
		console.log(cityName);
		console.log(longitude);
		console.log(latitude);
		data.addRows([ [ latitude, longitude, cityName ] ]);
	}
	console.log(data);
	return data;
}
