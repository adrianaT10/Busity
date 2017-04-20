package services;

import java.util.List;
import java.util.Optional;

import models.Line;

public interface LineService {

	List<Line> getAllLines();
	
	Optional<Line> findLine(String lineNo);
	
	Line save(Line line);
}
