package services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Line;
import repos.LineRepository;

@Service ("lineService")
public class LineServiceImpl implements LineService {

	@Autowired
	LineRepository lineRepo;
	
	public List<Line> getAllLines() {
		return lineRepo.findAll();
	}
	
	public Line save(Line line) {
		return lineRepo.save(line);
	}

	public Optional<Line> findLine(String lineNo) {
		return lineRepo.findOne(lineNo);
	}

	public Set<Line> getAllLineswithStations() {
		return lineRepo.findAllLinesWithStations();
	}
}
