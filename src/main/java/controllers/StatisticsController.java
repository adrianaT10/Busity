package controllers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import models.Awt;
import models.Line;
import models.LogEntry;
import models.Station;
import services.AwtService;
import services.LineService;
import services.LogEntryService;

/**
 * @author Adriana Tufa
 * Controller which computes and retrieves statistics
 */
@Controller
@EnableWebMvc
public class StatisticsController {

	@Autowired
	private LogEntryService logEntryService;
	@Autowired
	private LineService lineService;
	@Autowired
	private AwtService awtService;

	
	static final Comparator<LogEntry> ORDER_LOGS_BY_TIME = new Comparator<LogEntry>() {
		public int compare(LogEntry l1, LogEntry l2) {
			return l1.getPassingTime().compareTo(l2.getPassingTime());
		}
	};
	
	
	/**
	 * Get average waiting time for every station, day, hour for given line
	 * @param lineNo
	 * @return list of entries
	 */
	@RequestMapping("/stat")
	@ResponseBody
	public List<StatResponse> getWaitingTimes(@RequestParam(value="line")String lineNo) {
		Line line = lineService.findLine(lineNo).orElse(null);
		if (line == null) {
			return null;
		}
		
		Set<Awt> waitingTimes = awtService.getAwtByLine(line);
		
		if (waitingTimes.isEmpty()) {
			return null;
		}
		
		List<StatResponse> stats = new ArrayList<StatResponse>();
		for (Awt awt : waitingTimes) {
			stats.add(new StatResponse(lineNo, awt.getDay().toString(),awt.getHour(), 
					awt.getStation().getStationName(), 
					awt.getWaitingTime()));
		}
		
		return stats;
	}

	
	/**
	 * Computes average waiting times;
	 * Should be called once per day
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/statistics")
	@ResponseBody
	public void computeStatistics() {
		Set<LogEntry> entries = logEntryService.getTodaysEntries();

		System.out.println("today's entries:");
		for (LogEntry l : entries) {
			System.out.println(l.getPassingTime() + " " + l.getLatitude());
		}

		Set<Line> allLines = lineService.getAllLineswithStations();

		// compute average waiting time by every line, station, day of week and hour
		for (Line line : allLines) {
			System.out.println("Search for line " + line.getLineNo());
			for (Station station : line.getStations()) {
				System.out.println("Station " + station.getStationName());
				List<LogEntry> stationEntries = new ArrayList<LogEntry>();

				// save log entries that are within 20 metres of station
				for (LogEntry entry : entries) {
					if (entry.getLineNo().equals(line.getLineNo())) {
						double dist = DistanceCalculator.distance(entry.getLatitude(), entry.getLongitude(),
								station.getLatitude(), station.getLongitude());
						if (dist < 0.02) {
							stationEntries.add(entry);
						}
					}
				}

				// sort entries by time
				Collections.sort(stationEntries, ORDER_LOGS_BY_TIME);
				for (LogEntry e : stationEntries) {
					System.out.println(e);
				}
				
				// choose only one entry while vehicle is in station
				List<LogEntry> finalEntries = new ArrayList<LogEntry>();
				int startIndex = 0, endIndex = 0;
				for (int i = 1; i < stationEntries.size(); i++) {
					Duration delta = Duration.between(stationEntries.get(i - 1).getPassingTime().toInstant(),
							stationEntries.get(i).getPassingTime().toInstant());
					System.out.println(delta.getSeconds());

					if (delta.getSeconds() > 10 || i == stationEntries.size() - 1) {
						endIndex = i;
						int midIndex = startIndex + (endIndex - startIndex) / 2;
						startIndex = i + 1;
						
						finalEntries.add(stationEntries.get(midIndex));
					}
				}

				System.out.println(finalEntries);
				
				// compute average waiting time for every hour
				int totalSeconds = 0;
				int noEntries = 0;
				for (int i = 1; i < finalEntries.size(); i++) {
					Duration delta = Duration.between(
							finalEntries.get(i - 1).getPassingTime().toInstant(),
							finalEntries.get(i).getPassingTime().toInstant());
					totalSeconds += delta.getSeconds();
					noEntries++;

					if (finalEntries.get(i - 1).getPassingTime().getHours() != 
							finalEntries.get(i).getPassingTime().getHours() ||
							i == finalEntries.size() - 1) {
						// the hour has changed; compute average on the for the precedent hour
						if (noEntries > 0) {
							Duration average = Duration.ofSeconds(totalSeconds / noEntries);
							int day = finalEntries.get(i - 1).getPassingTime().getDay();
							int hour = finalEntries.get(i - 1).getPassingTime().getHours();
							System.out.println("Average time " + average.toMinutes() + " for hour " + hour);
							
							Awt awt = awtService.findByParams(line, station, day, hour);
							if (awt == null) {
								awt = new Awt(station, line, hour, day, (int)average.getSeconds());
							} else {
								awt.setWaitingTime((awt.getWaitingTime() + (int)average.getSeconds()) / 2);
							}
							awtService.save(awt);
						}

						totalSeconds = 0;
						noEntries = 0;
					}
				}

			}
		}

	}
}


class StatResponse {
	private String lineNo;
	private String dayOfWeek;
	private int hour;
	private String stationName;
	private int waitingTime;
	
	public StatResponse() {}
	
	public StatResponse(String lineNo, String dayOfWeek, int hour, String stationName, int waitingTime) {
		this.lineNo = lineNo;
		this.dayOfWeek = dayOfWeek;
		this.hour = hour;
		this.stationName = stationName;
		this.waitingTime = waitingTime;
	}

	public String getLineNo() {
		return this.lineNo;
	}
	
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public int getHour() {
		return hour;
	}

	public String getStationName() {
		return stationName;
	}

	public int getWaitingTime() {
		return waitingTime;
	}	
}


/**
 *  Calculates distance between 2 coordinate points on Earth.
 */
class DistanceCalculator {
	public static double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;

		return (dist);
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}
