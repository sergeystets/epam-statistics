package epam.cdp.spring.task3.bean;

import java.util.List;
import java.util.Map;

public class YearStatistics {

	private Map<Integer, List<City>> data;

	public Map<Integer, List<City>> getData() {
		return data;
	}

	public void setData(Map<Integer, List<City>> data) {
		this.data = data;
	}

}
