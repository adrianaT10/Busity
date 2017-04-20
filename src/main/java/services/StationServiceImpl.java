package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Station;
import repos.StationRepository;

@Service
public class StationServiceImpl implements StationService {
	
	@Autowired
	private StationRepository stationRepo;

	public List<Station> getAllStations() {
		return stationRepo.findAll();
	}

	public Station save(Station station) {
		return stationRepo.save(station);
	}

}
