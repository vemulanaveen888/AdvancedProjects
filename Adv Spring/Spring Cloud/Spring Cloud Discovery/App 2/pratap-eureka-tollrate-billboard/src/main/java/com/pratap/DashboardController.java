package com.pratap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class DashboardController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/dashboard")
	public String GetTollRate(@RequestParam int stationId, Model m) {
		
		// Hard coded service url
		//RestTemplate rest = new RestTemplate();
		//TollRate tr = rest.getForObject("http://localhost:8085/tollrate/" + stationId, TollRate.class);
		
		TollRate tr = restTemplate.getForObject("http://toll-service/tollrate/"+stationId , TollRate.class);
		System.out.println("stationId: " + stationId);
		m.addAttribute("rate", tr.getCurrentRate());
		return "dashboardview";
	}
	
	
}
