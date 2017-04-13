package services;

import java.util.List;
import java.util.Optional;

import models.Vehicle;

public interface VehicleService {
	public List<Vehicle> getAllVehicles();
	
	public Optional<Vehicle> getVehicle(String registration);
	
	public Vehicle save(Vehicle vehicle);
}
