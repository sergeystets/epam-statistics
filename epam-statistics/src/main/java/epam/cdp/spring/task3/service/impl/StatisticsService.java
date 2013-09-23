package epam.cdp.spring.task3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import epam.cdp.spring.task3.bean.City;
import epam.cdp.spring.task3.bean.EmployeesInfo;
import epam.cdp.spring.task3.dao.IStatistcisDao;
import epam.cdp.spring.task3.exception.DaoException;
import epam.cdp.spring.task3.exception.ServiceException;
import epam.cdp.spring.task3.service.IStatistcisService;

@Component
public class StatisticsService implements IStatistcisService {

	private IStatistcisDao statistcisDao;

	@Autowired
	public void setStatisticsDao(IStatistcisDao statistcisDao) {
		this.statistcisDao = statistcisDao;
	}

	@Override
	public List<City> getCitiesForYear(Integer year) throws ServiceException {
		try {
			if (!statistcisDao.getYears().contains(year)) {
				throw new ServiceException("now inforamtion for year:" + year);
			}
			return statistcisDao.getCitiesForYear(year);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<EmployeesInfo> getEmployeesInfo(String cityName)
			throws ServiceException {
		try {
			List<EmployeesInfo> employessInfo = statistcisDao
					.getEmployeesInfo(cityName);
			if (employessInfo == null || employessInfo.isEmpty()) {
				throw new ServiceException("no employess info for city: "
						+ cityName);
			}
			return employessInfo;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Integer> getYears() throws ServiceException {
		try {
			return statistcisDao.getYears();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}
