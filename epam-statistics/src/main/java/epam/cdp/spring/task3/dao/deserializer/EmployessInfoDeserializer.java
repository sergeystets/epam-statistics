package epam.cdp.spring.task3.dao.deserializer;

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
import static epam.cdp.spring.task3.dao.utils.ParseUtils.parseYear;

import epam.cdp.spring.task3.bean.EmployeesInfo;

public class EmployessInfoDeserializer implements
		JsonDeserializer<List<EmployeesInfo>> {

	private String cityName;

	public EmployessInfoDeserializer(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public List<EmployeesInfo> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		Type mapType = new TypeToken<LinkedHashMap<String, JsonObject>>() {
		}.getType();
		Type listType = new TypeToken<ArrayList<Double>>() {
		}.getType();

		JsonObject rootObject = json.getAsJsonObject().getAsJsonObject("EPAM");
		Gson gson = new Gson();
		Map<String, JsonObject> yearMap = gson.fromJson(rootObject, mapType);

		List<EmployeesInfo> employeesInfo = new ArrayList<EmployeesInfo>();
		for (Map.Entry<String, JsonObject> yearEntry : yearMap.entrySet()) {
			JsonObject cities = yearEntry.getValue();
			JsonElement numOfemployees = cities.get(cityName);
			if (numOfemployees == null) {
				continue;
			}
			List<Double> numOfEmployessPerQutaer = gson.fromJson(
					numOfemployees, listType);
			Integer currentYear = parseYear(yearEntry.getKey());

			EmployeesInfo info = new EmployeesInfo();
			info.setYear(currentYear);
			info.setNumOfEmployessPerQuoters(numOfEmployessPerQutaer);
			employeesInfo.add(info);
		}
		return employeesInfo;
	}
}
