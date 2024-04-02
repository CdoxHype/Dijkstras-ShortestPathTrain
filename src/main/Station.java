package main;

public class Station {
	
	String cityName;
	int distance = 0;
	
	public Station(String name, int dist) {
		this.cityName = name;
		this.distance = dist;
	}
	
	public String getCityName() {
		return this.cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public int getDistance() {
		return this.distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		return this.getCityName().equals(other.getCityName()) && this.getDistance() == other.getDistance();
	}
	@Override
	public String toString() {
		return "(" + this.getCityName() + ", " + this.getDistance() + ")";
	}

}
