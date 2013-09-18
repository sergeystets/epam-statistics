package epam.cdp.spring.task3.dao.impl;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import epam.cdp.spring.task3.bean.Location;

public class LocationDeserializer implements
		JsonDeserializer<Map<String, Location>> {

	
	@Override
	public Map<String, Location> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		Type type = new TypeToken<HashMap<String, JsonObject>>() {
		}.getType();
		Gson gson = new Gson();
		Map<String, JsonObject> rawLocations =  gson.fromJson(json, type);
		Map<String,Location> locations = new HashMap<String,Location>();
		for (Map.Entry<String, JsonObject> entry:rawLocations.entrySet()){
			String cityName = entry.getKey();
			double longitude = entry.getValue().get("longitude").getAsDouble();
			double latitude = entry.getValue().get("latitude").getAsDouble();
			Location location = new Location(longitude, latitude);
			locations.put(cityName, location);
		}		
		
		return locations;
	}

}
