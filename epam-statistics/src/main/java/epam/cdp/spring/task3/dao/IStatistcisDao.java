package epam.cdp.spring.task3.dao;

import java.util.List;

import epam.cdp.spring.task3.bean.City;
import epam.cdp.spring.task3.bean.EmployeesInfo;
import epam.cdp.spring.task3.exception.DaoException;

public interface IStatistcisDao {

	List<City> getCitiesForYear(Integer year) throws DaoException;

	List<EmployeesInfo> getEmployeesInfo(String cityName) throws DaoException;

	List<Integer> getYears() throws DaoException;

}