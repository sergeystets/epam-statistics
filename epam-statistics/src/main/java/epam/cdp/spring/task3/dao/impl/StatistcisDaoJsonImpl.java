package epam.cdp.spring.task3.dao.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import epam.cdp.spring.task3.bean.City;
import epam.cdp.spring.task3.dao.StatistcisDao;
import epam.cdp.spring.task3.bean.Location;

@Repository
public class StatistcisDaoJsonImpl implements StatistcisDao {

	private Resource epamStatistics;

	private Resource cityLocations;

	public void setEpamStatistics(Resource epamStatistics) throws IOException {
		this.epamStatistics = epamStatistics;
	}

	public void setCityLocations(Resource cityLocations) throws IOException {
		this.cityLocations = cityLocations;
	}

	@Override
	public List<City> getCitiesForYear(Integer year) throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Type type = new TypeToken<List<City>>() {
		}.getType();
		Map<String,Location> locations = getLocations();
		gsonBuilder.registerTypeAdapter(type, new CityDeserializer(year, locations));
		Gson gson = gsonBuilder.create();
		JsonParser parser = new JsonParser();
		JsonReader reader = new JsonReader(new FileReader(
				epamStatistics.getFile()));
		JsonElement citiesElement = parser.parse(reader);
		List<City> cities = gson.fromJson(citiesElement, type);

		return cities;
	}

	private Map<String, Location> getLocations() throws FileNotFoundException,
			IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Type type = new TypeToken<Map<String, Location>>() {
		}.getType();
		gsonBuilder.registerTypeAdapter(type, new LocationDeserializer());
		Gson gson = gsonBuilder.create();
		JsonParser parser = new JsonParser();
		JsonReader reader = new JsonReader(new FileReader(
				cityLocations.getFile()));
		JsonElement locationsElement = parser.parse(reader);
		Map<String, Location> locations = gson.fromJson(locationsElement, type);

		return locations;
	}

}
