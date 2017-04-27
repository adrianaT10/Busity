package services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.LogEntry;
import models.Vehicle;
import repos.VehicleRepository;

@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepo;
	
	public List<Vehicle> getAllVehicles() {
		return vehicleRepo.findAll();
	}

	public Optional<Vehicle> findVehicle(String registration) {
		return vehicleRepo.findOne(registration);
	}

	public Vehicle save(Vehicle vehicle) {
		return vehicleRepo.save(vehicle);
	}

	public LogEntry getLastPosition(Vehicle vehicle) {
		Vehicle v = vehicleRepo.findByRegistrNoAndFetchLogs(vehicle.getRegistrationNo());
		if (v == null || v.getLogEntries().isEmpty()) 
			return null;
		
		int size = v.getLogEntries().size();
		LogEntry last = v.getLogEntries().get(size - 1);
		
		return last;
	}

}
