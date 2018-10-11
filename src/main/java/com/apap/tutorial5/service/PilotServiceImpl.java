package com.apap.tutorial5.service;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.repository.PilotDb;
import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.repository.FlightDb;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PilotServiceImpl implements PilotService{
	@Autowired
	private PilotDb pilotDb;
	
	@Autowired
	private FlightDb flightDb;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}

	
	@Override
	public void addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
	}
	
	@Override
	public void deleteByLicenseNumber(String licenseNumber) {
		pilotDb.deleteByLicenseNumber(licenseNumber);
	}

	@Override
	public void updatePilot(String licenseNumber, PilotModel newPilot) {
		PilotModel updatePilot = pilotDb.findByLicenseNumber(licenseNumber);
		updatePilot.setFlyHour(newPilot.getFlyHour());
		updatePilot.setLicenseNumber(newPilot.getLicenseNumber());
		updatePilot.setName(newPilot.getName());
		pilotDb.save(updatePilot);
	}
}
