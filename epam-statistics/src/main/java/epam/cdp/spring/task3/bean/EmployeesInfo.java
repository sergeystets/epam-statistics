package epam.cdp.spring.task3.bean;

import java.util.List;

public class EmployeesInfo {

	private Integer year;

	private List<Double> numOfEmployessPerQuoters;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<Double> getNumOfEmployessPerQuoters() {
		return numOfEmployessPerQuoters;
	}

	public void setNumOfEmployessPerQuoters(
			List<Double> numOfEmployessPerQuoters) {
		if (numOfEmployessPerQuoters.size() > 4) {
			throw new IllegalArgumentException(
					"There are only 4 quoters in a year");
		}
		this.numOfEmployessPerQuoters = numOfEmployessPerQuoters;
	}

	@Override
	public String toString() {
		return "EmployeesInfo [year=" + year + ", numOfEmployessPerQuoters="
				+ numOfEmployessPerQuoters + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeesInfo other = (EmployeesInfo) obj;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

}
