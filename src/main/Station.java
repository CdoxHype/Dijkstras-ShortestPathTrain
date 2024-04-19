/**
 * The Station class represents a train station with its name and distance from another station.
 */
package main;

public class Station {
	
	/**
	 * The name of the city where the station is located.
	 */
	String cityName;
	
	/**
	 * The distance of the station from another station.
	 */
	int distance = 0;
	
	/**
	 * Constructs a Station object with the given name and distance.
	 *
	 * @param name The name of the station.
	 * @param dist The distance of the station.
	 */
	public Station(String name, int dist) {
		this.cityName = name;
		this.distance = dist;
	}
	
	/**
	 * Retrieves the name of the station.
	 *
	 * @return The name of the station.
	 */
	public String getCityName() {
		return this.cityName;
	}
	
	/**
	 * Sets the name of the station.
	 *
	 * @param cityName The name of the station.
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	/**
	 * Retrieves the distance of the station.
	 *
	 * @return The distance of the station.
	 */
	public int getDistance() {
		return this.distance;
	}
	
	/**
	 * Sets the distance of the station.
	 *
	 * @param distance The distance of the station.
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param obj The reference object with which to compare.
	 * @return true if this object is the same as the obj argument; false otherwise.
	 */
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
	
	/**
	 * Returns a string representation of the station.
	 *
	 * @return A string representation of the station.
	 */
	@Override
	public String toString() {
		return "(" + this.getCityName() + ", " + this.getDistance() + ")";
	}

}
