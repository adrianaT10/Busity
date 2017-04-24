package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bus_lines")
public class Line {
	
	@Id
	@Column(name = "line_no")
	private String lineNo;

	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "line", cascade = CascadeType.ALL)
	private Set<Vehicle> vehicles = new HashSet<Vehicle>();
	
	@ManyToMany(mappedBy = "lines")
	private Set<Station> stations = new HashSet<Station>();
	
	@OneToMany(mappedBy = "line", cascade = CascadeType.ALL)
	private Set<Awt> waitingTimes = new HashSet<Awt>();
	
	
	
	public Line() {}
	
	public Line(String lineNo) {
		this.lineNo = lineNo;
	}
	
	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Set<Station> getStations() {
		return stations;
	}

	public void setStations(Set<Station> stations) {
		this.stations = stations;
	}
	
	public boolean addStation(Station station) {
		return this.stations.add(station);
	}

	public void addVehicle(Vehicle v) {
		this.vehicles.add(v);
	}
	
	public void removeVehicle(Vehicle v) {
		this.vehicles.remove(v);
	}

	public Set<Awt> getWaitingTimes() {
		return waitingTimes;
	}

	public void setWaitingTimes(Set<Awt> waitingTimes) {
		this.waitingTimes = waitingTimes;
	}
	
	public boolean addAwt(Awt awt) {
		return this.waitingTimes.add(awt);
	}
}
