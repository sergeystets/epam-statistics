package epam.cdp.spring.task3.bean;

import java.util.ArrayList;
import java.util.List;

public class City {

	private String name;

	private Location location;

	private List<Double> emploeesPerQuater;

	public City() {
		emploeesPerQuater = new ArrayList<>();
		location = new Location(0, 0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Double> getEmploeesPerQuater() {
		return emploeesPerQuater;
	}

	public void setEmploeesPerQuater(List<Double> emploeesPerQuater) {
		this.emploeesPerQuater = emploeesPerQuater;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		City other = (City) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
