package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.LogEntry;
import repos.LogEntryRepository;


@Service("logEntryService")
public class LogEntryServiceImpl implements LogEntryService {
	@Autowired
	private LogEntryRepository repo;

	public LogEntry save(LogEntry entry) {
		return repo.save(entry);
	}

	public Set<LogEntry> getTodaysEntries() {
		//TODO
		LocalDateTime currentTime = LocalDateTime.now();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		
		try {
			return repo.findByPassingTimeBetween(
							dateFormat.parse(currentTime.toLocalDate().toString()),
							dateFormat.parse(currentTime.plusDays(1).toLocalDate().toString()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}

