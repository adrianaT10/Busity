package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLES")
//@IdClass(VehicleKey.class)
public class Vehicle {
//	@Id
//	@Column(name = "LINE_NO")
//	private String lineNo;
	
	@ManyToOne
    @JoinColumn(name = "LINES_LINE_NO")
	private Line line;
	
	
	@Id
	@Column(name = "REGISTRATION_NO")
	private String registrationNo;
	
	@Column(name = "VEHICLE_TYPE")
	@Enumerated(EnumType.STRING)
	private VehicleType vehicleType;
	
	private String brand;
	
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
	
	
}

class VehicleKey implements Serializable{
	private Line line;
	private String registrationNo;
}
