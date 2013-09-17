package epam.cdp.spring.task3.dao.impl;

import java.lang.reflect.Type;
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

public class LocationDeserializer implements
		JsonDeserializer<Map<String, Location>> {

	@Override
	public Map<String, Location> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		JsonObject locationObject = json.getAsJsonObject();
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, List>>() {
		}.getType();
		gson.fromJson(json, null);
		
		
		return null;
	}

}
