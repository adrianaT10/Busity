package controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import models.Line;
import models.Station;
import models.Vehicle;
import models.VehicleType;
import services.LineService;
import services.StationService;
import services.VehicleService;

@Controller
public class HelloWorldController {
	
	@Autowired
	LineService lineService;
	@Autowired
	VehicleService vehicleService;
	@Autowired
	StationService stationService;
	
	@RequestMapping("/hello")  
    public ModelAndView helloWorld() {  
        String message = "HELLO SPRING MVC HOW R U";  
        return new ModelAndView("hello", "message", message);  
    }  
	
	@RequestMapping("/rest")
	@ResponseBody
	public String getAllPlayedMatches() {
	    Line l = new Line("139");
	    Line l2 = new Line("136");
	    
	    Vehicle v1 = new Vehicle("123gh", VehicleType.BUS, "Mercedes");
	    l.addVehicle(v1);
	    v1.setLine(l);
//	    vehicleService.save(v1);
	    Vehicle v2 = new Vehicle("234rt", VehicleType.BUS, "Mercedes");
	    l.addVehicle(v2);
	    v2.setLine(l);
//	    vehicleService.save(v2);
	    
	    Station s1 = new Station("13Sept", 23.3444, 25.4445);
	    l2.addStation(s1);
	    s1.addLine(l2);
	    s1.addLine(l);
//	    stationService.save(s1);
	    
	    lineService.save(l);
	    lineService.save(l2);
	    
//	    vehicleService.delete(v1);
	    Set<Vehicle> remaining = l.getVehicles();
//	    remaining.clear();
	    System.out.println(remaining.size());
	    
	    return "mergeee";
	}
}
