package epam.cdp.spring.task3.dao.impl;

import static epam.cdp.spring.task3.dao.utils.ParseUtils.parseJsonElement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import epam.cdp.spring.task3.bean.City;
import epam.cdp.spring.task3.bean.EmployeesInfo;
import epam.cdp.spring.task3.bean.Location;
import epam.cdp.spring.task3.dao.IStatistcisDao;
import epam.cdp.spring.task3.dao.deserializer.CityDeserializer;
import epam.cdp.spring.task3.dao.deserializer.EmployessInfoDeserializer;
import epam.cdp.spring.task3.dao.deserializer.LocationDeserializer;
import epam.cdp.spring.task3.dao.deserializer.YearsDeserializer;

@Repository
public class StatistcisDaoJsonImpl implements IStatistcisDao {

	private Resource epamStatistics;

	private Resource cityLocations;

	public void setEpamStatistics(Resource epamStatistics) {
		this.epamStatistics = epamStatistics;
	}

	public void setCityLocations(Resource cityLocations) {
		this.cityLocations = cityLocations;
	}

	@Override
	public List<City> getCitiesForYear(Integer year) throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Type type = new TypeToken<List<City>>() {
		}.getType();
		Map<String, Location> locations = getLocations();
		gsonBuilder.registerTypeAdapter(type, new CityDeserializer(year,
				locations));
		Gson gson = gsonBuilder.create();
		JsonElement epamStatisticsElement = parseJsonElement(epamStatistics);
		List<City> cities = gson.fromJson(epamStatisticsElement, type);

		return cities;
	}

	private Map<String, Location> getLocations() throws FileNotFoundException,
			IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Type type = new TypeToken<Map<String, Location>>() {
		}.getType();
		gsonBuilder.registerTypeAdapter(type, new LocationDeserializer());
		Gson gson = gsonBuilder.create();
		JsonElement locationsElement = parseJsonElement(cityLocations);
		Map<String, Location> locations = gson.fromJson(locationsElement, type);

		return locations;
	}

	@Override
	public List<EmployeesInfo> getEmployeesInfo(String cityName)
			throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Type type = new TypeToken<List<EmployeesInfo>>() {
		}.getType();
		gsonBuilder.registerTypeAdapter(type, new EmployessInfoDeserializer(
				cityName));
		Gson gson = gsonBuilder.create();
		JsonElement epamStatisticsElement = parseJsonElement(epamStatistics);
		List<EmployeesInfo> employessInfo = gson.fromJson(
				epamStatisticsElement, type);

		return employessInfo;
	}

	@Override
	public List<Integer> getYears() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Type type = new TypeToken<List<Integer>>() {
		}.getType();
		gsonBuilder.registerTypeAdapter(type, new YearsDeserializer());
		Gson gson = gsonBuilder.create();
		JsonElement epamStatisticsElement = parseJsonElement(epamStatistics);
		List<Integer> years = gson.fromJson(epamStatisticsElement, type);
		return years;
	}
}