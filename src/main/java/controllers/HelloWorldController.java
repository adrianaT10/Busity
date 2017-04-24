package controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import models.Line;
import services.LineService;
import services.StationService;
import services.VehicleService;

/**
 * Ignore, just for testinf
 */
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
	public String addSomeData() {
//	    Line l1 = new Line("1");
//	    Line l2 = new Line("25");
//	    
//	    lineService.save(l1);
//	    lineService.save(l2);
//
//	    Station s1 = new Station("Calea 13 Septembrie", 44.422902, 26.069308);  
//	    Station s2 = new Station("APRODEX", 44.425739, 26.065578);
//	    Station s3 = new Station("Piata Danny Huwe", 44.428849, 26.061827);
//        
//	    stationService.save(s1);
//	    stationService.save(s2);
//	    stationService.save(s3);
//	    
//	    s1.addLine(l1);
//	    s1.addLine(l2);
//	    stationService.save(s1);
//	    
//	    s2.addLine(l1);
//	    s2.addLine(l2);
//	    stationService.save(s2);
//	    
//	    s3.addLine(l1);
//	    s3.addLine(l2);
//	    stationService.save(s3);
//	    
//	    Vehicle v1 = new Vehicle("11abc", VehicleType.TRAM, "");
//	    Vehicle v2 = new Vehicle("22abc", VehicleType.TRAM, "");
//	    
//	    v1.setLine(l1);
//	    v2.setLine(l1);
////	    l1.addVehicle(v1);
////	    l1.addVehicle(v2);
//	    lineService.save(l1);
//	    lineService.save(l2);
	    
	    Set<Line> lines = lineService.getAllLineswithStations();
	    for (Line line : lines) {
	    	System.out.println(line.getLineNo() + " " + line.getStations().size());
	    }
	    
	    return "mergeee";
	}
	
}
