package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BUS_LINES")
public class Line {
	
	@Id
	@Column(name = "LINE_NO")
	private String lineNo;

	
	@OneToMany(mappedBy = "line", cascade = CascadeType.ALL)
	private Set<Vehicle> vehicles = new HashSet<Vehicle>();
	
	public Line() {}
	
	public Line(String lineNo) {
		this.lineNo = lineNo;
	}
	
	public void addVehicle(Vehicle v) {
		vehicles.add(v);
	}
}
