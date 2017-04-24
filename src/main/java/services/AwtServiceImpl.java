package services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Awt;
import models.Line;
import models.Station;
import repos.AwtRepository;

@Service("awtService")
public class AwtServiceImpl implements AwtService {
	
	@Autowired
	private AwtRepository repo;

	public List<Awt> getAllAwt() {
		return repo.findAll();
	}

	public Awt save(Awt awt) {
		return repo.save(awt);
	}
	
	public Set<Awt> getAwtByLine(Line line) {
		return repo.findByLine(line);
	}
	
	public Awt findByParams(Line line, Station station, int day, int hour) {
		Awt.DayOfWeek dow = day >=1 && day <= 6? Awt.DayOfWeek.M_F : Awt.DayOfWeek.WEEKEND;
		return repo.findByLineAndStationAndDayAndHour(line, station, dow, hour);
	}

}
