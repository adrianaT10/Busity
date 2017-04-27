package services;

import java.util.List;
import java.util.Optional;

import models.LogEntry;
import models.Vehicle;

public interface VehicleService {
	public List<Vehicle> getAllVehicles();
	
	public Optional<Vehicle> findVehicle(String registration);
	
	public Vehicle save(Vehicle vehicle);
	
	public LogEntry getLastPosition(Vehicle vehicle);
	
}
