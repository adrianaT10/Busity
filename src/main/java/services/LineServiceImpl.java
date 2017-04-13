package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Line;
import repos.LineRepository;

@Service ("lineService")
public class LineServiceImpl implements LineService {

	@Autowired
	LineRepository lineRepo;
	
	public List<Line> getAllLines() {
		// TODO Auto-generated method stub
		return lineRepo.findAll();
	}
	
	public Line save(Line line) {
		return lineRepo.save(line);
	}

}
