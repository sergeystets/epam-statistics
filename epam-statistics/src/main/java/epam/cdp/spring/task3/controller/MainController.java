package epam.cdp.spring.task3.controller;

import java.util.List;

import org.apache.log4j.Logger;
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
import epam.cdp.spring.task3.exception.ServiceException;
import epam.cdp.spring.task3.service.IStatistcisService;

@Controller
public class MainController {

	private static Logger logger = Logger.getLogger(MainController.class);

	private IStatistcisService statistcisService;

	@Autowired
	public void setStatisticsService(IStatistcisService statistcisService) {
		this.statistcisService = statistcisService;
	}

	@RequestMapping(value = "/")
	public ModelAndView showMainPage() {
		ModelAndView mw = new ModelAndView();
		mw.setViewName("main-page");
		List<Integer> years = null;
		try {
			years = statistcisService.getYears();
		} catch (ServiceException e) {
			logger.error(e);
		}
		mw.addObject("years", years);
		return mw;
	}

	@RequestMapping(value = "/getCitiesByYear")
	public @ResponseBody
	String getCitiesForYear(
			@RequestParam(value = "year", required = false) Integer year) {

		List<City> cities = null;
		try {
			cities = statistcisService.getCitiesForYear(year);
		} catch (ServiceException e) {
			logger.error(e);
		}
		JsonObject response = new JsonObject();
		Gson gson = new Gson();
		response.add("cities", gson.toJsonTree(cities));
		return response.toString();
	}

	@RequestMapping(value = "/getEmployeesInfo")
	public @ResponseBody
	String getEmployessInfo(
			@RequestParam(value = "cityName", required = false) String cityName) {

		List<EmployeesInfo> employeesInfo = null;
		try {
			employeesInfo = statistcisService.getEmployeesInfo(cityName);
		} catch (ServiceException e) {
			logger.error(e);
		}
		JsonObject response = new JsonObject();
		Gson gson = new Gson();
		response.add("employeesInfo", gson.toJsonTree(employeesInfo));
		return response.toString();
	}
}
