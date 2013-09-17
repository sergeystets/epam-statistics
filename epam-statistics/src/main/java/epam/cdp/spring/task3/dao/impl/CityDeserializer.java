package epam.cdp.spring.task3.dao.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

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
		// TODO Auto-generated method stub
		return null;
	}

}
