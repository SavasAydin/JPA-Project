package homeronfire;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for GeoLocations Each Locations entity has 4
 * attributes; id, cityName, latitude and longitude.
 */
@Entity
@Table(name = "GeoLocations")
public class Locations {

	@Id
	@GeneratedValue
	private long id;

	private String cityName;
	@Column(name = "latitude")
	private double lat;
	@Column(name = "longitude")
	private double lon;

	public Locations() {

	}

	public Locations(String cityName, double lat, double lon) {
		this.cityName = cityName;
		this.lat = lat;
		this.lon = lon;
	}

	public Locations(long id, String cityName, double lat, double lon) {
		this.id = id;
		this.cityName = cityName;
		this.lat = lat;
		this.lon = lon;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

}
