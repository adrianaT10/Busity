package repos;

import java.util.Set;

import models.Awt;
import models.Line;
import models.Station;

public interface AwtRepository extends BaseRepository<Awt, Long>{
	
	public Set<Awt> findByLine(Line line);
	
	public Awt findByLineAndStationAndDayAndHour(Line line, Station station, Awt.DayOfWeek day, int hour);
}
