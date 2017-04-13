package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import models.Line;
import models.Vehicle;
import models.VehicleType;
import services.LineService;
import services.VehicleService;

@Controller
public class HelloWorldController {
	
	@Autowired
	LineService lineService;
	@Autowired
	VehicleService vehicleService;
	
	@RequestMapping("/hello")  
    public ModelAndView helloWorld() {  
        String message = "HELLO SPRING MVC HOW R U";  
        return new ModelAndView("hello", "message", message);  
    }  
	
	@RequestMapping("/rest")
	@ResponseBody
	public String getAllPlayedMatches() {
	    Line l = new Line("139");
	    lineService.save(l);
	    Vehicle v1 = new Vehicle("123gh", VehicleType.BUS, "Mercedes");
	    l.addVehicle(v1);
	    v1.setLine(l);
	    vehicleService.save(v1);
	    Vehicle v2 = new Vehicle("234rt", VehicleType.BUS, "Mercedes");
	    l.addVehicle(v2);
	    v2.setLine(l);
	    vehicleService.save(v2);
	    List<Line> lines = lineService.getAllLines();
	    return "mergeee";
	}
}
