package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import models.Line;
import models.LogEntry;
import models.Vehicle;
import services.LineService;
import services.VehicleService;

/**
 * @author Adriana Tufa
 * Controller for handling user requests
 */
@Controller
@EnableWebMvc
public class UserController {
	
	@Autowired
	private LineService lineService;
	@Autowired
	private VehicleService vehicleService;
	
	
	/**
	 * Get the last position of every vehicle registered on lineNo
	 * @param lineNo
	 * @return list of positions
	 */
	@RequestMapping("/r/line")
	@ResponseBody
	public List<LocationResponse> getLineVehicles(@RequestParam(value="line")String lineNo) {
		Line line = lineService.findLine(lineNo).orElse(null);
		if (line == null) {
			System.out.println(Message.INEXISTENT_LINE);
			return null;
		}
		
		List<LocationResponse> lastEntries = new ArrayList<LocationResponse>();
		
		Set<Vehicle> vehicles = line.getVehicles();
		for (Vehicle vehicle : vehicles) {
			System.out.println(vehicle.getRegistrationNo());
			LogEntry last = vehicleService.getLastPosition(vehicle);
			if (last == null) {
				continue;
			}
			System.out.println("Last passing time " + last.getPassingTime());
			lastEntries.add(new LocationResponse(last.getLatitude(), last.getLongitude()));
		}
		
		return lastEntries;
	}
	
	
	/**
	 * Returns all existing lines
	 * @return list of lines
	 */
	@RequestMapping("/get-lines")
	@ResponseBody
	public List<LineResponse> getLines() {
		List<LineResponse> lines = new ArrayList<LineResponse>();
		
		List<Line> allLines = lineService.getAllLines();
		
		for (Line l : allLines) {
			lines.add(new LineResponse(l.getLineNo(), l.getType().name()));
		}
		
		return lines;
	}
}


class LocationResponse {
	private double lat;
	private double longi;
	
	public LocationResponse(double lat, double longi) {
		super();
		this.lat = lat;
		this.longi = longi;
	}
	public double getLat() {
		return lat;
	}
	public double getLongi() {
		return longi;
	}
}

class LineResponse {
	private String lineNo;
	private String type;
	
	public LineResponse(String lineNo, String type) {
		this.lineNo = lineNo;
		this.type = type;
	}

	public String getLineNo() {
		return lineNo;
	}

	public String getType() {
		return type;
	}
	
	
}


