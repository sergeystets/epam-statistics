package epam.cdp.spring.task3.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import epam.cdp.spring.task3.bean.City;
import epam.cdp.spring.task3.bean.YearStatistics;
import epam.cdp.spring.task3.service.StatistcisService;

@Controller
public class MainController {

	private static final Logger logger = Logger.getLogger(MainController.class);

	@Autowired
	private StatistcisService statistcisService;

	public void setStatisticsService(StatistcisService statistcisService) {
		this.statistcisService = statistcisService;
	}

	public MainController() {
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody
	String showMainPage() throws IOException {

		YearStatistics statistcis = statistcisService.getStatisticsForCity();

		JsonObject response = new JsonObject();
		Gson gson = new Gson();
		response.add("yearStatistics", gson.toJsonTree(statistcis.getData()));
		return response.toString();
	}

	@RequestMapping(value = "/getCitiesByYear", method = RequestMethod.GET)
	public @ResponseBody
	String getCitiesForYear(@RequestParam("year") Integer year)
			throws IOException {

		List<City> cities = statistcisService.getCitiesForYear(year);

		JsonObject response = new JsonObject();
		Gson gson = new Gson();
		response.add("city", gson.toJsonTree(cities));
		return response.toString();
	}
}