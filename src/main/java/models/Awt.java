package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author AdrianaTufa
 * Average Waiting Time
 */
@Entity
public class Awt {

	public static enum DayOfWeek {
		M_F, WEEKEND
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	@JoinColumn(name = "station_name")
	private Station station;

	@ManyToOne
	@JoinColumn(name = "line_no")
	private Line line;

	// hour of day
	private int hour;

	// day of week
	@Enumerated(EnumType.STRING)
	private DayOfWeek day;

	@Column(name = "waiting_time")
	private int waitingTime;

	
	public Awt() {}

	public Awt(Station station, Line line, int hour, int day, int waitingTime) {
		super();
		this.station = station;
		this.line = line;
		this.hour = hour;
		this.setDay(day);
		this.waitingTime = waitingTime;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(int day) {
		if (day >= 1 && day <= 5) {
			this.day = DayOfWeek.M_F;
		} else {
			this.day = DayOfWeek.WEEKEND;
		}
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public long getId() {
		return id;
	}

}
