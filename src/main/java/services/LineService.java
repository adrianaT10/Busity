package services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import models.Line;

public interface LineService {

	List<Line> getAllLines();
	
	Optional<Line> findLine(String lineNo);
	
	Line save(Line line);
	
	Set<Line> getAllLineswithStations();
}
