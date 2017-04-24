package services;

import java.util.List;
import java.util.Set;

import models.Awt;
import models.Line;
import models.Station;

public interface AwtService {

	List<Awt> getAllAwt();
	
	Awt save(Awt awt);
	
	Set<Awt> getAwtByLine(Line line);
	
	Awt findByParams(Line line, Station station, int day, int hour);
}
