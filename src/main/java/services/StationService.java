package services;

import java.util.List;

import models.Station;

public interface StationService {
	List<Station> getAllStations();
	
	Station save(Station station);
}
