package epam.cdp.spring.task3.dao.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import epam.cdp.spring.task3.bean.City;
import epam.cdp.spring.task3.bean.YearStatistics;

public class YearStatisticsDeserializer implements
		JsonDeserializer<YearStatistics> {

	@Override
	public YearStatistics deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		JsonObject root = json.getAsJsonObject();
		JsonElement epam = root.get("EPAM");

		TypeToken<Map<String, Map<String, List<Double>>>> mapToken = new TypeToken<Map<String, Map<String, List<Double>>>>() {
		};
		Gson gson = new Gson();
		Map<String, Map<String, List<Double>>> data = gson.fromJson(epam,
				mapToken.getType());

		Map<Integer, List<City>> years = new HashMap<>();
		for (Entry<String, Map<String, List<Double>>> entry : data.entrySet()) {
			String stringWithYear = entry.getKey();
			Integer year = transformToYear(stringWithYear);

			Map<String, List<Double>> rawCities = entry.getValue();
			List<City> cities = new ArrayList<>();
			for (Map.Entry<String, List<Double>> rawCity : rawCities.entrySet()) {
				City city = new City();
				city.setName(rawCity.getKey());
				city.setEmploeesPerQuater(rawCity.getValue());
				cities.add(city);
			}

			years.put(year, cities);
		}
		YearStatistics ys = new YearStatistics();
		ys.setData(years);

		return ys;
	}

	private int transformToYear(String stringWithYear) {
		return Integer.valueOf(stringWithYear.substring(4,
				stringWithYear.length()));

	}

}
