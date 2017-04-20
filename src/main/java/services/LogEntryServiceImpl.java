package services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.LogEntry;
import repos.LogEntryRepository;


@Service("logEntryService")
public class LogEntryServiceImpl implements LogEntryService {
	@Autowired
	private LogEntryRepository repo;

	public List<LogEntry> getAllVehicles() {
		return repo.findAll();
	}

	public Optional<LogEntry> findVehicle(Long id) {
		return repo.findOne(id);
	}

	public LogEntry save(LogEntry entry) {
		return repo.save(entry);
	}

	public void delete(LogEntry entry) {
		repo.delete(entry);

	}

	public void update(LogEntry entry) {
		// TODO Auto-generated method stub

	}

}
