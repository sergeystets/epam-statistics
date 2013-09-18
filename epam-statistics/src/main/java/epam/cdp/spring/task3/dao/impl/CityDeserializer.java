package epam.cdp.spring.task3.dao.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import epam.cdp.spring.task3.bean.City;
import epam.cdp.spring.task3.bean.Location;

public class CityDeserializer implements JsonDeserializer<List<City>> {

	private Integer year;

	private Map<String, Location> locations;

	public CityDeserializer(Integer year, Map<String, Location> locations) {
		this.year = year;
		this.locations = locations;
	}

	@Override
	public List<City> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		JsonObject rootObject = json.getAsJsonObject().getAsJsonObject("EPAM");
		JsonObject citiesObject = rootObject.get("Year" + year)
				.getAsJsonObject();
		Type type = new TypeToken<HashMap<String, JsonObject>>() {
		}.getType();
		Gson gson = new Gson();
		Map<String, JsonObject> citiesMap = gson.fromJson(citiesObject, type);

		List<City> cities = new ArrayList<City>();
		for (Map.Entry<String, JsonObject> entry : citiesMap.entrySet()) {
			String cityName = entry.getKey();
			Location location = locations.get(cityName);
			City city = new City();
			city.setName(cityName);
			city.setLocation(location);
			cities.add(city);
		}

		return cities;
	}
}
