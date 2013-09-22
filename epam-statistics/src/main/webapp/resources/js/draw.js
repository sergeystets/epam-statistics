year = undefined;
cityList = undefined;

$(document).ready(
		function() {
			$("#years").on('change', function() {
				drawGeochart();
			});
			var selectedYear = $('#years option:selected').text();
			drawSelectedYear(selectedYear);
			geoChart = new google.visualization.GeoChart(document
					.getElementById("geochart"));
			google.visualization.events.addListener(geoChart, "ready",
					onGeoChartReadyHandler);
			google.visualization.events.addListener(geoChart, 'select',
					onCitySelectHandler);

		});

function drawSelectedYear(year) {
	$("#selectedYear").html("selected year: " + year);
}

function onGeoChartReadyHandler() {
	$("circle").attr("r", 5);
}

function onCitySelectHandler() {
	var selection = geoChart.getSelection();
	// remove selection because in accordance with documentation: only one
	// entity can be selected at any given moment
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
			drawEmployeesChart(data.employeesInfo, cityName);
		},
		error : function(xhr) {
			alert("something terrible happened" + xhr);
		}
	});
}

function drawGeochart() {
	var year = $('#years option:selected').text();
	$("#selectedYear").html("Selected year: " + year);

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
				},
				backgroundColor : '#E2E8F0',
				datalessRegionColor : '#D1CE85'
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
		data.addRows([ [ latitude, longitude, cityName ] ]);
	}

	return data;
}

function drawEmployeesChart(employeesInfo, cityName) {
	var dataForChart = new Array();
	var firstQuater = new Array();
	var secondQuater = new Array();
	var thirdQuater = new Array();
	var fourthQuater = new Array();
	dataForChart.push(firstQuater);
	dataForChart.push(secondQuater);
	dataForChart.push(thirdQuater);
	dataForChart.push(fourthQuater);
	var years = new Array();
	for ( var i = 0; i < employeesInfo.length; i++) {
		years.push(employeesInfo[i].year);
		var employees = employeesInfo[i].numOfEmployessPerQuoters;
		for ( var j = 0; j < employees.length; j++) {
			dataForChart[j].push(employees[j]);
		}
	}

	$("#chartPopup").remove();
	var popUpWidth = window.screen.width - window.screen.width * 0.22;
	var popUpHeight = window.screen.height - window.screen.height * 0.5;
	var chartWidth = window.screen.width - window.screen.width * 0.22;
	var chartHeight = window.screen.height - window.screen.height * 0.5;
	var chartPopup = $('<div id=\"chartPopup\"><div id=\"chart\" style=\"width:'
			+ chartWidth + 'px; height: ' + chartHeight + 'px;\"></div></div>');
	$("body").append(chartPopup);

	$.jqplot("chart", dataForChart, {
		title : cityName,
		seriesDefaults : {
			renderer : $.jqplot.BarRenderer,
			rendererOptions : {
				fillToZero : true
			},
			tickOptions : {
				angle : 30
			}
		},
		series : [ {
			label : "quarter#1"
		}, {
			label : "quarter#2"
		}, {
			label : "quarter#3"
		}, {
			label : "quarter#4"
		} ],
		legend : {
			show : true,
			placement : 'outside',
			xoffset : 50
		},
		axesDefaults : {
			tickRenderer : $.jqplot.CanvasAxisTickRenderer,
			tickOptions : {
				angle : 30
			}
		},
		axes : {
			xaxis : {
				renderer : $.jqplot.CategoryAxisRenderer,
				ticks : years
			},
			yaxis : {
				autoscale : true
			},
		}
	});

	chartPopup.css("visibility", "visible").dialog({
		resizable : false,
		modal : true,
		width : popUpWidth,
		height : popUpHeight,
		close : function() {
			$(this).dialog("destroy");
			$("#chartPopup").remove();
		}
	});
}