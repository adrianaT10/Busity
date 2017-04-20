package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import static javax.persistence.GenerationType.AUTO;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;

@Entity
@Table(name = "stations")
public class Station {
	
	@Id
	@GeneratedValue(strategy = AUTO)
	private Long id;
	
	private String stationName;
	
	private double latitude;
	
	private double longitude;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "line_station", joinColumns = @JoinColumn(name = "station_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "line_no", referencedColumnName = "line_no"))
	private Set<Line> lines = new HashSet<Line>();
	
	
	
	public Station() {}

	public Station(String stationName, double latitude, double longitude) {
		super();
		this.stationName = stationName;
		this.latitude = latitude;
		this.longitude = longitude;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Set<Line> getLines() {
		return lines;
	}

	public void setLines(Set<Line> lines) {
		this.lines = lines;
	}
	
	public boolean addLine(Line line) {
		return this.lines.add(line);
	}
}
