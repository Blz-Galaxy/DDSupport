package org.dota.support.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dota.support.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/data")
public class DataController {
	private Logger logger=Logger.getLogger(DataController.class);
	
	@Autowired
	private DataService dataService;
	
	@RequestMapping(value="/heros", method = RequestMethod.GET)
    public String login(HttpServletRequest request){
		String value = request.getParameter("a");
		System.out.println(value);
		
		System.out.println(dataService.getHeros().toString());
        return "welcome";
    }

}
