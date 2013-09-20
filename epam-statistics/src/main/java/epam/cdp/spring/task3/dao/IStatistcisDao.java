package epam.cdp.spring.task3.dao;

import java.io.IOException;
import java.util.List;

import epam.cdp.spring.task3.bean.City;
import epam.cdp.spring.task3.bean.EmployeesInfo;

public interface IStatistcisDao {

	List<City> getCitiesForYear(Integer year) throws IOException;
	
	List<EmployeesInfo> getEmployeesInfo(String cityName, Integer year) throws IOException;

}
