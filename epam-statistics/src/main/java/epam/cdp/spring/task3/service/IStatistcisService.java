package epam.cdp.spring.task3.service;

import java.util.List;

import epam.cdp.spring.task3.bean.City;
import epam.cdp.spring.task3.bean.EmployeesInfo;
import epam.cdp.spring.task3.exception.ServiceException;

public interface IStatistcisService {

	List<City> getCitiesForYear(Integer year) throws ServiceException;

	List<EmployeesInfo> getEmployeesInfo(String cityName)
			throws ServiceException;
	
	List<Integer> getYears() throws ServiceException;

}
