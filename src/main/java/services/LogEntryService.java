package services;

import java.util.List;
import java.util.Optional;

import models.LogEntry;

public interface LogEntryService {
	public List<LogEntry> getAllVehicles();

	public Optional<LogEntry> findVehicle(Long id);

	public LogEntry save(LogEntry entry);

	public void delete(LogEntry entry);

	public void update(LogEntry entry);
}
