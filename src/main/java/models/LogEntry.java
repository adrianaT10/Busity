package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.GenerationType.AUTO;

import java.util.Date;

import javax.persistence.Column;

/**
 * @author Adriana Tufa
 * Class for keeping location info about vehicles
 */
@Entity
public class LogEntry {
	
	@Id
	@GeneratedValue(strategy = AUTO)
	long id;
	
	@ManyToOne
	@JoinColumn(name = "registration_no")
	private Vehicle vehicle;
	
	private String lineNo;
	
	private double latitude;
	
	private double longitude;
	
	@Column(name = "passingTime", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date passingTime;
	
	
	public LogEntry() {}

	public LogEntry(Vehicle vehicle, double latitude, double longitude, Date passingTime) {
		super();
		this.vehicle = vehicle;
		this.lineNo = vehicle.getLine().getLineNo(); //really necessary?
		this.latitude = latitude;
		this.longitude = longitude;
		this.passingTime = passingTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Date getPassingTime() {
		return passingTime;
	}

	public void setPassingTime(Date passingTime) {
		this.passingTime = passingTime;
	}
	
	@Override
	public String toString() {
		return vehicle.getRegistrationNo() + " at " + passingTime + " " + latitude + " " + longitude; 
	}
}
