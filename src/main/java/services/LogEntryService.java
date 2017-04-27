package services;

import java.util.Set;

import models.LogEntry;

public interface LogEntryService {
	public LogEntry save(LogEntry entry);
	
	public Set<LogEntry> getTodaysEntries();
}
