package epam.cdp.spring.task3.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import epam.cdp.spring.task3.bean.City;
import epam.cdp.spring.task3.bean.EmployeesInfo;
import epam.cdp.spring.task3.service.IStatistcisService;

@Controller
public class MainController {

	@Autowired
	private IStatistcisService statistcisService;

	public void setStatisticsService(IStatistcisService statistcisService) {
		this.statistcisService = statistcisService;
	}

	public MainController() {
	}

	@RequestMapping(value = "/")
	public ModelAndView showMainPage() throws IOException {
		ModelAndView mw = new ModelAndView();
		mw.setViewName("main-page");
		List<Integer> years = statistcisService.getYears();
		mw.addObject("years", years);
		return mw;
	}

	@RequestMapping(value = "/getCitiesByYear")
	public @ResponseBody
	String getCitiesForYear(
			@RequestParam(value = "year", required = true) Integer year)
			throws IOException {

		List<City> cities = statistcisService.getCitiesForYear(year);
		JsonObject response = new JsonObject();
		Gson gson = new Gson();
		response.add("cities", gson.toJsonTree(cities));
		return response.toString();
	}

	@RequestMapping(value = "/getEmployeesInfo")
	public @ResponseBody
	String getEmployessInfo(
			@RequestParam(value = "cityName", required = true) String cityName)
			throws IOException {

		List<EmployeesInfo> employeesInfo = statistcisService.getEmployeesInfo(
				cityName);
		JsonObject response = new JsonObject();
		Gson gson = new Gson();
		response.add("employeesInfo", gson.toJsonTree(employeesInfo));
		return response.toString();
	}
}
