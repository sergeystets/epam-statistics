package epam.cdp.spring.task3.service;

import java.io.IOException;
import java.util.List;

import epam.cdp.spring.task3.bean.City;
import epam.cdp.spring.task3.bean.YearStatistics;

public interface StatistcisService {

	public YearStatistics getStatisticsForCity() throws IOException;

	public List<City> getCitiesForYear(Integer year) throws IOException;

}
