package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {
	
	@ManyToOne
    @JoinColumn(name = "line_no")
	private Line line;
	
	@Id
	@Column(name = "registration_no", unique = true)
	private String registrationNo;
	
	@Column(name = "vehicle_type")
	@Enumerated(EnumType.STRING)
	private VehicleType vehicleType;
	
	private String brand;
	
	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
	private Set<LogEntry> logEntries = new HashSet<LogEntry>();
	
	
	
	public Vehicle() {}
	
	public Vehicle(String registrationNo, VehicleType vehicleType, String brand) {
		this.registrationNo = registrationNo;
		this.vehicleType = vehicleType;
		this.brand = brand;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Set<LogEntry> getLogEntries() {
		return logEntries;
	}

	public void setLogEntries(Set<LogEntry> logEntries) {
		this.logEntries = logEntries;
	}
	
	public boolean addLogEntry(LogEntry logEntry) {
		return this.logEntries.add(logEntry);
	}
	
	
}
