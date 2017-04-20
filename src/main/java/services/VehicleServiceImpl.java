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

	public void delete(Vehicle vehicle) {
		vehicleRepo.delete(vehicle);
	}
	
	public void update(Vehicle updated) {
		vehicleRepo.updateLine(updated.getRegistrationNo(), updated.getLine());
		vehicleRepo.flush();
	}

	public LogEntry getLastPosition(Vehicle vehicle) {
//		System.out.println(vehicle.getLogEntries().size());
//		LogEntry last = vehicle.getLogEntries().get(0);
		Vehicle v = vehicleRepo.findByRegistrNoAndFetchLogs(vehicle.getRegistrationNo());
		System.out.println(v.getLogEntries().size());
		int size = v.getLogEntries().size();
		LogEntry last = v.getLogEntries().get(size - 1);
		System.out.println("Time " + last.getPassingTime());
		
		return last;
	}

}
