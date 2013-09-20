package epam.cdp.spring.task3.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import epam.cdp.spring.task3.bean.City;
import epam.cdp.spring.task3.bean.EmployeesInfo;
import epam.cdp.spring.task3.dao.IStatistcisDao;
import epam.cdp.spring.task3.service.IStatistcisService;

@Component
public class StatisticsService implements IStatistcisService {

	@Autowired
	private IStatistcisDao statistcisDao;

	public void setStatisticsDao(IStatistcisDao statistcisDao) {
		this.statistcisDao = statistcisDao;
	}

	@Override
	public List<City> getCitiesForYear(Integer year) throws IOException {
		return statistcisDao.getCitiesForYear(year);
	}

	@Override
	public List<EmployeesInfo> getEmployeesInfo(String cityName, Integer year)
			throws IOException {
		return getEmployeesInfo(cityName, year);
	}
}
