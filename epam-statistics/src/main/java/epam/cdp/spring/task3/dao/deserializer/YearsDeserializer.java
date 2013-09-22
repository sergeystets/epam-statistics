package epam.cdp.spring.task3.dao.deserializer;

import static epam.cdp.spring.task3.dao.utils.ParseUtils.parseYear;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class YearsDeserializer implements JsonDeserializer<List<Integer>> {

	@Override
	public List<Integer> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		Type mapType = new TypeToken<LinkedHashMap<String, JsonObject>>() {
		}.getType();		
		JsonObject rootObject = json.getAsJsonObject().getAsJsonObject("EPAM");
		Gson gson = new Gson();
		Map<String, JsonObject> yearMap = gson.fromJson(rootObject, mapType);
		List<Integer> years = new ArrayList<>();
		for (Map.Entry<String, JsonObject> yearEntry : yearMap.entrySet()) {
			Integer year = parseYear(yearEntry.getKey());
			years.add(year);
		}
		return years;
	}
}