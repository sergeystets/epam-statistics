package epam.cdp.spring.task3.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import epam.cdp.spring.task3.bean.City;
import epam.cdp.spring.task3.bean.YearStatistics;
import epam.cdp.spring.task3.dao.StatistcisDao;
import epam.cdp.spring.task3.service.StatistcisService;

@Component
public class StatisticsServiceImpl implements StatistcisService {

	@Autowired
	private StatistcisDao statistcisDao;

	public void setStatisticsDao(StatistcisDao statistcisDao) {
		this.statistcisDao = statistcisDao;
	}

	@Override
	public YearStatistics getStatisticsForCity() throws IOException {
		return statistcisDao.getYearStatistics();
	}

	@Override
	public List<City> getCitiesForYear(Integer year) throws IOException {
		return statistcisDao.getCitiesForYear(year);
	}

}
