package epam.cdp.spring.task3.dao;

import java.io.IOException;
import java.util.List;

import epam.cdp.spring.task3.bean.City;

public interface StatistcisDao {

	public List<City> getCitiesForYear(Integer year) throws IOException;

}
