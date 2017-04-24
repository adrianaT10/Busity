package repos;

import java.util.Date;
import java.util.Set;

import models.LogEntry;

public interface LogEntryRepository extends BaseRepository<LogEntry, Long> {
	
	Set<LogEntry> findByPassingTime(Date date);
	
	Set<LogEntry> findByPassingTimeBetween(Date start, Date end);

}
