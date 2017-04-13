package services;

import java.util.List;

import org.springframework.stereotype.Service;

import models.Line;

@Service
public interface LineService {

	List<Line> getAllLines();
	
	Line save(Line line);
}
