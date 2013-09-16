package epam.cdp.spring.task3.dao;

import java.io.IOException;
import java.util.List;

import epam.cdp.spring.task3.bean.City;
import epam.cdp.spring.task3.bean.YearStatistics;

public interface StatistcisDao {

	public YearStatistics getYearStatistics() throws IOException;

	public List<City> getCitiesForYear(Integer year) throws IOException;

}
