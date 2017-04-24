package repos;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;

import models.Line;

public interface LineRepository extends BaseRepository<Line, String>{

	@Query("SELECT l FROM Line l JOIN FETCH l.stations")
    public Set<Line> findAllLinesWithStations();
}
