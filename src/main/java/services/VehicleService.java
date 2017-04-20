package services;

import java.util.List;
import java.util.Optional;

import models.LogEntry;
import models.Vehicle;

public interface VehicleService {
	public List<Vehicle> getAllVehicles();
	
	public Optional<Vehicle> findVehicle(String registration);
	
	public Vehicle save(Vehicle vehicle);
	
	public void delete(Vehicle vehicle);
	
	public void update(Vehicle updated);
	
	public LogEntry getLastPosition(Vehicle vehicle);
	
}
