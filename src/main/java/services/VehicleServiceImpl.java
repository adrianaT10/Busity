package services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Vehicle;
import repos.VehicleRepository;

@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepo;
	
	public List<Vehicle> getAllVehicles() {
		return vehicleRepo.findAll();
	}

	public Optional<Vehicle> getVehicle(String registration) {
		return vehicleRepo.findOne(registration);
	}

	public Vehicle save(Vehicle vehicle) {
		return vehicleRepo.save(vehicle);
	}

}
