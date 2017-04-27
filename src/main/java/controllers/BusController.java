package controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import models.Line;
import models.LogEntry;
import models.Vehicle;
import services.LineService;
import services.LogEntryService;
import services.VehicleService;

/**
 * @author Adriana Tufa
 * Controller which handles request from the driver's app
 */
@Controller
@EnableWebMvc
public class BusController {
	
	@Autowired
	private LineService lineService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private LogEntryService logEntryService;

	
	/**
	 * Register a vehicle with a specific line
	 * @param lineNo
	 * @param registrationNo
	 * @return success/ error JSON reponse
	 */
	@RequestMapping("/register")
	public @ResponseBody ResponseMessage registerVehicleWithLine(@RequestParam(value="line")String lineNo, 
			@RequestParam(value="registr")String registrationNo) {
		Line line = lineService.findLine(lineNo).orElse(null);
		if (line == null) {
			System.err.println("ERROR! LINE DOES NOT EXIST");
			return new ResponseMessage(Message.ERROR, Message.INEXISTENT_LINE);
		}
		
		Vehicle vehicle = vehicleService.findVehicle(registrationNo).orElse(null);
		if (vehicle == null) {
			System.out.println("ERROR! VEHICLE NOT REGISTERED");
			return new ResponseMessage(Message.ERROR, Message.UNREGISTERED_VEHICLE);
		}
		
		// update vehicle's line
		vehicle.setLine(line);
		vehicleService.save(vehicle);
		
		return new ResponseMessage(Message.SUCCESS, "");
	}
	

	/**
	 * Delete any association between a vehicle and a line
	 * @param registrationNo
	 * @return ReponseMessage
	 */
	@RequestMapping("/unregister")
	@ResponseBody
	public ResponseMessage unregisterVehicleWithLine(
			@RequestParam(value="registr")String registrationNo) {

		Vehicle vehicle = vehicleService.findVehicle(registrationNo).orElse(null);
		if (vehicle == null) {
			System.out.println("ERROR! VEHICLE NOT REGISTERED");
			return new ResponseMessage(Message.ERROR, Message.UNREGISTERED_VEHICLE);
		}
		
		vehicle.setLine(null);
		vehicleService.save(vehicle);
		
		return new ResponseMessage(Message.SUCCESS, "");
	}
	
	
	/**
	 * Log locations received from vehicles.
	 * @param registrationNo
	 * @param latitude
	 * @param longitude
	 * @param date
	 */
	@RequestMapping("/update-location")
	@ResponseBody
	public void updateLocation(@RequestParam(value="registr")String registrationNo,
			@RequestParam(value="lat")double latitude, @RequestParam(value="long")double longitude,
			@RequestParam(value="date") @DateTimeFormat(pattern = "MMddyyyyHHmmss") Date date) {
		Vehicle vehicle = vehicleService.findVehicle(registrationNo).orElse(null);
		if (vehicle == null) {
			System.err.println(Message.UNREGISTERED_VEHICLE);
			return;
		}
		
		LogEntry entry = new LogEntry(vehicle, latitude, longitude, date);
		logEntryService.save(entry);
	}
}


class ResponseMessage {
	private String type;
	private String message;
	
	public ResponseMessage(String type, String message) {
		this.type = type;
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public String getType() {
		return this.type;
	}
}


class Message {
	public static final String INEXISTENT_LINE = "LINE DOES NOT EXIST";
	public static final String UNREGISTERED_VEHICLE = "VEHICLE NOT REGISTERED";
	public static final String SUCCESS = "Success";
	public static final String ERROR = "Error";
}
