package epam.cdp.spring.task3.dao.impl;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import epam.cdp.spring.task3.bean.City;
import epam.cdp.spring.task3.bean.YearStatistics;
import epam.cdp.spring.task3.dao.StatistcisDao;

@Repository
public class StatistcisDaoJsonImpl implements StatistcisDao {

	private Resource resource;

	public void setResource(Resource resource) throws IOException {
		this.resource = resource;
	}

	@Override
	public YearStatistics getYearStatistics() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(YearStatistics.class,
				new YearStatisticsDeserializer());
		Gson gson = gsonBuilder.create();
		JsonParser parser = new JsonParser();
		JsonReader reader = new JsonReader(new FileReader(resource.getFile()));
		JsonElement jsonWithStatistics = parser.parse(reader);
		YearStatistics statistcis = gson.fromJson(jsonWithStatistics,
				YearStatistics.class);

		return statistcis;
	}

	@Override
	public List<City> getCitiesForYear(Integer year) throws IOException {
		YearStatistics ys = getYearStatistics();
		return ys.getData().get(year);
	}

}
