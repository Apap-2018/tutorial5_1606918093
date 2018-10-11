package com.apap.tutorial5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.PilotService;
import com.apap.tutorial5.service.FlightService; 
@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@Autowired
	private FlightService flightService;

	
	@RequestMapping("/")
	private String home() {
		return "home";
	}

	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		model.addAttribute("title", "Add Pilot");
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	//ambil pilot berdasarkan licenseNumber
	
	@RequestMapping(value = "/pilot/delete/{licenseNumber}", method = RequestMethod.GET)
	private String deletePilot(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		pilotService.deleteByLicenseNumber(licenseNumber);
		model.addAttribute("title", "Delete Pilot");
		return "delete";
	}
	
	@RequestMapping(value = "/pilot/update/{licenseNumber}", method = RequestMethod.GET)
	private String updatePilot(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		model.addAttribute("pilot", new PilotModel());
		PilotModel oldPilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("oldPilot", oldPilot);
		model.addAttribute("title", "Pilot Update");
		return "update-pilot";
	}
	
	@RequestMapping(value = "/pilot/update", method = RequestMethod.POST)
	private String updatePilotSubmit(@ModelAttribute PilotModel pilot) {
		String licenseNumber = pilot.getLicenseNumber();
		pilotService.updatePilot(licenseNumber, pilot);
		return "update";
	}
	
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	private String viewAllPilotJourney(@RequestParam(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		List<FlightModel> addNew = flightService.getFlightDetailByPilot(archive.getLicenseNumber());
		if(archive != null) {
			model.addAttribute("dataflight", addNew);
			model.addAttribute("datapilot", archive);
			model.addAttribute("title", "Pilot Information System");
			return "view-pilot";
		}
		return "no-input";
	}
	
	
}
